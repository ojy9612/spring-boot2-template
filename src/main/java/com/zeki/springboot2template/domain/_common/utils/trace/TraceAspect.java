package com.zeki.springboot2template.domain._common.utils.trace;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zeki.springboot2template.domain._common.dto.CommonResDto;
import com.zeki.springboot2template.domain._common.utils.IpUtils;
import com.zeki.springboot2template.exception.APIException;
import com.zeki.springboot2template.exception.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@RequiredArgsConstructor
@Log4j2
public class TraceAspect {

    private final ObjectMapper om;

    @Around("execution(* com..domain..controller..*(..))")
//    @Transactional
    public Object trace(ProceedingJoinPoint joinPoint) {

        // Method 명 가져 오기
        String method = joinPoint.getSignature().getName();

        // IP 가져오기
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String requestIp = IpUtils.getClientIp(request);

        // RequestBody 가져 오기
        JsonNode requestBody = new ObjectNode(JsonNodeFactory.instance);
        for (Object arg : joinPoint.getArgs()) {
            requestBody = om.convertValue(arg, JsonNode.class);
        }


        Object result;
        String status;

        try {
            /* 메인 로직 실행 */
            status = "S";
            result = joinPoint.proceed();

        } catch (Throwable e) {
            status = "E";
            JsonNode errorResult = om.convertValue(e, JsonNode.class);

            e.printStackTrace(); // FIXME 임시용

            ResponseCode responseCode = ResponseCode.valueOf(errorResult.get("responseCode").asText());
            String errorMessage = errorResult.get("message").asText();

            result = CommonResDto.error(responseCode.getCode(), responseCode.getDefaultMessage(), errorMessage);
        }

        JsonNode responseBody = om.convertValue(result, JsonNode.class);


        // TODO: 로그 저장 로직 작성


        return result;
    }

}

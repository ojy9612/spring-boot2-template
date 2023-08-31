package com.zeki.springboot2template.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeki.springboot2template.domain._common.dto.CommonResDto;
import com.zeki.springboot2template.exception.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("rawtypes")
@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String exception = (String) request.getAttribute("exception");

        if (exception == null) {
            setResponse(response, ResponseCode.UNKNOWN_ERROR);
        }
        //토큰 정보가 없을 경우
        else if (exception.equals(ResponseCode.UNAUTHORIZED.getCode())) {
            setResponse(response, ResponseCode.UNAUTHORIZED);
        }
        //잘못된 타입의 토큰인 경우
        else if (exception.equals(ResponseCode.WRONG_TYPE_TOKEN.getCode())) {
            setResponse(response, ResponseCode.WRONG_TYPE_TOKEN);
        }
        //토큰 만료된 경우
        else if (exception.equals(ResponseCode.EXPIRED_TOKEN.getCode())) {
            setResponse(response, ResponseCode.EXPIRED_TOKEN);
        }
        //지원되지 않는 토큰인 경우
        else if (exception.equals(ResponseCode.UNSUPPORTED_TOKEN.getCode())) {
            setResponse(response, ResponseCode.UNSUPPORTED_TOKEN);
        } else {
            setResponse(response, ResponseCode.ACCESS_DENIED);
        }
    }

    //한글 출력을 위해 getWriter() 사용
    private void setResponse(HttpServletResponse response, ResponseCode responseCode) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // response 설정
        response.setCharacterEncoding("utf-8");
        response.setStatus(responseCode.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(CommonResDto.success()));
    }
}
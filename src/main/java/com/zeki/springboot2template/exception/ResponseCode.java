package com.zeki.springboot2template.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 처리결과 응답 코드
 */
@Getter
@AllArgsConstructor
public enum ResponseCode {

    /* 200 */
    OK(HttpStatus.OK, "200_0", "정상 처리 되었습니다."),

    /* 400 */
    BINDING_FAILED(HttpStatus.BAD_REQUEST, "400_0", "유효성 검사 실패"),
    INVALID_REQUEST_BODY(HttpStatus.BAD_REQUEST, "400_1", "Request body가 잘못되었습니다."),
    INVALID_XML(HttpStatus.BAD_REQUEST, "400_2", "XML 파싱에 실패하였습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "400_3", "잘못된 요청입니다."),

    /* 401 */
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "401_0", "비밀번호가 일치하지 않습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "401_1", "인증 정보가 없습니다."),

    /* 403 */
    UNMODIFIABLE_INFORMATION(HttpStatus.FORBIDDEN, "403_0", "변경할 수 없는 정보입니다."),
    UNKNOWN_ERROR(HttpStatus.FORBIDDEN, "403_1", "알 수 없는 에러입니다."),
    WRONG_TYPE_TOKEN(HttpStatus.FORBIDDEN, "403_2", "잘못된 타입의 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.FORBIDDEN, "403_3", "토큰이 만료되었습니다."),
    UNSUPPORTED_TOKEN(HttpStatus.FORBIDDEN, "403_4", "지원되지 않는 토큰입니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "403_5", "접근 권한이 없습니다."),
    WRONG_TOKEN(HttpStatus.FORBIDDEN, "403_6", "잘못된 토큰입니다."),
    FAILURE_VALIDATE_TOKEN(HttpStatus.FORBIDDEN, "403_7", "토큰 유효성 검사 실패"),


    /* 404 */
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "404_0", "데이터가 존재하지 않습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "404_1", "해당 유저가 존재하지 않습니다."),

    /* 409 Conflict - 클라이언트의 요청이 서버에서 충돌을 일으킨 경우 사용 */
    DUPLICATED_USER_ID(HttpStatus.CONFLICT, "409_0", "이미 존재하는 아이디입니다."),
    DOES_NOT_EXIST_INVENTORY(HttpStatus.CONFLICT, "409_1", "잔여 객실 없음"),
    DOES_NOT_MATCH_PRICE(HttpStatus.CONFLICT, "409_2", "요금 불일치"),

    /* 500 */
    GOOGLE_CHAT_CONNECT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500_0", "구글챗 통신 오류"),
    CANNOT_FIND_SELL_STATE(HttpStatus.INTERNAL_SERVER_ERROR, "500_1", "적절한 판매 상태를 찾을 수 없습니다."),
    INTERNAL_SERVER_LOGIC_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500_2", "서버측 로직 에러"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500_3", "서버 에러, 반드시 백엔드 호출할것!"),
    OPTIMISTIC_LOCKING_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, "500_4", "낙관적 락 발~동!"),
    CONNECT_WEBCLIENT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500_5", "WebClient 연결 에러"),
    RESERVE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "500_90", "예약 등록에 실패했습니다."),
    CANCEL_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "500_91", "예약 취소에 실패했습니다."),
    ALERT_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "500_92", "얼럿 등록에 실패했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String defaultMessage;

}

package com.zeki.springboot2template.config;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Map;

public class PropertiesConfig {

    public static final List<String> PARNAS_HOTEL_CODE_LIST = List.of("SEOHA", "SEOHB");
    public static final List<Integer> PARNAS_SPOT_ID_LIST = List.of(8411,8417);
    public static final Map<Integer,String> PARNAS_SPOT_ID_NAME_MAP = Map.of(8411,"그랜드 인터컨티넨탈 서울 파르나스",8417,"인터컨티넨탈 서울 코엑스");
    public static final Map<Integer,String> PARNAS_SPOT_ID_HOTEL_CODE_MAP = Map.of(8411,"SEOHA",8417,"SEOHB");
    public static final Map<Integer,String> PARNAS_SPOT_ID_IP_MAP = Map.of(8411,"10.152.18.82",8417,"10.152.18.81");
    public static final Map<String,String> PARNAS_HOTEL_CODE_IP_MAP = Map.of("SEOHA","10.152.18.82","SEOHB","10.152.18.81");

    private PropertiesConfig() {
        throw new IllegalStateException("Utility class");
    }

    @Value("${url.webhook.google-chat.parnas.log}")
    public static String googleChatLogUrl;

    @Value("${url.webhook.google-chat.parnas.report}")
    public static String googleChatReportUrl;

}

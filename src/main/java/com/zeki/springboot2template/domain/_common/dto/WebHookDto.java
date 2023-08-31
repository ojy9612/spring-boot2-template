package com.zeki.springboot2template.domain._common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WebHookDto {
    private String title;
    private String messageHeader;
    private String message;

    @Builder
    public WebHookDto(String title, String messageHeader, String message) {
        this.title = title;
        this.messageHeader = messageHeader;
        this.message = message;
    }
}

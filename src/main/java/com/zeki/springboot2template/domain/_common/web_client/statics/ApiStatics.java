package com.zeki.springboot2template.domain._common.web_client.statics;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "keys")
@Getter
@Setter
public class ApiStatics {
    private Webhook webhook;
    private WebhookLog webhookLog;
    private WebhookReport webhookReport;

    @Getter
    @Setter
    public static class Webhook {
        private String url;
        private String key;
        private String token;
    }

    @Getter
    @Setter
    public static class WebhookLog {
        private String url;
        private String key;
        private String token;
    }

    @Getter
    @Setter
    public static class WebhookReport {
        private String url;
        private String key;
        private String token;
    }
}

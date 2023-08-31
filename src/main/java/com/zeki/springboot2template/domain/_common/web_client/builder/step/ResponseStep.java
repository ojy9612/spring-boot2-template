package com.zeki.springboot2template.domain._common.web_client.builder.step;

import com.fasterxml.jackson.databind.JsonNode;

public interface ResponseStep {
    Object toObjectCall();
    JsonNode toJsonNodeCall();
    void toVoidCall();
}

package com.io.dropwizardhibernate.common;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Locale;

@Builder
@Data
public class RequestContext {
    private final String userId;
    private final String tokenId;
    private final String tenantId;
    private final List<String> entityIds;
    private final String entityId;
    private final String requestId;
    private final String trackId;
    private final Locale locale;
}

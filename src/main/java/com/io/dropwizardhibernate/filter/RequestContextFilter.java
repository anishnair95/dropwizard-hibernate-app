package com.io.dropwizardhibernate.filter;

import static com.io.dropwizardhibernate.utils.Helper.ifNullThen;
import static com.io.dropwizardhibernate.utils.Helper.isNullOrEmpty;
import static java.util.Collections.emptyList;

import com.io.dropwizardhibernate.common.ContextUtils;
import com.io.dropwizardhibernate.common.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

public class RequestContextFilter implements ContainerRequestFilter{

    public static final String TENANT_ID = "tenant-id";
    public static final String ENTITY_IDS = "entity-ids";
    public static final String ENTITY_ID = "entity-id";
    public static final String USER_ID = "user-id";
    public static final String TOKEN_ID = "token-id";
    public static final String REQUEST_ID = "request-id";
    public static final String TRACK_ID = "track-id";
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestContextFilter.class);

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        String tenantId = context.getHeaderString(TENANT_ID);
        String entityIds = context.getHeaderString(ENTITY_IDS);
        String entityId = context.getHeaderString(ENTITY_ID);
        String userId = context.getHeaderString(USER_ID);
        String tokenId = context.getHeaderString(TOKEN_ID);
        String requestId = context.getHeaderString(REQUEST_ID);
        String trackId = context.getHeaderString(TRACK_ID);
        List<String> entityIdsList = !isNullOrEmpty(entityIds) ? List.of(entityIds.split(",")) : emptyList();

        // TODO: add validations
        tenantId = ifNullThen(tenantId, "9");
        userId = ifNullThen(userId, "admin");

        RequestContext requestContext = RequestContext.builder()
                .userId(userId != null ? userId : tokenId.replaceAll("-",""))
                .tokenId(tokenId)
                .tenantId(tenantId)
                .entityIds(entityIdsList)
                .entityId(isNullOrEmpty(entityId) && !entityIdsList.isEmpty() ? entityIdsList.get(0) : null)
                .requestId(requestId)
                .trackId(trackId)
                .build();
        LOGGER.debug("RequestContext: {}", requestContext);
        ContextUtils.setRequestContext(requestContext);
    }
}

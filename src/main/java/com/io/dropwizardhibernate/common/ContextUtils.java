package com.io.dropwizardhibernate.common;

public class ContextUtils {

    private static ThreadLocal<RequestContext> requestContextThreadLocal = new ThreadLocal<>();

    public static RequestContext getRequestContext() {
        return requestContextThreadLocal.get();
    }

    public static void setRequestContext(RequestContext requestContext) {
        requestContextThreadLocal.set(requestContext);
    }

    public static void clearRequestContext() {
        requestContextThreadLocal.remove();
    }
}

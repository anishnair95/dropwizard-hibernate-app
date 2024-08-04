package com.io.dropwizardhibernate.error;


import com.io.dropwizardhibernate.common.ContextUtils;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Locale;

@Data
@Builder
@ToString
public class ErrorMessage {
    private final ErrorCode code;
    private final String message;
    private final Locale locale;
    private final String localizedMessage;
    private final String paramName;

    public static ErrorMessage buildWithParamName(ErrorCode code, String key, String paramName, String... tokens){
        MessageService messageService = MessageService.getInstance();
        Locale contextLocale = ContextUtils.getRequestContext().getLocale();
        String message = messageService.getString(Locale.getDefault(), key, tokens);
        String localizedMessage = messageService.getString(contextLocale, key, tokens);
        return new ErrorMessage(code, message, contextLocale, localizedMessage, paramName);
    }

    public static ErrorMessage buildWithMessage(ErrorCode code, String message, String paramName, String... tokens){
        MessageService messageService = MessageService.getInstance();
        Locale contextLocale = Locale.ENGLISH;
        String formattedMessage = messageService.getFormattedString(message, tokens);
        return new ErrorMessage(code, formattedMessage, contextLocale, formattedMessage, paramName);
    }

    public static ErrorMessage build(ErrorCode code, String key, String... tokens){
        return buildWithParamName(code, key , null, tokens);
    }
}

package com.io.dropwizardhibernate.error;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageService {

    private static final MessageService INSTANCE = new MessageService();

    private MessageService() {
    }

    public static MessageService getInstance() {
        return INSTANCE;
    }

    public String getString(Locale locale, String key, String... tokens) {
        locale = locale != null ? locale : Locale.getDefault();
        String message = ResourceBundle.getBundle("i18n.MessageBundle", locale).getString(key);
        if (tokens != null && tokens.length > 0) {
            return MessageFormat.format(message, tokens);
        }
        return message;
    }

    public String getFormattedString(String message, String... tokens) {
        if (tokens != null && tokens.length > 0) {
            return MessageFormat.format(message, tokens);
        }
        return message;
    }
}

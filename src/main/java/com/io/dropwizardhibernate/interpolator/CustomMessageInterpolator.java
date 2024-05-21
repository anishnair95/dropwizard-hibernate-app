package com.io.dropwizardhibernate.interpolator;

import java.util.Locale;

import javax.validation.MessageInterpolator;
public class CustomMessageInterpolator implements MessageInterpolator {
    private final MessageInterpolator defaultInterpolator;

    public CustomMessageInterpolator(MessageInterpolator interpolator) {
        this.defaultInterpolator = interpolator;
    }

    @Override
    public String interpolate(String messageTemplate, Context context) {
//        messageTemplate = messageTemplate.toUpperCase();
        return defaultInterpolator.interpolate(messageTemplate, context);
    }

    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale) {
//        messageTemplate = messageTemplate.toUpperCase();
        return defaultInterpolator.interpolate(messageTemplate, context, locale);
    }
}
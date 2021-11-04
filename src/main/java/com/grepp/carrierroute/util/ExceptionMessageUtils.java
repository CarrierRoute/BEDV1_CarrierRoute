package com.grepp.carrierroute.util;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import java.util.Locale;

@Component
public class ExceptionMessageUtils {

    private static MessageSourceAccessor messageSourceAccessor;

    public static void setExceptionMessageUtils(MessageSourceAccessor messageSourceAccessor) {
        ExceptionMessageUtils.messageSourceAccessor = messageSourceAccessor;
    }

    public static String getMessage(String code) {
        return getMessage(code, null);
    }

    public static String getMessage(String code, Object[] args) {
        return getMessage(code, args, null);
    }

    public static String getMessage(String code, Object[] args, Locale locale) {
        return messageSourceAccessor.getMessage(code, args, locale);
    }
}

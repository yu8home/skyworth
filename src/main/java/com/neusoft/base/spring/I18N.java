package com.neusoft.base.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class I18N {
    @Autowired
    private MessageSource ms;

    public String getMessage(String code, Object[] args, String defaultMessage) {
        return ms.getMessage(code, args, defaultMessage, LocaleContextHolder.getLocale());
    }

    public String getMessage(String code, Object[] args) {
        return this.getMessage(code, args, null);
    }

    public String getMessage(String code) {
        return this.getMessage(code, null, null);
    }

}
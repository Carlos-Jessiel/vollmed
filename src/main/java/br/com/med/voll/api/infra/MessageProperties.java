package br.com.med.voll.api.infra;

import org.springframework.context.support.MessageSourceResourceBundle;

public interface MessageProperties {

    String VALIDATION_MESSAGES = "ValidationMessages";

    default String getMensagem() {
        return MessageSourceResourceBundle.getBundle(VALIDATION_MESSAGES).getString(getMessageProperty());
    }

    default String getMensagem(Object... params) {
        var mensagem = MessageSourceResourceBundle.getBundle(VALIDATION_MESSAGES).getString(getMessageProperty());
        return String.format(mensagem, params);
    }

    String getMessageProperty();
}

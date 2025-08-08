package com.uader.poo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component; // O Service, dependiendo de cómo la uses

import java.util.Locale;

@Component // Marca la clase como un componente de Spring para inyección de dependencias
public class MessageUtils {

    private final MessageSource messageSource; // Usa 'final' y constructor para inyección

    @Autowired
    public MessageUtils(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Obtiene un mensaje del archivo de mensajes (messages.yml) por su código.
     * Utiliza el Locale actual del contexto de la solicitud.
     *
     * @param code El código (clave) del mensaje.
     * @return El mensaje localizado.
     */
    public String getMessage(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    /**
     * Obtiene un mensaje del archivo de mensajes con argumentos.
     *
     * @param code El código (clave) del mensaje.
     * @param args Los argumentos para el mensaje (ej. para "{0} {1}").
     * @return El mensaje localizado con los argumentos aplicados.
     */
    public String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    /**
     * Obtiene un mensaje con un Locale específico.
     *
     * @param code El código (clave) del mensaje.
     * @param locale El Locale a utilizar.
     * @return El mensaje localizado.
     */
    public String getMessage(String code, Locale locale) {
        return messageSource.getMessage(code, null, locale);
    }

    /**
     * Obtiene un mensaje con argumentos y un Locale específico.
     *
     * @param code El código (clave) del mensaje.
     * @param args Los argumentos para el mensaje.
     * @param locale El Locale a utilizar.
     * @return El mensaje localizado con los argumentos aplicados.
     */
    public String getMessage(String code, Object[] args, Locale locale) {
        return messageSource.getMessage(code, args, locale);
    }
}
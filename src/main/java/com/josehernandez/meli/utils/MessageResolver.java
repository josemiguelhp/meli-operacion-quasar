package com.josehernandez.meli.utils;

public interface MessageResolver {
    /**
     * Este metodo sirve tanto para resolver el Nivel 1 como cualquier caso general, devuelve el mensaje si lo puede decifrar
     * @param messages el mensaje tal cual es recibido en cada satelite en arreglo de strings
     * @return el mensaje tal cual lo genera el emisor del mensaje
     */
    String getMessage(final String[] ...messages) throws Exception;
}

package com.josehernandez.meli.utils;

public interface LocationTrilateration {
    /**
     * Caso de uso especifico para resolver el Nivel 1 del desafio, el metodo asume una posicion constante por
     * cada uno de los satelites.
     * @param distanceKenobi    distancia al emisor tal cual recibe el satellite kenobi
     * @param distanceSkywalker distancia al emisor tal cual recibe el satellite Skywalker
     * @param distanceSato      distancia al emisor tal cual recibe el satellite Sato
     * @return coordenadas 'x' e 'y' del emisor del mensaje
     */
    Point getLocation(final float distanceKenobi, final float distanceSkywalker, final float distanceSato) throws Exception;

    /**
     * Caso de uso general, recibe posiciones y distancias y con eso trilatera la posicion de la nave u objeto en cuestion
     * distances y positions deben tener la misma cantidad de elementos
     *
     * @param distances distancia al emisor tal cual se recibe en cada satelite
     * @param positions posicion de cada satelite
     * @return coordenadas 'x' e 'y' del emisor del mensaje
     */
    Point getLocation(final double[] distances, final double[][] positions) throws Exception;
}

package com.josehernandez.meli.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
    Para test unitarios trato de evitar levantarme el contexto de spring, con junit basta.
 */
public class LocationTrilaterationTests {

    private LocationTrilateration locationTrilateration;

    @BeforeEach
    void initUseCase() {
        locationTrilateration = new LocationTrilaterationImpl();
    }

    @Test
    void getNotNullLocation() {
        Point position = null;
        try {
            position = locationTrilateration.getLocation(100f,115.5f,142.7f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert position != null;
        Assertions.assertThat(position.getX()).isEqualTo(-487.28592f);
        Assertions.assertThat(position.getY()).isEqualTo(1557.0143f);
    }
}


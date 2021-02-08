package com.josehernandez.meli.repository;

import com.josehernandez.meli.models.Satellite;
import com.josehernandez.meli.utils.Point;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/*
    Deberia usar sprinboot data para interactuar con base de datos pero en las consignas no exigian implementar una base de datos
    asi que simulare como si estubiera interactuando con una...
 */
@Component
public class SatelliteRepository {

    private List<Satellite> satellites;

    public SatelliteRepository() {
        Satellite kenobi = new Satellite();
        kenobi.setName("kenobi");
        kenobi.setPosition(new Point(-500,-200));
        Satellite skywalker = new Satellite();
        skywalker.setName("skywalker");
        skywalker.setPosition(new Point(100,-100));
        Satellite sato = new Satellite();
        sato.setName("sato");
        sato.setPosition(new Point(500,-100));
        satellites = new ArrayList<>();
        satellites.add(kenobi);
        satellites.add(skywalker);
        satellites.add(sato);
    }

    public double[] findSatellitePosition(String name) {
        for (Satellite satellite:satellites) {
            if(satellite.getName().equals(name))
                return new double[]{satellite.getPosition().getX(), satellite.getPosition().getY()};
        }
        return null;
    }
}

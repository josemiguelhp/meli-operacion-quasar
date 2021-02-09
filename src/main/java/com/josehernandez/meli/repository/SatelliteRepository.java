package com.josehernandez.meli.repository;

import com.josehernandez.meli.models.Satellite;
import com.josehernandez.meli.utils.Point;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
    Deberia usar sprinboot data para interactuar con base de datos pero en las consignas no exigian implementar una base de datos
    asi que simulare como si estubiera interactuando con una...
 */
@Component
public class SatelliteRepository {

    private final List<Satellite> satellites;

    public SatelliteRepository() {
        Satellite kenobi = new Satellite();
        kenobi.setId(0);
        kenobi.setName("kenobi");
        kenobi.setPosition(new Point(-500,-200));
        Satellite skywalker = new Satellite();
        skywalker.setId(1);
        skywalker.setName("skywalker");
        skywalker.setPosition(new Point(100,-100));
        Satellite sato = new Satellite();
        sato.setId(2);
        sato.setName("sato");
        sato.setPosition(new Point(500,-100));
        satellites = new ArrayList<>();
        satellites.add(kenobi);
        satellites.add(skywalker);
        satellites.add(sato);
        resetSatellitesObservations();
    }

    public double[] findPositionByName(String name) {
        for (Satellite satellite:satellites) {
            if(satellite.getName().equals(name))
                return new double[]{satellite.getPosition().getX(), satellite.getPosition().getY()};
        }
        return null;
    }

    public List<Satellite> findSatellites() {
        return satellites;
    }

    public void resetSatellitesObservations() {
        satellites.forEach((Satellite satellite)->{
            satellite.setDistance(-1);
            satellite.setMessage(null);
        });
    }

    public Boolean updateDistanceAndMessageByName(String satelliteName, float distance, String[] message) {
        Optional<Satellite> satelliteFind = satellites.stream().filter((Satellite satellite)->satellite.getName().equals(satelliteName)).findFirst();
        if(satelliteFind.isPresent()){
            satelliteFind.get().setDistance(distance);
            satelliteFind.get().setMessage(message);
        }
        return satelliteFind.isPresent();
    }
}

package com.josehernandez.meli.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SatellitesObservationsDto {

    private double[] distances;
    private double[][] positions;
    private String[][] messages;

    public SatellitesObservationsDto(int numbersOfSatellites) {
        this.distances = new double[numbersOfSatellites];
        this.positions = new double[numbersOfSatellites][];
        this.messages = new String[numbersOfSatellites][];
    }
}

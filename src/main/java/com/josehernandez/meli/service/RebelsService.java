package com.josehernandez.meli.service;

import com.josehernandez.meli.dto.SatelliteSplitRequestDto;
import com.josehernandez.meli.dto.TopSecretRequestDto;
import com.josehernandez.meli.dto.TopSecretResponseDto;
import com.josehernandez.meli.models.Satellite;

import java.util.List;

public interface RebelsService {
    /**
     * Register a new ship coordinates-message information
     *
     * @param topSecretRequestDto
     * @return ship coordinates-message information
     */
    TopSecretResponseDto obtainTopSecretShip(final TopSecretRequestDto topSecretRequestDto);

    /**
     * Register a new ship coordinates-message information
     *
     * @param satelliteName
     * @param satelliteSplitRequestDto
     */
    void setTopSecretSatelliteObservations(final String satelliteName, final SatelliteSplitRequestDto satelliteSplitRequestDto);

    TopSecretResponseDto obtainLastTopSecretShip();

    List<Satellite> obtainSatellites();
}

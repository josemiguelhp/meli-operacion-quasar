package com.josehernandez.meli.service;

import com.josehernandez.meli.dto.TopSecretShipRequestDto;
import com.josehernandez.meli.dto.TopSecretShipResponseDto;

public interface RebelsService {
    /**
     * Register a new navy coordinates-message information
     *
     * @param topSecretShipRequestDto
     * @return
     */
    TopSecretShipResponseDto getNavyInfo(TopSecretShipRequestDto topSecretShipRequestDto);
}

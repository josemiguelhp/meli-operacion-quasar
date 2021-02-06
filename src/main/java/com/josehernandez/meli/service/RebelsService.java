package com.josehernandez.meli.service;

import com.josehernandez.meli.dto.TopSecretNavyRequestDto;
import com.josehernandez.meli.dto.TopSecretNavyResponseDto;

public interface RebelsService {
    /**
     * Register a new navy coordinates-message information
     *
     * @param topSecretNavyRequestDto
     * @return
     */
    TopSecretNavyResponseDto getNavyInfo(TopSecretNavyRequestDto topSecretNavyRequestDto);
}

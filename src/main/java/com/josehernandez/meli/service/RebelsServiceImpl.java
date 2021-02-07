package com.josehernandez.meli.service;

import com.josehernandez.meli.dto.TopSecretShipRequestDto;
import com.josehernandez.meli.dto.TopSecretShipResponseDto;
import com.josehernandez.meli.utils.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RebelsServiceImpl implements RebelsService{

    private final Logger logger = LoggerFactory.getLogger(RebelsServiceImpl.class);

    @Override
    public TopSecretShipResponseDto getNavyInfo(final TopSecretShipRequestDto topSecretShipRequestDto) {
        TopSecretShipResponseDto topSecretShipResponseDto = new TopSecretShipResponseDto();
        topSecretShipResponseDto.setPositon(new Point(100,100));
        topSecretShipResponseDto.setMessage(topSecretShipRequestDto.getSatellites().toString());
        return topSecretShipResponseDto;
    }
}

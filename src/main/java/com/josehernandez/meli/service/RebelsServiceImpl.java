package com.josehernandez.meli.service;

import com.josehernandez.meli.dto.TopSecretShipRequestDto;
import com.josehernandez.meli.dto.TopSecretShipResponseDto;
import com.josehernandez.meli.utils.Point;
import org.springframework.stereotype.Component;

@Component
public class RebelsServiceImpl implements RebelsService{
    @Override
    public TopSecretShipResponseDto getNavyInfo(TopSecretShipRequestDto topSecretShipRequestDto) {
        TopSecretShipResponseDto topSecretShipResponseDto = new TopSecretShipResponseDto();
        topSecretShipResponseDto.setPositon(new Point(100,100));
        topSecretShipResponseDto.setMessage(topSecretShipRequestDto.getSatellites().toString());
        return topSecretShipResponseDto;
    }
}

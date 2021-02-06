package com.josehernandez.meli.service;

import com.josehernandez.meli.dto.TopSecretNavyRequestDto;
import com.josehernandez.meli.dto.TopSecretNavyResponseDto;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class RebelsServiceImpl implements RebelsService{
    @Override
    public TopSecretNavyResponseDto getNavyInfo(TopSecretNavyRequestDto topSecretNavyRequestDto) {
        TopSecretNavyResponseDto topSecretNavyResponseDto = new TopSecretNavyResponseDto();
        topSecretNavyResponseDto.setPositon(new Point(100,100));
        topSecretNavyResponseDto.setMessage(topSecretNavyRequestDto.getSatellites().toString());
        return topSecretNavyResponseDto;
    }
}

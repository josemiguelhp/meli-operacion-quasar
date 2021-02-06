package com.josehernandez.meli.controller.v1;

import com.josehernandez.meli.dto.TopSecretNavyRequestDto;
import com.josehernandez.meli.dto.TopSecretNavyResponseDto;
import com.josehernandez.meli.service.RebelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1")
public class RebelsController {

    @Autowired
    private RebelsService rebelsService;

    /**
     * Handles the incoming POST API "/v1/topsecret"
     *
     * @param topSecretNavyRequestDto
     * @return
     */
    @PostMapping("/topsecret")
    public ResponseEntity<TopSecretNavyResponseDto> createNavy(@RequestBody @Valid TopSecretNavyRequestDto topSecretNavyRequestDto) {
        TopSecretNavyResponseDto topSecretNavyResponseDto = rebelsService.getNavyInfo(topSecretNavyRequestDto);
        return ResponseEntity.ok(topSecretNavyResponseDto);
    }
}

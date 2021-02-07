package com.josehernandez.meli.controller.v1;

import com.josehernandez.meli.dto.TopSecretShipRequestDto;
import com.josehernandez.meli.dto.TopSecretShipResponseDto;
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
     * @param topSecretShipRequestDto
     * @return
     */
    @PostMapping("/topsecret")
    public ResponseEntity<TopSecretShipResponseDto> createShip(@RequestBody @Valid TopSecretShipRequestDto topSecretShipRequestDto) {
        TopSecretShipResponseDto topSecretShipResponseDto = rebelsService.getNavyInfo(topSecretShipRequestDto);
        return ResponseEntity.ok(topSecretShipResponseDto);
    }
}

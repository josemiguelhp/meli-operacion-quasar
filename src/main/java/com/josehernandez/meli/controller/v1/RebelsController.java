package com.josehernandez.meli.controller.v1;

import com.josehernandez.meli.dto.TopSecretShipRequestDto;
import com.josehernandez.meli.dto.TopSecretShipResponseDto;
import com.josehernandez.meli.service.RebelsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(RebelsController.class);

    /**
     * Handles the incoming POST API "/v1/topsecret"
     *
     * @param topSecretShipRequestDto
     * @return
     */
    @PostMapping("/topsecret")
    public ResponseEntity<TopSecretShipResponseDto> createShip(@RequestBody @Valid TopSecretShipRequestDto topSecretShipRequestDto) {
        logger.debug(topSecretShipRequestDto.toString());
        TopSecretShipResponseDto topSecretShipResponseDto = rebelsService.getNavyInfo(topSecretShipRequestDto);
        return ResponseEntity.ok(topSecretShipResponseDto);
    }
}

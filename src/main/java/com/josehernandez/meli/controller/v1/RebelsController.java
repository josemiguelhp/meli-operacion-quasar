package com.josehernandez.meli.controller.v1;

import com.josehernandez.meli.dto.SatelliteSplitRequestDto;
import com.josehernandez.meli.dto.TopSecretRequestDto;
import com.josehernandez.meli.dto.TopSecretResponseDto;
import com.josehernandez.meli.models.Satellite;
import com.josehernandez.meli.service.RebelsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1")
public class RebelsController {

    @Autowired
    private RebelsService rebelsService;

    private final Logger logger = LoggerFactory.getLogger(RebelsController.class);

    @PostMapping("/topsecret")
    public ResponseEntity<TopSecretResponseDto> obtainTopSecretShip(@RequestBody @Valid TopSecretRequestDto topSecretRequestDto) {
        logger.debug(topSecretRequestDto.toString());
        return ResponseEntity.ok(rebelsService.obtainTopSecretShip(topSecretRequestDto));
    }

    //Para mi deberia ser un patch, porque segun como entiendo en el ejercicio esto solo actualiza la observacion mas reciente del satellite
    @PostMapping("/topsecret_split/{satellite_name}")
    public ResponseEntity<TopSecretResponseDto> setSatelliteCurrentObservations(@PathVariable("satellite_name") String satelliteName, @RequestBody @Valid SatelliteSplitRequestDto satelliteSplitRequestDto) {
        logger.debug(satelliteName);
        logger.debug(satelliteSplitRequestDto.toString());
        rebelsService.setTopSecretSatelliteObservations(satelliteName, satelliteSplitRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/topsecret_split")
    public ResponseEntity<TopSecretResponseDto> obtainTopSecretLastShip() {
        return ResponseEntity.ok(rebelsService.obtainLastTopSecretShip());
    }

    /**
     * Servicio de utilidad para saber el estado actual de todos los satelites
     * @return all satellites
     */
    @GetMapping("/satellites")
    public ResponseEntity<List<Satellite>> obtainSatellites() {
        return ResponseEntity.ok(rebelsService.obtainSatellites());
    }
}

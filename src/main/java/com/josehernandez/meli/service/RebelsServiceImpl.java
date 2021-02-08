package com.josehernandez.meli.service;

import com.josehernandez.meli.dto.TopSecretShipRequestDto;
import com.josehernandez.meli.dto.TopSecretShipResponseDto;
import com.josehernandez.meli.exception.CalculationIndeterminacyException;
import com.josehernandez.meli.models.Satellite;
import com.josehernandez.meli.repository.SatelliteRepository;
import com.josehernandez.meli.utils.LocationTrilateration;
import com.josehernandez.meli.utils.MessageResolver;
import com.josehernandez.meli.utils.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class RebelsServiceImpl implements RebelsService {

    @Autowired
    private LocationTrilateration locationTrilateration;
    @Autowired
    private MessageResolver messageResolver;

    @Autowired
    private SatelliteRepository satelliteRepository;

    private final Logger logger = LoggerFactory.getLogger(RebelsServiceImpl.class);

    @Override
    public TopSecretShipResponseDto getNavyInfo(final TopSecretShipRequestDto topSecretShipRequestDto) {
        try {
            double[] distances = new double[topSecretShipRequestDto.getSatellites().size()];
            double[][] positions = new double[topSecretShipRequestDto.getSatellites().size()][];
            String[][] messages = new String[topSecretShipRequestDto.getSatellites().size()][];
            for (int i = 0; i < topSecretShipRequestDto.getSatellites().size(); i++) {
                Satellite satellite = topSecretShipRequestDto.getSatellites().get(i);
                positions[i] = satelliteRepository.findSatellitePosition(satellite.getName());
                if (positions[i] == null) {
                    throw new IllegalArgumentException("No se pudo encontrar la posicion actual del satelite " + satellite.getName());
                }
                distances[i] = satellite.getDistance();
                messages[i] = satellite.getMessage();
            }
            Point topSecretShipPoint = locationTrilateration.getLocation(distances, positions);
            String topSecretMessage = messageResolver.getMessage(messages);
            TopSecretShipResponseDto topSecretShipResponseDto = new TopSecretShipResponseDto();
            topSecretShipResponseDto.setPositon(topSecretShipPoint);
            topSecretShipResponseDto.setMessage(topSecretMessage);
            return topSecretShipResponseDto;
        } catch (CalculationIndeterminacyException | IllegalArgumentException e) {
            logger.error("Error trying to post entity", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Unexpected error", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}

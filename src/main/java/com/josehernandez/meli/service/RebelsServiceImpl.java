package com.josehernandez.meli.service;

import com.josehernandez.meli.dto.*;
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

import java.util.List;
import java.util.stream.Collectors;

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
    public TopSecretResponseDto obtainTopSecretShip(final TopSecretRequestDto topSecretRequestDto) {
        try {
            SatellitesObservationsDto satellitesObservationsDto = new SatellitesObservationsDto(topSecretRequestDto.getSatellites().size());
            for (int i = 0; i < topSecretRequestDto.getSatellites().size(); i++) {
                SatelliteRequestDto satellite = topSecretRequestDto.getSatellites().get(i);
                satellitesObservationsDto.getPositions()[i] = findSatellitePosition(satellite.getName());
                satellitesObservationsDto.getDistances()[i] = satellite.getDistance();
                satellitesObservationsDto.getMessages()[i] = satellite.getMessage();
            }
            return obtainShip(satellitesObservationsDto);
        } catch (CalculationIndeterminacyException | IllegalArgumentException e) {
            logger.error("Error trying to post entity", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Unexpected error", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @Override
    public void setTopSecretSatelliteObservations(final String satelliteName, final SatelliteSplitRequestDto satelliteSplitRequestDto) {
        try {
            if(!satelliteRepository.updateDistanceAndMessageByName(satelliteName,
                    satelliteSplitRequestDto.getDistance(), satelliteSplitRequestDto.getMessage()))
                throw new IllegalArgumentException("Satelite "+satelliteName+" no encontrado");
        } catch (IllegalArgumentException e) {
            logger.error("Error trying to post entity", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @Override
    public TopSecretResponseDto obtainLastTopSecretShip() {
        try {
            //Obtenemos los satelites que esten con observaciones recientes seteadas
            List<Satellite> satellites = satelliteRepository.findSatellites()
                    .stream().filter((Satellite satellite)-> satellite.getDistance() != -1 && satellite.getMessage() != null)
                    .collect(Collectors.toList());
            if(satellites.size()<2){
                throw new CalculationIndeterminacyException("No existe suficiente informacion (satelites con observaciones recientes)");
            }else{
                SatellitesObservationsDto satellitesObservationsDto = new SatellitesObservationsDto(satellites.size());
                for (int i = 0; i < satellites.size(); i++) {
                    satellitesObservationsDto.getPositions()[i] = findSatellitePosition(satellites.get(i).getName());
                    satellitesObservationsDto.getDistances()[i] = satellites.get(i).getDistance();
                    satellitesObservationsDto.getMessages()[i] = satellites.get(i).getMessage();
                }
                TopSecretResponseDto topSecretResponseDto=obtainShip(satellitesObservationsDto);
                //reiniciamos las observaciones recientes porque ya con esa informacion fue suficiente para determinar la posicion y mensaje de la nave
                satelliteRepository.resetSatellitesObservations();
                //devolvemos la respuesta
                return topSecretResponseDto;
            }
        } catch (CalculationIndeterminacyException | IllegalArgumentException e) {
            logger.error("Error trying to post entity", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Unexpected error", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @Override
    public List<Satellite> obtainSatellites() {
        return satelliteRepository.findSatellites();
    }

    private double[] findSatellitePosition(String satelliteName) {
        double[] satellitePosition = satelliteRepository.findPositionByName(satelliteName);
        if (satellitePosition == null) {
            String satelliteNotFoundMessage = "No se pudo encontrar la posicion del satelite " + satelliteName;
            logger.error(satelliteNotFoundMessage);
            throw new IllegalArgumentException(satelliteNotFoundMessage);
        }
        return satellitePosition;
    }

    private TopSecretResponseDto obtainShip(SatellitesObservationsDto satellitesObservationsDto) throws Exception {
        Point topSecretShipPoint = locationTrilateration.getLocation(satellitesObservationsDto.getDistances(), satellitesObservationsDto.getPositions());
        String topSecretMessage = messageResolver.getMessage(satellitesObservationsDto.getMessages());
        return TopSecretResponseDto.builder().positon(topSecretShipPoint).message(topSecretMessage).build();
    }
}

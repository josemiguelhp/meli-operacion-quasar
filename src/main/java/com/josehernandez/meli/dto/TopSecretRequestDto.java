package com.josehernandez.meli.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TopSecretRequestDto {
    @NotNull
    private List<SatelliteRequestDto> satellites;
}

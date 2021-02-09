package com.josehernandez.meli.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class SatelliteRequestDto extends SatelliteSplitRequestDto {
    @NotNull
    private String name;
}

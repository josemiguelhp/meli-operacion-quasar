package com.josehernandez.meli.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class SatelliteSplitRequestDto {
    @NotNull
    private float distance;
    @NotNull
    private String[] message;
}

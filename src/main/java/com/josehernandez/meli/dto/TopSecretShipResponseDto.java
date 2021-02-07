package com.josehernandez.meli.dto;

import com.josehernandez.meli.utils.Point;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TopSecretShipResponseDto {
    private Point positon;
    private String message;
}

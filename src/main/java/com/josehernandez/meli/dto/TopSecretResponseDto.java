package com.josehernandez.meli.dto;

import com.josehernandez.meli.utils.Point;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class TopSecretResponseDto {
    private Point positon;
    private String message;
}

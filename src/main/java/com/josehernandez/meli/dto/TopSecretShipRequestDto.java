package com.josehernandez.meli.dto;

import com.josehernandez.meli.models.Satellite;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TopSecretShipRequestDto {
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private List<Satellite> satellites;
}

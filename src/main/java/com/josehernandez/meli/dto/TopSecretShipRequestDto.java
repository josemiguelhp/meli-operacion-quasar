package com.josehernandez.meli.dto;

import com.josehernandez.meli.models.Satellite;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TopSecretShipRequestDto {
    @NotNull
    private List<Satellite> satellites;
}

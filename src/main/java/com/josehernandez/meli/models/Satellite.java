package com.josehernandez.meli.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
public class Satellite extends BaseModel{
    @NotNull
    private String name;
    @NotNull
    private float distance;
    @NotNull
    private String[] message;
}

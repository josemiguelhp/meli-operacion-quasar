package com.josehernandez.meli.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Satellite {

    private String name;

    private float distance;

    private String[] message;
}

package com.josehernandez.meli.models;

import com.josehernandez.meli.utils.Point;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseModel {

    private int id;
    private Point position;

}

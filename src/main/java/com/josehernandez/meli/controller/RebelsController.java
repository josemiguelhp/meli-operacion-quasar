package com.josehernandez.meli.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("controller_V1")
@RequestMapping("v1/topsecret")
public class RebelsController {
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createNavy() {
        return "Product is saved successfully";
    }
}

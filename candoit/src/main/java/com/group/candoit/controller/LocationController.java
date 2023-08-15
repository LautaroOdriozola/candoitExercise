package com.group.candoit.controller;

import com.group.candoit.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping(value = "/all-with-latest-weather",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLastLocationsWithWeather() {
        return ResponseEntity.ok(locationService.getAllWithLatestWeather());
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLocations() {
        return ResponseEntity.ok(locationService.getAll());
    }


}

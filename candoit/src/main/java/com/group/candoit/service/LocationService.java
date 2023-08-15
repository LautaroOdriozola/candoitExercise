package com.group.candoit.service;


import com.group.candoit.dto.LocationDto;
import com.group.candoit.dto.LocationWithoutWeatherDto;
import com.group.candoit.entity.Location;

import java.util.List;

public interface LocationService {

    List<Location> saveWeatherAndLocation() throws Exception;

    List<LocationDto> getAllWithLatestWeather();

    List<LocationWithoutWeatherDto> getAll();

}

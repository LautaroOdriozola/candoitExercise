package com.group.candoit.service.serviceImpl;

import com.group.candoit.dto.WeatherDto;
import com.group.candoit.dto.LocationDto;
import com.group.candoit.entity.Location;
import com.group.candoit.entity.Weather;
import com.group.candoit.repository.LocationRepository;
import com.group.candoit.service.LocationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Location> saveWeatherAndLocation() throws Exception {
        try{
            String URL = "https://ws.smn.gob.ar/map_items/weather";
            List<LocationDto> locationDtos = WebClient.create(URL)
                    .get()
                    .retrieve()
                    .bodyToFlux(LocationDto.class)
                    .collect(Collectors.toList())
                    .share()
                    .block();

            assert locationDtos != null;
            if(!locationDtos.isEmpty()){
                List<Location> locations = locationDtos
                        .stream()
                        .map( locationDto -> {
                            WeatherDto weatherDto = locationDto.getWeather();
                            Weather weather = modelMapper.map(weatherDto, Weather.class);
                            Location location = modelMapper.map(locationDto, Location.class);
                            location.setWeather(weather);
                            return location;
                        }).collect(Collectors.toList());

                return locationRepository.saveAll(locations);
            }

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return null;
    }

    @Override
    public List<LocationDto> getLastLocations() {

        List<Location> locations = locationRepository.getLastLocations();
        assert locations!=null;

        return locations
                .stream()
                .map(location -> {
                    Weather weather = location.getWeather();
                    WeatherDto weatherDto = modelMapper.map(weather, WeatherDto.class);
                    LocationDto locationDto = modelMapper.map(location, LocationDto.class);
                    locationDto.setWeather(weatherDto);
                    return locationDto;
                }).collect(Collectors.toList());
    }
}

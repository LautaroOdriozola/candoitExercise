package com.group.candoit.scheduler;

import com.group.candoit.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class LocationScheduler {

    @Autowired
    private LocationService locationService;

    @Scheduled(cron = "0 */5 * * * *")
    public void saveWeatherAndLocation(){
        try{
            locationService.saveWeatherAndLocation();
        } catch (Exception e){
            System.out.println("Error calling weather api.");
        }

    }
}

package com.group.candoit.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class LocalidadScheduler {

    //TODO: cambiar a /5 para que sea cada 5 minutos
    @Scheduled(cron = "0 */1 * * * *")
    public void getClimaLocalidad(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp + ": Java cron job test. ");
    }
}

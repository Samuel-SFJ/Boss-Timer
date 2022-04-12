package com.example.demo.DataModel;

import java.time.Duration;
import java.time.LocalTime;

public class Boss {

    private String name;
    private Duration timer;
    private String place;
    private LocalTime respawn;

    public Boss(String name, Duration timer, String place) {
        this.name = name;
        this.timer = timer;
        this.place = place;
        this.respawn = LocalTime.now().plus(Duration.ofMinutes(timer.toMinutes()));
    }

    public String getName() {
        return name;
    }

    public Duration getTimer() {
        return timer;
    }

    public String getPlace() {
        return place;
    }

    public LocalTime getRespawn() {
        return respawn;
    }

    public void markKill(){
        this.respawn = respawn.plus(timer);
    }

    public void markKill(LocalTime time){
        this.respawn = time.plus(timer);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTimer(Duration timer) {
        this.timer = timer;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

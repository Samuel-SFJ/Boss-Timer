package com.example.demo.DataModel;

import java.time.Duration;
import java.time.LocalTime;

public class Boss {

    private final String name;
    private final Duration timer;
    private final String place;
    private LocalTime respawn;

    public Boss(String name, Duration timer, String place) {
        this.name = name;
        this.timer = timer;
        this.place = place;
        this.respawn = null;
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

    public void setRespawn(LocalTime respawn) {
        this.respawn = respawn;
    }

    public void markKill(){
        this.respawn = respawn.plus(timer);
    }

    @Override
    public String toString() {
        return this.name;
    }
}

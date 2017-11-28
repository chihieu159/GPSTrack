package com.example.demo.domain.controller.responses;


import com.example.demo.domain.entities.Trackpoint;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class TrackpointDTO {

    private Double lat;
    private Double lon;
    private Float ele;
    private String time;

    public TrackpointDTO(Trackpoint trackpoint){
        this.lat = trackpoint.getLatitude();
        this.lon = trackpoint.getLongitude();
        this.ele = trackpoint.getEle();
        this.time = trackpoint.getDatetime().toString();
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public Float getEle() {
        return ele;
    }

    public String getTime() {
        return time;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public void setEle(Float ele) {
        this.ele = ele;
    }

    public void setTime(String time) {
        this.time = time.toString();
    }
}

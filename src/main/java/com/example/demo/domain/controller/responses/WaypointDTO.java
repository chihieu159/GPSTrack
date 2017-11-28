package com.example.demo.domain.controller.responses;

import com.example.demo.domain.entities.TrackSegment;
import com.example.demo.domain.entities.Waypoint;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class WaypointDTO {

    private String name;
    private Double lat;
    private Double lon;
    private String symbol;

    public WaypointDTO(Waypoint waypoint){
        this.name = waypoint.getName();
        this.lat = waypoint.getLatitude();
        this.lon = waypoint.getLongitude();
        this.symbol = waypoint.getSymbol();
    }


}

package com.example.demo.domain.controller.responses;


import com.example.demo.domain.entities.Trackpoint;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class TrackSegmentDTO {
    List<TrackpointDTO> trackpoints;

    public TrackSegmentDTO(List<Trackpoint> trackpointList){
        this.trackpoints = new ArrayList<TrackpointDTO>();
        for(Trackpoint item: trackpointList){
            TrackpointDTO dtoItem = new TrackpointDTO(item);
            this.trackpoints.add(dtoItem);
        }
    }

    public List<TrackpointDTO> getTrackpoints() {
        return trackpoints;
    }

    public void setTrackpoints(List<TrackpointDTO> trackpoints) {
        this.trackpoints = trackpoints;
    }
}

package com.example.demo.domain.controller.responses;

import com.example.demo.domain.entities.TrackSegment;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class TrackDTO {

    List<TrackSegmentDTO> segments;

    public TrackDTO(List<TrackSegment> trackSegmentList){
        segments = new ArrayList<TrackSegmentDTO>();

        for(TrackSegment item: trackSegmentList){
            TrackSegmentDTO dtoItem = new TrackSegmentDTO(item.getTrackpointList());
            this.segments.add(dtoItem);
        }
    }

    public List<TrackSegmentDTO> getSegments() {
        return segments;
    }

    public void setSegments(List<TrackSegmentDTO> segments) {
        this.segments = segments;
    }
}

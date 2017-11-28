package com.example.demo.domain.controller.responses;

import com.example.demo.domain.entities.Metadata;
import com.example.demo.domain.entities.Track;
import com.example.demo.domain.entities.Waypoint;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class GPXResponse {
    MetadataDTO metadata;
    List<WaypointDTO> waypoints;
    List<TrackDTO> tracks;

    public GPXResponse(Metadata metadata, List<Waypoint> waypointList, List<Track> trackList){
        setMetadataDTO(metadata);
        setWaypointDTOList(waypointList);
        setTrackDTOList(trackList);
    }

    private void setMetadataDTO(Metadata metadataItem){
        if (metadataItem != null) {
            metadata = new MetadataDTO(metadataItem);
        }
    }
    private void setTrackDTOList(List<Track> trackList){
        tracks = new ArrayList();
        if (trackList == null) {return;}

        for(Track track: trackList){
            TrackDTO trackDTO = new TrackDTO(track.getTrackSegments());
            tracks.add(trackDTO);
        }
    }

    private void setWaypointDTOList(List<Waypoint> waypointList){
        waypoints = new ArrayList();
        if (waypointList == null) {return;}

        for(Waypoint waypoint: waypointList){
            WaypointDTO waypointDTO = new WaypointDTO(waypoint);
            waypoints.add(waypointDTO);
        }
    }


}

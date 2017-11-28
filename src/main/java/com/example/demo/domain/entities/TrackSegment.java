package com.example.demo.domain.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "track_segment")
public class TrackSegment {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id", unique=true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="trackId", nullable = false)
    private Track track;

    @Transient
    private List<Trackpoint> trackpointList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public List<Trackpoint> getTrackpointList() {
        return trackpointList;
    }

    public void setTrackpointList(List<Trackpoint> trackpointList) {
        this.trackpointList = trackpointList;
    }
}

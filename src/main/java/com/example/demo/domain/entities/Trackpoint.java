package com.example.demo.domain.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "track_point")
public class Trackpoint {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id", unique=true, nullable = false)
    private Long id;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "ele")
    private Float ele;

    @Column(name = "date_time")
    private LocalDateTime datetime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="segmentId", nullable = false)
    private TrackSegment trackSegment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Float getEle() {
        return ele;
    }

    public void setEle(Float ele) {
        this.ele = ele;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public TrackSegment getTrackSegment() {
        return trackSegment;
    }

    public void setTrackSegment(TrackSegment trackSegment) {
        this.trackSegment = trackSegment;
    }
}

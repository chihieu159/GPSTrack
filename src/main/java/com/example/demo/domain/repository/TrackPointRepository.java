package com.example.demo.domain.repository;

import com.example.demo.domain.entities.TrackSegment;
import com.example.demo.domain.entities.Trackpoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackPointRepository extends JpaRepository<Trackpoint, Long> {
    List<Trackpoint> findByTrackSegment(TrackSegment trackSegment);
}

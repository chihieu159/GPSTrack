package com.example.demo.domain.repository;

import com.example.demo.domain.entities.Track;
import com.example.demo.domain.entities.TrackSegment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackSegmentRepository extends JpaRepository<TrackSegment, Long> {
    List<TrackSegment> findByTrack(Track track);
}

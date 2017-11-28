package com.example.demo.domain.service.impl;

import com.example.demo.domain.entities.Track;
import com.example.demo.domain.entities.TrackSegment;
import com.example.demo.domain.entities.Trackpoint;
import com.example.demo.domain.entities.User;
import com.example.demo.domain.repository.TrackPointRepository;
import com.example.demo.domain.repository.TrackRepository;
import com.example.demo.domain.repository.TrackSegmentRepository;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.domain.service.ITrackService;
import com.example.demo.domain.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService implements ITrackService {
    private final Logger logger = LoggerFactory.getLogger(TrackService.class);

    @Autowired
    TrackRepository trackRepository;

    @Autowired
    TrackSegmentRepository trackSegmentRepository;

    @Autowired
    TrackPointRepository trackPointRepository;

    public Page<Track> list(Pageable pageable){
        logger.info("[TrackService][list]");
        return trackRepository.findAll(pageable);
    }

    public List<Track> getTracks(User user){
        logger.info("[TrackService][getTracks]");

        List<Track> trackList = trackRepository.findByUser(user);
        for(Track track: trackList){
            track.setUser(null);
            List<TrackSegment> trackSegmentList = getTrackSegments(track);
            track.setTrackSegments(trackSegmentList);
        }
        return trackList;
    }

    private List<TrackSegment> getTrackSegments(Track track){
        logger.debug("[TrackService][getTrackSegments]");
        List<TrackSegment> trackSegmentList = trackSegmentRepository.findByTrack(track);
        for(TrackSegment trackSegment: trackSegmentList){
            trackSegment.setTrack(null);
            List<Trackpoint> trackpointList = trackPointRepository.findByTrackSegment(trackSegment);
            trackSegment.setTrackpointList(trackpointList);
        }
        return trackSegmentList;
    }
}

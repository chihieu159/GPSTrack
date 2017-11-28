package com.example.demo.domain.service.impl;

import com.example.demo.domain.entities.User;
import com.example.demo.domain.entities.Waypoint;
import com.example.demo.domain.repository.WaypointRepository;
import com.example.demo.domain.service.IWaypointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaypointService implements IWaypointService {
    private final Logger logger = LoggerFactory.getLogger(WaypointService.class);

    @Autowired
    WaypointRepository waypointRepository;

    public List<Waypoint> getWaypoints(User user){
        logger.info("[WaypointService][getWaypoints]");
        List<Waypoint> waypointList = waypointRepository.findByUser(user);
        for(Waypoint waypoint: waypointList){
            waypoint.setUser(null);
        }

        return waypointList;
    }
}

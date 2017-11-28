package com.example.demo.domain.service;

import com.example.demo.domain.entities.Track;
import com.example.demo.domain.entities.User;
import com.example.demo.domain.entities.Waypoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IWaypointService {

    List<Waypoint> getWaypoints(User user);
}

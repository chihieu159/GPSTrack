package com.example.demo.domain.repository;

import com.example.demo.domain.entities.User;
import com.example.demo.domain.entities.Waypoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WaypointRepository extends JpaRepository<Waypoint, Long> {
    List<Waypoint> findByUser(User user);
}

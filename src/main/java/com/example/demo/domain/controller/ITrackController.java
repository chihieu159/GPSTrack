package com.example.demo.domain.controller;


import com.example.demo.domain.entities.Track;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ITrackController {
    Page<Track> list(Pageable pageable);
}

package com.example.demo.domain.service;

import com.example.demo.domain.entities.Track;
import com.example.demo.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITrackService {

    Page<Track> list(Pageable pageable);
    List<Track> getTracks(User user);
}

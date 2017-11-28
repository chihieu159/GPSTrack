package com.example.demo.domain.service;

import com.example.demo.domain.entities.Track;
import com.example.demo.domain.entities.User;
import com.example.demo.domain.entities.Waypoint;

import java.util.List;

public interface IUserService {

    void createUser(String username, String password);
    User getById(Long userId);
}

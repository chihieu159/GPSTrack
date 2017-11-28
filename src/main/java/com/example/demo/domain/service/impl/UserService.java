package com.example.demo.domain.service.impl;

import com.example.demo.domain.entities.Track;
import com.example.demo.domain.entities.TrackSegment;
import com.example.demo.domain.entities.User;
import com.example.demo.domain.entities.Waypoint;
import com.example.demo.domain.repository.TrackRepository;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.domain.repository.WaypointRepository;
import com.example.demo.domain.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class UserService implements IUserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public User getById(Long userId){
        logger.info("[UserService][getById]");
        return userRepository.findOne(userId);
    }

    public void createUser(String username, String password) {
        logger.info("[UserService][createUser]");
        User user = createUserInstance(username, password);
        userRepository.save(user);
    }

    private User createUserInstance(String username, String password){
        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setCreatedDate(LocalDateTime.now());

        return user;
    }

}

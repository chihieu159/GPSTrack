package com.example.demo.domain.controller.impl;

import com.example.demo.domain.controller.ITrackController;
import com.example.demo.domain.entities.Track;
import com.example.demo.domain.repository.TrackRepository;
import com.example.demo.domain.service.ITrackService;
import com.example.demo.domain.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tracks")
public class TrackController implements ITrackController{
    private final Logger logger = LoggerFactory.getLogger(TrackController.class);

    @Autowired
    private ITrackService trackService;

    @Autowired
    TrackRepository trackRepository;

    /**
     * Frontend side could invoke the API with those params in URI to search latest track:
     * page=pageIndex (started from 0)
     * size=pageSize
     * sort=createdDate,desc
     * Ex: http://RestAPIDomain/tracks/list?page=0&size=3&sort=createdDate,desc
     * @param pageable
     * @return list of Track in json
     */
    @GetMapping(value="list")
    public Page<Track> list(Pageable pageable){
        logger.info("[TrackController][listLatestTracks][page:{}-{}]", pageable.getPageNumber(), pageable.getPageSize());
        return trackRepository.findAll(pageable);
    }
}

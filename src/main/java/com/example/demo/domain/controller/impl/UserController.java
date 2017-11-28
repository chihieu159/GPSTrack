package com.example.demo.domain.controller.impl;

import com.example.demo.domain.controller.IUserController;
import com.example.demo.domain.controller.exceptions.UploadRequestException;
import com.example.demo.domain.controller.responses.GPXResponse;
import com.example.demo.domain.controller.responses.TrackDTO;
import com.example.demo.domain.entities.Metadata;
import com.example.demo.domain.entities.Track;
import com.example.demo.domain.entities.User;
import com.example.demo.domain.entities.Waypoint;
import com.example.demo.domain.service.IMetadataService;
import com.example.demo.domain.service.ITrackService;
import com.example.demo.domain.service.IUploadService;
import com.example.demo.domain.service.IUserService;
import com.example.demo.domain.service.IWaypointService;
import com.example.demo.domain.validator.RequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/users")
public class UserController implements IUserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IUploadService uploadService;

    @Autowired
    private IWaypointService waypointService;

    @Autowired
    private ITrackService trackService;

    @Autowired
    private IMetadataService metadataService;

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestParam String username, @RequestParam String password) {
        logger.info("[UserController][create user][username={}]", username);

        RequestValidator validator = new RequestValidator();
        validator.addItem("username", username);
        validator.addItem("password", password);

        if (!validator.validate()){
            return new ResponseEntity(validator.getErrors(), HttpStatus.BAD_REQUEST);
        }

        userService.createUser(username, password);

        return new ResponseEntity("user is created successfully", HttpStatus.CREATED);
    }

    @PostMapping("/{id}/uploadgpx")
    public ResponseEntity uploadFile(@PathVariable("id") Long userId,
                                        @RequestParam("file") MultipartFile uploadfile) {
        logger.info("[uploadFile][id={}][uploadFile size={}]", userId, uploadfile.getSize());

        if (userId == null || uploadfile == null || uploadfile.getSize() == 0){
            return new ResponseEntity("param is invalid", HttpStatus.BAD_REQUEST);
        }

        uploadService.uploadGPXToDB(userId, uploadfile);

        return new ResponseEntity("Successfully uploaded", HttpStatus.OK);
    }

    @GetMapping("/{id}/viewgpx")
    public ResponseEntity<ArrayList> viewFile(@PathVariable("id") Long userId){
        logger.info("[viewFile][id={}]", userId);
        RequestValidator validator = new RequestValidator();
        validator.addItem("userId", userId);

        if (!validator.validate()){
            return new ResponseEntity(validator.getErrors(), HttpStatus.BAD_REQUEST);
        }

        User user = userService.getById(userId);
        if (user == null){
            throw new UploadRequestException("user is not found");
        }

        Metadata metadata = metadataService.getMetadata(user);
        List<Waypoint> waypointList = waypointService.getWaypoints(user);
        List<Track> trackList = trackService.getTracks(user);

        GPXResponse gpxResponse = new GPXResponse(metadata, waypointList, trackList);

        return new ResponseEntity(gpxResponse, HttpStatus.OK);
    }

}

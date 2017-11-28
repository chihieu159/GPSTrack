package com.example.demo.domain.service.impl;

import com.example.demo.domain.controller.exceptions.UploadRequestException;
import com.example.demo.domain.entities.Metadata;
import com.example.demo.domain.entities.Track;
import com.example.demo.domain.entities.TrackSegment;
import com.example.demo.domain.entities.Trackpoint;
import com.example.demo.domain.entities.User;
import com.example.demo.domain.repository.MetadataRepository;
import com.example.demo.domain.repository.TrackPointRepository;
import com.example.demo.domain.repository.TrackRepository;
import com.example.demo.domain.repository.TrackSegmentRepository;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.domain.repository.WaypointRepository;
import com.example.demo.domain.utils.Constants;
import com.example.demo.domain.entities.Waypoint;
import com.example.demo.domain.service.IUploadService;

import com.example.demo.domain.utils.DateUtils;
import com.example.demo.domain.utils.XMLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.time.LocalDateTime;

@Service
public class UploadService implements IUploadService {

    private final Logger logger = LoggerFactory.getLogger(UploadService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    MetadataRepository metadataRepository;

    @Autowired
    WaypointRepository waypointRepository;

    @Autowired
    TrackRepository trackRepository;

    @Autowired
    TrackSegmentRepository trackSegmentRepository;

    @Autowired
    TrackPointRepository trackPointRepository;

    public void uploadGPXToDB(Long userId, MultipartFile uploadfile) throws UploadRequestException {
        logger.info("[UploadService][uploadGPXToDB][userId={}]", userId);

        User user = userRepository.findOne(userId);
        if (user == null)
        {
            throw new UploadRequestException("user is not found");
        }

        try {
            Document rootNode = XMLUtils.parseXML(uploadfile.getInputStream());
            transformXMLToMetadata(rootNode, user);
            transformXMLToWayPoints(rootNode, user);
            transformXMLToTracks(rootNode, user);

        }catch(Exception ex){
            logger.error("[UploadService][uploadGPXToDB][ex={}]", ex.getStackTrace());
            throw new UploadRequestException("uploadGPXToDB is not successful because of an error");
        }
    }

    private void transformXMLToMetadata(Document rootNode, User user){
        logger.debug("[UploadService][transformXMLToMetadata]");
        NodeList nodeList = rootNode.getElementsByTagName(Constants.XML_ELEMENT_METADATA);

        for(int index = 0; index < nodeList.getLength(); index++){
            createMetadata(nodeList.item(index), user);
        }
    }

    private void createMetadata(Node metadataNode, User user) {
        try {
            if (metadataNode == null) {return;}
            Metadata metadata = new Metadata();

            metadata.setName(XMLUtils.getNodeValue(metadataNode, Constants.XML_ELEMENT_METADATA_NAME));
            metadata.setDescription(XMLUtils.getNodeValue(metadataNode, Constants.XML_ELEMENT_METADATA_DESCRIPTION));
            metadata.setAuthor(XMLUtils.getNodeValue(metadataNode, Constants.XML_ELEMENT_METADATA_AUTHOR));

            Node linkNode = ((Element) metadataNode).getElementsByTagName(Constants.XML_ELEMENT_METADATA_LINK).item(0);
            metadata.setLink("<a href='" + XMLUtils.getNodeAttribute(linkNode, Constants.XML_ELEMENT_METADATA_LINK_ATTRIBUTE) +
                    "'>" + XMLUtils.getNodeValue(linkNode, Constants.XML_ELEMENT_METADATA_LINK_TEXT) + "</a>");

            String timeInString = XMLUtils.getNodeValue(metadataNode, Constants.XML_ELEMENT_TRACK_POINT_TIME);
            metadata.setCreatedDate(DateUtils.convertStringToDate(timeInString, Constants.TIME_PATTERN));

            metadata.setUser(user);

            metadataRepository.save(metadata);
        }catch(Exception ex){
            logger.error("[UploadService][createMetadata][ex={}]", ex.getStackTrace());
        }
    }

    private void transformXMLToWayPoints(Document rootNode, User user){
        logger.debug("[UploadService][transformXMLToWayPoints]");
        NodeList nodeList = rootNode.getElementsByTagName(Constants.XML_ELEMENT_WAYPOINT);

        for(int index = 0; index < nodeList.getLength(); index++){
            createWaypoint(nodeList.item(index), user);
        }
    }

    private void createWaypoint(Node currentNode, User user) {
        Waypoint waypoint = new Waypoint();

        waypoint.setName(XMLUtils.getNodeValue(currentNode, Constants.XML_ELEMENT_WAYPOINT_NAME));
        waypoint.setSymbol(XMLUtils.getNodeValue(currentNode, Constants.XML_ELEMENT_WAYPOINT_SYM));
        waypoint.setLatitude(Double.parseDouble(XMLUtils.getNodeAttribute(currentNode, Constants.XML_ATTRIBUTE_LAT)));
        waypoint.setLongitude(Double.parseDouble(XMLUtils.getNodeAttribute(currentNode, Constants.XML_ATTRIBUTE_LON)));
        waypoint.setUser(user);

        waypointRepository.save(waypoint);
    }

    private void transformXMLToTracks(Document rootNode, User user){
        logger.debug("[UploadService][transformXMLToTracks]");
        NodeList trackList = rootNode.getElementsByTagName(Constants.XML_ELEMENT_TRACK);

        for(int index = 0; index < trackList.getLength(); index++){
            Track track = createTrack(user, index);
            saveTrackSegmentList(trackList.item(index), track);
        }
    }

    private Track createTrack(User user, int index) {
        Track track = new Track();
        track.setName("track No " + String.valueOf(index + 1));
        track.setUser(user);
        track.setCreatedDate(LocalDateTime.now());
        trackRepository.save(track);

        return track;
    }

    private void saveTrackSegmentList(Node currentNode, Track track){
        logger.debug("[UploadService][saveTrackSegmentList]");

        NodeList trackSegmentNodes = ((Element)currentNode).getElementsByTagName(Constants.XML_ELEMENT_TRACK_SEGMENT);

        for(int index = 0; index < trackSegmentNodes.getLength(); index++){
            TrackSegment trackSegment = createTrackSegment(track);
            saveTrackPointList(trackSegmentNodes.item(index), trackSegment);
        }
    }

    private TrackSegment createTrackSegment(Track track) {
        TrackSegment trackSegment = new TrackSegment();
        trackSegment.setTrack(track);
        trackSegmentRepository.save(trackSegment);
        return trackSegment;
    }

    private void saveTrackPointList(Node trackSegmentNode, TrackSegment trackSegment){
        logger.debug("[UploadService][saveTrackPointList]");

        NodeList trackpointNodes = ((Element)trackSegmentNode).getElementsByTagName(Constants.XML_ELEMENT_TRACK_POINT);

        for(int index = 0; index < trackpointNodes.getLength(); index++){
            createTrackPoint(trackpointNodes.item(index), trackSegment);
        }
    }

    private void createTrackPoint(Node trackPointNode, TrackSegment trackSegment) {
        Trackpoint trackpoint = new Trackpoint();

        trackpoint.setLatitude(Double.parseDouble(
                XMLUtils.getNodeAttribute(trackPointNode,
                Constants.XML_ATTRIBUTE_LAT)));

        trackpoint.setLongitude(Double.parseDouble(
                XMLUtils.getNodeAttribute(trackPointNode,
                        Constants.XML_ATTRIBUTE_LON)));

        trackpoint.setEle(Float.parseFloat(
                XMLUtils.getNodeValue(trackPointNode,
                Constants.XML_ELEMENT_TRACK_POINT_ELE)));

        String timeInString = XMLUtils.getNodeValue(trackPointNode, Constants.XML_ELEMENT_TRACK_POINT_TIME);
        trackpoint.setDatetime(DateUtils.convertStringToDate(timeInString, Constants.TIME_PATTERN));

        trackpoint.setTrackSegment(trackSegment);

        trackPointRepository.save(trackpoint);
    }
}

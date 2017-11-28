package com.example.demo.domain.service.impl;

import com.example.demo.domain.entities.Metadata;
import com.example.demo.domain.entities.User;
import com.example.demo.domain.entities.Waypoint;
import com.example.demo.domain.repository.MetadataRepository;
import com.example.demo.domain.repository.WaypointRepository;
import com.example.demo.domain.service.IMetadataService;
import com.example.demo.domain.service.IWaypointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetadataService implements IMetadataService{
    private final Logger logger = LoggerFactory.getLogger(MetadataService.class);

    @Autowired
    MetadataRepository metadataRepository;

    public Metadata getMetadata(User user){
        logger.info("[MetadataService][getMetadata]");
        List<Metadata> metadataList =  metadataRepository.findByUser(user);
        if (metadataList == null || metadataList.isEmpty()) return null;

        return metadataList.get(0);
    }
}

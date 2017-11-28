package com.example.demo.domain.controller.responses;

import com.example.demo.domain.entities.Metadata;
import com.example.demo.domain.entities.Waypoint;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class MetadataDTO {

    private String name;
    private String description;
    private String author;
    private String link;
    private String time;

    public MetadataDTO(Metadata metadata){
        this.name = metadata.getName();
        this.description = metadata.getDescription();
        this.author = metadata.getAuthor();
        this.link = metadata.getLink();
        this.time = metadata.getCreatedDate().toString();
    }


}

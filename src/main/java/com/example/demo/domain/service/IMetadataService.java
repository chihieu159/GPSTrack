package com.example.demo.domain.service;

import com.example.demo.domain.entities.Metadata;
import com.example.demo.domain.entities.User;

public interface IMetadataService {

    Metadata getMetadata(User user);

}

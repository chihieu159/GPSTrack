package com.example.demo.domain.service;

import com.example.demo.domain.controller.exceptions.UploadRequestException;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {

    void uploadGPXToDB(Long userId, MultipartFile uploadfile) throws UploadRequestException;
}

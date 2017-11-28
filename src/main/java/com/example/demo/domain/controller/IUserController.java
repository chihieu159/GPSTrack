package com.example.demo.domain.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface IUserController {

    ResponseEntity createUser(String username, String password);
    ResponseEntity uploadFile(@PathVariable("id") Long id,
                                        @RequestParam("file") MultipartFile uploadfile);


}

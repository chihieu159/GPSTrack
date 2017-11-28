package com.example.demo;

import com.example.demo.domain.controller.impl.UserController;
import com.example.demo.domain.service.IMetadataService;
import com.example.demo.domain.service.ITrackService;
import com.example.demo.domain.service.IUploadService;
import com.example.demo.domain.service.IUserService;
import com.example.demo.domain.service.IWaypointService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest_CreateUser {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IUserService userService;

    @MockBean
    private IUploadService uploadService;

    @MockBean
    private IWaypointService waypointService;

    @MockBean
    private ITrackService trackService;

    @MockBean
    private IMetadataService metadataService;

    @Test
    public void givenUsernameAndPassword_whenCreateUser_thenReturnSuccessful()
            throws Exception {

        mvc.perform(
                MockMvcRequestBuilders.post("/users/create")
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("username", "user1")
                        .param("password", "1234"))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void givenUsernameAndEmptyPassword_whenCreateUser_thenReturnError()
            throws Exception {

        mvc.perform(
                MockMvcRequestBuilders.post("/users/create")
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("username", "user1")
                        .param("password", ""))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void givenWithoutParams_whenCreateUser_thenReturnError()
            throws Exception {

        mvc.perform(
                MockMvcRequestBuilders.post("/users/create")
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void givenEmptyUsernameAndPassword_whenCreateUser_thenReturnError()
            throws Exception {

        mvc.perform(
                MockMvcRequestBuilders.post("/users/create")
                        .contentType(MediaType.TEXT_PLAIN)
                        .param("username", "")
                        .param("password", "1234"))
                .andExpect(status().is4xxClientError());
    }
}

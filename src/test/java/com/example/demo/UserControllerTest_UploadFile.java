package com.example.demo;

import com.example.demo.domain.controller.impl.UserController;
import com.example.demo.domain.service.IMetadataService;
import com.example.demo.domain.service.ITrackService;
import com.example.demo.domain.service.IUploadService;
import com.example.demo.domain.service.IUserService;
import com.example.demo.domain.service.IWaypointService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.InputStream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest_UploadFile {
    private final Logger logger = LoggerFactory.getLogger(UserControllerTest_UploadFile.class);

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
    public void givenUserAndValidFile_whenUploadFile_thenReturnSuccessful()
            throws Exception {

        final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("sample.gpx");
        final MockMultipartFile gpxFile = new MockMultipartFile("file", "sample.gpx", "gpx", inputStream);

        logger.debug("inputStream:" + Thread.currentThread().getContextClassLoader());
        mvc.perform(
                MockMvcRequestBuilders.fileUpload("/users/1/uploadgpx")
                        .file(gpxFile)
                        .param("id", "1"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void givenUserAndInvalidFile_whenUploadFile_thenReturnError()
            throws Exception {

        final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test");
        final MockMultipartFile gpxFile = new MockMultipartFile("file", "sample.gpx", "gpx", inputStream);

        mvc.perform(
                MockMvcRequestBuilders.fileUpload("/users/1/uploadgpx")
                        .file(gpxFile))
                .andExpect(status().is4xxClientError());
    }
}

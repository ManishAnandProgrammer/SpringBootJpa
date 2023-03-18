package com.example.controllers;

import com.example.SpringJpaWithAuditingApplication;
import com.example.domains.Student;
import com.example.utils.StudentUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = SpringJpaWithAuditingApplication.class)
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    public void ableToSaveStudent() {
        Student studentToSave = StudentUtils.getStudent();
        String studentToSaveInJsonForm = objectMapper.writeValueAsString(studentToSave);

        MockHttpServletRequestBuilder postRequest = post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(studentToSaveInJsonForm)
                .characterEncoding(Charset.defaultCharset());

        MvcResult mvcResult = mockMvc.perform(postRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String contentAsString = response.getContentAsString();
        Student savedStudent = objectMapper.readValue(contentAsString, Student.class);
        assertEquals(studentToSave.getFirstName(), savedStudent.getFirstName());
    }
}
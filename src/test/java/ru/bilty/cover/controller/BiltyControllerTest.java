package ru.bilty.cover.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.bilty.cover.model.BoxingLink;
import ru.bilty.cover.model.UnboxingLink;

@SpringBootTest
class BiltyControllerTest {

    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @SneakyThrows
    @Test
    void boxingUrl() {
        final String inputJson = mapToJson(new UnboxingLink("https://google.com"));
        final String expectedResult = "http://bilty-cover/0";
        final String uri = "/boxing";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());
        assertEquals(expectedResult, result.getResponse().getContentAsString());
    }

    @SneakyThrows
    @Test
    void unboxingUrl() {
        final String inputJson = mapToJson(new BoxingLink("http://bilty-cover/0"));
        final String expectedResult = "https://google.com";
        final String uri = "/unboxing";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());
        assertEquals(expectedResult, result.getResponse().getContentAsString());
    }

    @SneakyThrows
    @Test
    void unboxingUrl_whenNotFoundLink() {
        final String inputJson = mapToJson(new BoxingLink("http://bilty-cover/15"));
        final String uri = "/unboxing";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andReturn();
        assertEquals(404, result.getResponse().getStatus());
    }

    @SneakyThrows
    @Test
    void unboxingUrl_whenIllegalForBoxingLink() {
        final String inputJson = mapToJson(new UnboxingLink("invalid_link"));
        final String uri = "/boxing";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andReturn();
        assertEquals(422, result.getResponse().getStatus());
    }

    @SneakyThrows
    @Test
    void unboxingUrl_whenIllegalForUnboxingLink() {
        final String inputJson = mapToJson(new BoxingLink("invalid_link"));
        final String uri = "/unboxing";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andReturn();
        assertEquals(422, result.getResponse().getStatus());
    }

    @SneakyThrows
    protected String mapToJson(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
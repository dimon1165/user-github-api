package com.tui.mobile.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tui.mobile.controller.GitHubRepositoryController;
import com.tui.mobile.service.GitHubRepositoryService;
import feign.FeignException;
import feign.Request;
import feign.RequestTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GitHubRepositoryController.class)
public class ApplicationExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GitHubRepositoryService gitHubRepositoryService;

    @Test
    void whenGivenNonExistingUserThenReturn404AndError() throws Exception {
        // given
        var login = "test_user";
        var expectedErrorMessage = "Not found";
        int status = 404;
        var expected = ExceptionBody.builder()
                .status(status)
                .message(expectedErrorMessage)
                .build();

        Request request = Request.create(Request.HttpMethod.GET, "url",
                new HashMap<>(), null, new RequestTemplate());

        when(gitHubRepositoryService.getRepositoryInfoByUseName(login))
                .thenThrow(new FeignException.NotFound(expectedErrorMessage, request, null, null));

        // when
        var mvcResult = mockMvc.perform(get("/repository/user/{login}", login)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        var actualResultString = mvcResult.getResponse().getContentAsString();
        var actual = objectMapper.readValue(actualResultString, ExceptionBody.class);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void whenGivenNotSupportedMediaTypeThenReturn406AndError() throws Exception {
        // given
        var login = "test_user";
        var expectedErrorMessage = "Could not find acceptable representation";
        var expected = ExceptionBody.builder()
                .status(406)
                .message(expectedErrorMessage)
                .build();

        // when
        var mvcResult = mockMvc.perform(get("/repository/user/{login}", login)
                        .accept(MediaType.APPLICATION_XML_VALUE))
                .andExpect(status().isNotAcceptable())
                .andReturn();
        var actualResultString = mvcResult.getResponse().getContentAsString();
        var actual = objectMapper.readValue(actualResultString, ExceptionBody.class);

        //then
        assertThat(actual).isEqualTo(expected);
    }
}

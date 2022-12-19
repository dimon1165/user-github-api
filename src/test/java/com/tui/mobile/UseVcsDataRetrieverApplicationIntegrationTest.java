package com.tui.mobile;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static wiremock.com.google.common.net.HttpHeaders.ACCEPT;

@ActiveProfiles("test")
@SpringBootTest(properties = "wiremock.server.port=8081")
@AutoConfigureWireMock
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class UseVcsDataRetrieverApplicationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSuccess() throws Exception {
        stubFor(WireMock.get(urlPathMatching("/github/users/validUserName/repos"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("validUserRepositories.json")
                ));

        stubFor(WireMock.get(urlPathMatching("/github/repos/validUserName/notForkRepository/branches"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("repositoriesBranches.json")
                ));

        mockMvc.perform(get("/repository/user/validUserName")
                        .header(ACCEPT, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("[" +
                                "{" +
                                "\"repositoryName\":\"notForkRepository\"," +
                                "\"ownerLogin\":\"validUserName\"," +
                                "\"branches\":[" +
                                "{" +
                                "\"name\":\"dependabot/maven/org.springframework.boot-spring-boot-starter-web-2.5.12\"," +
                                "\"lastCommitSha1\":\"59d06e3f691222cd77948d193e4d8cccee7f976c\"" +
                                "}," +
                                "{" +
                                "\"name\":\"master\"," +
                                "\"lastCommitSha1\":\"636b9e3001dd79dc7006d99ae5ebce41ff39292c\"" +
                                "}" +
                                "]" +
                                "}]",
                        true));
    }
}

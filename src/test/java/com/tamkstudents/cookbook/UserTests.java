package com.tamkstudents.cookbook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tamkstudents.cookbook.Controller.Request.SignUpRequest;
import com.tamkstudents.cookbook.Controller.Request.UpdateUserDetailsRequest;
import com.tamkstudents.cookbook.Domain.Dao.LoginUserDao;
import com.tamkstudents.cookbook.Service.LoginService;
import lombok.SneakyThrows;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@ActiveProfiles("test-containers")
public class UserTests {
    private final Faker faker = new Faker();
    @Autowired
    private LoginService loginService;
    @Autowired
    private MockMvc mvc;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    private LoginUserDao createLoginUser(SignUpRequest signUpRequest) {
        return loginService.createNewUser(
                signUpRequest
        );
    }

    @Test
    public void changeUserDescription() throws Exception {
        var signUpRequest = fakeDataSignUpRequest();
        var loginUser = createLoginUser(signUpRequest);
        var updateDetails = UpdateUserDetailsRequest.builder()
                .description(faker.seinfeld().business())
                .build();

        mvc.perform(put("/api/users/" + loginUser.getProfileId())
                        .content(asJsonString(updateDetails))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(user(loginUser)))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.description").value(updateDetails.getDescription())
                )
                .andDo(print());
    }

    private SignUpRequest fakeDataSignUpRequest() {
        return new SignUpRequest(
                faker.name().username(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.internet().password(8, 30)
        );
    }
}

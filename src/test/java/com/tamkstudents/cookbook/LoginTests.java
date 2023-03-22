package com.tamkstudents.cookbook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tamkstudents.cookbook.Controller.Request.SignUpRequest;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@ActiveProfiles("test-containers")
public class LoginTests {
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
    public void getCurrentUserWithValidSession() throws Exception {
        var signUpRequest = fakeDataSignUpRequest();
        var loginUser = createLoginUser(signUpRequest);

        mvc.perform(get("/users/current").with(user(loginUser)))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        MockMvcResultMatchers.jsonPath("$.firstName").value(signUpRequest.getFirstname()),
                        MockMvcResultMatchers.jsonPath("$.lastName").value(signUpRequest.getLastname())
                )
                .andDo(print());
    }

    @Test
    public void getCurrentUserWithoutSession() throws Exception {
        mvc.perform(get("/users/current"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    public void signUpWithValidRequest() throws Exception {
        mvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(fakeDataSignUpRequest())))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void signUpWithInvalidEmail() throws Exception {
        var validRequest = fakeDataSignUpRequest();

        var missingEmailAtRequest = validRequest.withEmail(
                validRequest.getEmail().replace('@', 'a')
        );

        mvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(missingEmailAtRequest)))
                .andExpect(status().isBadRequest())
                .andDo(print());

        var missingEmailPostfixRequest = validRequest.withEmail(
                validRequest.getEmail().substring(validRequest.getEmail().indexOf('@'))
        );

        mvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(missingEmailPostfixRequest)))
                .andExpect(status().isBadRequest())
                .andDo(print());

        var missingEmailPrefixRequest = validRequest.withEmail(
                validRequest.getEmail().substring(0, validRequest.getEmail().indexOf('@') + 1)
        );
        mvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(missingEmailPrefixRequest)))
                .andExpect(status().isBadRequest())
                .andDo(print());

    }

    @Test
    public void signUpWithInvalidPassword() throws Exception {
        var validRequest = fakeDataSignUpRequest();

        var tooShortPasswordRequest = validRequest.withPassword(
                validRequest.getPassword().substring(0, 6)
        );

        mvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(tooShortPasswordRequest)))
                .andExpect(status().isBadRequest())
                .andDo(print());

        var tooLongPasswordRequest = validRequest.withPassword(
                faker.internet().password(73, 100)
        );

        mvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(tooLongPasswordRequest)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void signUpTwiceWithSameCredentials() throws Exception {
        var validRequest = fakeDataSignUpRequest();

        mvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(validRequest)))
                .andExpect(status().isCreated())
                .andDo(print());

        mvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(validRequest)))
                .andExpect(status().isConflict())
                .andDo(print());
    }

    @Test
    public void signUpWithExistingSession() throws Exception {
        var signUpRequest = fakeDataSignUpRequest();
        var loginUser = createLoginUser(signUpRequest);

        mvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(fakeDataSignUpRequest()))
                        .with(user(loginUser)))
                .andExpect(status().isForbidden())
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

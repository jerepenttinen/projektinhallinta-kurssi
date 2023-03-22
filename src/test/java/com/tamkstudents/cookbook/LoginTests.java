package com.tamkstudents.cookbook;

import com.tamkstudents.cookbook.Controller.Request.SignUpRequest;
import com.tamkstudents.cookbook.Domain.Dao.LoginUserDao;
import com.tamkstudents.cookbook.Service.LoginService;
import lombok.SneakyThrows;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @SneakyThrows
    private LoginUserDao createLoginUser(SignUpRequest signUpRequest) {
        return loginService.createNewUser(
                signUpRequest
        );
    }

    @Test
    public void getCurrentUserWithValidSession() throws Exception {
        var signUpRequest = new SignUpRequest(
                faker.name().username(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.internet().emailAddress(),
                faker.internet().password(8, 30)
        );
        var loginUser = createLoginUser(signUpRequest);

        mvc.perform(get("/users/current").with(user(loginUser)))
                .andExpectAll(
                        status().isOk(),
                        content().contentType("application/json"),
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
}

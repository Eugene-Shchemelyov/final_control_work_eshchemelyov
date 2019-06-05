package com.gmail.eugene.controlwork.web.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static com.gmail.eugene.controlwork.service.constant.SecurityConstant.ADMINISTRATOR;
import static com.gmail.eugene.controlwork.service.constant.SecurityConstant.CUSTOMER;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiControllerIntegrationSecureTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    protected MockMvc mockMvc;

    @Before
    public void init() throws SQLException {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/control;user=test;password=test;");
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM T_BUSINESS_CARD WHERE F_ID = 100");
            statement.executeUpdate("INSERT INTO T_BUSINESS_CARD (F_ID, F_USER_ID, F_TITLE, F_FULL_NAME," +
                    " F_WORKING_TELEPHONE)" +
                    " VALUES (100, 1, 'tetet', 'tete', '1111')");
        }
    }

    @WithUserDetails(value = "customer", userDetailsServiceBeanName = "userDetailsServiceImpl")
    @Test
    public void shouldGetSucceedWith200ForCustomer() throws Exception {
        this.mockMvc.perform(get("/api/v1/users/cards"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotGetSucceedWith200ForCustomer() throws Exception {
        this.mockMvc.perform(get("/api/v1/users/cards"))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(authorities = {ADMINISTRATOR})
    @Test
    public void shouldGetSucceedWith200ForAdministratorAddCard() throws Exception {
        this.mockMvc.perform(post("/api/v1/cards")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{" +
                        "\"username\" : \"customer\"," +
                        "\"title\" : \"apipip\"," +
                        "\"fullName\" : \"pop\"," +
                        "\"phone\" : \"3456\"" +
                        "}"))
                .andExpect(status().isOk());
    }

    @WithMockUser(authorities = {CUSTOMER})
    @Test
    public void shouldNotGetSucceedWith200ForCustomerAddCard() throws Exception {
        this.mockMvc.perform(post("/api/v1/cards")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{" +
                        "\"username\" : \"customer\"," +
                        "\"title\" : \"apipip\"," +
                        "\"fullName\" : \"pop\"," +
                        "\"phone\" : \"3456\"" +
                        "}"))
                .andExpect(status().isFound());
    }

    @Test
    public void shouldNotGetSucceedWith200ForAllAddCard() throws Exception {
        this.mockMvc.perform(post("/api/v1/cards")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{" +
                        "\"username\" : \"customer\"," +
                        "\"title\" : \"apipip\"," +
                        "\"fullName\" : \"pop\"," +
                        "\"phone\" : \"3456\"" +
                        "}"))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser(authorities = {ADMINISTRATOR})
    @Test
    public void shouldGetSucceedWith200ForAdministratorWhenDeleting() throws Exception {
        this.mockMvc.perform(delete("/api/v1/cards/100"))
                .andExpect(status().isOk());
    }

    @WithMockUser(authorities = {CUSTOMER})
    @Test
    public void shouldNotGetSucceedWith200ForCustomerWhenDeleting() throws Exception {
        this.mockMvc.perform(delete("/api/v1/cards/100"))
                .andExpect(status().isFound());
    }

    @Test
    public void shouldNotGetSucceedWith200ForAllWhenDeleting() throws Exception {
        this.mockMvc.perform(delete("/api/v1/cards/100"))
                .andExpect(status().is4xxClientError());
    }
}

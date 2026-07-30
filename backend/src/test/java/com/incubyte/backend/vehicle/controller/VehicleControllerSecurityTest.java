package com.incubyte.backend.vehicle.controller;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VehicleControllerSecurityTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(
            username = "user@gmail.com",
            roles = "USER"
    )
    void shouldRejectVehicleCreationForNormalUser() throws Exception {

        String request = """
            {
                "make":"Toyota",
                "model":"Fortuner",
                "category":"SUV",
                "price":4200000,
                "quantity":5
            }
            """;

        mockMvc.perform(

                        post("/api/vehicles")

                                .contentType(
                                        MediaType.APPLICATION_JSON
                                )

                                .content(request)

                )

                .andExpect(

                        status().isForbidden()

                );

    }
}
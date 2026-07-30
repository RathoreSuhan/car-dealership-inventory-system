package com.incubyte.backend.vehicle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incubyte.backend.vehicle.dto.CreateVehicleRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VehicleControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test

    @WithMockUser(

            username = "admin",

            roles = "ADMIN"      // Mock authenticated ADMIN user

    )
    void shouldCreateVehicleSuccessfully() throws Exception {

        // Arrange : Build request body
        CreateVehicleRequest request =
                new CreateVehicleRequest(

                        "Toyota",

                        "Fortuner",

                        "SUV",

                        4200000.0,

                        10

                );

        // Act + Assert : Perform POST request and expect HTTP 201
        mockMvc.perform(

                        post("/api/vehicles")

                                .contentType(MediaType.APPLICATION_JSON)

                                .content(

                                        objectMapper.writeValueAsString(request)

                                )

                )

                .andExpect(status().isCreated());

    }
}
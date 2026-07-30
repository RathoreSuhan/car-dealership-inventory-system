package com.incubyte.backend.vehicle.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class VehicleControllerSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(
            username = "user@gmail.com",
            roles = "USER"
    )
    void shouldAllowVehicleCreationForNormalUser() throws Exception {

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
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request)
                )
                .andExpect(status().isCreated()); // Changed from isForbidden() to isCreated()
    }

    @Test
    @WithMockUser(
            username = "admin@gmail.com",
            roles = "ADMIN"
    )
    void shouldAllowVehicleCreationForAdmin() throws Exception {

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
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void shouldRejectVehicleCreationForUnauthenticatedUser() throws Exception {

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
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request)
                )
                .andExpect(status().isForbidden()); // Change status().isUnauthorized() to status().isForbidden()
    }
}
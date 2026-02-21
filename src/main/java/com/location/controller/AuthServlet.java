package com.location.controller;

import com.location.model.User;
import com.location.repository.UserRepository;
import com.location.service.AuthService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class AuthServlet {
    private final AuthService authService = new AuthService();
    private final UserRepository userRepository = new UserRepository();

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON) // Tells Jersey to expect JSON in the body
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User credentials) {
        // credentials.getEmail() and credentials.getPassword() are now populated from the body
        String token = authService.authenticate(credentials.getEmail(), credentials.getPassword());

        if (token != null) {
            return Response.ok("{\"token\": \"" + token + "\"}").build();
        }

        return Response.status(Response.Status.UNAUTHORIZED)
                .entity("{\"error\": \"invalid credentials\"}")
                .build();
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(User newUser) {
        // Check if user already exists in the DataStore
        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("{\"error\": \"User already exists\"}")
                    .build();
        }

        // Force role to Client for public registration
        newUser.setRole("Client");

        // Save to our in-memory DataStore via Repository
        userRepository.save(newUser);

        return Response.status(Response.Status.CREATED)
                .entity("{\"message\": \"Registration successful\"}")
                .build();
    }
}
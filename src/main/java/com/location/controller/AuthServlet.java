package com.location.controller;

import com.location.dto.LoginRequestDto;
import com.location.dto.UserRequestDto;
import com.location.model.User;
import com.location.repository.UserRepository;
import com.location.service.AuthService;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthServlet {

    private final AuthService authService = new AuthService();
    private final UserRepository userRepository = new UserRepository();

    // ================= LOGIN =================
    @POST
    @Path("/login")
    public Response login(@Valid LoginRequestDto credentials) {

        String token = authService.authenticate(
                credentials.getEmail(),
                credentials.getPassword()
        );

        if (token != null) {
            return Response.ok("{\"token\":\"" + token + "\"}").build();
        }

        return Response.status(Response.Status.UNAUTHORIZED)
                .entity("{\"error\":\"invalid credentials\"}")
                .build();
    }


    // ================= REGISTER =================
    @POST
    @Path("/register")
    public Response register(@Valid UserRequestDto request) {

        // check existing user
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("{\"error\":\"User already exists\"}")
                    .build();
        }

        // DTO → Entity mapping
        User newUser = new User(
                request.getEmail(),
                request.getPassword(),
                request.getFirstName(),
                request.getLastName(),
                request.getLicenseNumber(),
                request.getLicenseObtainedDate(),
                request.getBirthDate(),
                request.getPhoneNumber(),
                request.getAddress()
        );

        newUser.setRole("CLIENT");

        userRepository.save(newUser);

        return Response.status(Response.Status.CREATED)
                .entity("{\"message\":\"Registration successful\"}")
                .build();
    }
}
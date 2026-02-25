package com.location.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class UserRequestDto {

    // ---------- AUTH ----------
    @NotBlank(message = "Email is required")
    @Email(message = "Email format is invalid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must contain at least 6 characters")
    private String password;

    @Pattern(
            regexp = "ADMIN|MANAGER|CLIENT",
            message = "Role must be ADMIN, MANAGER or CLIENT"
    )
    private String role;


    // ---------- CLIENT PROFILE ----------
    @NotBlank(message = "firstName is required")
    @Size(min = 2, max = 50, message = "First name must contain 2–50 characters")
    private String firstName;

    @NotBlank(message = "lastName is required")
    @Size(min = 2, max = 50, message = "Last name must contain 2–50 characters")
    private String lastName;

    @NotBlank(message = "licenseNumber is required")
    @Size(min = 5, max = 30, message = "License number length invalid")
    private String licenseNumber;

    @NotBlank(message = "licenseObtainedDate is required")
    @PastOrPresent(message = "License obtained date must be in the past")
    private LocalDate licenseObtainedDate;

    @NotBlank(message = "birthDate is required")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @NotBlank(message = "phoneNumber is required")
    @Pattern(
            regexp = "^[2-3][0-9]{7}$",
            message = "Phone number must start with 2,3 or 4 and composed of 8 digits"
    )
    private String phoneNumber;

    @NotBlank(message = "adress is required")
    @Size(max = 255, message = "Address too long")
    private String address;


    // ---------- CONSTRUCTOR ----------
    public UserRequestDto() {
    }


    // ---------- GETTERS & SETTERS ----------
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }

    public LocalDate getLicenseObtainedDate() { return licenseObtainedDate; }
    public void setLicenseObtainedDate(LocalDate licenseObtainedDate) {
        this.licenseObtainedDate = licenseObtainedDate;
    }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
package com.location.model;
import java.time.LocalDate;



public class User {

    private String email;
    private String password;
    private String role; // ADMIN, MANAGER, CLIENT

    // Client-specific profile data
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private LocalDate licenseObtainedDate;
    private LocalDate birthDate;
    private String phoneNumber;
    private String address;

    public User() {
    }
    // Constructor for Admin Registration
    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Constructor for Client Registration
    public User(String email, String password, String firstName, String lastName,
                String licenseNumber, LocalDate licenseObtainedDate, LocalDate birthDate,
                String phoneNumber, String address) {
        this.email = email;
        this.password = password;
        this.role = "CLIENT";
        this.firstName = firstName;
        this.lastName = lastName;
        this.licenseNumber = licenseNumber;
        this.licenseObtainedDate = licenseObtainedDate;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }


    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getLicenseNumber() { return licenseNumber; }
    public LocalDate getLicenseObtainedDate() { return licenseObtainedDate; }
    public LocalDate getBirthDate() { return birthDate; }
}
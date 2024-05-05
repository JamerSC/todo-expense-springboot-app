package com.jamersc.springboot.todoexpense.validation;

import com.jamersc.springboot.todoexpense.entity.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ManageUser {

    @NotNull(message = "Firstname is required!")
    @Size(min = 1, message = "Firstname is required!")
    private String firstName;

    @NotNull(message = "Lastname is required!")
    @Size(min = 1, message = "Lastname is required!")
    private String lastName;

    @NotNull(message = "Gender is required!")
    private Gender gender;

    @NotNull(message = "Email is required!")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Username is required!")
    @Size(min = 1, message = "Username is required!")
    private String username;

    @NotNull(message = "Password is required!")
    @Size(min = 1, message = "Password is required!")
    private String password;

    public ManageUser() {
    }

    public ManageUser(String firstName, String lastName,
                      Gender gender, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AccountCreationValidation{" +
                ", createFirstName='" + firstName + '\'' +
                ", createLastName='" + lastName + '\'' +
                ", createGender=" + gender +
                ", createEmail='" + email + '\'' +
                ", createUsername='" + username + '\'' +
                ", createPassword='" + password + '\'' +
                '}';
    }
}
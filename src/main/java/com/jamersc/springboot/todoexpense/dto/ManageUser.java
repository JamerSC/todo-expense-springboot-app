package com.jamersc.springboot.todoexpense.dto;

import com.jamersc.springboot.todoexpense.model.Gender;
import com.jamersc.springboot.todoexpense.validation.PasswordMatches;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@PasswordMatches
public class ManageUser {

    private Integer id;

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
    @Size(min = 4, message = "Minimum of 4 characters!")
    private String username;

    @NotNull(message = "Password is required!")
    @Size(min = 3, message = "Password is minimum of 3 characters!")
    private String password;

    @NotNull(message = "Confirm Password is required!")
    @Size(min = 3, message = "Password is minimum of 3 characters!")
    private String confirmPassword;

    public ManageUser() {
    }

    public ManageUser(Integer id, String firstName, String lastName, Gender gender,
                      String email, String username, String password, String confirmPassword) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "ManageUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}

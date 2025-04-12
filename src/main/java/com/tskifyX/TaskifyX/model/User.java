package com.tskifyX.TaskifyX.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;


    public String getPassword() {
        return this.password;
    }

    // Add all other getters/setters
    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

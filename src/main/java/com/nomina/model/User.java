package com.nomina.model;

import java.util.UUID;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class User {
    private String id;
    private String name;
    private String surnames;
    private String email;
    private String password;

    public User(String name, String surnames, String email, String password) {
        // Generar un nuevo ID único utilizando UUID
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.surnames = surnames;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getEmail() {
        return email;
    }
 

    public String getPassword() {
        return password;
    }

}

package com.pragma.powerup.usermicroservice.domain.model;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;

public class User {
    private Long id;
    private String name;
    private String surname;
    private String dni;
    private String phone;
    private String birdDate;
    private String email;
    private String password;
    private RoleEntity idRole;

    public User(Long id, String name, String surname, String dni, String phone, String birdDate, String email, String password, RoleEntity idRole) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.phone = phone;
        this.birdDate = birdDate;
        this.email = email;
        this.password = password;
        this.idRole = idRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirdDate() {
        return birdDate;
    }

    public void setBirdDate(String birdDate) {
        this.birdDate = birdDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEntity getIdRole() {
        return idRole;
    }

    public void setIdRole(RoleEntity idRole) {
        this.idRole = idRole;
    }
}

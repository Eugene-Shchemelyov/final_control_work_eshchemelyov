package com.gmail.eugene.controlwork.service.model;

import com.gmail.eugene.controlwork.repository.model.enums.RoleEnum;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private RoleEnum role;
    private List<BusinessCardDTO> businessCards = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public List<BusinessCardDTO> getBusinessCards() {
        return businessCards;
    }

    public void setBusinessCards(List<BusinessCardDTO> businessCards) {
        this.businessCards = businessCards;
    }
}

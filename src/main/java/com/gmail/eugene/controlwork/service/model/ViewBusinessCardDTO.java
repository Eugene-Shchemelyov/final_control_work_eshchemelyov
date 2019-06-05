package com.gmail.eugene.controlwork.service.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ViewBusinessCardDTO {
    @NotEmpty
    private String title;
    private String fullName;
    @Size(max = 20)
    private String phone;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

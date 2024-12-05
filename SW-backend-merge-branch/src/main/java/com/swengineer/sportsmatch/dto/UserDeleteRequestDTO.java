package com.swengineer.sportsmatch.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDeleteRequestDTO {
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

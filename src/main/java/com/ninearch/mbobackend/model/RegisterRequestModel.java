package com.ninearch.mbobackend.model;

import lombok.Data;

@Data
public class RegisterRequestModel {
    private String email;
    private String password;
    private String name;
    private String roles;
}

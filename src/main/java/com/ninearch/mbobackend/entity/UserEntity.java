package com.ninearch.mbobackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_user")
public class UserEntity extends BaseEntity{
    @Column(nullable = false, unique = true,length = 60)
    private String email;
    @JsonIgnore
    @Column(nullable = false,length = 256)
    private String password;
    @Column(nullable = false,length = 120)
    private String name;
    @Column(nullable = false,length = 120)
    private String roles;


}
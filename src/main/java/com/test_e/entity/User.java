package com.test_e.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private  Integer userId;
    private String username;
    private String password;
    private String realName;
    private String role;
    private String phone;
    private String email;
    private String create_time;
    private String update_time;
    private boolean enabled = true;



}

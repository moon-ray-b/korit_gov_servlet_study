package com.korit.korit_gov_servlet_study.prt;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class User {
    private String username;
    private String password;
    private String name;
    private String email;
}

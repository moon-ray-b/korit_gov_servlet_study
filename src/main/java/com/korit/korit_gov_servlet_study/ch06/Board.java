package com.korit.korit_gov_servlet_study.ch06;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    private String title;
    private String content;
    private String username;
}

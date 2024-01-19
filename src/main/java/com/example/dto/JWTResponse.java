package com.example.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class JWTResponse {
    private String jwtToken;
    private String userName;
}

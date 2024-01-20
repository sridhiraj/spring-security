package com.example.controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class ApplicationController {




 @GetMapping("/")
 public String home() {
     return "home";
 }

 @GetMapping("/login")
 public String login(){
        return "login";
 }

 @PostMapping("/login")
 public String loginForm(RequestParam params){
    System.out.println(params);
    return "hello";
 }
 
 @GetMapping("/hello")
 public ModelAndView hello() {
     ModelAndView mv = new ModelAndView();
     mv.setViewName("hello");
     return mv;
 }
 
 @GetMapping("/test")
 public String test() {
     return "test";
 }

 @GetMapping("/authorization-code/callback")
 public String handleCallback(@RequestParam("code") String code) {
     // Exchange the authorization code for an access token
     String tokenEndpoint = "https://dev-57158829.okta.com/oauth2/default/v1/token";
     MultiValueMap<String, String> formData = new LinkedMultiValueMap();
     formData.add("grant_type", "authorization_code");
     formData.add("code", code);
     formData.add("redirect_uri", "http://localhost:8080/spring-security/authorization-code/callback");
     
     HttpHeaders headers = new HttpHeaders();
     headers.set("Authorization", "Bearer " + "0oadvnetpwBnz6XtA5d7" + ":" + "V_bDpFX9sHfQ-KSV-pjNxJk1NLRZQvgiqgg679meeLhiQzfJTu0X6gToCsoW5Xe3"); // Set the Authorization header

     HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(formData, headers);

     ResponseEntity<CustomTokenResponse> response = restTemplate().postForEntity(tokenEndpoint, request, CustomTokenResponse.class);

     // Process the access token (response.getBody().getAccessToken())
     return "callback";
 }
 
 @Bean
 public RestTemplate restTemplate() {
     return new RestTemplate();
 }
 
 
 @GetMapping("/getOktaToken")
 public String initiateLogin()  throws Exception{

	 String codeVerifier = generateCodeVerifier();
	 String codeChallenge = generateCodeChallenge(codeVerifier);

     return "redirect:" + "https://dev-57158829.okta.com/oauth2/default/v1/authorize" +
             "?client_id=0oadvnetpwBnz6XtA5d7" +
             "&redirect_uri=http://localhost:8080/spring-security/authorization-code/callback" +
             "&response_type=code" +
             "&scope=openid profile"+
             "&state=" + generateRandomState()+
             "&code_challenge=" + codeChallenge +
             "&code_challenge_method=S256";
 }
 
 public static String generateRandomState() {
     // Create a cryptographically secure random number generator
     SecureRandom secureRandom = new SecureRandom();

     // Generate a byte array to hold the random value
     byte[] randomBytes = new byte[32]; // Adjust the size based on your requirements

     // Fill the byte array with random values
     secureRandom.nextBytes(randomBytes);

     // Encode the random bytes to a Base64 string
     String randomState = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);

     return randomState;
 }
 
 public String generateCodeVerifier() {
     SecureRandom secureRandom = new SecureRandom();
     byte[] codeVerifier = new byte[32];
     secureRandom.nextBytes(codeVerifier);
     return Base64.getUrlEncoder().withoutPadding().encodeToString(codeVerifier);
 }

 public static String generateCodeChallenge(String codeVerifier) throws NoSuchAlgorithmException {
     MessageDigest digest = MessageDigest.getInstance("SHA-256");
     byte[] bytes = digest.digest(codeVerifier.getBytes());
     return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
 }
 
}


class CustomTokenResponse {

	   
    private String accessToken;

    private String tokenType;

    // Add other necessary fields

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
 
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
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 public String handleCallback( @RequestParam(name = "accessToken", required = false) String accessToken,
                                     Model model) {
         if (accessToken==null) {
             model.addAttribute("isUserLoggedIn", "true");
             return "login";
         }
         else {
             model.addAttribute("userName", "test");
//             return "home";
             return "redirect:/home";
         }
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
 
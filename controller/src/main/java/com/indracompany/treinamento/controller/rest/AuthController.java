package com.indracompany.treinamento.controller.rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.indracompany.treinamento.model.entity.User;
import com.indracompany.treinamento.model.service.UserService;
import com.indracompany.treinamento.payload.SignInRequest;




@RestController
@RequestMapping("rest/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	
	

    @PostMapping(value = "/signIn", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticate(@RequestBody SignInRequest signInRequest) {
    	
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        
   
       User user = userService.findByEmail(signInRequest.getEmail());
       
       
       
       boolean validPassword = encoder.matches(signInRequest.getPassword(), user.getPassword());
       
      
       if(!validPassword) {
    	   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }

     
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

        return ResponseEntity.ok(jwt);
    }



}
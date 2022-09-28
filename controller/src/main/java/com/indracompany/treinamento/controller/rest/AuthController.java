package com.indracompany.treinamento.controller.rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.indracompany.treinamento.model.entity.User;
import com.indracompany.treinamento.model.service.UserService;
import com.indracompany.treinamento.payload.SignInRequest;




@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private AuthenticationManager authenticationManager;
    

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
      
    }

    @PostMapping(value = "/signIn", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticate(@RequestBody SignInRequest signInRequest) {

       User user = userService.findByEmail(signInRequest.getEmail());
       
       if(user == null) {
    	   return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }

        String jwt = "54df6gr346sgs6543544fdgs435";
        return ResponseEntity.ok(jwt);
    }



}
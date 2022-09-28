package com.indracompany.treinamento.controller.rest;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import com.indracompany.treinamento.model.entity.User;
import com.indracompany.treinamento.model.service.UserService;

	



@RestController
@RequestMapping("/rest/users")
public class UserRest {
	
	@Autowired
	private UserService userService;

    @PostMapping(value = "/criarUsuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        User u = userService.create(user);
        if (u == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @GetMapping(value = "/bucarUsuarios", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<User> findAll() {
        return userService.findAll();
    }
    
    @GetMapping(value = "/bucarUsuario", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<User> findByEmail(@RequestParam String email) {
		User user = userService.findByEmail(email);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
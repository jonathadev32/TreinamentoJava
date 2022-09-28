package com.indracompany.treinamento.model.service;

import com.indracompany.treinamento.model.entity.User;
import com.indracompany.treinamento.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
		
	    @Autowired
		private UserRepository userRepository;
	    @Autowired
	    private PasswordEncoder passwordEncoder;  
	 
	
	    public User create(User user) {	      	       
	        String hashPwd = passwordEncoder.encode(user.getPassword());
	        user.setPassword(hashPwd);
	        return userRepository.save(user);
	    }

	
	    public Iterable<User> findAll() {
	        return userRepository.findAll();
	    }
	    
	    public User findByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }
	    
	    public User findByEmailAndPassword(String email, String password) {
	    	String hashPwd = passwordEncoder.encode(password);
	        return userRepository.findByEmailAndPassword(email, hashPwd);
	        
	    }
	    
	    

	   
	
	

}

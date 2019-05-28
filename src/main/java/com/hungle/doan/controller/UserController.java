package com.hungle.doan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hungle.doan.model.Blog;
import com.hungle.doan.model.User;
import com.hungle.doan.service.UserService;

@CrossOrigin("*")
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/api/user")
	private ResponseEntity<List<User>> list(){
		List<User> list = userService.list();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping("/api/user")
	public ResponseEntity<?> save(@RequestBody User user){
		userService.save(user);
		return ResponseEntity.ok().body("User have been saved");
	}
	
	@GetMapping("/api/user/{id}")
	public ResponseEntity<User> get(@PathVariable("id") long id){
		User user = userService.get(id);
		return ResponseEntity.ok().body(user);
	}
	
	@PutMapping("/api/user/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id,@RequestBody User user){
		userService.update(id, user);
		return ResponseEntity.ok().body("User has been updated");
	}
	
	@DeleteMapping("/api/user/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id){
		userService.delete(id);
		return ResponseEntity.ok().body("User has been deleted");
	}
	
	@GetMapping("/api/user/login/{userName}/{password}")
	public ResponseEntity<String> getList(@PathVariable("userName") String userName, @PathVariable("password") String password){
		return ResponseEntity.ok().body(userService.login(userName, password));
	}
}

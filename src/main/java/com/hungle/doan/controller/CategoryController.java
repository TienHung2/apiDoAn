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

import com.hungle.doan.model.Category;
import com.hungle.doan.service.CategoryService;

@CrossOrigin("*")
@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/api/category")
	public ResponseEntity<List<Category>> list(){
		List<Category> list = categoryService.list();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping("/api/category")
	public ResponseEntity<?> save(@RequestBody Category category){
		categoryService.save(category);
		return ResponseEntity.ok().body("Category have been saved");
	}
	
	@GetMapping("/api/category/{id}")
	public ResponseEntity<Category> get(@PathVariable("id") long id){
		Category category = categoryService.get(id);
		return ResponseEntity.ok().body(category);
	}
	
	@DeleteMapping("/api/category/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id){
		categoryService.delete(id);
		return ResponseEntity.ok("Category have been deleted");
	}
	
	@PutMapping("/api/category/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Category category){
		categoryService.update(id, category);
		return ResponseEntity.ok().body("Category have been updated");
	}
	
}

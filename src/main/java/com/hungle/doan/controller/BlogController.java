package com.hungle.doan.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.hungle.doan.service.BlogService;
import com.hungle.doan.common.CommonUtil;

@CrossOrigin("*")
@RestController
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@GetMapping("/api/blog")
	public ResponseEntity<List<Blog>> list() {
		List<Blog> list = blogService.list();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping("/api/blog")
	public ResponseEntity<?> save(@RequestBody Blog blog){
		
		
		Date dNow = new Date( );
		SimpleDateFormat ft = new SimpleDateFormat ("dd MMM yyyy HH:mm:ss z");
		
		String date = (ft.format(dNow)).toString();
		blog.setDate(date);
//		try {
//			new CommonUtil();
//			String img = CommonUtil.saveImage("", blog.getImg(),"");
//			blog.setImg(img);;
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		blogService.save(blog);
		return ResponseEntity.ok().body("Blog have been saved");
	}
	
	@GetMapping("/api/blog/{id}")
	public ResponseEntity<Blog> get(@PathVariable("id") long id){
		Blog blog = blogService.get(id);
		return ResponseEntity.ok().body(blog);
	}
	
	@PutMapping("/api/blog/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id,@RequestBody Blog blog){
		blogService.update(id, blog);
		return ResponseEntity.ok().body("Blog has been updated");
	}
	
	@DeleteMapping("/api/blog/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id){
		blogService.delete(id);
		return ResponseEntity.ok().body("Blog has been deleted");
	}
	
	@GetMapping("/api/blog/search/{idCategory}")
	public ResponseEntity<String> getList(@PathVariable("idCategory") String idCategory){
		return ResponseEntity.ok().body(blogService.getBlog(idCategory));
	}
	
}

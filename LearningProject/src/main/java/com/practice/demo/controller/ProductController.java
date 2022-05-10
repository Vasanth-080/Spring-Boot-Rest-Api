package com.practice.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.demo.entity.Product;
import com.practice.demo.exception.ResouseNotFoundException;
import com.practice.demo.repository.ProductRepository;

@RestController
@RequestMapping("/api")
@Controller
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	// save values in database
	@PostMapping
	public Product addProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}

	// get all values from database
	@GetMapping("/get")
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	// get values by id
	@GetMapping("/{id}")
	public Product getById(@PathVariable Long id) {
		return productRepository.findById(id).orElseThrow(() -> new ResouseNotFoundException(id));
	}

	// update values by id
	@PutMapping("/{id}")
	public Product updateProduct(@RequestBody Product product, @PathVariable Long id) {
		Product existingProduct = productRepository.findById(id).orElseThrow(() -> new ResouseNotFoundException(id));
		existingProduct.setName(product.getName());
		existingProduct.setDescription(product.getDescription());
		return productRepository.save(existingProduct);
	}

	// delete values by id
	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deleteProduct(@RequestBody Product product, @PathVariable Long id) {
		Product existingProduct = productRepository.findById(id).orElseThrow(() -> new ResouseNotFoundException(id));
		productRepository.delete(existingProduct);
		return ResponseEntity.ok().build();
	}

}

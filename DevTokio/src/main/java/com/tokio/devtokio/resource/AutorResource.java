package com.tokio.devtokio.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tokio.devtokio.model.Autor;
import com.tokio.devtokio.repository.AutorRepository;

@RestController
@RequestMapping("/autor")
public class AutorResource {

	@Autowired
	private AutorRepository autorRepository;

	
	@PostMapping
	public ResponseEntity<Autor> save(@Valid @RequestBody Autor autor, HttpServletResponse response){
		Autor autorSaved = this.autorRepository.save(autor);
		return ResponseEntity.status(HttpStatus.CREATED).body(autorSaved);
	}
	
	@GetMapping
	public List<Autor> findAll(){
		return this.autorRepository.findAll();
	}
}

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

import com.tokio.devtokio.model.Livro;
import com.tokio.devtokio.repository.LivroRepository;

@RestController
@RequestMapping("/livro")
public class LivroResource {
	
	@Autowired
	private LivroRepository livroRepository;
		
	@PostMapping
	public ResponseEntity<Livro> save(@Valid @RequestBody Livro livro, HttpServletResponse response){
		Livro livroSaved = this.livroRepository.save(livro);
		return ResponseEntity.status(HttpStatus.CREATED).body(livroSaved);
	}
	
	@GetMapping
	public List<Livro> getAllLivros(){
		return this.livroRepository.findAll();
	}

	
	
}

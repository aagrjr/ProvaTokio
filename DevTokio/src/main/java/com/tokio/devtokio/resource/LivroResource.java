package com.tokio.devtokio.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
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
	@Autowired 
	private JmsTemplate jmsTemplate;	
	@PostMapping
	public ResponseEntity<String> save(@Valid @RequestBody Livro livro, HttpServletResponse response){
		try {
			jmsTemplate.convertAndSend("queue.livro",livro);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("NOk");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Ok");
	}
	
	@GetMapping
	public List<Livro> getAllLivros(){
		return this.livroRepository.findAll();
	}

	@JmsListener(destination = "queue.livro")
	public void onReceiverQueue(Livro livro) {
		try {
			this.livroRepository.save(livro);
			System.out.println(livro.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

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

import com.tokio.devtokio.model.Autor;
import com.tokio.devtokio.repository.AutorRepository;

@RestController
@RequestMapping("/autor")
public class AutorResource {

	@Autowired
	private AutorRepository autorRepository;

	@Autowired 
	private JmsTemplate jmsTemplate;	
	
	@PostMapping
	public ResponseEntity<String> save(@Valid @RequestBody Autor autor, HttpServletResponse response){
		try {
			jmsTemplate.convertAndSend("queue.autor",autor);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("NOk");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Ok");
	}
	
	@GetMapping
	public List<Autor> findAll(){
		return this.autorRepository.findAll();
	}
	
	@JmsListener(destination = "queue.autor")
	public void onReceiverQueue(Autor autor) {
		try {
			this.autorRepository.save(autor);
			System.out.println(autor.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}

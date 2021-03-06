package org.generation.lojagames.controller;

import java.util.List;

import org.generation.lojagames.model.CategoriaModel;
import org.generation.lojagames.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping
	public ResponseEntity<List <CategoriaModel>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	@GetMapping("/{}id")
	public ResponseEntity<CategoriaModel> getByIdEntity(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok (resp)).orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/nome {nome}")
	public ResponseEntity<List<CategoriaModel>> getByTexto(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	@PostMapping
	public ResponseEntity<CategoriaModel> post(@RequestBody CategoriaModel versao){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(versao));
	}
	@PutMapping
	public ResponseEntity<CategoriaModel> put(@RequestBody CategoriaModel genero){
		return ResponseEntity.ok(repository.save(genero));
	}
	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id) {
		repository.deleteById(id);
	}
}
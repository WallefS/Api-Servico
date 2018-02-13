package com.wso.ordem.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wso.ordem.model.Ordem;
import com.wso.ordem.repository.Ordens;

@RestController
@RequestMapping("/ordens")
public class OrdensResource {

	@Autowired
	private Ordens ordens;

	@GetMapping() 
	public List<Ordem> listar() {
		return ordens.findAll();
	}

	@PostMapping()
	public Ordem adicionar(@RequestBody @Valid Ordem ordem) {
		return ordens.save(ordem);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleta(@PathVariable Long id) {
		Ordem ordem = ordens.findOne(id);
		
		if (ordem==null) {
			return ResponseEntity.notFound().build();
			
		}
		ordens.delete(ordem);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Ordem> atualizar(@PathVariable Long id,@Valid @RequestBody Ordem ordem){
		Ordem contem = ordens.findOne(id);
		
		if (contem == null) {
			return ResponseEntity.notFound().build();
		}
		BeanUtils.copyProperties(ordem, contem, "id");
		contem = ordens.save(contem);
		return ResponseEntity.ok(contem);
			
		}
	
	
}

package com.gestaopessoas.OnSafety.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestaopessoas.OnSafety.modelo.Pessoas;
import com.gestaopessoas.OnSafety.repository.PessoasRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/usuarios", description = "Persistir os dados, alterar, selecionar e excluir contas conforme regra de negocio proposta")
@RestController
@RequestMapping(value = "/usuarios")
public class PessoasControllerAPI {

	@Autowired
	private PessoasRepository repository;
	
	@GetMapping
	@ApiOperation(value = "Retorna lista de usuarios cadastradas no banco de dados")
	public List<Pessoas> lista(){
		return  (List<Pessoas>) repository.findAll();
	}
	
	@GetMapping("{id}")
	@ApiOperation(value = "Realiza a busca no banco de dados usuario fornecendo id")
	public Optional<Pessoas> buscarUsuarioUnicoId(@PathVariable(value = "id") int id) {
		return repository.findById(id);
	}
	
	
	@PostMapping
	@ApiOperation(value = "Realiza cadastro do usuario no banco de dados/sistema")
	public void salvar(@RequestBody Pessoas pessoa) {
		repository.save(pessoa);
	}
	

	
	@PutMapping
	@ApiOperation(value = "Altera os dados do usuario atraves do id fornecido")
	public void alterar (@RequestBody Pessoas pessoa) {
		
		if(pessoa.getId() > 0) {
			repository.save(pessoa);
		}
	}
	
	@DeleteMapping
	@ApiOperation(value = "Deleta  usuario do banco de dados/sistema")
	public void deletar (@RequestBody Pessoas pessoa) {
		Integer id = pessoa.getId();
		repository.deleteById(id);
	}
	
}

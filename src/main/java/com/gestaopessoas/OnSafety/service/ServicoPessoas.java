package com.gestaopessoas.OnSafety.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestaopessoas.OnSafety.exceptions.UsuarioPrincipalNotFoundExceptions;
import com.gestaopessoas.OnSafety.modelo.Pessoas;
import com.gestaopessoas.OnSafety.repository.PessoasRepository;


@Service
public class ServicoPessoas {
	@Autowired
	private PessoasRepository repository;
	
	public List <Pessoas>listarTodos(){
		
		return (List<Pessoas>) repository.findAll();
		
	}

	public void salvarUsuario(Pessoas usuario) {

		repository.save(usuario);
	}
	
	public Pessoas get(Integer id) throws UsuarioPrincipalNotFoundExceptions  {
		Optional<Pessoas> userId = repository.findById(id);
		if(userId.isPresent()) {
			return userId.get();

		}
		throw new UsuarioPrincipalNotFoundExceptions("Usuario com o ID solicitado nao encontrado " + userId);
	}
	
	public void delete(Integer id) throws UsuarioPrincipalNotFoundExceptions {
		Long contando = repository.countById(id);
		
		if(contando ==null || contando == 0) {
			throw new UsuarioPrincipalNotFoundExceptions("Usuario com o ID solicitado nao encontrado " + id);

		}
		repository.deleteById(id);
	}
}
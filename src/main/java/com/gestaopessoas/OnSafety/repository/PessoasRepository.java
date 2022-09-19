package com.gestaopessoas.OnSafety.repository;

import org.springframework.data.repository.CrudRepository;

import com.gestaopessoas.OnSafety.modelo.Pessoas;

	public interface PessoasRepository extends CrudRepository<Pessoas, Integer> {
		public Long countById(Integer id);
		}


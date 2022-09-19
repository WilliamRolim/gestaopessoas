package com.gestaopessoas.OnSafety.modelo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="pessoas")
public class Pessoas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 35, nullable = false)
	private String nome;
	
	@Column(nullable = false, unique = true, length = 14)
	@NotNull
	private String cpf;
	
	@Column(nullable = false ,name="data_nascimento")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;
	
	@Column(nullable = false, unique = true, length = 25)
	private String email;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void formatarData() {
	 LocalDate g = getDataNascimento();
         
         java.util.Date d = new java.util.Date();

         SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");

         System.out.println(f.format(g));
	}
	
	@Override
	public String toString() {
		return "Pessoas [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento
				+ ", email=" + email + "]";
	}
	
	
}

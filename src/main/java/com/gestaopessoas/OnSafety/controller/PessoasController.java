package com.gestaopessoas.OnSafety.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gestaopessoas.OnSafety.exceptions.UsuarioPrincipalNotFoundExceptions;
import com.gestaopessoas.OnSafety.modelo.Pessoas;
import com.gestaopessoas.OnSafety.repository.PessoasRepository;
import com.gestaopessoas.OnSafety.service.ServicoPessoas;



@Controller
public class PessoasController {

	
	@Autowired
	private PessoasRepository repository;
	
	@Autowired
	private ServicoPessoas service;
	
    @GetMapping("/index")
    public String mostreTodasPessoasCad(Model model) {
    	List<Pessoas> listaPessoas = service.listarTodos();
    	System.out.println(listaPessoas);

    	model.addAttribute("listaPessoas", listaPessoas);
    	model.addAttribute("paginaTitulo", "Adicionar Novo Usuario");
    	return "index";
    	
    }
	
    @GetMapping("/index/formulario_pessoas")
    public String adicionarNovoUsuario(Model model) {
    	model.addAttribute("pessoas", new Pessoas());

    	model.addAttribute("paginaTitulo", "Adicionar Novo Usuario");
    	return "formulario_pessoas";
    	
    }
    
    @PostMapping("/index/salvar")
    public String salvarUsuario(Pessoas pessoa, RedirectAttributes ra) {
    	service.salvarUsuario(pessoa);
    	
    	ra.addFlashAttribute("mensagem", "Usuario salvo com sucesso");
    	return "redirect:/index";
    }
    
    @GetMapping("/pessoas/editar/{id}")
    public String mostrarFormEditado(@PathVariable("id") Integer id, Model model,RedirectAttributes ra) {
    	try {
			Pessoas usuario = service.get(id);
			model.addAttribute("pessoas",usuario);
	    	model.addAttribute("paginaTitulo", "Editar Usuario (ID : " + id + ")");
	    	return "formulario_pessoas";

		} catch (UsuarioPrincipalNotFoundExceptions e) {
	    	ra.addFlashAttribute("mensagem", "Usuario salvo com sucesso");
	    	return "redirect:/index";
		}    	
    }
    
    @GetMapping("/pessoas/deletar/{id}")
    public String deletandoUsuario(@PathVariable("id") Integer id,RedirectAttributes ra) {
    	try {
			service.delete(id);
			ra.addFlashAttribute("mensagem", " O usuario foi deletado com sucesso id : " + id);
		} catch (UsuarioPrincipalNotFoundExceptions e) {
	    	ra.addFlashAttribute("mensagem", e.getMessage());
		}    
    	return "redirect:/index";

    }
    
    @GetMapping
	public List<Pessoas> lista(){
		return  (List<Pessoas>) repository.findAll();
	}
	
	@PostMapping
	public void salvar(@RequestBody Pessoas pessoa) {
		repository.save(pessoa);
	}
	
	@PutMapping
	public void alterar (@RequestBody Pessoas pessoa) {
		
		if(pessoa.getId() > 0) {
			repository.save(pessoa);
		}
	}
	
	@DeleteMapping
	public void deletar (@RequestBody Pessoas pessoa) {
		Integer id = pessoa.getId();
		repository.deleteById(id);
	}
    
}

package br.com.project.cobranca_spb.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.project.cobranca_spb.model.StatusTitulo;
import br.com.project.cobranca_spb.model.Titulo;
import br.com.project.cobranca_spb.repository.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired
	private Titulos titulos;
	
	private static final String CADASTRO_VIEW = "CadastroTitulo";
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW );
		mv.addObject(new Titulo());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Titulo titulo, Errors errors) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW );
		if(errors.hasErrors()) {
			return mv;
		}
		
		titulos.save(titulo);
		
		mv.addObject("mensagem", "Título cadastrado com sucesso!");
		return mv;
	}
	
	@RequestMapping
	public ModelAndView pesquisa() {
		List<Titulo> listaTitulos = titulos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTitulo");
		mv.addObject("titulos", listaTitulos);
		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable Long id) {
		Optional<Titulo> titulo = titulos.findById(id);
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW );
		mv.addObject("titulo", titulo.get());
		return mv;
	}
	
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}
}

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		titulos.save(titulo);
		attributes.addFlashAttribute("mensagem", "Título cadastrado com sucesso!");
		return "redirect:/titulos/novo";
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
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		titulos.deleteById(id);
		
		attributes.addFlashAttribute("mensagem", "Título excluído com sucesso!");
		return "redirect:/titulos";
	}
	
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}
}

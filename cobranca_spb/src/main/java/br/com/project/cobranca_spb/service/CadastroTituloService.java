package br.com.project.cobranca_spb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.project.cobranca_spb.model.Titulo;
import br.com.project.cobranca_spb.repository.Titulos;

@Service
public class CadastroTituloService {
	
	@Autowired
	private Titulos titulos;
	
	public void salvar(Titulo titulo) {
		try {
			titulos.save(titulo);
		} catch(DataIntegrityViolationException ex) {
			 throw new IllegalArgumentException("Formato Inválido");
		}
		
	}

	public void excluir(Long id) {
		titulos.deleteById(id);
	}
}

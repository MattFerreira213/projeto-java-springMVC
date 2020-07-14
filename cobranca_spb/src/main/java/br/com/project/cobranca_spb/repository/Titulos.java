package br.com.project.cobranca_spb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.project.cobranca_spb.model.Titulo;

public interface Titulos extends JpaRepository<Titulo, Long> {

}

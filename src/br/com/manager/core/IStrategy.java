package br.com.manager.core;

import br.com.manager.dominio.EntidadeDominio;

public interface IStrategy {
	public String processar(EntidadeDominio entidade);
}
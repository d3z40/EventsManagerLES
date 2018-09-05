package br.com.manager.controle.web.command;

import br.com.manager.core.aplicacao.Resultado;
import br.com.manager.dominio.EntidadeDominio;

public interface ICommand {
	public Resultado execute(EntidadeDominio entidade);
}
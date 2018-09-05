package br.com.manager.controle.web.command.impl;

import br.com.manager.core.aplicacao.Resultado;
import br.com.manager.dominio.EntidadeDominio;

public class LogarCommand extends AbstractCommand {

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		return fachada.logar(entidade);
	}

}

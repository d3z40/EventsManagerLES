package br.com.manager.controle.web.command.impl;

import br.com.manager.controle.web.command.ICommand;
import br.com.manager.core.IFachada;
import br.com.manager.core.impl.controle.Fachada;

public abstract class AbstractCommand implements ICommand {
	protected IFachada fachada = new Fachada();
}
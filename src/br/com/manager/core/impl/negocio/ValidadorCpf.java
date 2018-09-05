package br.com.manager.core.impl.negocio;

import br.com.manager.core.IStrategy;
import br.com.manager.dominio.EntidadeDominio;
import br.com.manager.dominio.Usuario;

public class ValidadorCpf implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if (entidade instanceof Usuario) {
			Usuario u = (Usuario) entidade;
			if (u.getCpf() == null)
				return "CPF não pode ser nulo!";
			if (u.getCpf().trim().equals(""))
				return "CPF deve ser preenchido!";
			if (u.getCpf().length() < 9)
				return "CPF deve conter 14 digitos!";
		} else {
			return "CPF não pode ser válidado, pois entidade não é um usuário!";
		}
		return null;
	}
}
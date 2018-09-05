package br.com.manager.core.impl.negocio;

import br.com.manager.core.IStrategy;
import br.com.manager.dominio.EntidadeDominio;
import br.com.manager.dominio.Usuario;

public class ValidadorDadosObrigatoriosLogin implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Usuario){
			Usuario usuario = (Usuario) entidade;
			
			String email = usuario.getEmail();
			String senha = usuario.getSenha();
			
			if(email == null || senha == null){
				return "E-mail ou Senha são de preenchimento obrigatório!";
			}
			
			if(email.trim().equals("") || senha.trim().equals(""))
				return "E-mail ou Senha são de preenchimento obrigatório!";
		} else {
			return "Deve ser registrado um usuário!";
		}
		return null;
	}
}
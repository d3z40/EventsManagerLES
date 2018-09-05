package br.com.manager.core.impl.negocio;

import br.com.manager.core.IStrategy;
import br.com.manager.dominio.EntidadeDominio;
import br.com.manager.dominio.Usuario;

public class ValidadorDadosObrigatoriosUsuario implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Usuario){
			Usuario usuario = (Usuario) entidade;
			
			String nome = usuario.getNome();
			String apelido = usuario.getApelido();
			String email = usuario.getEmail();
			String senha = usuario.getSenha();
			String cpf = usuario.getCpf();
			
			if(nome == null || apelido == null || email == null || senha == null || cpf == null){
				return "Nome, Apelido, E-mail, Senha e CPF são de preenchimento obrigatório!";
			}
			
			if(nome.trim().equals("") || apelido.trim().equals("") || email.trim().equals("") || senha.trim().equals("") || cpf.trim().equals(""))
				return "Nome, Apelido, E-mail, Senha e CPF são de preenchimento obrigatório!";
		} else {
			return "Deve ser registrado um usuário!";
		}
		return null;
	}
}
package br.com.manager.controle.web.vh.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.manager.controle.web.vh.IViewHelper;
import br.com.manager.core.aplicacao.Resultado;
import br.com.manager.dominio.EntidadeDominio;
import br.com.manager.dominio.Usuario;

public class UsuarioViewHelper implements IViewHelper {

	/** 
	 * TODO Descrição do Método
	 * @param request
	 * @param response
	 * @return
	 * @see br.com.manager.controle.web.vh.IViewHelper#getEntidade(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String nome = request.getParameter("txtNome");
		String apelido = request.getParameter("txtApelido");
		String email = request.getParameter("txtEmail");
		String senha = request.getParameter("txtSenha");
		String cpf = request.getParameter("txtCPF");
		String id = request.getParameter("txtId");
		
		Usuario u = new Usuario();
		u.setNome(nome);
		u.setApelido(apelido);
		u.setEmail(email);
		u.setSenha(senha);
		u.setCpf(cpf);
		
		if (id != null && !id.trim().equals("")) {
			u.setId(Integer.parseInt(id));
		}
		
		return u;
	}
	
	/** 
	 * TODO Descrição do Método
	 * @param request
	 * @param response
	 * @return
	 * @see br.com.manager.controle.web.vh.IViewHelper#setView(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	}
}
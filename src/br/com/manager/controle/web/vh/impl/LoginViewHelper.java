package br.com.manager.controle.web.vh.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.manager.controle.web.vh.IViewHelper;
import br.com.manager.core.aplicacao.Resultado;
import br.com.manager.dominio.EntidadeDominio;
import br.com.manager.dominio.Usuario;

public class LoginViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String email = request.getParameter("txtEmail");
		String senha = request.getParameter("txtSenha");
		
		Usuario u = new Usuario();
		u.setEmail(email);
		u.setSenha(senha);
				
		return u;
	}
	
	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if (resultado.getMsg() != null) {
			request.setAttribute("msg", resultado.getMsg());
			
			request.getRequestDispatcher("msg-erro.jsp").forward(request, response);
		} else if (resultado.getEntidades().isEmpty()) {
			request.setAttribute("email", request.getParameter("txtEmail"));
			request.setAttribute("senha", request.getParameter("txtSenha"));
			
			request.getRequestDispatcher("nao-logado.jsp").forward(request, response);
		} else {
			Usuario usuario = (Usuario) resultado.getEntidades().get(0);
			
			request.setAttribute("nome", usuario.getNome());
			request.getRequestDispatcher("loagado.jsp").forward(request, response);
		}
	}
}
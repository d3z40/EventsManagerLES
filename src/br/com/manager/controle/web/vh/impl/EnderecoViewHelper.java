package br.com.manager.controle.web.vh.impl;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.manager.controle.web.vh.IViewHelper;
import br.com.manager.core.aplicacao.Resultado;
import br.com.manager.dominio.Endereco;
import br.com.manager.dominio.EntidadeDominio;

public class EnderecoViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		int idUsuario = Integer.parseInt(request.getParameter("txtIdUsuario"));
		String descricao = request.getParameter("txtDescricao");
		String cep = request.getParameter("txtCEP");
		String logradouro = request.getParameter("txtLogradouro");
		int numero = Integer.parseInt(request.getParameter("txtNumero"));
		String bairro = request.getParameter("txtBairro");
		String cidade = request.getParameter("txtCidade");
		String estado = request.getParameter("txtEstado");
		String id = request.getParameter("txtId");
		
		Endereco e = new Endereco();
		e.setDescricao(descricao);
		e.setIdUsuario(idUsuario);
		e.setCep(cep);
		e.setLogradouro(logradouro);
		e.setNumero(numero);
		e.setBairro(bairro);
		e.setCidade(cidade);
		e.setEstado(estado);
		e.setDtCadastro(new Date());
		
		if (id != null && !id.trim().equals("")) {
			e.setId(Integer.parseInt(id));
		}
		
		return e;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if (resultado.getMsg() != null) {
			request.setAttribute("msg", resultado.getMsg());
			
			request.getRequestDispatcher("msg-erro.jsp").forward(request, response);
		} else  {
			request.getRequestDispatcher("login.html").forward(request, response);
		}
	}
}
package br.com.manager.controle.web.vh;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.manager.core.aplicacao.Resultado;
import br.com.manager.dominio.EntidadeDominio;

public interface IViewHelper {
	
	public EntidadeDominio getEntidade(HttpServletRequest request);
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException;
}
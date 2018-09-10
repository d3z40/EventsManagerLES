package br.com.manager.core.impl.negocio;

import br.com.manager.core.IStrategy;
import br.com.manager.dominio.Endereco;
import br.com.manager.dominio.EntidadeDominio;

public class ValidadorDadosObrigatoriosEndereco implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Endereco){
			Endereco endereco = (Endereco) entidade;
			
			int idUsuario = endereco.getIdUsuario();
			String logradouro = endereco.getLogradouro();
			int numero = endereco.getNumero();
			String bairro = endereco.getBairro();
			String cidade = endereco.getCidade();
			String estado = endereco.getEstado();
			String cep = endereco.getCep();
			
			if(idUsuario <= 0 || logradouro == null || numero <= 0 || bairro == null || cidade == null|| estado == null || cep == null)
				return "idUsuário, número, bairro, cidade, estado e CEP são de preenchimento obrigatório!";
			
			if(logradouro.trim().equals("") || bairro.trim().equals("") || cidade.trim().equals("") || estado.trim().equals("") || cep.trim().equals(""))
				return "idUsuário, número, bairro, cidade, estado e CEP são de preenchimento obrigatório!";
		} else {
			return "Deve ser registrado um endereço!";
		}
		return null;
	}
}
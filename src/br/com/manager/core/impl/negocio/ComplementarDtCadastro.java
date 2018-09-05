package br.com.manager.core.impl.negocio;

import java.util.Date;

import br.com.manager.core.IStrategy;
import br.com.manager.dominio.EntidadeDominio;

public class ComplementarDtCadastro implements IStrategy {

	/* (non-Javadoc)
	 * @see br.com.manager.core.IStrategy#processar(br.com.manager.dominio.EntidadeDominio)
	 */
	@Override
	public String processar(EntidadeDominio entidade) {
		if(extracted(entidade) != null){
			Date data = new Date();		
			extracted(entidade).setDtCadastro(data);
		} else {
			return "Entidade: " + extracted(entidade).getClass().getCanonicalName() + " nula!";
		}
		
		return null;
	}

	private EntidadeDominio extracted(EntidadeDominio entidade) {
		return entidade;
	}
}
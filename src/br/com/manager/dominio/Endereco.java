package br.com.manager.dominio;

public class Endereco extends EntidadeDominio {
	private String descricao;
	private int idUsuario;
	private String logradouro;
	private int numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the idUsuario
	 */
	public int getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the logradouro
	 */
	public String getLogradouro() {
		return logradouro;
	}
	
	/**
	 * @param logradouro the logradouro to set
	 */
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}
	
	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	/**
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}
	
	/**
	 * @param bairro the bairro to set
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	/**
	 * @return the cidade
	 */
	public String getCidade() {
		return cidade;
	}
	
	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}
	
	/**
	 * @param cep the cep to set
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}
}
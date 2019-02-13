package br.com.manager.core.impl.controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.manager.core.IDAO;
import br.com.manager.core.IFachada;
import br.com.manager.core.IStrategy;
import br.com.manager.core.aplicacao.Resultado;
import br.com.manager.core.impl.dao.EnderecoDAO;
import br.com.manager.core.impl.dao.UsuarioDAO;
import br.com.manager.core.impl.negocio.ComplementarDtCadastro;
import br.com.manager.core.impl.negocio.ValidadorCpf;
import br.com.manager.core.impl.negocio.ValidadorDadosObrigatoriosEndereco;
import br.com.manager.core.impl.negocio.ValidadorDadosObrigatoriosLogin;
import br.com.manager.core.impl.negocio.ValidadorDadosObrigatoriosUsuario;
import br.com.manager.dominio.Endereco;
import br.com.manager.dominio.EntidadeDominio;
import br.com.manager.dominio.Usuario;

public class Fachada implements IFachada {

	/**
	 * Mapa de DAOS, será indexado pelo nome da entidade O valor é uma instância do
	 * DAO para uma dada entidade;
	 */
	private Map<String, IDAO> daos;

	/**
	 * Mapa para conter as regras de negócio de todas operações por entidade; O
	 * valor é um mapa que de regras de negócio indexado pela operação
	 */
	private Map<String, Map<String, List<IStrategy>>> rns;

	private Resultado resultado;

	public Fachada() {
		/* Intânciando o Map de DAOS */
		daos = new HashMap<String, IDAO>();
		/* Intânciando o Map de Regras de Negócio */
		rns = new HashMap<String, Map<String, List<IStrategy>>>();

		/* Criando instâncias dos DAOs a serem utilizados */
		UsuarioDAO usuDAO = new UsuarioDAO();
		EnderecoDAO endDAO = new EnderecoDAO();
		
		/* Adicionando cada dao no MAP indexando pelo nome da classe */
		daos.put(Usuario.class.getName(), usuDAO);
		daos.put(Endereco.class.getName(), endDAO);
		
		/* Criando instâncias de regras de negócio a serem utilizados */
		ValidadorDadosObrigatoriosUsuario vrDadosObrigatoriosUsuario = new ValidadorDadosObrigatoriosUsuario();
		ComplementarDtCadastro cDtCadastro = new ComplementarDtCadastro();
		ValidadorCpf vCpf = new ValidadorCpf();
		
		/*
		 * Criando uma lista para conter as regras de negócio de fornencedor quando a
		 * operação for salvar
		 */
		List<IStrategy> rnsSalvarUsuario = new ArrayList<IStrategy>();
		/* Adicionando as regras a serem utilizadas na operação salvar do fornecedor */
		rnsSalvarUsuario.add(vrDadosObrigatoriosUsuario);
		rnsSalvarUsuario.add(vCpf);
		rnsSalvarUsuario.add(cDtCadastro);
		
		/*
		 * Cria o mapa que poderá conter todas as listas de regras de negócio específica
		 * por operação do fornecedor
		 */
		Map<String, List<IStrategy>> rnsUsuario = new HashMap<String, List<IStrategy>>();
		/*
		 * Adiciona a listra de regras na operação salvar no mapa do fornecedor (lista
		 * criada na linha 70)
		 */
		rnsUsuario.put("SALVAR", rnsSalvarUsuario);
		
		/* Criando instâncias de regras de negócio a serem utilizados */
		ValidadorDadosObrigatoriosLogin vrDadosObrigatoriosLogin = new ValidadorDadosObrigatoriosLogin();
		
		/*
		 * Criando uma lista para conter as regras de negócio de fornencedor quando a
		 * operação for salvar
		 */
		List<IStrategy> rnsLogin = new ArrayList<IStrategy>();
		/* Adicionando as regras a serem utilizadas na operação salvar do fornecedor */
		rnsLogin.add(vrDadosObrigatoriosLogin);
		
		/*
		 * Cria o mapa que poderá conter todas as listas de regras de negócio específica
		 * por operação do fornecedor
		 */
		Map<String, List<IStrategy>> rnsMapLogin = new HashMap<String, List<IStrategy>>();
		/*
		 * Adiciona a listra de regras na operação salvar no mapa do fornecedor (lista
		 * criada na linha 70)
		 */
		rnsMapLogin.put("LOGAR", rnsLogin);

		/* Criando instâncias de regras de negócio a serem utilizados */
		ValidadorDadosObrigatoriosEndereco vrDadosObrigatoriosEndereco = new ValidadorDadosObrigatoriosEndereco();
		
		/*
		 * Criando uma lista para conter as regras de negócio de fornencedor quando a
		 * operação for salvar
		 */
		List<IStrategy> rnsSalvarEndereco = new ArrayList<IStrategy>();
		/* Adicionando as regras a serem utilizadas na operação salvar do fornecedor */
		rnsSalvarEndereco.add(vrDadosObrigatoriosEndereco);
		rnsSalvarEndereco.add(cDtCadastro);
		
		/*
		 * Cria o mapa que poderá conter todas as listas de regras de negócio específica
		 * por operação do fornecedor
		 */
		Map<String, List<IStrategy>> rnsEndereco = new HashMap<String, List<IStrategy>>();
		/*
		 * Adiciona a listra de regras na operação salvar no mapa do fornecedor (lista
		 * criada na linha 70)
		 */
		rnsEndereco.put("SALVAR", rnsSalvarEndereco);
		
		/*
		 * Adiciona o mapa(criado na linha 79) com as regras indexadas pelas operações
		 * no mapa geral indexado pelo nome da entidade
		 */
		rns.put(Usuario.class.getName(), rnsUsuario);
//		rns.put(Usuario.class.getName(), rnsMapLogin);
		rns.put(Endereco.class.getName(), rnsEndereco);
	}
	
	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();
		
		String msg = executarRegras(entidade, "SALVAR");
		
		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			try {
				dao.salvar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");
			}
		} else {
			resultado.setMsg(msg);
		}
		return resultado;
	}
	
	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "ALTERAR");

		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			try {
				dao.alterar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");
			}
		} else {
			resultado.setMsg(msg);
		}
		return resultado;
	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "EXCLUIR");

		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			try {
				dao.excluir(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");
			}
		} else {
			resultado.setMsg(msg);
		}
		return resultado;
	}

	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "CONSULTAR");
		
		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			try {
				resultado.setEntidades(dao.consultar(entidade));
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar a consulta!");
			}
		} else {
			resultado.setMsg(msg);
		}
		return resultado;
	}

	@Override
	public Resultado visualizar(EntidadeDominio entidade) {
		resultado = new Resultado();
		resultado.setEntidades(new ArrayList<EntidadeDominio>(1));
		resultado.getEntidades().add(entidade);
		return resultado;
	}
	
	private String executarRegras(EntidadeDominio entidade, String operacao) {
		String nmClasse = entidade.getClass().getName();
		StringBuilder msg = new StringBuilder();
		
		Map<String, List<IStrategy>> regrasOperacao = rns.get(nmClasse);
		
		if (regrasOperacao != null) {
			List<IStrategy> regras = regrasOperacao.get(operacao);
			
			if (regras != null) {
				for (IStrategy s : regras) {
					String m = s.processar(entidade);

					if (m != null) {
						msg.append(m);
						msg.append("\n");
					}
				}
			}
		}
		if (msg.length() > 0)
			return msg.toString();
		else
			return null;
	}

	@Override
	public Resultado logar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();
		
		String msg = executarRegras(entidade, "LOGAR");
		
		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			try {
				resultado.setEntidades(dao.logar(entidade));
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o login!");
			}
		} else {
			resultado.setMsg(msg);
		}
		return resultado;
	}
}
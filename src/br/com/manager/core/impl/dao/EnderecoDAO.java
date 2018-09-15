package br.com.manager.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import br.com.manager.dominio.Endereco;
import br.com.manager.dominio.EntidadeDominio;

public class EnderecoDAO extends AbstractJdbcDAO {

	public EnderecoDAO() {
		super("tb_endereco", "id");
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		PreparedStatement pst = null;
		Endereco endereco = (Endereco) entidade;
		
		try {
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_endereco (idUsuario, logradouro, numero, bairro, cidade, estado, cep, dtCadastro) ");
			sql.append("VALUES (?,?,?,?,?,?,?,?)");

			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, endereco.getIdUsuario());
			pst.setString(2, endereco.getLogradouro());
			pst.setInt(3, endereco.getNumero());
			pst.setString(4, endereco.getBairro());
			pst.setString(5, endereco.getCidade());
			pst.setString(6, endereco.getEstado());
			pst.setString(7, endereco.getCep());
			Timestamp time = new Timestamp(endereco.getDtCadastro().getTime());
			pst.setTimestamp(8, time);
			
			pst.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		return null;
	}

	@Override
	public List<EntidadeDominio> logar(EntidadeDominio entidade) throws SQLException {
		return null;
	}
}
package br.com.manager.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import br.com.manager.dominio.Endereco;
import br.com.manager.dominio.EntidadeDominio;
import br.com.manager.dominio.Usuario;

public class UsuarioDAO extends AbstractJdbcDAO {

	public UsuarioDAO() {
		super("tb_usuario", "id_usu");
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		PreparedStatement pst = null;
		Usuario usuario = (Usuario) entidade;
		Endereco end = usuario.getEndereco();
		
		try {
			connection.setAutoCommit(false);
			UsuarioDAO usuDAO = new UsuarioDAO();
			usuDAO.connection = connection;
			usuDAO.ctrlTransaction = false;
			usuDAO.salvar(end);

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_usuario(rzsocial, cnpj, end_id, ");
			sql.append("dt_cadastro) VALUES (?,?,?,?)");

			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, usuario.getNome());
			pst.setInt(3, end.getId());
			Timestamp time = new Timestamp(usuario.getDtCadastro().getTime());
			pst.setTimestamp(4, time);
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
}
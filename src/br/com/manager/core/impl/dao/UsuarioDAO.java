package br.com.manager.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.manager.dominio.EntidadeDominio;
import br.com.manager.dominio.Usuario;

public class UsuarioDAO extends AbstractJdbcDAO {

	public UsuarioDAO() {
		super("tb_usuario", "id");
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		PreparedStatement pst = null;
		Usuario usuario = (Usuario) entidade;
		
		try {
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_usuario (nome, apelido, email, senha, cpf, dtCadastro) ");
			sql.append("VALUES (?,?,?,?,?,?)");

			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getApelido());
			pst.setString(3, usuario.getEmail());
			pst.setString(4, usuario.getSenha());
			pst.setString(5, usuario.getCpf());
			Timestamp time = new Timestamp(usuario.getDtCadastro().getTime());
			pst.setTimestamp(6, time);
			
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
		openConnection();
		PreparedStatement pst = null;
		Usuario usuarioBD;
		List<EntidadeDominio> usuarios = new ArrayList<EntidadeDominio>();
		
		Usuario usuario = (Usuario) entidade;
		
		try {			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM tb_usuario WHERE BINARY email = ? AND BINARY senha = ?");
			
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, usuario.getEmail());
			pst.setString(2, usuario.getSenha());
			
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				usuarioBD = new Usuario();
				usuarioBD.setNome(rs.getString("nome"));
				usuarioBD.setApelido(rs.getString("apelido"));
				usuarioBD.setEmail(rs.getString("email"));
				usuarioBD.setSenha(rs.getString("senha"));
				usuarioBD.setCpf(rs.getString("cpf"));
				usuarioBD.setDtCadastro(rs.getDate("dtCadastro"));
				
				usuarios.add(usuarioBD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return usuarios;
	}
}
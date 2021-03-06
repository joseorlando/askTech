package asktechforum.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import asktechforum.util.ConnectionUtil;
import asktechforum.util.UsuarioUtil;
import asktechforum.dominio.Usuario;

public class UsuarioDAO {
	private Connection connection = null;
    UsuarioUtil usuarioUtil;
	
	public UsuarioDAO(){
		this.usuarioUtil = new UsuarioUtil();
	}
	
	public void alterarUsuario(Usuario usuario) throws SQLException {
		PreparedStatement preparedStatement = null;
		try {
			this.connection = ConnectionUtil.getConnection();
            preparedStatement = this.connection
                    .prepareStatement("update usuario set nome=?,dt_nasc=?,admin=?,email=?,localizacao=?,senha=? where idUsuario=?");

            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setDate(2, usuario.getDataNascimento());
            preparedStatement.setBoolean(3, usuario.isAdmin());
            preparedStatement.setString(4, usuario.getEmail());
            preparedStatement.setString(5, usuario.getLocalizacao());
            preparedStatement.setString(6, usuario.getSenha());
            preparedStatement.setInt(7, usuario.getIdUsuario());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            this.connection.close();
        }
    }
	
	public void alterarUsuarioAdmin(Usuario usuario) throws SQLException {
		PreparedStatement preparedStatement = null;
		try {
			this.connection = ConnectionUtil.getConnection();
            preparedStatement = this.connection
                    .prepareStatement("update usuario set admin=? where idUsuario=?");

            preparedStatement.setBoolean(1, usuario.isAdmin());
            preparedStatement.setInt(2, usuario.getIdUsuario());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            this.connection.close();
        }
    }
	
	public void adicionarUsuario(Usuario usuario) throws SQLException {
		PreparedStatement preparedStatement = null;
		try {
			this.connection = ConnectionUtil.getConnection();
            preparedStatement = this.connection
                    .prepareStatement("insert into usuario(nome,dt_nasc,email,localizacao,senha,admin) values ( ?, ?, ?, ?, ?, ? )");
            
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setDate(2, usuario.getDataNascimento());
            preparedStatement.setString(3, usuario.getEmail());
            preparedStatement.setString(4, usuario.getLocalizacao());
            preparedStatement.setString(5, usuario.getSenha());
            preparedStatement.setBoolean(6, usuario.isAdmin());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            this.connection.close();
        }
    }
	
	public void deletarUsuario(String email) throws SQLException {
		PreparedStatement preparedStatement = null;
        try {
    		this.connection = ConnectionUtil.getConnection();
            preparedStatement = this.connection
                    .prepareStatement("delete from usuario where email=?");
            
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            this.connection.close();
        }
    }
	
	public void deletarUsuarioPorId(int idUsuario) throws SQLException {
		PreparedStatement preparedStatement = null;
        try {
    		this.connection = ConnectionUtil.getConnection();
            preparedStatement = this.connection
                    .prepareStatement("delete from usuario where idUsuario=?");
            
            preparedStatement.setInt(1, idUsuario);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            this.connection.close();
        }
    }
	
	public Usuario consultarUsuarioPorEmail_Senha(String email,String senha) 
			throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Usuario usuario = new Usuario();
		try {
			this.connection = ConnectionUtil.getConnection();
			preparedStatement = this.connection
					.prepareStatement("select * from usuario where email=? and senha=?");
			preparedStatement.setString(1,email);
			preparedStatement.setString(2,senha);
			rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setDataNascimento(rs.getDate("dt_nasc"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLocalizacao(rs.getString("localizacao"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setAdmin(rs.getBoolean("admin"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            preparedStatement.close();
            rs.close();
            this.connection.close();
        }
		
		return usuario;
	}
	
	public Usuario consultarUsuarioPorId(int idUsuario) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Usuario usuario = new Usuario();
		try {
			this.connection = ConnectionUtil.getConnection();
			preparedStatement = this.connection
					.prepareStatement("select * from usuario where idUsuario=?");
			
			preparedStatement.setInt(1, idUsuario);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setDataNascimento(rs.getDate("dt_nasc"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLocalizacao(rs.getString("localizacao"));
				usuario.setAdmin(rs.getBoolean("admin"));
				usuario.setSenha(rs.getString("senha"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            preparedStatement.close();
            rs.close();
            this.connection.close();
        }
		
		return usuario;
	}
		
	public Usuario consultarUsuarioPorEmail(String email) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Usuario usuario = new Usuario();
		try {
			this.connection = ConnectionUtil.getConnection();
			preparedStatement = this.connection
					.prepareStatement("select * from usuario where email=?");
			
			preparedStatement.setString(1, email);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setDataNascimento(rs.getDate("dt_nasc"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLocalizacao(rs.getString("localizacao"));
				usuario.setAdmin(rs.getBoolean("admin"));
				usuario.setSenha(rs.getString("senha"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            preparedStatement.close();
            rs.close();
            this.connection.close();
        }
		
		return usuario;
	}	
	
	public List<Usuario> consultarUsuarioPorNome(String nome) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			this.connection = ConnectionUtil.getConnection();
			preparedStatement = this.connection
					.prepareStatement("select * from usuario where nome=?");
			
			preparedStatement.setString(1, nome);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setDataNascimento(rs.getDate("dt_nasc"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLocalizacao(rs.getString("localizacao"));
				usuario.setAdmin(rs.getBoolean("admin"));
				usuario.setSenha(rs.getString("senha"));
				usuarios.add(usuario);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            preparedStatement.close();
            rs.close();
            this.connection.close();
        }
		
		return usuarios;
	}	
	
	public List<Usuario> consultarTodosUsuarios() throws SQLException {
		Statement statement = null;
		ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
    		this.connection = ConnectionUtil.getConnection();
            statement = this.connection.createStatement();
            rs = statement.executeQuery("select * from usuario;");
            
            while(rs.next()) {
            	Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setDataNascimento(rs.getDate("dt_nasc"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLocalizacao(rs.getString("localizacao"));
				usuario.setAdmin(rs.getBoolean("admin"));
				usuario.setSenha(rs.getString("senha"));
            	usuarios.add(usuario);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	statement.close();
            rs.close();
            this.connection.close();
        }
        
        return usuarios;
    }
	
}
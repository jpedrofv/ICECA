package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.login;
import beans.obreiro;


public class LoginDAO {
	
	public int codigoRetorno;
	String status;
	public static int Login_Id_Login;
	public static String Login_Usuario;
	public static String Login_Senha;
	public static String Login_Acesso;
	public static String Login_Status;
	
	private static final  String CONSULTA_LOGIN =
			"select * from Login where usuario like ? order by usuario";
	
	private static final String PESQUISAR_CARGO =
			"select id_cargo from Obreiros where id_cargo = ?";
	
	   public boolean autenticando (String login, String senha) throws SQLException{  
	    	// Manda como parametro para ele duas variaveis para ele procurar no banco de dados!  
			Connection conn = Conexao.conectar();
			PreparedStatement statement = null;
			ResultSet result = null;
	    	  
	    	            boolean autenticado = false;  
	    	            String sql;  
	    	            sql = "select usuario, senha, status from dbo.Login where usuario = ? and senha = ?";  
	    	            PreparedStatement ps;  
	    	  
	    	            ps = conn.prepareStatement(sql);  
	    	            ps.setString(1, login);  
	    	            ps.setString(2, senha);  
	    	  
	    	            ResultSet rs;  
	    	            rs = ps.executeQuery();  
	        
	    	            if (rs.next()) {  
	    	                String loginBanco = rs.getString("usuario");  
	    	                String senhaBanco = rs.getString("senha");
	    	                status = rs.getString("status");
	    	                
	    	            autenticado = true;
	    	            
	    	            }
	    	            
	    	            ps.close();  
	    	            
	    	            
	    	            return autenticado;
	      
	}
			
	
	public boolean consultar(String objLogin2) throws SQLException{		
		Connection conn = Conexao.conectar();
		PreparedStatement statement = null;
		ResultSet result = null;
		//List<login> listaLogin = new ArrayList<login>();
		try {
			if(objLogin2.equals("")){
				statement = conn.prepareStatement(objLogin2);
			}else{
				statement = conn.prepareStatement(CONSULTA_LOGIN);
				statement.setString(1, "%"+objLogin2+"%");
			}
			result = statement.executeQuery();
			while (result.next()) {
				//login objLogin = new login();
				Login_Id_Login = result.getInt(1);
				Login_Usuario = result.getString(2);
				Login_Senha = result.getString(3);
				Login_Acesso = result.getString(4);
				Login_Status = result.getString(5);
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			Conexao.desconectar(conn, statement, result);
		}
		return true;
	}
	
	
	


}


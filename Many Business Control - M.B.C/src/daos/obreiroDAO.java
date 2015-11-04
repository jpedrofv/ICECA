package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class obreiroDAO {
	
	public static String Consulta = "select nome, id_cargo, id_login, status from Obreiros where id_login = ?";
	public static String Login_Nome;
	public static int Login_Id_Cargo;
	public static int Login_Id_Login;
	public static String Login_Status;
	
	public boolean consultarLogin(int id_Login2) throws SQLException{		
		Connection conn = Conexao.conectar();
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			if(id_Login2<0){
				JOptionPane.showMessageDialog(null, "Login inválido!!!");
			}else{
				
				statement = conn.prepareStatement(Consulta);
				statement.setInt(1, id_Login2);
				//JOptionPane.showMessageDialog(null, "Status: " +super.getStatus());
			}
			result = statement.executeQuery();
			while (result.next()) {
				//Funcionario objFunc = new Funcionario();
				Login_Nome = result.getString(1);
				Login_Id_Cargo = result.getInt(2);
				Login_Id_Login = result.getInt(3);
				Login_Status = result.getString(4);
			}
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			Conexao.desconectar(conn, statement, result);
		}
		return true;		
	}

}

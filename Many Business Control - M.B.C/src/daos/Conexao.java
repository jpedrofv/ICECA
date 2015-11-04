package daos;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Conexao {
 static Connection con;
 
 public static void main(String[]args){
  Conexao frame = new Conexao();
  frame.conectar();
 
 }
  
 public static Connection conectar() {
  try{   
   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
   con = DriverManager.getConnection(
     "jdbc:sqlserver://localhost:1433;" +
     "database = MBC;" +
     "user=sa;" +
     "password=12345678");
  	
   //JOptionPane.showMessageDialog(null, "Conectado!\n");
  }catch(SQLException e){
   JOptionPane.showMessageDialog(null, "Problemas na conexão!!!!\n" + e.toString());
  }catch(ClassNotFoundException e){
   JOptionPane.showMessageDialog(null, "Driver NÃO ENCONTRADO!!!!\n" + e.toString());
  }
return con;
 } 
 public static void desconectar(Connection conn, Statement statement, ResultSet result) {
		try {
			if (conn != null) {
				conn.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (result != null) {
				result.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


 
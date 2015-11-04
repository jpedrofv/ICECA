package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import daos.LoginDAO;
import daos.obreiroDAO;
import beans.login;
import beans.obreiro;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.List;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;
	String Nivel;
	String Status;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("M.B.C - (ICECA)Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 330);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setForeground(Color.ORANGE);
		lblUsurio.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblUsurio.setBounds(20, 218, 67, 19);
		contentPane.add(lblUsurio);
		
		txtUsuario = new JTextField();
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(txtUsuario.getHeight()!=0){
				String x =	txtUsuario.getText().toUpperCase();
				txtUsuario.setText(x);
				}
			}
		});
		txtUsuario.setBounds(97, 219, 152, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(Color.ORANGE);
		lblSenha.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		lblSenha.setBounds(280, 220, 61, 14);
		contentPane.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(351, 219, 173, 20);
		contentPane.add(txtSenha);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Login.class.getResource("/image/ICECA - Login.jpg")));
		label.setBounds(20, 0, 550, 208);
		contentPane.add(label);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login obj = new login();
				LoginDAO objDAO = new LoginDAO();
				boolean result;
				
				
				String usuario = txtUsuario.getText();
				String senha = txtSenha.getText();
				
				try {
				result = objDAO.autenticando(usuario,senha);
				 objDAO.consultar(usuario);
				 
				 
				 if(result==true){
					 dispose();
					 JOptionPane.showMessageDialog(null, "Login realizado com sucesso!!!");
					 Inicial ti = new Inicial();
					 ti.setVisible(true);
				 }else{
					 JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!!!");
				 }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
				
				
				
				
			}
		});
		btnEntrar.setBackground(Color.ORANGE);
		btnEntrar.setForeground(Color.WHITE);
		btnEntrar.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		btnEntrar.setBounds(85, 262, 200, 19);
		contentPane.add(btnEntrar);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = JOptionPane.showConfirmDialog(null, "Deseja encerrar o Sistema?");
				if(x == JOptionPane.OK_OPTION){
				dispose();
				}else{
					
				}
			}
		});
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		btnNewButton.setBounds(338, 262, 200, 19);
		contentPane.add(btnNewButton);
	}
	
	
}

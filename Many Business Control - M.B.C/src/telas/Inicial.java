package telas;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;

import java.awt.Font;

import javax.swing.JLabel;

import util.HoraData;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

import beans.obreiro;
import daos.LoginDAO;
import daos.obreiroDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Calendar;

public class Inicial extends JFrame {

	private JPanel contentPane;
	JLabel Data;
	JLabel Horas;
	JLabel Usuario;
	JLabel Cargo;
	HoraData HD = new HoraData();
	JToggleButton btn1;
	JToggleButton btn2;
	JToggleButton btn3;
	JToggleButton btn4;
	JToggleButton btn5;
	JToggleButton btnObreiros;
	JToggleButton btnMembros;
	JToggleButton btnDizimos;
	JToggleButton btnFinanceiro;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicial frame = new Inicial();
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
	public Inicial() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				try {
					User_Sistema(LoginDAO.Login_Id_Login);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				HD.start(Data);
				HD.start1(Horas);
				
				fechamento(true);
			}
		});
		setUndecorated(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0,screenSize.width, screenSize.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnSistema = new JPanel();
		pnSistema.setBorder(new TitledBorder(new LineBorder(new Color(255, 200, 0)), "Status do Sistema", TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		pnSistema.setBackground(Color.WHITE);
		pnSistema.setBounds(10, 661, 1346, 96);
		contentPane.add(pnSistema);
		pnSistema.setLayout(null);
		
		JLabel lblUsurioLogado = new JLabel("Usu\u00E1rio Logado:");
		lblUsurioLogado.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
		lblUsurioLogado.setBounds(10, 24, 136, 14);
		pnSistema.add(lblUsurioLogado);
		
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
		lblCargo.setBounds(10, 49, 77, 14);
		pnSistema.add(lblCargo);
		
		Usuario = new JLabel("");
		Usuario.setBounds(192, 24, 257, 14);
		pnSistema.add(Usuario);
		
		Cargo = new JLabel("");
		Cargo.setBounds(192, 49, 257, 14);
		pnSistema.add(Cargo);
		
		JLabel lblHorrioAtual = new JLabel("Hor\u00E1rio Atual:");
		lblHorrioAtual.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
		lblHorrioAtual.setBounds(459, 24, 130, 14);
		pnSistema.add(lblHorrioAtual);
		
		JLabel lblDataAtual = new JLabel("Data Atual:");
		lblDataAtual.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
		lblDataAtual.setBounds(459, 49, 114, 14);
		pnSistema.add(lblDataAtual);
		
		Horas = new JLabel("");
		Horas.setForeground(Color.ORANGE);
		Horas.setFont(new Font("Comic Sans MS", Font.ITALIC, 12));
		Horas.setBounds(599, 24, 147, 14);
		pnSistema.add(Horas);
		
		Data = new JLabel("");
		Data.setForeground(Color.ORANGE);
		Data.setFont(new Font("Comic Sans MS", Font.ITALIC, 12));
		Data.setBounds(583, 49, 147, 14);
		pnSistema.add(Data);
		
		JButton Logoff = new JButton("");
		Logoff.setIcon(new ImageIcon(Inicial.class.getResource("/image/Logoff.jpg")));
		Logoff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int x = JOptionPane.showConfirmDialog(null, "Deseja fazer o Logoff?");
				if(x == JOptionPane.OK_OPTION){
				dispose();
				Login tl = new Login();
				tl.setVisible(true);
				}else{
					
				}
			}
		});
		Logoff.setBounds(1166, 38, 69, 47);
		pnSistema.add(Logoff);
		
		JButton Exit = new JButton("");
		Exit.setIcon(new ImageIcon(Inicial.class.getResource("/image/Exit.jpg")));
		Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int x = JOptionPane.showConfirmDialog(null, "Deseja encerrar o Sistema?");
				if(x == JOptionPane.OK_OPTION){
				dispose();
				}else{
					
				}
			}
		});
		Exit.setBounds(1245, 24, 91, 61);
		pnSistema.add(Exit);
		
		JButton NovoUsuario = new JButton("");
		NovoUsuario.setIcon(new ImageIcon(Inicial.class.getResource("/image/usuario.jpg")));
		NovoUsuario.setBounds(1113, 56, 43, 29);
		pnSistema.add(NovoUsuario);
		
		JPanel pnSetores = new JPanel();
		pnSetores.setBackground(Color.WHITE);
		pnSetores.setBorder(new TitledBorder(new LineBorder(new Color(255, 200, 0), 1, true), "Setores", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 200, 0)));
		pnSetores.setBounds(10, 232, 205, 418);
		contentPane.add(pnSetores);
		pnSetores.setLayout(null);
		
		btnObreiros = new JToggleButton("Obreiros");
		btnObreiros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(btnObreiros.isSelected()){
					btn1.setVisible(true);
					btn1.setEnabled(true);
					btn1.setText("Cadastrar Obreiro");
					btn2.setVisible(true);
					btn2.setEnabled(true);
					btn2.setText("Consultar Obreiro");
					btn3.setVisible(false);
					btn3.setEnabled(false);
					btn4.setVisible(false);
					btn4.setEnabled(false);
					btn5.setVisible(false);
					btn5.setEnabled(false);
				}
			}
		});
		btnObreiros.setBounds(10, 25, 156, 23);
		pnSetores.add(btnObreiros);
		buttonGroup.add(btnObreiros);
		btnObreiros.setBackground(Color.ORANGE);
		btnObreiros.setForeground(Color.WHITE);
		btnObreiros.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		
		btnMembros = new JToggleButton("Membros");
		btnMembros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnMembros.isSelected()){
					btn1.setVisible(true);
					btn1.setEnabled(true);
					btn1.setText("Cadastrar Membro");
					btn2.setVisible(true);
					btn2.setEnabled(true);
					btn2.setText("Consultar Membro");
					btn3.setVisible(false);
					btn3.setEnabled(false);
					btn4.setVisible(false);
					btn4.setEnabled(false);
					btn5.setVisible(false);
					btn5.setEnabled(false);
				}
			}
		});
		btnMembros.setBounds(11, 59, 155, 23);
		pnSetores.add(btnMembros);
		buttonGroup.add(btnMembros);
		btnMembros.setBackground(Color.ORANGE);
		btnMembros.setForeground(Color.WHITE);
		btnMembros.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		
		btnDizimos = new JToggleButton("Dizimos e Ofertas");
		btnDizimos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnDizimos.isSelected()){
					btn1.setVisible(true);
					btn1.setEnabled(true);
					btn1.setText("Entrada");
					btn2.setVisible(true);
					btn2.setEnabled(true);
					btn2.setText("Saída Diária");
					btn3.setVisible(true);
					btn3.setEnabled(true);
					btn3.setText("Relátórios de Saídas");
					btn4.setVisible(true);
					btn4.setEnabled(true);
					btn4.setText("Gráficos");
					btn5.setVisible(false);
					btn5.setEnabled(false);
				}
			}
		});
		btnDizimos.setBounds(10, 93, 155, 23);
		pnSetores.add(btnDizimos);
		buttonGroup.add(btnDizimos);
		btnDizimos.setBackground(Color.ORANGE);
		btnDizimos.setForeground(Color.WHITE);
		btnDizimos.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		
		btnFinanceiro = new JToggleButton("Financeiro");
		btnFinanceiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(btnFinanceiro.isSelected()){
					btn1.setVisible(true);
					btn1.setEnabled(true);
					btn1.setText("Entradas");
					btn2.setVisible(true);
					btn2.setEnabled(true);
					btn2.setText("Saídas");
					btn3.setVisible(true);
					btn3.setEnabled(true);
					btn3.setText("Situações");
					btn4.setVisible(true);
					btn4.setEnabled(true);
					btn4.setText("Gráficos");
					btn5.setVisible(false);
					btn5.setEnabled(false);
				}
			}
		});
		buttonGroup.add(btnFinanceiro);
		btnFinanceiro.setForeground(Color.WHITE);
		btnFinanceiro.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		btnFinanceiro.setBackground(Color.ORANGE);
		btnFinanceiro.setBounds(10, 127, 156, 23);
		pnSetores.add(btnFinanceiro);
		
		JPanel pnAtividades = new JPanel();
		pnAtividades.setBackground(Color.WHITE);
		pnAtividades.setBorder(new TitledBorder(new LineBorder(new Color(255, 200, 0)), "Bot\u00F5es de Atividade", TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		pnAtividades.setBounds(221, 11, 1135, 82);
		contentPane.add(pnAtividades);
		pnAtividades.setLayout(null);
		
		btn1 = new JToggleButton("");
		btn1.setVisible(false);
		btn1.setEnabled(false);
		buttonGroup_1.add(btn1);
		btn1.setForeground(Color.WHITE);
		btn1.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		btn1.setBackground(Color.ORANGE);
		btn1.setBounds(10, 48, 200, 23);
		pnAtividades.add(btn1);
		
		btn2 = new JToggleButton("");
		btn2.setVisible(false);
		btn2.setEnabled(false);
		buttonGroup_1.add(btn2);
		btn2.setForeground(Color.WHITE);
		btn2.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		btn2.setBackground(Color.ORANGE);
		btn2.setSelected(false);
		btn2.setBounds(220, 48, 200, 23);
		pnAtividades.add(btn2);
		
		btn3 = new JToggleButton("");
		btn3.setVisible(false);
		btn3.setEnabled(false);
		buttonGroup_1.add(btn3);
		btn3.setForeground(Color.WHITE);
		btn3.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		btn3.setBackground(Color.ORANGE);
		btn3.setSelected(false);
		btn3.setBounds(430, 48, 200, 23);
		pnAtividades.add(btn3);
		
		btn4 = new JToggleButton("");
		btn4.setVisible(false);
		btn4.setEnabled(false);
		buttonGroup_1.add(btn4);
		btn4.setForeground(Color.WHITE);
		btn4.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		btn4.setBackground(Color.ORANGE);
		btn4.setSelected(false);
		btn4.setBounds(640, 48, 200, 23);
		pnAtividades.add(btn4);
		
		btn5 = new JToggleButton("");
		btn5.setVisible(false);
		btn5.setEnabled(false);
		buttonGroup_1.add(btn5);
		btn5.setForeground(Color.WHITE);
		btn5.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		btn5.setBackground(Color.ORANGE);
		btn5.setSelected(false);
		btn5.setBounds(850, 48, 200, 23);
		pnAtividades.add(btn5);
		
		JPanel StObreiros = new JPanel();
		StObreiros.setBorder(new TitledBorder(new LineBorder(new Color(255, 200, 0)), "Setor Selecionado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 200, 0)));
		StObreiros.setBackground(Color.WHITE);
		StObreiros.setBounds(221, 104, 1135, 546);
		contentPane.add(StObreiros);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Inicial.class.getResource("/image/ICECA - Logo.jpg")));
		label.setBounds(0, 11, 215, 210);
		contentPane.add(label);
	}
	
	public void fechamento(boolean fimdemes){
		
		Calendar c = Calendar.getInstance();
		System.out.println(c.get(Calendar.MONTH));
		if(c.get(Calendar.MONTH)==0){
			System.out.println(c.get(Calendar.MONTH));
			if(c.get(Calendar.DAY_OF_MONTH)==31){
				System.out.println(c.get(Calendar.DAY_OF_MONTH));
				int x = JOptionPane.showConfirmDialog(null, "Deseja iniciar o fechamento mensal?");
				if(x==JOptionPane.OK_OPTION){
					
				}
			}
		}
		
		if(c.get(Calendar.MONTH)==1){
			System.out.println(c.get(Calendar.MONTH));
			if(c.get(Calendar.DAY_OF_MONTH)==28){
				System.out.println(c.get(Calendar.DAY_OF_MONTH));
				int x = JOptionPane.showConfirmDialog(null, "Deseja iniciar o fechamento mensal?");
				if(x==JOptionPane.OK_OPTION){
					
				}
			}
		}
		
		if(c.get(Calendar.MONTH)==2){
			System.out.println(c.get(Calendar.MONTH));
			if(c.get(Calendar.DAY_OF_MONTH)==31){
				System.out.println(c.get(Calendar.DAY_OF_MONTH));
				int x = JOptionPane.showConfirmDialog(null, "Deseja iniciar o fechamento mensal?");
				if(x==JOptionPane.OK_OPTION){
					
				}
			}
		}
		
		if(c.get(Calendar.MONTH)==3){
			System.out.println(c.get(Calendar.MONTH));
			if(c.get(Calendar.DAY_OF_MONTH)==30){
				System.out.println(c.get(Calendar.DAY_OF_MONTH));
				int x = JOptionPane.showConfirmDialog(null, "Deseja iniciar o fechamento mensal?");
				if(x==JOptionPane.OK_OPTION){
					
				}
			}
		}
		
		if(c.get(Calendar.MONTH)==4){
			System.out.println(c.get(Calendar.MONTH));
			if(c.get(Calendar.DAY_OF_MONTH)==31){
				System.out.println(c.get(Calendar.DAY_OF_MONTH));
				int x = JOptionPane.showConfirmDialog(null, "Deseja iniciar o fechamento mensal?");
				if(x==JOptionPane.OK_OPTION){
					
				}
			}
		}
		
		if(c.get(Calendar.MONTH)==5){
			System.out.println(c.get(Calendar.MONTH));
			if(c.get(Calendar.DAY_OF_MONTH)==30){
				System.out.println(c.get(Calendar.DAY_OF_MONTH));
				int x = JOptionPane.showConfirmDialog(null, "Deseja iniciar o fechamento mensal?");
				if(x==JOptionPane.OK_OPTION){
					
				}
			}
		}
		
		if(c.get(Calendar.MONTH)==6){
			System.out.println(c.get(Calendar.MONTH));
			if(c.get(Calendar.DAY_OF_MONTH)==31){
				System.out.println(c.get(Calendar.DAY_OF_MONTH));
				int x = JOptionPane.showConfirmDialog(null, "Deseja iniciar o fechamento mensal?");
				if(x==JOptionPane.OK_OPTION){
					
				}
			}
		}
		
		if(c.get(Calendar.MONTH)==7){
			System.out.println(c.get(Calendar.MONTH));
			if(c.get(Calendar.DAY_OF_MONTH)==31){
				System.out.println(c.get(Calendar.DAY_OF_MONTH));
				int x = JOptionPane.showConfirmDialog(null, "Deseja iniciar o fechamento mensal?");
				if(x==JOptionPane.OK_OPTION){
					
				}
			}
		}
		
		if(c.get(Calendar.MONTH)==8){
			System.out.println(c.get(Calendar.MONTH));
			if(c.get(Calendar.DAY_OF_MONTH)==30){
				System.out.println(c.get(Calendar.DAY_OF_MONTH));
				int x = JOptionPane.showConfirmDialog(null, "Deseja iniciar o fechamento mensal?");
				if(x==JOptionPane.OK_OPTION){
					
				}
			}
		}
		
		if(c.get(Calendar.MONTH)==9){
			System.out.println(c.get(Calendar.MONTH));
			if(c.get(Calendar.DAY_OF_MONTH)==30){
				System.out.println(c.get(Calendar.DAY_OF_MONTH));
				int x = JOptionPane.showConfirmDialog(null, "Deseja iniciar o fechamento mensal?");
				if(x==JOptionPane.OK_OPTION){
					
				}
			}
		}
		
		if(c.get(Calendar.MONTH)==10){
			System.out.println(c.get(Calendar.MONTH));
			if(c.get(Calendar.DAY_OF_MONTH)==30){
				System.out.println(c.get(Calendar.DAY_OF_MONTH));
				int x = JOptionPane.showConfirmDialog(null, "Deseja iniciar o fechamento mensal?");
				if(x==JOptionPane.OK_OPTION){
					
				}
			}
		}
		
		if(c.get(Calendar.MONTH)==11){
			System.out.println(c.get(Calendar.MONTH));
			if(c.get(Calendar.DAY_OF_MONTH)==31){
				System.out.println(c.get(Calendar.DAY_OF_MONTH));
				int x = JOptionPane.showConfirmDialog(null, "Deseja iniciar o fechamento mensal?");
				if(x==JOptionPane.OK_OPTION){
					
				}
			}
		}
		
	}
	
	public void User_Sistema(int Id_Login) throws SQLException{
		obreiro obj = new obreiro();
		LoginDAO objDAO2 = new LoginDAO();
		obreiroDAO objDAO = new obreiroDAO();
		
		boolean x = false;
		try {
			x = objDAO.consultarLogin(LoginDAO.Login_Id_Login);
			
			if(x==true){
				Usuario.setText(objDAO.Login_Nome);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}

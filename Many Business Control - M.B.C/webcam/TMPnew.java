package telas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import de.humatic.dsj.CaptureDeviceControls;
import de.humatic.dsj.DSCapture;
import de.humatic.dsj.DSFilterInfo;
import de.humatic.dsj.DSFiltergraph;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TMPnew extends JFrame {

	private JPanel contentPane;
	CaptureDeviceControls controls = null; 
	DSFilterInfo[][] dsi = null;  
    DSFilterInfo[][] dsiVideo = null;  
    DSFilterInfo[][] dsiAudio = null;  
    DSCapture graph = null;
    
    CaptureDeviceControls controls2 = null; 
	DSFilterInfo[][] dsi2 = null;  
    DSFilterInfo[][] dsiVideo2 = null;  
    DSFilterInfo[][] dsiAudio2 = null;  
    DSCapture graph2 = null; 
    JPanel CAM1;
    JPanel CAM2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TMPnew frame = new TMPnew();
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
	public TMPnew() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				CAM1 = Cam();
				CAM2 = Cam2();
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
		
		CAM1 = new JPanel();
		CAM1.setBackground(Color.WHITE);
		CAM1.setBorder(new TitledBorder(new LineBorder(new Color(255, 200, 0)), "CAM1", TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		CAM1.setBounds(10, 187, 657, 515);
		contentPane.add(CAM1);
		
		CAM2 = new JPanel();
		CAM2.setBackground(Color.WHITE);
		CAM2.setBorder(new TitledBorder(new LineBorder(new Color(255, 200, 0)), "CAM2", TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		CAM2.setBounds(699, 187, 657, 515);
		contentPane.add(CAM2);
		
		JLabel lblMonitoramentoIceca = new JLabel("Monitoramento ICECA");
		lblMonitoramentoIceca.setForeground(Color.ORANGE);
		lblMonitoramentoIceca.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		lblMonitoramentoIceca.setBounds(492, 86, 343, 31);
		contentPane.add(lblMonitoramentoIceca);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(TMPnew.class.getResource("/image/ICECA - Logo.jpg")));
		label.setBounds(29, 11, 220, 165);
		contentPane.add(label);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setBackground(Color.ORANGE);
		btnVoltar.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
		btnVoltar.setBounds(1267, 713, 89, 23);
		contentPane.add(btnVoltar);
	}
	
	public JPanel Cam(){
		
		if(dsi == null){
			dsi = DSCapture.queryDevices();
			dsiVideo = DSCapture.queryDevices(DSCapture.SKIP_AUDIO);  
	        dsiAudio = DSCapture.queryDevices(DSCapture.SKIP_VIDEO);  
	        //dsi[0][0].setPreferredFormat(1);  
	        //graph = new DSCapture(DSFiltergraph.RENDER_NATIVE,dsi[0][0],true,dsi[0][0], null);  
	        graph = DSCapture.fromUserDialog(this, DSFiltergraph.VMR7,null);  
	          
	        if(((DSCapture) graph).getActiveVideoDevice() != null){  
	            controls = ((DSCapture) graph).getActiveVideoDevice().getControls();  
	        }  
	        if (controls != null) {  
	            for (int i = CaptureDeviceControls.BRIGHTNESS; i < CaptureDeviceControls.FOCUS; i++) {  
	                try {  
	                    //     jf.add(controls.getController(i, 0, true));  
	                } catch (Exception e) {  
	                    System.out.println("Erro ao inserir controle: " + controls.getController(i, 0, true));  
	                }  
	            }  
	        }  

	        graph.asComponent().setBounds(0, 0, 460, 380);  
	        graph.setPreview();  
	        //setContentPane(CAM1);
	        CAM1.add(graph.asComponent());  
	        //getContentPane().add(graph.asComponent());  
	          
	          
	        CAM1.repaint();  
	        validate(); 
			
		}
		return CAM1;
         
	}
	
public JPanel Cam2(){
		
		if(dsi2 == null){
			dsi2 = DSCapture.queryDevices();
			dsiVideo2 = DSCapture.queryDevices(DSCapture.SKIP_AUDIO);  
	        dsiAudio2 = DSCapture.queryDevices(DSCapture.SKIP_VIDEO);  
	        //dsi[0][0].setPreferredFormat(1);  
	        //graph = new DSCapture(DSFiltergraph.RENDER_NATIVE,dsi[0][0],true,dsi[0][0], null);  
	        graph2 = DSCapture.fromUserDialog(this, DSFiltergraph.VMR7,null);  
	          
	        if(((DSCapture) graph2).getActiveVideoDevice() != null){  
	            controls2 = ((DSCapture) graph2).getActiveVideoDevice().getControls();  
	        }  
	        if (controls2 != null) {  
	            for (int i = CaptureDeviceControls.BRIGHTNESS; i < CaptureDeviceControls.FOCUS; i++) {  
	                try {  
	                    //     jf.add(controls.getController(i, 0, true));  
	                } catch (Exception e) {  
	                    System.out.println("Erro ao inserir controle: " + controls2.getController(i, 0, true));  
	                }  
	            }  
	        }  

	        graph2.asComponent().setBounds(0, 0, 460, 380);  
	        graph2.setPreview();  
	        //setContentPane(CAM1);
	        CAM2.add(graph2.asComponent());  
	        //getContentPane().add(graph.asComponent());  
	          
	          
	        CAM2.repaint();  
	        validate(); 
			
		}
		return CAM2;
         
	}
}

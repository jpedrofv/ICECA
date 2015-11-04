package util;  

import java.text.SimpleDateFormat;  
import java.util.Date;  

import javax.swing.JLabel; 

public class HoraData {  

	public static void start(final JLabel lblDiaAtual){  
		Thread atualizaHora = new Thread(new Runnable() {  

			@Override  
			public void run() {  
				try {  
					while (true) {  
						Date date = new Date();  
						StringBuffer data = new StringBuffer();  
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Para Atualizar a DATA so mudar para o formato dd/MM/yyyy  
						lblDiaAtual.setText(data.toString() + sdf.format(date));  
						Thread.sleep(1000);  
						lblDiaAtual.revalidate();  
					}  
				} catch (InterruptedException ex) {  
					ex.printStackTrace();  
				}  

			}  
		});  
		atualizaHora.start();  
	}  

	public static void start1(final JLabel lblHoraAtual){  
		Thread atualizaHora = new Thread(new Runnable() {  

			@Override  
			public void run() {  
				try {  
					while (true) {  
						Date date = new Date();  
						StringBuffer data = new StringBuffer();  

						SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); // Para Atualizar a DATA so mudar para o formato dd/MM/yyyy  
						lblHoraAtual.setText(data.toString() + sdf.format(date));  
						Thread.sleep(1000);  
						lblHoraAtual.revalidate();  
					}  
				} catch (InterruptedException ex) {  
					ex.printStackTrace();  
				}  

			}  
		});  
		atualizaHora.start();  
	}  
} 
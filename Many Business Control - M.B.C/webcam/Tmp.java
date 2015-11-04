package telas;

import de.humatic.dsj.CaptureDeviceControls;  
import de.humatic.dsj.DSCapture;  
import de.humatic.dsj.DSFilterInfo;  
import de.humatic.dsj.DSFiltergraph;  
import javax.imageio.ImageIO;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JOptionPane;  
import javax.swing.JButton;  
import javax.swing.JTabbedPane;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.image.BufferedImage;  
  
import java.io.File;  
import java.io.IOException;  
/** 
* 
* @author AmiLtiNhO 
*/  
public class Tmp extends JFrame implements ActionListener{  
        CaptureDeviceControls controls = null;  
        JFrame frame = new JFrame();  
        JTabbedPane pane = new JTabbedPane();  
        DSFilterInfo[][] dsi = null;  
        DSFilterInfo[][] dsiVideo = null;  
        DSFilterInfo[][] dsiAudio = null;  
        DSCapture graph = null;  
        JButton btnCapturar = null;  
        JButton btnCancelar = null;  
        JButton btnGravar = null;  
        JLabel aux = null;  
          
        public Tmp(){  
            super();  
            getContentPane().setLayout(null);  
            setVisible(true);  
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
            setSize(600,600);  
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
                        System.out.println("Erro aou inserir controle: " + controls.getController(i, 0, true));  
                    }  
                }  
            }  
  
            graph.asComponent().setBounds(0, 0, 590, 450);  
            graph.setPreview();  
            add(graph.asComponent());  
            //getContentPane().add(graph.asComponent());  
              
            btnGravar = new JButton("Gravar");  
            btnGravar.setBounds(287, 527, 89, 23);  
            getContentPane().add(btnGravar);  
              
            btnCancelar = new JButton("Cancelar");  
            btnCancelar.setBounds(485, 527, 89, 23);  
            getContentPane().add(btnCancelar);  
              
            btnCapturar = new JButton("Capturar");  
            btnCapturar.setBounds(386, 527, 89, 23);  
            getContentPane().add(btnCapturar);  
              
            repaint();  
            validate();  
            addEventos();  
        }  
    private void addEventos() {  
        btnGravar.addActionListener(this);  
        btnCapturar.addActionListener(this);  
        btnCancelar.addActionListener(this);     
    }  
      
    public void actionPerformed(ActionEvent e) {  
        if(e.getSource() == btnGravar){  
            //Verifica se o dispositivo está no status PREVIEW  
            if (graph.getState() == DSCapture.PREVIEW) {  
               File f = new File("NewVideo.avi");  
               graph.setCaptureFile(f.getAbsolutePath(), null, null, true);  
               graph.record();  
            //Se não estiver, muda o status atual para PREVIEW  
            } else {  
               graph.setPreview();  
            }  
        }  
        if(e.getSource() == btnCapturar){  
              
            int nome = 0;  
            BufferedImage im = graph.getImage();  
              
            try {  
                  
                 //JOptionPane.showMessageDialog(null, caminho);  
                File f = new File(nome+".jpg");  
                while(f.exists()){  
                    nome = nome + 1;  
                              
                    f = new File(nome+".jpg");  
                }  
                  
                ImageIO.write(im, "JPG", new File(nome+".jpg"));  
            } catch (IOException e1) {  
                // TODO Auto-generated catch block  
                e1.printStackTrace();  
            }  
              
              
              
        }  
        if(e.getSource() == btnCancelar){  
            JOptionPane.showMessageDialog(null, "parado");  
            repaint();  
            validate();  
            graph.stop();  
            graph.setPreview();  
            try {  
                Thread.sleep(2000);  
            } catch (InterruptedException e1) {  
                // TODO Auto-generated catch block  
                e1.printStackTrace();  
            }  
            System.exit(0);  
              
        }  
          
    }  
     
    public static void main(String args[]) {  
        new Tmp();  
    }  
} 
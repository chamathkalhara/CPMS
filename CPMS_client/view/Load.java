package login;

import javax.swing.*;
import java.util.*;
import java.awt.*;

public class Load{
	
	public static void main(String []args){
		
		final JFrame load=new JFrame();
		load.setSize(600,200);
		load.setLocationRelativeTo(null);
		load.setUndecorated(true);
		
		JPanel barPanel=new JPanel(new GridLayout(5,1));
		barPanel.setBackground(Color.WHITE);
		final JProgressBar loadBar=new JProgressBar();
		
		loadBar.setStringPainted(true);
		barPanel.add(new JLabel(" "));
		final JLabel infoLabel=new JLabel("");
		barPanel.add(infoLabel);
		barPanel.add(loadBar);
		JPanel panel=new JPanel(new BorderLayout(20,20));
		panel.setBackground(Color.WHITE);
		panel.add(barPanel);
		panel.add(new JLabel(" "),BorderLayout.WEST);
		panel.add(new JLabel(" "),BorderLayout.EAST);
		
		new Thread(new Runnable() {
     
            public void run() {
                for (int i = 0; i < 100; i++) {
                    loadBar.setValue(i);
                    loadBar.setString(i+"%");
                    if (i == 10) {
                        infoLabel.setText("Loading Modules....");
                    }
                    if (i == 20) {
                        infoLabel.setText("Connecting DataBase....");
                    }
                    if (i == 50) {
                        infoLabel.setText("SuccessFully Connected....");
                    }
                    if (i == 70) {
                        infoLabel.setText("Starting....");
                    }
                    if (i == 99) {
                        new LogIn().setVisible(true);
                        load.dispose();
                    }
                    
                    try {
							Thread.sleep(70);
						
                    } catch (InterruptedException ex) {
                        
                    }   
                }
            }
        }).start();
        
		load.add(panel);
		
		load.setVisible(true);
		
	}
}

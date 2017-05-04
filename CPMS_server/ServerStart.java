package server;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.*;
import java.rmi.registry.*;
import controllerImpl.*;
import java.text.*;

class ImagePanel4 extends JPanel {
	
    Image bg = new ImageIcon("images/background.jpg").getImage();
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}

public class ServerStart extends JFrame{
		
	private JButton serverButton;
	private JLabel connectedLabel;
	private JLabel connectedTimeLabel;
	private JLabel startedTimeLabel;
	private int onAndOff=0;
	private long connectedTime;
	
	private Registry reservationRegistry;
	private Thread timeThread;
		
	public ServerStart(){
		setSize(530,470);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel imagePanel=new ImagePanel4();
		imagePanel.setLayout(new BorderLayout(20,40));
		setContentPane(imagePanel);
		
		serverButton=new JButton();
		JPanel serverButtonPanel=new JPanel();
		serverButtonPanel.add(serverButton);
		serverButtonPanel.setOpaque(false);
		
		serverButton.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent evt){
				
			}
			
		});
		serverButton.addMouseListener(new MouseAdapter(){
			public void mouseExited(MouseEvent evt){
				
			}
			
		});
		
		
		serverButton.setContentAreaFilled(false);
		serverButton.setIcon(new ImageIcon(getClass().getResource("/images/unpluged.png")));
		serverButton.setBorderPainted(false);
		serverButton.setToolTipText("Connect");
		
		
		JPanel southPanel=new JPanel(new GridLayout(2,1));
		JPanel southUpPanel=new JPanel(new GridLayout(2,1));
		JPanel southDownPanel=new JPanel(new GridLayout(3,1));
		
		southPanel.setOpaque(false);
		southUpPanel.setOpaque(false);
		southDownPanel.setOpaque(false);
		
		JPanel connectedPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
		connectedPanel.setOpaque(false);
		connectedLabel=new JLabel("Not connected");
		connectedPanel.add(connectedLabel);
		JPanel connectedTimePanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
		connectedTimePanel.setOpaque(false);
		connectedTimeLabel=new JLabel("00:00:00");
		connectedTimePanel.add(connectedTimeLabel);
		
		Font font=new Font("",1,30);
		connectedLabel.setFont(font);
		connectedTimeLabel.setFont(font);
		connectedLabel.setForeground(Color.WHITE);
		connectedTimeLabel.setForeground(Color.WHITE);
		
		southUpPanel.add(connectedPanel);
		southUpPanel.add(connectedTimePanel);
		southPanel.add(southUpPanel);
		
		JPanel datePanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		datePanel.setOpaque(false);
		JLabel dateLabel=new JLabel("");
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		String curDate=sdf.format(date);
		dateLabel.setText(curDate);
		JLabel dateLabel2=new JLabel("Date : ");
		JLabel startedTimeLabel2=new JLabel("Started time : ");
		datePanel.add(dateLabel2);
		datePanel.add(dateLabel);
		JPanel startedTimePanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		startedTimePanel.setOpaque(false);
		startedTimeLabel=new JLabel("00:00 PM");
		startedTimePanel.add(startedTimeLabel2);
		startedTimePanel.add(startedTimeLabel);
		JLabel emptyLabel=new JLabel(" ");
		startedTimePanel.add(emptyLabel);
		
		Font font2=new Font("",1,20);
		dateLabel.setFont(font2);
		startedTimeLabel.setFont(font2);
		dateLabel2.setFont(font2);
		startedTimeLabel2.setFont(font2);
		emptyLabel.setFont(font2);
		dateLabel.setForeground(Color.WHITE);
		startedTimeLabel.setForeground(Color.WHITE);
		dateLabel2.setForeground(Color.WHITE);
		startedTimeLabel2.setForeground(Color.WHITE);
		
		southDownPanel.add(new JLabel(""));
		southDownPanel.add(datePanel);
		southDownPanel.add(startedTimePanel);
		southPanel.add(southDownPanel);
		
		imagePanel.add(new JLabel(" "),BorderLayout.NORTH);
		imagePanel.add(southPanel,BorderLayout.SOUTH);
		//imagePanel.add(new JLabel(" "),BorderLayout.WEST);
		//imagePanel.add(new JLabel(" "),BorderLayout.EAST);
		imagePanel.add(serverButtonPanel,BorderLayout.CENTER);
		
		try{
			reservationRegistry = LocateRegistry.createRegistry(6050);
			
		}catch(RemoteException ex){
					
		}
		serverButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				try{
					final SimpleDateFormat sdf2=new SimpleDateFormat("00:mm:ss");
					final SimpleDateFormat sdf3=new SimpleDateFormat("hh:mm:ss");
					final SimpleDateFormat sdf4=new SimpleDateFormat("hh:mm a");
					
					if(onAndOff==0){
						onAndOff=1;
						serverButton.setIcon(new ImageIcon(getClass().getResource("/images/pluged.png")));
						serverButton.setToolTipText("Disconnect");
						connectedLabel.setText("Connected");
						connectedTime=System.currentTimeMillis();
						timeThread=new Thread(){
							public void run(){
								while(true){
									long duration = System.currentTimeMillis()-connectedTime;
									if(duration>=3600000){
										connectedTimeLabel.setText(sdf3.format(new Date(duration-3600000*4-1800000*3)));
									}else{
										connectedTimeLabel.setText(sdf2.format(new Date(duration-1800000)));
										
									}
								}
							}
						};
						timeThread.start();
						startedTimeLabel.setText(sdf4.format(new Date()));
						reservationRegistry.rebind("ReservationServer", new ReservationControllerImpl());
					}else{
						serverButton.setIcon(new ImageIcon(getClass().getResource("/images/unpluged.png")));
						onAndOff=0;
						serverButton.setToolTipText("Connect");
						connectedLabel.setText("Not connected");
						timeThread.stop();
						connectedTimeLabel.setText(sdf2.format(new Date(0-1800000)));
						reservationRegistry.unbind("ReservationServer");
					}
				}catch(RemoteException ex){
					
				}catch(NotBoundException ex){
					
				}
			}
		});
		
	}
	
	public static void main(String args[]){
		new ServerStart().setVisible(true);
	}
}

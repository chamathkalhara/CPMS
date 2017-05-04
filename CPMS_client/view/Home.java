package home;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.text.*;
import javax.swing.border.*;
import java.awt.event.*;
import reservation.*;
import login.*;
import controller.*;
import connector.*;
import java.rmi.*;
import java.net.*;
import java.io.*;
import history.*;

class ImagePanel extends JPanel {
	
    Image bg = new ImageIcon("../images/car2.jpg").getImage();
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}


class Content extends JPanel{
	
	private JLabel timeLabel=new JLabel();
	private JLabel dayLabel=new JLabel();
	
	Content(){
		setOpaque(false);
		setLayout(new FlowLayout());
		
	    JLabel iconLabel=new JLabel();
		ImageIcon icon = new ImageIcon("../images/new.png");
		iconLabel.setIcon(icon);
		add(iconLabel);
		
		JLabel nameLabel=new JLabel("         CPMS");
		nameLabel.setFont(new Font("",1,90));
		nameLabel.setForeground(Color.BLACK);
		add(nameLabel);
		
		final JPanel datePanel=new JPanel();
		datePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		final JPanel timePanel=new JPanel();
		timePanel.setLayout(new GridLayout(2,1));
		
		timeLabel.setForeground(Color.WHITE);
		dayLabel.setForeground(Color.WHITE);
		
		timePanel.setOpaque(false);
		datePanel.setOpaque(false);
		
		timePanel.add(timeLabel);
		timePanel.add(dayLabel);
					
		datePanel.add(timePanel);
		JLabel spaceLabel=new JLabel("            ");
		spaceLabel.setFont(new Font("",1,100));
		add(spaceLabel);
		add(datePanel);
		
		new Thread(){  
			
			public void run() {
				while(true){
					Date date=new Date();
					SimpleDateFormat sdf=new SimpleDateFormat("hh:mm:ss a");
					timeLabel.setText(sdf.format(date));
					timeLabel.setFont(new Font("",1,50));	
					
					SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
					dayLabel.setText(sdf2.format(date));
					dayLabel.setFont(new Font("",1,30));
					
				}    
			}
		}.start();
		
	}
}

class Content2 extends JPanel{
	
	Content2(){
		setOpaque(false);
		setLayout(new GridLayout(4,1));
		
		JPanel resevePanel=new JPanel();
		JPanel relesePanel=new JPanel();
		JPanel infoPanel=new JPanel();
		
		resevePanel.setLayout(new BorderLayout(30,1));
		relesePanel.setLayout(new BorderLayout(30,1));
		infoPanel.setLayout(new BorderLayout(30,1));
		
		resevePanel.setOpaque(false);
		relesePanel.setOpaque(false);
		infoPanel.setOpaque(false);
		
		JButton reseveButton=new JButton("      Reseve Slot      ");
		
		reseveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				new ReserveSlot().setVisible(true);
			}
		});
		
		JButton releseButtton=new JButton("     Relese Slot      ");
		
		releseButtton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				new ReleseSlot().setVisible(true);
			}
		});
		
		JButton infoButton=new JButton("      Infomations      ");
		
		infoButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				new History().setVisible(true);
			}
		});
		
		reseveButton.setBackground(Color.LIGHT_GRAY);
		releseButtton.setBackground(Color.LIGHT_GRAY);
		infoButton.setBackground(Color.LIGHT_GRAY);
		
		reseveButton.setForeground(Color.BLACK);
		releseButtton.setForeground(Color.BLACK);
		infoButton.setForeground(Color.BLACK);
		
		reseveButton.setBorder(new LineBorder(Color.BLACK,3));
		releseButtton.setBorder(new LineBorder(Color.BLACK,3));
		infoButton.setBorder(new LineBorder(Color.BLACK,3));
		
		resevePanel.add(new JLabel(" "),BorderLayout.WEST);
		resevePanel.add(new JLabel(" "),BorderLayout.EAST);
		resevePanel.add(new JLabel(" "),BorderLayout.NORTH);
		resevePanel.add(new JLabel(" "),BorderLayout.SOUTH);
		
		relesePanel.add(new JLabel(" "),BorderLayout.WEST);
		relesePanel.add(new JLabel(" "),BorderLayout.EAST);
		relesePanel.add(new JLabel(" "),BorderLayout.NORTH);
		relesePanel.add(new JLabel(" "),BorderLayout.SOUTH);
		
		infoPanel.add(new JLabel(" "),BorderLayout.WEST);
		infoPanel.add(new JLabel(" "),BorderLayout.EAST);
		infoPanel.add(new JLabel(" "),BorderLayout.NORTH);
		infoPanel.add(new JLabel(" "),BorderLayout.SOUTH);
		
		resevePanel.add(reseveButton);
		relesePanel.add(releseButtton);
		infoPanel.add(infoButton);
		
		
		Font font=new Font("",0,35);
		reseveButton.setFont(font);
		releseButtton.setFont(font);
		infoButton.setFont(font);
		
		JLabel slotLabel=new JLabel("  Available :"+50);
		
		try{
			ReservationController reservationController=ServerConnector.getServerConnector().getReservationController();	
			ArrayList<Integer> availableList=reservationController.getAvailableSlots();
			
			slotLabel.setText("  Available :"+availableList.size());
						
		}catch(NotBoundException ex){
					
		}catch(MalformedURLException ex){
					
		}catch(RemoteException ex){
					
		}catch(ClassNotFoundException ex){
					
		}catch(IOException ex){
					
		}
		
		slotLabel.setFont(new Font("",1,50));
		slotLabel.setForeground(Color.GREEN);
		
		add(resevePanel);
		add(relesePanel);
		add(infoPanel);
		add(slotLabel);
	}
}

class Content3 extends JPanel{
	
	Content3(){
		setOpaque(false);
		setLayout(new GridLayout(1,6));
		
		JButton exitButton=new JButton("  EXIT  ");
		
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				System.exit(0);
			}
		});
		
		JButton logOutButton=new JButton("  LOG OUT  ");
		
		logOutButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				new LogIn().setVisible(true);
				Home.home.dispose();
			}
		});
		
		Font font=new Font("",0,35);
		exitButton.setFont(font);
		logOutButton.setFont(font);
		
		exitButton.setForeground(Color.BLACK);
		logOutButton.setForeground(Color.BLACK);
		
		exitButton.setBackground(Color.LIGHT_GRAY);
		logOutButton.setBackground(Color.LIGHT_GRAY);
		
		exitButton.setBorder(new LineBorder(Color.BLACK,3));
		logOutButton.setBorder(new LineBorder(Color.BLACK,3));
		
		JPanel exitBPanel=new JPanel();
		JPanel logOBPanel=new JPanel();
		
		exitBPanel.setOpaque(false);
		logOBPanel.setOpaque(false);
		
		exitBPanel.setLayout(new BorderLayout(10,5));
		logOBPanel.setLayout(new BorderLayout(10,5));
		
		exitBPanel.add(exitButton);
		logOBPanel.add(logOutButton);
		
		exitBPanel.add(new JLabel(" "),BorderLayout.SOUTH);
		exitBPanel.add(new JLabel(" "),BorderLayout.WEST);
		exitBPanel.add(new JLabel(" "),BorderLayout.EAST);
		exitBPanel.add(new JLabel(" "),BorderLayout.NORTH);
		
		logOBPanel.add(new JLabel(" "),BorderLayout.SOUTH);
		logOBPanel.add(new JLabel(" "),BorderLayout.WEST);
		logOBPanel.add(new JLabel(" "),BorderLayout.EAST);
		logOBPanel.add(new JLabel(" "),BorderLayout.NORTH);
		
		add(exitBPanel);
		add(logOBPanel);
		add(new JLabel(" "));
		add(new JLabel(" "));
		add(new JLabel(" "));
	}
}

public class Home extends JFrame {
	
	public static Home home;
	//public JPanel mainPanel;
	
	public Home(){
		JPanel mainPanel= new ImagePanel();
        mainPanel.setLayout(new BorderLayout());
		
		mainPanel.add(new Content(), BorderLayout.NORTH);
		mainPanel.add(new Content2(),BorderLayout.EAST);
		mainPanel.add(new Content3(),BorderLayout.SOUTH);
		
		setContentPane(mainPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Toolkit toolkit=getToolkit();
		setSize(toolkit.getScreenSize());        
	}
	public static void main(String args[]){
		Home home=new Home();
		home.setVisible(true);
		Home.home=home;
	}
  
}

package login;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import home.*;
import controller.*;
import connector.*;
import java.net.*;
import java.rmi.*;
import java.io.*;

class LogInContent extends JPanel{
	
	LogInContent(){
		setOpaque(false);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel iconLabel=new JLabel();
		ImageIcon icon = new ImageIcon("../images/new3.png");
		iconLabel.setIcon(icon);
		add(iconLabel);
		
		JLabel descLabel=new JLabel("          Enter userID and password ... ");
		descLabel.setFont(new Font("",0,25));
		descLabel.setForeground(Color.BLACK);
		add(descLabel);
	}
}

class LogInContent2 extends JPanel{
	
	private JTextField idText;
	private JPasswordField passwordText;
	private JLabel errorLabel;
	
	LogInContent2(){
		setOpaque(false);
		setLayout(new GridLayout(4,1));
		
		JPanel idPanel=new JPanel();
		idPanel.setOpaque(false);
		
		JLabel idLabel=new JLabel("User ID      :   ");
		idText=new JTextField(17);
		idText.setBorder(new LineBorder(Color.BLACK,3));
		Font font=new Font("",0,25);
		idLabel.setFont(font);
		idText.setFont(font);
		idLabel.setForeground(Color.BLACK);
		idText.setForeground(Color.BLACK);
		idPanel.add(idLabel);
		idPanel.add(idText);
		idPanel.add(new JLabel("                         "));
		add(idPanel);
		
		JPanel passwordPanel=new JPanel();
		passwordPanel.setOpaque(false);
		
		JLabel passwordLabel=new JLabel("     Password  :   ");
		passwordText=new JPasswordField(17);
		passwordText.setBorder(new LineBorder(Color.BLACK,3));
		passwordLabel.setFont(font);
		passwordText.setFont(font);
		passwordLabel.setForeground(Color.BLACK);
		passwordText.setForeground(Color.BLACK);
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordText);
		passwordPanel.add(new JLabel("   "));
		JButton changeButton=new JButton("  Change  ");
		
		changeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				new ChangePassword().setVisible(true);
			}
		});
		
		changeButton.setBackground(Color.WHITE);
		changeButton.setForeground(Color.BLACK);
		changeButton.setFont(new Font("",0,20));
		changeButton.setBorder(new LineBorder(Color.BLACK,3));
		passwordPanel.add(changeButton);
		add(passwordPanel);
		
		JPanel errorLabelPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
		errorLabelPanel.setOpaque(false);
		errorLabel=new JLabel("");
		errorLabel.setFont(new Font("",0,20));
		errorLabel.setForeground(Color.RED);
		errorLabelPanel.add(errorLabel);
		add(errorLabelPanel);
		
		JPanel buttonPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setOpaque(false);
		JButton cancelButton=new JButton("  Cancel  ");
		
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				System.exit(0);
			}
		});
		
		JButton logInButton=new JButton("  LogIn  ");
		
		logInButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				
				try{	
					ReservationController reservationController=ServerConnector.getServerConnector().getReservationController();
					String password=reservationController.getPassword(Integer.valueOf(idText.getText()));
					
					if(password.equals(passwordText.getText())){
						Home home=new Home();
						Home.home=home;
						home.setVisible(true);
					}else{
						errorLabel.setText("Sorry, user name or password is incorrect");
						idText.setText("");
						passwordText.setText("");
						idText.requestFocus();
					}
					
				}catch(NotBoundException ex){
				
				}catch(RemoteException ex){
				
				}catch(MalformedURLException ex){
				
				}catch(ClassNotFoundException ex){
					
				}catch(IOException ex){
					
				}					
			}
		});
		
		logInButton.setFont(new Font("",0,22));
		cancelButton.setFont(new Font("",0,22));
		cancelButton.setBackground(Color.WHITE);
		logInButton.setBackground(Color.WHITE);
		cancelButton.setForeground(Color.BLACK);
		logInButton.setForeground(Color.BLACK);
		cancelButton.setBorder(new LineBorder(Color.BLACK,3));
		logInButton.setBorder(new LineBorder(Color.BLACK,3));
		buttonPanel.add(logInButton);
		buttonPanel.add(new JLabel("    "));
		buttonPanel.add(cancelButton);
		buttonPanel.add(new JLabel("           "));
		add(buttonPanel);
	}
}

public class LogIn extends JFrame{

	public LogIn(){
		JPanel panel=new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(Color.BLACK,3));
		
		panel.add(new LogInContent(),BorderLayout.NORTH);
		panel.add(new LogInContent2(),BorderLayout.CENTER);
		
		add(panel);		
		setSize(800,470);
		setLocationRelativeTo(null);
		setResizable(false);

	}
}

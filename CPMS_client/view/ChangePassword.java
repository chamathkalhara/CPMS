package login;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;

public class ChangePassword extends JFrame{
	
	public ChangePassword(){
		setSize(450,320);
		setLocationRelativeTo(null);
		JPanel panel=new JPanel(new GridLayout(6,1));
		
		JPanel idPanel=new JPanel();
		idPanel.setBackground(Color.WHITE);
		JPanel currentPanel=new JPanel();
		currentPanel.setBackground(Color.WHITE);
		JPanel newPanel=new JPanel();
		newPanel.setBackground(Color.WHITE);
		JPanel reNewPanel=new JPanel();
		reNewPanel.setBackground(Color.WHITE);
		JPanel buttonPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setBackground(Color.WHITE);
		
		Font font=new Font("",0,18);
		
		panel.add(new JLabel(""));
		
		JLabel idLabel=new JLabel("        Id  :  ");
		JTextField idText=new JTextField(15);
		idText.setBorder(new LineBorder(Color.BLACK,3));
		idLabel.setFont(font);
		idText.setFont(font);
		idLabel.setForeground(Color.BLACK);
		idText.setForeground(Color.BLACK);
		idPanel.add(idLabel);
		idPanel.add(idText);
		panel.add(idPanel);
		
		JLabel currentLabel=new JLabel("      Current  :  ");
		JPasswordField currentText=new JPasswordField(15);
		currentText.setBorder(new LineBorder(Color.BLACK,3));
		currentLabel.setFont(font);
		currentText.setFont(font);
		currentLabel.setForeground(Color.BLACK);
		currentText.setForeground(Color.BLACK);
		currentPanel.add(currentLabel);
		currentPanel.add(currentText);
		panel.add(currentPanel);
		
		JLabel newLabel=new JLabel("          New  :  ");
		JPasswordField newText=new JPasswordField(15);
		newText.setBorder(new LineBorder(Color.BLACK,3));
		newLabel.setFont(font);
		newText.setFont(font);
		newLabel.setForeground(Color.BLACK);
		newText.setForeground(Color.BLACK);
		newPanel.add(newLabel);
		newPanel.add(newText);
		panel.add(newPanel);
		
		JLabel reNewLabel=new JLabel("Re-En New  :  ");
		JPasswordField reNewText=new JPasswordField(15);
		reNewText.setBorder(new LineBorder(Color.BLACK,3));
		reNewLabel.setFont(font);
		reNewText.setFont(font);
		reNewLabel.setForeground(Color.BLACK);
		reNewText.setForeground(Color.BLACK);
		reNewPanel.add(reNewLabel);
		reNewPanel.add(reNewText);
		panel.add(reNewPanel);
		
		JButton cancelButton=new JButton("  Cancel  ");
		
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
			}
		});
		
		cancelButton.setFont(font);
		cancelButton.setForeground(Color.BLACK);
		cancelButton.setBackground(Color.WHITE);
		cancelButton.setBorder(new LineBorder(Color.BLACK,3));
		
		JButton changeButton=new JButton("  Change  ");
		
		changeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				
			}
		});
		
		changeButton.setFont(font);
		changeButton.setForeground(Color.BLACK);
		changeButton.setBackground(Color.WHITE);
		changeButton.setBorder(new LineBorder(Color.BLACK,3));
		
		buttonPanel.add(changeButton);
		buttonPanel.add(new JLabel("     "));
		buttonPanel.add(cancelButton);
		buttonPanel.add(new JLabel("            "));
		panel.add(buttonPanel);
		
		panel.setBackground(Color.WHITE);
		add(panel);
		
		
	}		
	
}



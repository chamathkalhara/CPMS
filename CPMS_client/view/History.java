package history;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import connector.*;
import controller.*;
import java.rmi.*;
import java.net.*;
import java.io.*;
import model.*;
import java.text.*;

class ImagePanel extends JPanel {
	
    Image bg = new ImageIcon("../images/hc.jpg").getImage();
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}

public class History extends JFrame{
	
	private JTextField dateText;
	private JTextField vehicleText;
	private JList list;
	private JScrollPane listScroll;
	private JLabel billNoLabel2;
	private JLabel dateLabel2;
	private JLabel arrivedLabel2;
	private JLabel leavingLabel2;
	private JLabel slotLabel2;
	private JLabel vehicleLabel2;
	private JLabel paymentLabel2;
	
	private JPanel centerRightPanel;
	private JPanel centerPanel;
	
	private ArrayList<Reservation> searchByDateList;
	private ArrayList<Reservation> searchByVehicleList;
	
	public History(){
		
		setSize(1100,750);
		setLocationRelativeTo(null);
		
		JPanel imagePanel=new ImagePanel();
		imagePanel.setLayout(new BorderLayout());
		setContentPane(imagePanel);
		
		JPanel northPanel=new JPanel(new BorderLayout());
		northPanel.setOpaque(false);
		
		JPanel northUpPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		northUpPanel.setOpaque(false);
		JLabel iconLabel=new JLabel();
		ImageIcon icon = new ImageIcon("../images/new3.png");
		iconLabel.setIcon(icon);
		northUpPanel.add(iconLabel);
		northPanel.add(northUpPanel,BorderLayout.CENTER);
		
		final Font font=new Font("",0,15);
		
		JPanel northDownPanel=new JPanel(new GridLayout(1,2));
		northDownPanel.setOpaque(false);
		JPanel northDownRightPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel northDownLeftPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		northDownRightPanel.setOpaque(false);
		northDownLeftPanel.setOpaque(false);
		JLabel emptyLabel=new JLabel("                   ");
		emptyLabel.setFont(new Font("",1,35));
		northDownLeftPanel.add(emptyLabel);
		
		dateText=new JTextField("Search by date",15);
		
		dateText.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				String searchDate=dateText.getText();
				try{
					ReservationController reservationController=ServerConnector.getServerConnector().getReservationController();
					searchByDateList=reservationController.searchByDate(dateText.getText());
					String []vehicleList=new String[searchByDateList.size()];
					
					int i=0;
					for(Reservation reservation : searchByDateList){
						vehicleList[i]=reservation.getVehicleNumber();
						i++;
					}
					
					list.setListData(vehicleList);
					
					
				}catch(NotBoundException ex){
					
				}catch(MalformedURLException ex){
					
				}catch(RemoteException ex){
					
				}catch(ClassNotFoundException ex){
					
				}catch(IOException ex){
				
				}
			}
		});
		
		dateText.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				if(dateText.getText().equals("Search by date")){
					dateText.setText("");
				}if(vehicleText.getText().equals("")){
					vehicleText.setText("Search by vehicle");
				}
			}
		});
		
		dateText.setFont(font);
		dateText.selectAll();
		northDownRightPanel.add(dateText);
		
		vehicleText=new JTextField("Search by vehicle",15);
		
		vehicleText.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				String searchVehicle=vehicleText.getText();
				try{
					ReservationController reservationController=ServerConnector.getServerConnector().getReservationController();
					searchByVehicleList=reservationController.searchByVehicleNumber(vehicleText.getText());
					String []dateList=new String[searchByVehicleList.size()];
					
					int i=0;
					for(Reservation reservation : searchByVehicleList){
						dateList[i]=reservation.getDate();
						i++;
					}
					
					list.setListData(dateList);
					
					
				}catch(NotBoundException ex){
					
				}catch(MalformedURLException ex){
					
				}catch(RemoteException ex){
					
				}catch(ClassNotFoundException ex){
					
				}catch(IOException ex){
				
				}
			}
		});
		
		vehicleText.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt){
				if(vehicleText.getText().equals("Search by vehicle")){
					vehicleText.setText("");
				}if(dateText.getText().equals("")){
					dateText.setText("Search by date");
				}
			}
		});
		
		vehicleText.setFont(font);
		vehicleText.selectAll();
		northDownRightPanel.add(vehicleText);
		northDownPanel.add(northDownLeftPanel);
		northDownPanel.add(northDownRightPanel);
		northPanel.add(northDownPanel,BorderLayout.SOUTH);
		
		Font font2=new Font("",1,50);
		JLabel nameLabel=new JLabel("Search info");
		nameLabel.setFont(font2);
		nameLabel.setForeground(Color.BLACK);
		JLabel spaceLabel=new JLabel("                ");
		spaceLabel.setFont(font2);
		northUpPanel.add(spaceLabel);
		northUpPanel.add(nameLabel);

		imagePanel.add(northPanel,BorderLayout.NORTH);
		
		centerPanel=new JPanel(new GridLayout(1,2));
		centerPanel.setOpaque(false);
		
		JPanel centerLeftPanel=new JPanel(new GridLayout(8,1));
		centerLeftPanel.setOpaque(false);
		
		
		JLabel billNoLabel=new JLabel("Bill no             :  ");
		billNoLabel2=new JLabel("");
		JPanel billNoPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel emptyLabel2=new JLabel("       ");
		emptyLabel2.setFont(font2);
		billNoPanel.add(emptyLabel2);
		billNoPanel.add(billNoLabel);
		billNoPanel.add(billNoLabel2);
		billNoPanel.setOpaque(false);
		
		JLabel dateLabel=new JLabel("Date               :  ");
		final JPanel datePanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel emptyLabel3=new JLabel("       ");
		dateLabel2=new JLabel("");
		emptyLabel3.setFont(font2);
		datePanel.add(emptyLabel3);
		datePanel.add(dateLabel);
		datePanel.add(dateLabel2);
		datePanel.setOpaque(false);
		
		JLabel arrivedLabel=new JLabel("Arrived time   :  ");
		final JPanel arrivedPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel emptyLabel4=new JLabel("       ");
		arrivedLabel2=new JLabel("");
		emptyLabel4.setFont(font2);
		arrivedPanel.add(emptyLabel4);
		arrivedPanel.add(arrivedLabel);
		arrivedPanel.add(arrivedLabel2);
		arrivedPanel.setOpaque(false);
		
		JLabel leavingLabel=new JLabel("Leaving time   :  ");
		final JPanel leavingPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel emptyLabel5=new JLabel("       ");
		leavingLabel2=new JLabel("");
		emptyLabel5.setFont(font2);
		leavingPanel.add(emptyLabel5);
		leavingPanel.add(leavingLabel);
		leavingPanel.add(leavingLabel2);
		leavingPanel.setOpaque(false);
		
		JLabel slotLabel=new JLabel("Slot no           :  ");
		JPanel slotPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel emptyLabel6=new JLabel("       ");
		slotLabel2=new JLabel("");
		emptyLabel6.setFont(font2);
		slotPanel.add(emptyLabel6);
		slotPanel.add(slotLabel);
		slotPanel.add(slotLabel2);
		slotPanel.setOpaque(false);
		
		JLabel vehicleLabel=new JLabel("Vehicle no     :  ");
		JPanel vehiclePanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel emptyLabel7=new JLabel("       ");
		vehicleLabel2=new JLabel("");
		emptyLabel7.setFont(font2);
		vehiclePanel.add(emptyLabel7);
		vehiclePanel.add(vehicleLabel);
		vehiclePanel.add(vehicleLabel2);
		vehiclePanel.setOpaque(false);
		
		JLabel paymentLabel=new JLabel("Payment        :  ");
		paymentLabel2=new JLabel("");
		JPanel paymentPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel emptyLabel8=new JLabel("       ");
		emptyLabel8.setFont(font2);
		paymentPanel.add(emptyLabel8);
		paymentPanel.add(paymentLabel);
		paymentPanel.add(paymentLabel2);
		paymentPanel.setOpaque(false);
		
		Font font3=new Font("",0,23);
		
		billNoLabel.setFont(font3);
		billNoLabel2.setFont(font3);
		dateLabel.setFont(font3);
		dateLabel2.setFont(font3);
		arrivedLabel.setFont(font3);
		arrivedLabel2.setFont(font3);
		leavingLabel.setFont(font3);
		leavingLabel2.setFont(font3);
		slotLabel.setFont(font3);
		slotLabel2.setFont(font3);
		vehicleLabel.setFont(font3);
		vehicleLabel2.setFont(font3);
		paymentLabel.setFont(font3);
		paymentLabel2.setFont(font3);
		
		billNoLabel.setForeground(Color.BLACK);
		billNoLabel2.setForeground(Color.BLACK);
		dateLabel.setForeground(Color.BLACK);
		dateLabel2.setForeground(Color.BLACK);
		arrivedLabel.setForeground(Color.BLACK);
		arrivedLabel2.setForeground(Color.BLACK);
		leavingLabel.setForeground(Color.BLACK);
		leavingLabel2.setForeground(Color.BLACK);
		slotLabel.setForeground(Color.BLACK);
		slotLabel2.setForeground(Color.BLACK);
		vehicleLabel.setForeground(Color.BLACK);
		vehicleLabel2.setForeground(Color.BLACK);
		paymentLabel.setForeground(Color.BLACK);
		paymentLabel2.setForeground(Color.BLACK);
		
		centerLeftPanel.add(billNoPanel);
		centerLeftPanel.add(datePanel);
		centerLeftPanel.add(arrivedPanel);
		centerLeftPanel.add(leavingPanel);
		centerLeftPanel.add(slotPanel);
		centerLeftPanel.add(vehiclePanel);
		centerLeftPanel.add(paymentPanel);
		
		list=new JList();
		listScroll=new JScrollPane();
		listScroll.setSize(200,600);
		listScroll.setViewportView(list);
		list.setFixedCellHeight(30);
		list.setFixedCellWidth(200);
		list.setFont(font);
		centerRightPanel=new JPanel(new BorderLayout(100,20));
		centerRightPanel.setOpaque(false);
		centerRightPanel.add(listScroll,BorderLayout.CENTER);
		centerRightPanel.add(new JLabel(""),BorderLayout.WEST);
		centerRightPanel.add(new JLabel(""),BorderLayout.EAST);
		centerRightPanel.add(new JLabel(""),BorderLayout.SOUTH);
		
		centerPanel.add(centerLeftPanel);
		centerPanel.add(centerRightPanel);
		imagePanel.add(centerPanel,BorderLayout.CENTER);
		
		
		list.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt) {                                    
			   if(!list.isSelectionEmpty()){
					System.out.println(String.valueOf(list.getSelectedValue()));
					doListClick(String.valueOf(list.getSelectedValue()));
			   }
			}     
		});
		                              
		list.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent evt) {                                   
				if(evt.getKeyCode()==KeyEvent.VK_DOWN || evt.getKeyCode()==KeyEvent.VK_UP){
					System.out.println(String.valueOf(list.getSelectedValue()));
					doListClick(String.valueOf(list.getSelectedValue()));
				}
			}   
		});
		        
		
		JPanel southPanel=new JPanel(new GridLayout(1,6));
		southPanel.setOpaque(false);
		
		JButton cancelButton=new JButton("Cancel");
		
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				dispose();
			}
		});
		
		JPanel cancelButtonPanel=new JPanel(new BorderLayout(5,5));
		cancelButtonPanel.setOpaque(false);
		cancelButtonPanel.add(cancelButton);
		cancelButton.setForeground(Color.BLACK);
		cancelButton.setBackground(Color.RED);
		Font buttonFont=new Font("",1,25);
		cancelButton.setFont(buttonFont);
		
		cancelButtonPanel.add(new JLabel(" "),BorderLayout.SOUTH);
		cancelButtonPanel.add(new JLabel(" "),BorderLayout.EAST);
		
		southPanel.add(new JLabel(" "));
		southPanel.add(new JLabel(" "));
		southPanel.add(new JLabel(" "));
		southPanel.add(new JLabel(" "));
		southPanel.add(cancelButtonPanel);
		
		imagePanel.add(southPanel,BorderLayout.SOUTH);
		
	}
	
	private void doListClick(String data){
		String []splitData=data.split("-");
		int size=0;
		for(String temp : splitData){
			size++;
		}
		if(size==2){
			for(Reservation reservation : searchByDateList){
				if(reservation.getVehicleNumber().equals(data)){
					NumberFormat numberFormat = NumberFormat.getNumberInstance();
					numberFormat.setMinimumIntegerDigits(5);
					String value = numberFormat.format(reservation.getBillNumber());
					String[] newID = value.split(",");
					String newValue = "";
					for (String next : newID) {
						newValue += next;
					}
					billNoLabel2.setText(newValue);
					dateLabel2.setText(reservation.getDate());
					arrivedLabel2.setText(reservation.getArrivedTime());								
					leavingLabel2.setText(reservation.getLeavingTime());
					slotLabel2.setText(String.valueOf(reservation.getSlotNumber()));
					vehicleLabel2.setText(reservation.getVehicleNumber());
					paymentLabel2.setText("Rs : 50");
				}
			}
		}else if(size==3){
			for(Reservation reservation : searchByVehicleList){
				if(reservation.getDate().equals(data)){
					NumberFormat numberFormat = NumberFormat.getNumberInstance();
					numberFormat.setMinimumIntegerDigits(5);
					String value = numberFormat.format(reservation.getBillNumber());
					String[] newID = value.split(",");
					String newValue = "";
					for (String next : newID) {
						newValue += next;
					}
					billNoLabel2.setText(newValue);
					dateLabel2.setText(reservation.getDate());
					arrivedLabel2.setText(reservation.getArrivedTime());								
					leavingLabel2.setText(reservation.getLeavingTime());
					slotLabel2.setText(String.valueOf(reservation.getSlotNumber()));
					vehicleLabel2.setText(reservation.getVehicleNumber());
					paymentLabel2.setText("Rs : 50");
				}
			}
		}
	}
}

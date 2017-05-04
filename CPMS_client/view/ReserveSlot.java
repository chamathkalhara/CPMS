package reservation;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.text.*;
import java.awt.event.*;
import observer.*;
import connector.*;
import controller.*;
import java.rmi.*;
import java.net.*;
import java.io.*;
import model.*;
import home.*;

class ImagePanel2 extends JPanel{
	
    Image bg = new ImageIcon("../images/hc.jpg").getImage();
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}

public class ReserveSlot extends JFrame{
	
	private JLabel billNoLabel2;
	private JLabel dateLabel2;
	private JLabel arrivedLabel2;
	private JTextField vehicleText;
	private JTextField slotText;
	private JLabel[][] cars;
	private ImageIcon[][] whiteCar;
	private ImageIcon[][] redCar;
	
	private static int tempSlot;
	
	private ReservationController reservationController;
	private ReservationController tempReservationController;
	private ReserveObserver reserveObserver;
	
	public ReserveSlot(){
		
		try{	
			reservationController=ServerConnector.getServerConnector().getReservationController();
			reserveObserver=new ReserveObserver(this);
			reservationController.addObserver(reserveObserver);
					
		}catch(NotBoundException ex){
			JOptionPane.showMessageDialog(null, "notbound");
		}catch(RemoteException ex){
			JOptionPane.showMessageDialog(null, "remote ");
		}catch(MalformedURLException ex){
			JOptionPane.showMessageDialog(null, "malformed");
		}
		
		
		setSize(1100,750);
		setLocationRelativeTo(null);
		
		JPanel imagePanel=new ImagePanel2();
		imagePanel.setLayout(new BorderLayout());
		setContentPane(imagePanel);
		
		JPanel northPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		northPanel.setOpaque(false);
		JLabel iconLabel=new JLabel();
		ImageIcon icon = new ImageIcon("../images/new3.png");
		iconLabel.setIcon(icon);
		northPanel.add(iconLabel);
		
		Font font2=new Font("",1,50);
		
		JLabel nameLabel=new JLabel("Reserve Slot");
		nameLabel.setFont(font2);
		nameLabel.setForeground(Color.BLACK);
		JLabel spaceLabel=new JLabel("                ");
		spaceLabel.setFont(font2);
		northPanel.add(spaceLabel);
		northPanel.add(nameLabel);

		imagePanel.add(northPanel,BorderLayout.NORTH);
		
		JPanel centerPanel=new JPanel(new GridLayout(6,1));
		centerPanel.setOpaque(false);
		
		JLabel billNoLabel=new JLabel("Bill no             :  ");
		billNoLabel2=new JLabel("");
		
		new Thread(){
			public void run(){
				while(true){
					try{
						
						int newBillNumber=reservationController.getNewBillNumber();
						
						NumberFormat numberFormat = NumberFormat.getNumberInstance();
						numberFormat.setMinimumIntegerDigits(5);
						String value = numberFormat.format(newBillNumber);
						String[] newID = value.split(",");
						String newValue = "";
						for (String next : newID) {
							newValue += next;
						}
						
						billNoLabel2.setText(newValue);
						
					}catch(RemoteException ex){
						JOptionPane.showMessageDialog(null, "remote Exception");
					}catch(FileNotFoundException ex){
						JOptionPane.showMessageDialog(null, "file not found");
					}catch(IOException ex){
						JOptionPane.showMessageDialog(null, "io excepion");
					}
				}
			}
		}.start();
		
		JPanel billNoPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel emptyLabel=new JLabel("       ");
		emptyLabel.setFont(font2);
		billNoPanel.add(emptyLabel);
		billNoPanel.add(billNoLabel);
		billNoPanel.add(billNoLabel2);
		billNoPanel.setOpaque(false);
		
		JLabel dateLabel=new JLabel("Date               :  ");
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		dateLabel2=new JLabel(sdf.format(date));
		JPanel datePanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel emptyLabel2=new JLabel("       ");
		emptyLabel2.setFont(font2);
		datePanel.add(emptyLabel2);
		datePanel.add(dateLabel);
		datePanel.add(dateLabel2);
		datePanel.setOpaque(false);
		
		JLabel arrivedLabel=new JLabel("Arrived time   :  ");
		SimpleDateFormat sdf2=new SimpleDateFormat("hh:mm a");
		arrivedLabel2=new JLabel(sdf2.format(date));
		JPanel arrivedPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel emptyLabel3=new JLabel("       ");
		emptyLabel3.setFont(font2);
		arrivedPanel.add(emptyLabel3);
		arrivedPanel.add(arrivedLabel);
		arrivedPanel.add(arrivedLabel2);
		arrivedPanel.setOpaque(false);
		
		JLabel slotLabel=new JLabel("Slot no           :  ");
		slotText=new JTextField(10);
		
		slotText.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				try{
					ReservationController reservationController=ServerConnector.getServerConnector().getReservationController();
					
					reservationController.releseSlot(String.valueOf(tempSlot));				
					tempSlot=Integer.valueOf(slotText.getText());			
					tempReservationController=reservationController;
		
					boolean isReserved=reservationController.reserveSlot(slotText.getText());
					
					ArrayList<Reservation> list=reservationController.getNotReleasedVehicles();
					for(Reservation reservation:list){
						if(reservation.getSlotNumber()==Integer.valueOf(slotText.getText())){
							
							JOptionPane.showMessageDialog(null, "Used Slot " + slotText.getText());
						}
					}
					
					if(isReserved){
						vehicleText.requestFocus();
					}else{
						JOptionPane.showMessageDialog(null, "Locked Slot " + slotText.getText());
					}
				}catch(RemoteException ex){
					
				}catch(NotBoundException ex){
					
				}catch(MalformedURLException ex){
					
				}catch(ClassNotFoundException ex){
					
				}catch(IOException ex){
					
				}
			}
		});
		
		
		JPanel slotPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel emptyLabel4=new JLabel("       ");
		emptyLabel4.setFont(font2);
		slotPanel.add(emptyLabel4);
		slotPanel.add(slotLabel);
		slotPanel.add(slotText);
		slotPanel.setOpaque(false);
		
		JLabel vehicleLabel=new JLabel("Vehicle no     :  ");
		vehicleText=new JTextField(15);
		JPanel vehiclePanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel emptyLabel5=new JLabel("       ");
		emptyLabel5.setFont(font2);
		vehiclePanel.add(emptyLabel5);
		vehiclePanel.add(vehicleLabel);
		vehiclePanel.add(vehicleText);
		vehiclePanel.setOpaque(false);
		
		JLabel paymentLabel=new JLabel("Payment        :  ");
		final JLabel paymentLabel2=new JLabel("Rs : 50");
		JPanel paymentPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel emptyLabel6=new JLabel("       ");
		emptyLabel6.setFont(font2);
		paymentPanel.add(emptyLabel6);
		paymentPanel.add(paymentLabel);
		paymentPanel.add(paymentLabel2);
		paymentPanel.setOpaque(false);
		
		
		Font font=new Font("",0,23);
		
		billNoLabel.setFont(font);
		billNoLabel2.setFont(font);
		dateLabel.setFont(font);
		dateLabel2.setFont(font);
		arrivedLabel.setFont(font);
		arrivedLabel2.setFont(font);
		slotLabel.setFont(font);
		slotText.setFont(font);
		vehicleLabel.setFont(font);
		vehicleText.setFont(font);
		paymentLabel.setFont(font);
		paymentLabel2.setFont(font);
		
		billNoLabel.setForeground(Color.BLACK);
		billNoLabel2.setForeground(Color.BLACK);
		dateLabel.setForeground(Color.BLACK);
		dateLabel2.setForeground(Color.BLACK);
		arrivedLabel.setForeground(Color.BLACK);
		arrivedLabel2.setForeground(Color.BLACK);
		slotLabel.setForeground(Color.BLACK);
		slotText.setForeground(Color.BLACK);
		vehicleLabel.setForeground(Color.BLACK);
		vehicleText.setForeground(Color.BLACK);
		paymentLabel.setForeground(Color.BLACK);
		paymentLabel2.setForeground(Color.BLACK);
		
		centerPanel.add(billNoPanel);
		centerPanel.add(datePanel);
		centerPanel.add(arrivedPanel);
		centerPanel.add(slotPanel);
		centerPanel.add(vehiclePanel);
		centerPanel.add(paymentPanel);
		
		imagePanel.add(centerPanel,BorderLayout.WEST);
		
		JPanel eastPanel=new JPanel(new GridLayout(11,1));
		eastPanel.setOpaque(false);
		
		
		cars=new JLabel[10][5];
		whiteCar=new ImageIcon[10][5];
		redCar=new ImageIcon[10][5];
		
		JPanel raw1=new JPanel(new GridLayout(1,5));
		eastPanel.add(raw1);
		raw1.setOpaque(false);
		
		whiteCar[0][0] = new ImageIcon("../images/numCar1new.png");
		redCar[0][0] = new ImageIcon("../images/redCar1.png");	
		cars[0][0]=new JLabel();
		cars[0][0].setIcon(whiteCar[0][0]);
		raw1.add(cars[0][0]);
		cars[0][1]=new JLabel();
		whiteCar[0][1] = new ImageIcon("../images/numCar2new.png");
		redCar[0][1] = new ImageIcon("../images/redCar2.png");
		cars[0][1].setIcon(whiteCar[0][1]);
		raw1.add(cars[0][1]);
		cars[0][2]=new JLabel();
		whiteCar[0][2] = new ImageIcon("../images/numCar3new.png");
		redCar[0][2] = new ImageIcon("../images/redCar3.png");
		cars[0][2].setIcon(whiteCar[0][2]);
		raw1.add(cars[0][2]);
		cars[0][3]=new JLabel();
		whiteCar[0][3] = new ImageIcon("../images/numCar4new.png");
		redCar[0][3] = new ImageIcon("../images/redCar4.png");
		cars[0][3].setIcon(whiteCar[0][3]);
		raw1.add(cars[0][3]);
		cars[0][4]=new JLabel();
		whiteCar[0][4] = new ImageIcon("../images/numCar5new.png");
		redCar[0][4] = new ImageIcon("../images/redCar5.png");
		cars[0][4].setIcon(whiteCar[0][4]);
		raw1.add(cars[0][4]);
		
		
		JPanel raw2=new JPanel(new GridLayout(1,5));
		eastPanel.add(raw2);
		raw2.setOpaque(false);
		
		cars[1][0]=new JLabel();
		whiteCar[1][0] = new ImageIcon("../images/numCar6new.png");
		redCar[1][0] = new ImageIcon("../images/redCar6.png");
		cars[1][0].setIcon(whiteCar[1][0]);
		raw2.add(cars[1][0]);
		cars[1][1]=new JLabel();
		whiteCar[1][1] = new ImageIcon("../images/numCar7new.png");
		redCar[1][1] = new ImageIcon("../images/redCar7.png");
		cars[1][1].setIcon(whiteCar[1][1]);
		raw2.add(cars[1][1]);
		cars[1][2]=new JLabel();
		whiteCar[1][2] = new ImageIcon("../images/numCar8new.png");
		redCar[1][2] = new ImageIcon("../images/redCar8.png");
		cars[1][2].setIcon(whiteCar[1][2]);
		raw2.add(cars[1][2]);
		cars[1][3]=new JLabel();
		whiteCar[1][3] = new ImageIcon("../images/numCar9new.png");
		redCar[1][3] = new ImageIcon("../images/redCar9.png");
		cars[1][3].setIcon(whiteCar[1][3]);
		raw2.add(cars[1][3]);
		cars[1][4]=new JLabel();
		whiteCar[1][4] = new ImageIcon("../images/numCar10new.png");
		redCar[1][4] = new ImageIcon("../images/redCar10.png");
		cars[1][4].setIcon(whiteCar[1][4]);
		raw2.add(cars[1][4]);
			
		JPanel raw3=new JPanel(new GridLayout(1,5));
		eastPanel.add(raw3);
		raw3.setOpaque(false);
		
		cars[2][0]=new JLabel();
		whiteCar[2][0] = new ImageIcon("../images/numCar11new.png");
		redCar[2][0] = new ImageIcon("../images/redCar11.png");
		cars[2][0].setIcon(whiteCar[2][0]);
		raw3.add(cars[2][0]);
		cars[2][1]=new JLabel();
		whiteCar[2][1] = new ImageIcon("../images/numCar12new.png");
		redCar[2][1] = new ImageIcon("../images/redCar12.png");
		cars[2][1].setIcon(whiteCar[2][1]);
		raw3.add(cars[2][1]);
		cars[2][2]=new JLabel();
		whiteCar[2][2] = new ImageIcon("../images/numCar13new.png");
		redCar[2][2] = new ImageIcon("../images/redCar13.png");
		cars[2][2].setIcon(whiteCar[2][2]);
		raw3.add(cars[2][2]);
		cars[2][3]=new JLabel();
		whiteCar[2][3] = new ImageIcon("../images/numCar14new.png");
		redCar[2][3] = new ImageIcon("../images/redCar14.png");
		cars[2][3].setIcon(whiteCar[2][3]);
		raw3.add(cars[2][3]);
		cars[2][4]=new JLabel();
		whiteCar[2][4] = new ImageIcon("../images/numCar15new.png");
		redCar[2][4] = new ImageIcon("../images/redCar15.png");
		cars[2][4].setIcon(whiteCar[2][4]);
		raw3.add(cars[2][4]);
		
		JPanel raw4=new JPanel(new GridLayout(1,5));
		eastPanel.add(raw4);
		raw4.setOpaque(false);
		
		cars[3][0]=new JLabel();
		whiteCar[3][0] = new ImageIcon("../images/numCar16new.png");
		redCar[3][0] = new ImageIcon("../images/redCar16.png");
		cars[3][0].setIcon(whiteCar[3][0]);
		raw4.add(cars[3][0]);
		cars[3][1]=new JLabel();
		whiteCar[3][1] = new ImageIcon("../images/numCar17new.png");
		redCar[3][1] = new ImageIcon("../images/redCar17.png");
		cars[3][1].setIcon(whiteCar[3][1]);
		raw4.add(cars[3][1]);
		cars[3][2]=new JLabel();
		whiteCar[3][2] = new ImageIcon("../images/numCar18new.png");
		redCar[3][2] = new ImageIcon("../images/redCar18.png");
		cars[3][2].setIcon(whiteCar[3][2]);
		raw4.add(cars[3][2]);
		cars[3][3]=new JLabel();
		whiteCar[3][3] = new ImageIcon("../images/numCar19new.png");
		redCar[3][3] = new ImageIcon("../images/redCar19.png");
		cars[3][3].setIcon(whiteCar[3][3]);
		raw4.add(cars[3][3]);
		cars[3][4]=new JLabel();
		whiteCar[3][4] = new ImageIcon("../images/numCar20new.png");
		redCar[3][4] = new ImageIcon("../images/redCar20.png");
		cars[3][4].setIcon(whiteCar[3][4]);
		raw4.add(cars[3][4]);
		
		JPanel raw5=new JPanel(new GridLayout(1,5));
		eastPanel.add(raw5);
		raw5.setOpaque(false);
		
		cars[4][0]=new JLabel();
		whiteCar[4][0] = new ImageIcon("../images/numCar21new.png");
		redCar[4][0] = new ImageIcon("../images/redCar21.png");
		cars[4][0].setIcon(whiteCar[4][0]);
		raw5.add(cars[4][0]);
		cars[4][1]=new JLabel();
		whiteCar[4][1] = new ImageIcon("../images/numCar22new.png");
		redCar[4][1] = new ImageIcon("../images/redCar22.png");
		cars[4][1].setIcon(whiteCar[4][1]);
		raw5.add(cars[4][1]);
		cars[4][2]=new JLabel();
		whiteCar[4][2] = new ImageIcon("../images/numCar23new.png");
		redCar[4][2] = new ImageIcon("../images/redCar23.png");
		cars[4][2].setIcon(whiteCar[4][2]);
		raw5.add(cars[4][2]);
		cars[4][3]=new JLabel();
		whiteCar[4][3] = new ImageIcon("../images/numCar24new.png");
		redCar[4][3] = new ImageIcon("../images/redCar24.png");
		cars[4][3].setIcon(whiteCar[4][3]);
		raw5.add(cars[4][3]);
		cars[4][4]=new JLabel();
		whiteCar[4][4] = new ImageIcon("../images/numCar25new.png");
		redCar[4][4] = new ImageIcon("../images/redCar25.png");
		cars[4][4].setIcon(whiteCar[4][4]);
		raw5.add(cars[4][4]);
		
		JPanel raw6=new JPanel(new GridLayout(1,5));
		eastPanel.add(raw6);
		raw6.setOpaque(false);
		
		cars[5][0]=new JLabel();
		whiteCar[5][0] = new ImageIcon("../images/numCar26new.png");
		redCar[5][0] = new ImageIcon("../images/redCar26.png");
		cars[5][0].setIcon(whiteCar[5][0]);
		raw6.add(cars[5][0]);
		cars[5][1]=new JLabel();
		whiteCar[5][1] = new ImageIcon("../images/numCar27new.png");
		redCar[5][1] = new ImageIcon("../images/redCar27.png");
		cars[5][1].setIcon(whiteCar[5][1]);
		raw6.add(cars[5][1]);
		cars[5][2]=new JLabel();
		whiteCar[5][2] = new ImageIcon("../images/numCar28new.png");
		redCar[5][2] = new ImageIcon("../images/redCar28.png");
		cars[5][2].setIcon(whiteCar[5][2]);
		raw6.add(cars[5][2]);
		cars[5][3]=new JLabel();
		whiteCar[5][3] = new ImageIcon("../images/numCar29new.png");
		redCar[5][3] = new ImageIcon("../images/redCar29.png");
		cars[5][3].setIcon(whiteCar[5][3]);
		raw6.add(cars[5][3]);
		cars[5][4]=new JLabel();
		whiteCar[5][4] = new ImageIcon("../images/numCar30new.png");
		redCar[5][4] = new ImageIcon("../images/redCar30.png");
		cars[5][4].setIcon(whiteCar[5][4]);
		raw6.add(cars[5][4]);
		
		JPanel raw7=new JPanel(new GridLayout(1,5));
		eastPanel.add(raw7);
		raw7.setOpaque(false);
		
		cars[6][0]=new JLabel();
		whiteCar[6][0] = new ImageIcon("../images/numCar31new.png");
		redCar[6][0] = new ImageIcon("../images/redCar31.png");
		cars[6][0].setIcon(whiteCar[6][0]);
		raw7.add(cars[6][0]);
		cars[6][1]=new JLabel();
		whiteCar[6][1] = new ImageIcon("../images/numCar32new.png");
		redCar[6][1] = new ImageIcon("../images/redCar32.png");
		cars[6][1].setIcon(whiteCar[6][1]);
		raw7.add(cars[6][1]);
		cars[6][2]=new JLabel();
		whiteCar[6][2] = new ImageIcon("../images/numCar33new.png");
		redCar[6][2] = new ImageIcon("../images/redCar33.png");
		cars[6][2].setIcon(whiteCar[6][2]);
		raw7.add(cars[6][2]);
		cars[6][3]=new JLabel();
		whiteCar[6][3] = new ImageIcon("../images/numCar34new.png");
		redCar[6][3] = new ImageIcon("../images/redCar34.png");
		cars[6][3].setIcon(whiteCar[6][3]);
		raw7.add(cars[6][3]);
		cars[6][4]=new JLabel();
		whiteCar[6][4] = new ImageIcon("../images/numCar35new.png");
		redCar[6][4] = new ImageIcon("../images/redCar35.png");
		cars[6][4].setIcon(whiteCar[6][4]);
		raw7.add(cars[6][4]);
		
		JPanel raw8=new JPanel(new GridLayout(1,5));
		eastPanel.add(raw8);
		raw8.setOpaque(false);
		
		cars[7][0]=new JLabel();
		whiteCar[7][0] = new ImageIcon("../images/numCar36new.png");
		redCar[7][0] = new ImageIcon("../images/redCar36.png");
		cars[7][0].setIcon(whiteCar[7][0]);
		raw8.add(cars[7][0]);
		cars[7][1]=new JLabel();
		whiteCar[7][1] = new ImageIcon("../images/numCar37new.png");
		redCar[7][1] = new ImageIcon("../images/redCar37.png");
		cars[7][1].setIcon(whiteCar[7][1]);
		raw8.add(cars[7][1]);
		cars[7][2]=new JLabel();
		whiteCar[7][2] = new ImageIcon("../images/numCar38new.png");
		redCar[7][2] = new ImageIcon("../images/redCar38.png");
		cars[7][2].setIcon(whiteCar[7][2]);
		raw8.add(cars[7][2]);
		cars[7][3]=new JLabel();
		whiteCar[7][3] = new ImageIcon("../images/numCar39new.png");
		redCar[7][3] = new ImageIcon("../images/redCar39.png");
		cars[7][3].setIcon(whiteCar[7][3]);
		raw8.add(cars[7][3]);
		cars[7][4]=new JLabel();
		whiteCar[7][4] = new ImageIcon("../images/numCar40new.png");
		redCar[7][4] = new ImageIcon("../images/redCar40.png");
		cars[7][4].setIcon(whiteCar[7][4]);
		raw8.add(cars[7][4]);
		
		JPanel raw9=new JPanel(new GridLayout(1,5));
		eastPanel.add(raw9);
		raw9.setOpaque(false);
		
		cars[8][0]=new JLabel();
		whiteCar[8][0] = new ImageIcon("../images/numCar41new.png");
		redCar[8][0] = new ImageIcon("../images/redCar41.png");
		cars[8][0].setIcon(whiteCar[8][0]);
		raw9.add(cars[8][0]);
		cars[8][1]=new JLabel();
		whiteCar[8][1] = new ImageIcon("../images/numCar42new.png");
		redCar[8][1] = new ImageIcon("../images/redCar42.png");
		cars[8][1].setIcon(whiteCar[8][1]);
		raw9.add(cars[8][1]);
		cars[8][2]=new JLabel();
		whiteCar[8][2] = new ImageIcon("../images/numCar43new.png");
		redCar[8][2] = new ImageIcon("../images/redCar43.png");
		cars[8][2].setIcon(whiteCar[8][2]);
		raw9.add(cars[8][2]);
		cars[8][3]=new JLabel();
		whiteCar[8][3] = new ImageIcon("../images/numCar44new.png");
		redCar[8][3] = new ImageIcon("../images/redCar44.png");
		cars[8][3].setIcon(whiteCar[8][3]);
		raw9.add(cars[8][3]);
		cars[8][4]=new JLabel();
		whiteCar[8][4] = new ImageIcon("../images/numCar45new.png");
		redCar[8][4] = new ImageIcon("../images/redCar45.png");
		cars[8][4].setIcon(whiteCar[8][4]);
		raw9.add(cars[8][4]);
		
		JPanel raw10=new JPanel(new GridLayout(1,5));
		eastPanel.add(raw10);
		raw10.setOpaque(false);
		
		cars[9][0]=new JLabel();
		whiteCar[9][0] = new ImageIcon("../images/numCar46new.png");
		redCar[9][0] = new ImageIcon("../images/redCar46.png");
		cars[9][0].setIcon(whiteCar[9][0]);
		raw10.add(cars[9][0]);
		cars[9][1]=new JLabel();
		whiteCar[9][1] = new ImageIcon("../images/numCar47new.png");
		redCar[9][1] = new ImageIcon("../images/redCar47.png");
		cars[9][1].setIcon(whiteCar[9][1]);
		raw10.add(cars[9][1]);
		cars[9][2]=new JLabel();
		whiteCar[9][2] = new ImageIcon("../images/numCar48new.png");
		redCar[9][2] = new ImageIcon("../images/redCar48.png");
		cars[9][2].setIcon(whiteCar[9][2]);
		raw10.add(cars[9][2]);
		cars[9][3]=new JLabel();
		whiteCar[9][3] = new ImageIcon("../images/numCar49new.png");
		redCar[9][3] = new ImageIcon("../images/redCar49.png");
		cars[9][3].setIcon(whiteCar[9][3]);
		raw10.add(cars[9][3]);
		cars[9][4]=new JLabel();
		whiteCar[9][4] = new ImageIcon("../images/numCar50new.png");
		redCar[9][4] = new ImageIcon("../images/redCar50.png");
		cars[9][4].setIcon(whiteCar[9][4]);
		raw10.add(cars[9][4]);
		
		
		try{
			ArrayList<Reservation> currentList=reservationController.getNotReleasedVehicles();
			
			for(Reservation currentOnes:currentList){
				int slotNum=currentOnes.getSlotNumber();
				int slot=slotNum-1;
				int slot10=slot/5;
				int slot1=slot%5;
				
				cars[slot10][slot1].setIcon(redCar[slot10][slot1]);			
			}
		}catch(RemoteException ex){
			
		}catch(ClassNotFoundException ex){
			
		}catch(IOException ex){
			
		}
		
		
		imagePanel.add(eastPanel,BorderLayout.EAST);
		
		JPanel southPanel=new JPanel(new GridLayout(1,6));
		southPanel.setOpaque(false);
		
		JButton cancelButton=new JButton("Cancel");
		
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if(tempReservationController!=null){
					try{
						tempReservationController.releseSlot(String.valueOf(tempSlot));
						
					}catch(RemoteException ex){
						
					}
				}
				
				dispose();
			}
		});
		
		JPanel cancelButtonPanel=new JPanel(new BorderLayout(5,5));
		cancelButtonPanel.setOpaque(false);
		cancelButtonPanel.add(cancelButton);
		cancelButton.setForeground(Color.BLACK);
		cancelButton.setBackground(Color.RED);
		
		cancelButtonPanel.add(new JLabel(" "),BorderLayout.SOUTH);
		cancelButtonPanel.add(new JLabel(" "),BorderLayout.EAST);
		JButton confirmButton=new JButton("Confirm");
		
		confirmButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				try{
					ReservationController reservationController=ServerConnector.getServerConnector().getReservationController();
					
					ArrayList<Integer> availableList=reservationController.getAvailableSlots();
					
					if(availableList.contains(Integer.valueOf(slotText.getText()))){
						if(!vehicleText.getText().equals("")){
							Reservation reservation=new Reservation(Integer.valueOf(billNoLabel2.getText()),dateLabel2.getText(),arrivedLabel2.getText(),Integer.valueOf(slotText.getText()),"Stil not leaved",50,vehicleText.getText());
							reservationController.reserveSlot(reservation);
							JOptionPane.showMessageDialog(null,"Slot "+slotText.getText()+" is reserve");
							reservationController.removeObserver(reserveObserver);
							Home.home.dispose();
							Home home=new Home();
							Home.home=home;
							home.setVisible(true);
							dispose();
						}else{
							JOptionPane.showMessageDialog(null, "Vehicle number field is empty");
						}
					}else{
						JOptionPane.showMessageDialog(null, "Incorrect slot number");
					}
					
					reservationController.releseSlot(slotText.getText());
					
				}catch(NotBoundException ex){
					
				}catch(MalformedURLException ex){
					
				}catch(RemoteException ex){
					
				}catch(ClassNotFoundException ex){
					
				}catch(IOException ex){
					
				}
			}
		});
		
		JPanel confirmButtonPanel=new JPanel(new BorderLayout(5,5));
		confirmButtonPanel.setOpaque(false);
		confirmButtonPanel.add(confirmButton);
		confirmButton.setForeground(Color.BLACK);
		confirmButton.setBackground(Color.RED);
		
		confirmButtonPanel.add(new JLabel(" "),BorderLayout.SOUTH);
		confirmButtonPanel.add(new JLabel(" "),BorderLayout.EAST);
		
		Font buttonFont=new Font("",1,25);
		cancelButton.setFont(buttonFont);
		confirmButton.setFont(buttonFont);
		
		southPanel.add(new JLabel(" "));
		southPanel.add(new JLabel(" "));
		southPanel.add(new JLabel(" "));
		southPanel.add(new JLabel(" "));
		southPanel.add(confirmButtonPanel);
		southPanel.add(cancelButtonPanel);
		
		imagePanel.add(southPanel,BorderLayout.SOUTH);
	}
	
	//~ public static void main(String []args){
		//~ new ReserveSlot().setVisible(true);
	//~ }
	public void updateSlots(int slotNumber) {
        
        int slot=slotNumber-1;
        int slot10=slot/5;
        int slot1=slot%5;
        
        ImageIcon imageIcon=(ImageIcon)cars[slot10][slot1].getIcon();
        String icon=imageIcon.getDescription();
        if(icon.endsWith("new.png")){
			cars[slot10][slot1].setIcon(redCar[slot10][slot1]);
		}else if(icon.contains("redCar")){
			cars[slot10][slot1].setIcon(whiteCar[slot10][slot1]);
		}
        
    }
}

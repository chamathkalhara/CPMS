package reservation;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.text.*;
import java.awt.event.*;
import javax.swing.border.*;
import observer.*;
import connector.*;
import controller.*;
import java.rmi.*;
import java.net.*;
import model.*;
import java.io.*;
import other.*;
import javax.swing.plaf.basic.*;
import home.*;

class ImagePanel3 extends JPanel {
	
    Image bg = new ImageIcon("../images/hc.jpg").getImage();
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}

public class ReleseSlot extends JFrame{
	
	private JLabel billNoLabel2;
	private JLabel dateLabel2;
	private JLabel arrivedLabel2;
	private JLabel leavingLabel2;
	private JLabel slotLabel2;
	private JLabel vehicleLabel2;
	private JTextField slotNoText;
	private JLabel paymentLabel2;
	private JComboBox<String> vehicleNoCombo;
	private Date date;
	
	private ArrayList<Reservation> searchReservedList;
	private static ArrayList<Object> slotNumberList;
	private static ArrayList<String> vehicleNumberList;
	
	private ReleaseObserver releaseObserver;
	
	public ReleseSlot(){
		
		try{	
			ReservationController reservationController=ServerConnector.getServerConnector().getReservationController();
			releaseObserver=new ReleaseObserver(this);
			reservationController.addObserver(releaseObserver);
			
		}catch(NotBoundException ex){
			
		}catch(RemoteException ex){
			
		}catch(MalformedURLException ex){
			
		}
		
		setSize(1100,750);
		setLocationRelativeTo(null);
		
		
		
		JPanel imagePanel=new ImagePanel3();
		imagePanel.setLayout(new BorderLayout());
		setContentPane(imagePanel);
		
		JPanel northPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		northPanel.setOpaque(false);
		JLabel iconLabel=new JLabel();
		ImageIcon icon = new ImageIcon("../images/new3.png");
		iconLabel.setIcon(icon);
		northPanel.add(iconLabel);
		
		Font font2=new Font("",1,50);
		
		JLabel nameLabel=new JLabel("Relese Slot");
		nameLabel.setFont(font2);
		nameLabel.setForeground(Color.BLACK);
		JLabel spaceLabel=new JLabel("                ");
		spaceLabel.setFont(font2);
		northPanel.add(spaceLabel);
		northPanel.add(nameLabel);

		imagePanel.add(northPanel,BorderLayout.NORTH);
		
		JPanel centerPanel=new JPanel(new GridLayout(7,1));
		centerPanel.setOpaque(false);
		
		JLabel billNoLabel=new JLabel("Bill no             :  ");
		billNoLabel2=new JLabel("");
		JPanel billNoPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel emptyLabel=new JLabel("       ");
		emptyLabel.setFont(font2);
		billNoPanel.add(emptyLabel);
		billNoPanel.add(billNoLabel);
		billNoPanel.add(billNoLabel2);
		billNoPanel.setOpaque(false);
		
		JLabel dateLabel=new JLabel("Date               :  ");
		date=new Date();
		final JPanel datePanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel emptyLabel2=new JLabel("       ");
		dateLabel2=new JLabel("");
		emptyLabel2.setFont(font2);
		datePanel.add(emptyLabel2);
		datePanel.add(dateLabel);
		datePanel.add(dateLabel2);
		datePanel.setOpaque(false);
		
		JLabel arrivedLabel=new JLabel("Arrived time   :  ");
		final JPanel arrivedPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel emptyLabel3=new JLabel("       ");
		arrivedLabel2=new JLabel("");
		emptyLabel3.setFont(font2);
		arrivedPanel.add(emptyLabel3);
		arrivedPanel.add(arrivedLabel);
		arrivedPanel.add(arrivedLabel2);
		arrivedPanel.setOpaque(false);
		
		JLabel leavingLabel=new JLabel("Leaving time   :  ");
		SimpleDateFormat sdf2=new SimpleDateFormat("hh:mm a");
		final JPanel leavingPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel emptyLabel4=new JLabel("       ");
		leavingLabel2=new JLabel("");
		emptyLabel4.setFont(font2);
		leavingPanel.add(emptyLabel4);
		leavingPanel.add(leavingLabel);
		leavingPanel.add(leavingLabel2);
		leavingPanel.setOpaque(false);
		
		JLabel slotLabel=new JLabel("Slot no           :  ");
		JPanel slotPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel emptyLabel5=new JLabel("       ");
		slotLabel2=new JLabel("");
		emptyLabel5.setFont(font2);
		slotPanel.add(emptyLabel5);
		slotPanel.add(slotLabel);
		slotPanel.add(slotLabel2);
		slotPanel.setOpaque(false);
		
		JLabel vehicleLabel=new JLabel("Vehicle no     :  ");
		JPanel vehiclePanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel emptyLabel6=new JLabel("       ");
		vehicleLabel2=new JLabel("");
		emptyLabel6.setFont(font2);
		vehiclePanel.add(emptyLabel6);
		vehiclePanel.add(vehicleLabel);
		vehiclePanel.add(vehicleLabel2);
		vehiclePanel.setOpaque(false);
		
		JLabel paymentLabel=new JLabel("Payment        :  ");
		paymentLabel2=new JLabel("");
		JPanel paymentPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel emptyLabel7=new JLabel("       ");
		emptyLabel7.setFont(font2);
		paymentPanel.add(emptyLabel7);
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
		leavingLabel.setFont(font);
		leavingLabel2.setFont(font);
		slotLabel.setFont(font);
		slotLabel2.setFont(font);
		vehicleLabel.setFont(font);
		vehicleLabel2.setFont(font);
		paymentLabel.setFont(font);
		paymentLabel2.setFont(font);
		
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
		
		centerPanel.add(billNoPanel);
		centerPanel.add(datePanel);
		centerPanel.add(arrivedPanel);
		centerPanel.add(leavingPanel);
		centerPanel.add(slotPanel);
		centerPanel.add(vehiclePanel);
		centerPanel.add(paymentPanel);
		
		imagePanel.add(centerPanel,BorderLayout.WEST);
		
		JPanel eastPanel=new JPanel(new GridLayout(7,1));
		eastPanel.setOpaque(false);
		
		JPanel descPanel=new JPanel(new GridLayout(2,1));
		descPanel.setOpaque(false);
		JLabel line1=new JLabel("Type the Slot no: or Vehicle no:              ");
		JLabel line2=new JLabel("to select the vehicle");
		
		Font font3=new Font("",0,18);
		line1.setFont(font3);
		line2.setFont(font3);
		
		descPanel.add(line1);
		descPanel.add(line2);
		eastPanel.add(new JLabel(" "));
		eastPanel.add(descPanel);
		
		JLabel slotNoLabel=new JLabel("Slot no        :   ");
		JLabel vehicleNoLabel=new JLabel("Vehicle no  :   ");
		slotNoText=new JTextField(10);
		
		
		slotNoText.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				try{	
					ReservationController reservationController=ServerConnector.getServerConnector().getReservationController();
					
					searchReservedList=reservationController.getNotReleasedVehicles();
					ArrayList<Integer> emptySlotList=reservationController.getAvailableSlots();
					ArrayList<Integer> list50Num=new ArrayList<Integer>();
					
					for(int i=1;i<51;i++){
						list50Num.add(i);
					}
					
					if(emptySlotList.contains(Integer.valueOf(slotNoText.getText()))){
						JOptionPane.showMessageDialog(null,"Slot "+slotNoText.getText()+" is empty");
					}else if(list50Num.contains(Integer.valueOf(slotNoText.getText()))){
						for(Reservation reservation:searchReservedList){
							if(reservation.getSlotNumber()==Integer.valueOf(slotNoText.getText())){
								NumberFormat numberFormat = NumberFormat.getNumberInstance();
								numberFormat.setMinimumIntegerDigits(5);
								String value = numberFormat.format(reservation.getBillNumber());
								String[] newID = value.split(",");
								String newValue = "";
								for (String next : newID) {
									newValue += next;
								}
								billNoLabel2.setText(newValue);
								SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
								dateLabel2.setText(sdf.format(date));
								arrivedLabel2.setText(reservation.getArrivedTime());								
								SimpleDateFormat sdf2=new SimpleDateFormat("hh:mm a");
								leavingLabel2.setText(sdf2.format(date));
								slotLabel2.setText(slotNoText.getText());
								vehicleLabel2.setText(reservation.getVehicleNumber());
								paymentLabel2.setText("Rs : 50");
							}
						}
					}else{
						JOptionPane.showMessageDialog(null,"Wrong slot number");
					}
					
				}catch(NotBoundException ex){
					
				}catch(RemoteException ex){
					
				}catch(MalformedURLException ex){
					
				}catch(ClassNotFoundException ex){
					
				}catch(IOException ex){
					
				}
			}
		});
		
		
		try{	
			ReservationController reservationController=ServerConnector.getServerConnector().getReservationController();
				
			searchReservedList=reservationController.getNotReleasedVehicles();
			String[] reservedVehicleNoList=new String[searchReservedList.size()];
					
			int i=0;
			for(Reservation reservation:searchReservedList){
				reservedVehicleNoList[i]=reservation.getVehicleNumber();
				i++;
			}
					
			vehicleNoCombo=new MyComboBox<String>(reservedVehicleNoList);
			vehicleNoCombo.setSelectedIndex(-1);
					
			ComboSearch comboSearch=new ComboSearch();
			comboSearch.search(vehicleNoCombo,false,"No vehicle found..");
					
					
		}catch(NotBoundException ex){
					
		}catch(RemoteException ex){
					
		}catch(MalformedURLException ex){
					
		}catch(ClassNotFoundException ex){
					
		}catch(IOException ex){
				
		}
		
		JTextField comboText=(JTextField)vehicleNoCombo.getEditor().getEditorComponent();
		comboText.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if(vehicleNoCombo.getSelectedIndex()!=-1){
					for(Reservation reservation:searchReservedList){
						if(reservation.getVehicleNumber().equals(String.valueOf(vehicleNoCombo.getSelectedItem()))){
							NumberFormat numberFormat = NumberFormat.getNumberInstance();
							numberFormat.setMinimumIntegerDigits(5);
							String value = numberFormat.format(reservation.getBillNumber());
							String[] newID = value.split(",");
							String newValue = "";
							for (String next : newID) {
								newValue += next;
							}
							billNoLabel2.setText(newValue);
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
							dateLabel2.setText(sdf.format(date));
							arrivedLabel2.setText(reservation.getArrivedTime());								
							SimpleDateFormat sdf2=new SimpleDateFormat("hh:mm a");
							leavingLabel2.setText(sdf2.format(date));
							slotLabel2.setText(String.valueOf(reservation.getSlotNumber()));
							vehicleLabel2.setText(reservation.getVehicleNumber());
							paymentLabel2.setText("Rs : 50");
						}
					}
				}else{
					JOptionPane.showMessageDialog(null,"Select vehicle..");
				}
			}
		});
		
		slotNoLabel.setFont(font3);
		vehicleNoLabel.setFont(font3);
		slotNoText.setFont(font3);
		vehicleNoCombo.setFont(font3);
		
		slotNoLabel.setForeground(Color.BLACK);
		vehicleNoLabel.setForeground(Color.BLACK);
		slotNoText.setForeground(Color.BLACK);
		vehicleNoCombo.setForeground(Color.BLACK);
		
		slotNoText.setBorder(new LineBorder(Color.BLACK,2));
		vehicleNoCombo.setBorder(new LineBorder(Color.BLACK,2));
		
		JPanel slotNoPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		slotNoPanel.setOpaque(false);
		slotNoPanel.add(slotNoLabel);
		slotNoPanel.add(slotNoText);
		
		JPanel vehicleNoPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		vehicleNoPanel.setOpaque(false);
		vehicleNoPanel.add(vehicleNoLabel);
		vehicleNoPanel.add(vehicleNoCombo);
		
		eastPanel.add(slotNoPanel);
		eastPanel.add(vehicleNoPanel);
		imagePanel.add(eastPanel,BorderLayout.EAST);
		
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
		
		cancelButtonPanel.add(new JLabel(" "),BorderLayout.SOUTH);
		cancelButtonPanel.add(new JLabel(" "),BorderLayout.EAST);
		JButton confirmButton=new JButton("Confirm");
		
		confirmButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if(!billNoLabel2.equals("") && !dateLabel2.equals("") && !arrivedLabel2.equals("") && !leavingLabel2.equals("") && !slotLabel2.equals("") && !vehicleLabel2.equals("") && !paymentLabel2.equals("")){
					try{	
						ReservationController reservationController=ServerConnector.getServerConnector().getReservationController();
						
						Reservation reservation=null;
						for(Reservation reserve : searchReservedList){
							
							if(reserve.getBillNumber()==Integer.valueOf(billNoLabel2.getText())){
								reservation=reserve;
							}
						}
						reservation.setLeavingTime(leavingLabel2.getText());
						reservationController.releseSlot(reservation);
						JOptionPane.showMessageDialog(null,"Slot "+slotLabel2.getText()+" is release");
						reservationController.removeObserver(releaseObserver);
						Home.home.dispose();
						Home home=new Home();
						Home.home=home;
						home.setVisible(true);
						dispose();
						
					}catch(NotBoundException ex){
					
					}catch(RemoteException ex){
					
					}catch(MalformedURLException ex){
								
					}catch(ClassNotFoundException ex){
								
					}catch(IOException ex){
							
					}
					
				}else{
					JOptionPane.showMessageDialog(null,"Select vehicle..");
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
		
		
				//~ vehicleNoCombo.addKeyListener(new KeyAdapter(){
			//~ public void keyReleased(KeyEvent evt){
				//~ try{	
					//~ ReservationController reservationController=ServerConnector.getServerConnector().getReservationController();
					//~ 
					//~ ArrayList<Reservation> list=reservationController.getNotReleasedVehicles();
					//~ String[] reservedVehicleNoList=new String[list.size()];
					//~ 
					//~ int i=0;
					//~ for(Reservation reservation:list){
						//~ reservedVehicleNoList[i]=reservation.getVehicleNumber();
						//~ i++;
					//~ }
					//~ 
					//~ vehicleNoCombo=new MyComboBox<String>(reservedVehicleNoList);
					//~ vehicleNoCombo.setSelectedIndex(-1);
					//~ 
					//~ ComboSearch comboSearch=new ComboSearch();
					//~ comboSearch.search(vehicleNoCombo,false,"No vehicle found..");
					//~ ArrayList<Integer> list50Num=new ArrayList<Integer>();
					//~ 
					//~ for(int i=1;i<51;i++){
						//~ list50Num.add(i);
					//~ }
					
					
					//~ if(emptySlotList.contains(Integer.valueOf(slotNoText.getText()))){
						//~ JOptionPane.showMessageDialog(null,"Slot "+slotNoText.getText()+" is empty");
					//~ }else if(list50Num.contains(Integer.valueOf(slotNoText.getText()))){
						//~ for(Reservation reservation:list){
							//~ if(reservation.getSlotNumber()==Integer.valueOf(slotNoText.getText())){
								
							//~ }
						//~ }
					//~ }else{
						//~ JOptionPane.showMessageDialog(null,"Wrong slot number");
					//~ }
					
					
					//~ 
				//~ }catch(NotBoundException ex){
					//~ 
				//~ }catch(RemoteException ex){
					//~ 
				//~ }catch(MalformedURLException ex){
					//~ 
				//~ }catch(ClassNotFoundException ex){
					//~ 
				//~ }catch(IOException ex){
					//~ 
				//~ }
			//~ }
		//~ });
		//~ 
		
	}
	
	//~ public static void main(String []args){
		//~ new ReleseSlot().setVisible(true);
	//~ }
	
	
	public void updateSlots(int slotNumber) {
        
        //JOptionPane.showMessageDialog(null,"Slot "+slotNumber+" released..");
    }
}

class MyComboBox<E> extends JComboBox<E> {

    MyComboBox(E[] list) {
        super(list);
        this.setEditable(true);
        this.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                return new JButton() {
                    @Override
                    public int getWidth() {
                        return 0;
                    }
                };
            }
        });
        this.remove(this.getComponent(0));
    }
}

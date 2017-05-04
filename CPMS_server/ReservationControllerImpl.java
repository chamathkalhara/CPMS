package controllerImpl;

import java.rmi.*;
import java.rmi.server.*;
import controller.*;
import model.*;
import observe.Observer;
import java.util.*;
import dataAccess.*;
import reserve.*;
import java.io.*;
import observable.*;

public class ReservationControllerImpl extends UnicastRemoteObject implements ReservationController{
	
	private ReservationDataAccess reservationDataAccess=new ReservationDataAccess();
	private static SlotReserver slotReserver=new SlotReserver();
	private static ReservationObservable reservationObservable=new ReservationObservable();
	
	public ReservationControllerImpl() throws RemoteException{
		
	}
	
	public void reserveSlot(final Reservation reseve) throws RemoteException, ClassNotFoundException, IOException{
		reservationDataAccess.reserveSlot(reseve);
		
		new Thread(){
			public void run(){
				try{
					reservationObservable.slotUpdated(reseve.getSlotNumber());
					
				}catch(RemoteException ex){
					
				}
			}
		}.start();
		
	}
	
	public void releseSlot(final Reservation relese) throws RemoteException, ClassNotFoundException,FileNotFoundException,IOException{
		reservationDataAccess.releseSlot(relese);
		
		new Thread(){
			public void run(){
				try{
					reservationObservable.slotUpdated(relese.getSlotNumber());
					
				}catch(RemoteException ex){
					
				}
			}
		}.start();
	}
	
	public ArrayList<Reservation> searchByDate(String date) throws RemoteException, ClassNotFoundException, IOException{
		return reservationDataAccess.searchByDate(date);
	}
	
	public ArrayList<Reservation> searchByVehicleNumber(String vehicleNumber) throws RemoteException, ClassNotFoundException, IOException{
		return reservationDataAccess.searchByVehicleNumber(vehicleNumber);
	}
	
	public ArrayList<Reservation> getNotReleasedVehicles() throws RemoteException, ClassNotFoundException,IOException{
		return reservationDataAccess.getNotReleasedVehicles();
	}
	
	public ArrayList<Integer> getAvailableSlots() throws RemoteException, ClassNotFoundException,IOException{
		return reservationDataAccess.getAvailableSlots();
	}
	
	public int getNewBillNumber()throws RemoteException, FileNotFoundException,IOException{
		return reservationDataAccess.getNewBillNumber();
	}
	
	public void addObserver(Observer observer)throws RemoteException{
		reservationObservable.addObserver(observer);
	}
	
	public void removeObserver(Observer observer)throws RemoteException{
		reservationObservable.removeObserver(observer);
	}
	
	public boolean reserveSlot(String slotNumber)throws RemoteException{
		return slotReserver.reserveSlot(slotNumber,this);
	}
	
	public boolean releseSlot(String slotNumber)throws RemoteException{
		return slotReserver.releseSlot(slotNumber,this);
	}
	
	public String getPassword(int id)throws RemoteException, ClassNotFoundException, IOException{
		return reservationDataAccess.getPassword(id);
	}
	
	public void addNewUser(User user)throws RemoteException, ClassNotFoundException, IOException{
		reservationDataAccess.addNewUser(user);
	}
}

package controller;

import java.rmi.*;
import model.*;
import java.util.*;
import observe.Observer;
import java.io.*;

public interface ReservationController extends Remote{
		
		public void reserveSlot(final Reservation reseve) throws RemoteException, ClassNotFoundException, IOException;
		
		public void releseSlot(final Reservation relese) throws RemoteException, ClassNotFoundException,FileNotFoundException,IOException;
		
		public ArrayList<Reservation> searchByDate(String date) throws RemoteException, ClassNotFoundException, IOException;
		
		public ArrayList<Reservation> searchByVehicleNumber(String vehicleNumber) throws RemoteException, ClassNotFoundException, IOException;
		
		public ArrayList<Reservation> getNotReleasedVehicles() throws RemoteException, ClassNotFoundException,IOException;
		
		public ArrayList<Integer> getAvailableSlots() throws RemoteException, ClassNotFoundException,IOException;
		
		public int getNewBillNumber()throws RemoteException, FileNotFoundException,IOException;
		
		public void addObserver(Observer observer)throws RemoteException;
		
		public void removeObserver(Observer observer)throws RemoteException;
		
		public boolean reserveSlot(String slotNumber)throws RemoteException;
		
		public boolean releseSlot(String slotNumber)throws RemoteException;
		
		public String getPassword(int id)throws RemoteException, ClassNotFoundException, IOException;
		
		public void addNewUser(User user)throws RemoteException, ClassNotFoundException, IOException;
		
}

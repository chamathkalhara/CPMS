package observer;

import java.rmi.server.*;
import observe.Observer;
import reservation.*;
import java.rmi.*;

public class ReserveObserver extends UnicastRemoteObject implements Observer{
	
	private ReserveSlot reserveSlot;
	
	public ReserveObserver(ReserveSlot reserveSlot)throws RemoteException{
		this.reserveSlot=reserveSlot;
	}
	
	public void update(int slotNumber)throws RemoteException{
		reserveSlot.updateSlots(slotNumber);
	}
}


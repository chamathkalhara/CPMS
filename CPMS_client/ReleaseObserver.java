package observer;

import java.rmi.server.*;
import observe.Observer;
import reservation.*;
import java.rmi.*;

public class ReleaseObserver extends UnicastRemoteObject implements Observer{
	
	private ReleseSlot releseSlot;
	
	public ReleaseObserver(ReleseSlot releseSlot)throws RemoteException{
		this.releseSlot=releseSlot;
	}
	
	public void update(int slotNumber)throws RemoteException{
		releseSlot.updateSlots(slotNumber);
	}
}

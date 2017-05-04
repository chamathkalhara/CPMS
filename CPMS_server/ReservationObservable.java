package observable;

import java.util.*;
import java.rmi.*;
import model.*;
import observe.Observer;

public class ReservationObservable{
	
	private ArrayList<Observer> observers;
	
	public ReservationObservable(){
		observers=new ArrayList<>();
	}
	
	public void addObserver(Observer observer){
        observers.add(observer);
    }
    
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }
    
    private void notifyObservers(int slotNumber) throws RemoteException{
        for (Observer observer : observers) {
            observer.update(slotNumber);
        }
    }
    
    public void slotUpdated(int slotNumber)throws RemoteException{
		notifyObservers(slotNumber);
	}
}

package observe;

import java.rmi.*;

public interface Observer extends Remote{
		
		public void update(int slotNumber) throws RemoteException;
}

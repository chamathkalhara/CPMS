package other;

import dataAccess.*;
import java.io.*;
import java.rmi.*;

public class AutoGenarateBillNumber{
	
	public static int genarate()throws RemoteException, FileNotFoundException,IOException{
		
		int lastBillNumber=new ReservationDataAccess().getLastBillNumber();
		return lastBillNumber+1;
	}
}

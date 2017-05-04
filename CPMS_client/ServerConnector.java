package connector;

import controller.*;
import java.rmi.*;
import java.net.*;

public class ServerConnector{
	
	private static ServerConnector serverConnector;
	private ReservationController reservationController;
	
	private ServerConnector()throws NotBoundException,MalformedURLException,RemoteException{
		if(reservationController==null){
			reservationController=(ReservationController)Naming.lookup("rmi://localhost:6050/ReservationServer");
		}
	}
	
	public static ServerConnector getServerConnector()throws NotBoundException,MalformedURLException,RemoteException{
		if(serverConnector==null){
			serverConnector=new ServerConnector();
		}
		return serverConnector;
	}
	
	public ReservationController getReservationController(){
		return reservationController;
	}
}

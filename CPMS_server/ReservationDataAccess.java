package dataAccess;

import java.rmi.*;
import java.rmi.server.*;
import model.*;
import observe.Observer;
import java.util.*;
import java.util.concurrent.locks.*;
import java.io.*;
import java.text.*;

public class ReservationDataAccess{
	
	public static ReentrantReadWriteLock rwLock=new ReentrantReadWriteLock();
	
	
	public void reserveSlot(Reservation reseve) throws RemoteException, ClassNotFoundException, IOException{
		try {
            rwLock.writeLock().lock();
			FileWriter fileWriter=new FileWriter("data/Reservation.txt",true);
			BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
			
			NumberFormat numberFormat = NumberFormat.getNumberInstance();
			numberFormat.setMinimumIntegerDigits(5);
			String value = numberFormat.format(reseve.getBillNumber());
			String[] newID = value.split(",");
			String newValue = "";
			for (String next : newID) {
				newValue += next;
			}
			
			String line=newValue+","+reseve.getDate()+","+reseve.getArrivedTime()+","+Integer.toString(reseve.getSlotNumber())+","+"Stil not leaved"+","+Double.toString(reseve.getPayment())+","+reseve.getVehicleNumber()+";";
			bufferedWriter.write(line);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			
		} finally {
			rwLock.writeLock().unlock();
        }
	}
	public void releseSlot(Reservation relese) throws RemoteException, ClassNotFoundException,FileNotFoundException,IOException{
		
		try {
            rwLock.writeLock().lock();
			FileReader fileReader=new FileReader("data/Reservation.txt");
			BufferedReader bufferedReader=new BufferedReader(fileReader);
				
			String read="";
			String line="";
			int count=0;
			while ((line=bufferedReader.readLine())!=null && line!="\n") {                
				read+=line;
				count++;
			}
			
			
			NumberFormat numberFormat = NumberFormat.getNumberInstance();
			numberFormat.setMinimumIntegerDigits(5);
			String value = numberFormat.format(relese.getBillNumber());
			String[] newID = value.split(",");
			String newValue = "";
			for (String next : newID) {
				newValue += next;
			}
			
			String newLine=newValue+","+relese.getDate()+","+relese.getArrivedTime()+","+Integer.toString(relese.getSlotNumber())+","+relese.getLeavingTime()+","+Double.toString(relese.getPayment())+","+relese.getVehicleNumber();
			
			String[] lines=read.split(";");
			
			for(int i=0;i<count-1;i++){
				if(lines[i].contains(newValue)){
					lines[i]=newLine;
				}
			}
			
			FileWriter fileWriter=new FileWriter("data/Reservation.txt");
			
			for(String newLines : lines){
				if(!newLines.equals("\n")){
					fileWriter.write(newLines+";\n");
				}
			}
			
			fileWriter.flush();
			
		} finally {
			rwLock.writeLock().unlock();
        }
	}
	public ArrayList<Reservation> searchByDate(String date) throws RemoteException, ClassNotFoundException, IOException{
		
		try {
            rwLock.readLock().lock();
			FileReader fileReader=new FileReader("data/Reservation.txt");
			BufferedReader bufferedReader=new BufferedReader(fileReader);
			
			
			String read="";
			String readLine=bufferedReader.readLine();
			
			
			while (readLine!=null) {                
				read=read.concat(readLine);
				readLine=bufferedReader.readLine();
			}
			
			
			String[] lines=read.split(";");
			ArrayList<Reservation> list=new ArrayList<Reservation>();
			for(String line:lines){
				if(line.contains(date)){
					String []datas=line.split(",");
					list.add(new Reservation(Integer.valueOf(datas[0]),datas[1],datas[2],Integer.valueOf(datas[3]),datas[4],Double.valueOf(datas[5]),datas[6]));	
				}
			}
			return list;
			
		} finally {
			rwLock.readLock().unlock();
        }
	}
	public ArrayList<Reservation> searchByVehicleNumber(String vehicleNumber) throws RemoteException, ClassNotFoundException, IOException{
		
		try{
			rwLock.readLock().lock();
			FileReader fileReader=new FileReader("data/Reservation.txt");
			BufferedReader bufferedReader=new BufferedReader(fileReader);
			
			String read="";
			String readLine=bufferedReader.readLine();
			while (readLine!=null) {                
				read=read.concat(readLine);
				readLine=bufferedReader.readLine();
			}
			String[] lines=read.split(";");
			ArrayList<Reservation> list=new ArrayList<Reservation>();
			for(String line:lines){
				if(line.contains(vehicleNumber)){
					String []datas=line.split(",");
					list.add(new Reservation(Integer.valueOf(datas[0]),datas[1],datas[2],Integer.valueOf(datas[3]),datas[4],Double.valueOf(datas[5]),datas[6]));	
				}
			}
			return list;
		}finally{
			rwLock.readLock().unlock();
		}
	}
	
	public ArrayList<Reservation> getNotReleasedVehicles() throws RemoteException, ClassNotFoundException,IOException{
		
		try{
			rwLock.readLock().lock();
			FileReader fileReader=new FileReader("data/Reservation.txt");
			BufferedReader bufferedReader=new BufferedReader(fileReader);
			
			String read="";
			String readLine=bufferedReader.readLine();
			while (readLine!=null) {                
				read=read.concat(readLine);
				readLine=bufferedReader.readLine();
			}
			
			String[] lines=read.split(";");
			ArrayList<Reservation> list=new ArrayList<Reservation>();
			for(String line:lines){
				if(line.contains("Stil not leaved")){
					String []datas=line.split(",");
					Reservation reservation=new Reservation(Integer.valueOf(datas[0]),datas[1],datas[2],Integer.valueOf(datas[3]),datas[4],Double.valueOf(datas[5]),datas[6]);	
					list.add(reservation);
				}
			}
			
			return list;
		}finally{
			rwLock.readLock().unlock();
		}
	}
	
	public ArrayList<Integer> getAvailableSlots() throws RemoteException, ClassNotFoundException,IOException{
		ArrayList<Reservation> list=getNotReleasedVehicles();
		ArrayList<Integer> intList=new ArrayList<Integer>();
		
		ArrayList<Integer> reserveList=new ArrayList<Integer>();
		
		for(Reservation reservation:list){
			reserveList.add(reservation.getSlotNumber());
		}
		for(int j=1;j<51;j++){
			if(!reserveList.contains(j)){
				intList.add(j);
			}
		}
		
		return intList;
	}
	
	public int getNewBillNumber()throws RemoteException, FileNotFoundException,IOException{
		
		FileReader fileReader=new FileReader("data/Reservation.txt");
		BufferedReader bufferedReader=new BufferedReader(fileReader);
		
		String lastBillNumber="";
		String readLine=bufferedReader.readLine();
				
		while (readLine!=null && readLine!="\n") { 
			             
			lastBillNumber=readLine.substring(0,5);
			readLine=bufferedReader.readLine();
		}
		if(lastBillNumber.equals("")){
			
			return 00001;
		}else{
			return Integer.valueOf(lastBillNumber)+1;
		}
	}
	
	public String getPassword(int id)throws RemoteException, ClassNotFoundException, IOException{
		FileReader fileReader=new FileReader("data/UserData.txt");
		BufferedReader bufferedReader=new BufferedReader(fileReader);
			
			String read="";
			String readLine=bufferedReader.readLine();
			while (readLine!=null) {                
				read=read.concat(readLine);
				readLine=bufferedReader.readLine();
			}
			
			String[] lines=read.split(";");
			String password="";
			
			for(String line:lines){
				if(line.startsWith(String.valueOf(id))){
					String []datas=line.split(",");
					password=datas[2];
					break;
				}
			}
			
			return password;
	}
	
	public void addNewUser(User user)throws RemoteException, ClassNotFoundException, IOException{
		FileWriter fileWriter=new FileWriter("data/UserData.txt",true);
		BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
		
		String line=Integer.toString(user.getId())+","+user.getName()+","+user.getPassword()+","+user.getState()+";";
		bufferedWriter.write(line);
		bufferedWriter.newLine();
		bufferedWriter.flush();
	}
	
}

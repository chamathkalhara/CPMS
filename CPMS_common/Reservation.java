package model;

import java.io.Serializable;

/**
 * @author Chamath
 */

public class Reservation implements Serializable{
	
	private int billNumber;
	private String date;
	private String arrivedTime;
	private int slotNumber;
	private String leavingTime;
	private double payment;
	private String vehicleNumber;
		
	public Reservation() {
   
    }

    public Reservation(int billNumber, String date, String arrivedTime, int slotNumber, String leavingTime, double payment, String vehicleNumber) {
        this.billNumber = billNumber;
        this.date = date;
        this.arrivedTime = arrivedTime;
        this.slotNumber = slotNumber;
        this.leavingTime = leavingTime;
        this.payment = payment;
        this.vehicleNumber = vehicleNumber;
    }

    public int getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(int billNumber) {
        this.billNumber = billNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArrivedTime() {
        return arrivedTime;
    }

    public void setArrivedTime(String arrivedTime) {
        this.arrivedTime = arrivedTime;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public String getLeavingTime() {
        return leavingTime;
    }

    public void setLeavingTime(String leavingTime) {
        this.leavingTime = leavingTime;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}

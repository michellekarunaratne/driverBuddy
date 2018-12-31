package com.example.michelle.driverbuddy;

import java.util.Date;

public class FineTicket {

    String vehicleNumber;
    int amount;
    SpotFine fine[];
    Driver driver[];
    Police police[];
    Date timeStamp;
    String nic;
    String policeId;
    String fineName;
    int paid;

    public FineTicket(String vehicleNumber, int amount, SpotFine[] fine, Driver[] driver, Police[] police, Date timeStamp) {
        this.vehicleNumber = vehicleNumber;
        this.amount = amount;
        this.fine = fine;
        this.driver = driver;
        this.police = police;
        this.timeStamp = timeStamp;
    }

    public FineTicket(String vehicleNumber, int amount, SpotFine[] fine, Driver[] driver, Police[] police, Date timeStamp, String nic, String policeId, String fineName, int paid) {
        this.vehicleNumber = vehicleNumber;
        this.amount = amount;
        this.fine = fine;
        this.driver = driver;
        this.police = police;
        this.timeStamp = timeStamp;
        this.nic = nic;
        this.policeId = policeId;
        this.fineName = fineName;
        this.paid = paid;
    }

    public FineTicket(String vehicleNumber, int amount, String nic, String policeId, String fineName) {
        this.vehicleNumber = vehicleNumber;
        this.amount = amount;
        this.nic = nic;
        this.policeId = policeId;
        this.fineName = fineName;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        amount = amount;
    }

    public SpotFine[] getFine() {
        return fine;
    }

    public void setFine(SpotFine[] fine) {
        this.fine = fine;
    }

    public Driver[] getDriver() {
        return driver;
    }

    public void setDriver(Driver[] driver) {
        this.driver = driver;
    }

    public Police[] getPolice() {
        return police;
    }

    public void setPolice(Police[] police) {
        this.police = police;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}

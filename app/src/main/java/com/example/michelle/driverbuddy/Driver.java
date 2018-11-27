package com.example.michelle.driverbuddy;

public class Driver {

    String nic;
    String firstName;
    String lastName;
    int mobile;
    String email;
    int license;

    public Driver(String firstName, String lastName, String email, String nic, int license, int mobile) {
        this.nic = nic;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.license = license;
    }

    public String getNic() {
        return nic;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public int getLicense() {
        return license;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLicense(int license) {
        this.license = license;
    }
}


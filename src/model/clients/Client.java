package model.clients;


public class Client {
    private int clientId;
    
    public int getClientId() {
        return clientId;
    }
    private String firstName;
    private String surName;
    private String lastName;
    private String phoneNumber;
    
    public Client(int clientId, String firstName, String phoneNumber) {
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
    }
    public Client(int clientId, String firstName, String surName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.surName = surName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getSurName() {
        return surName;
    }
    public void setSurName(String surName) {
        this.surName = surName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString() {
        return this.lastName + " " + this.firstName.charAt(0) + "." + this.surName.charAt(0) + ".";        
    }
    
    
    
}

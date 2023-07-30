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
    
    
    public Client(int clientId, String lastName, String phoneNumber) {
        this.clientId = clientId;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
    
    public Client(int clientId,String firstName, String lastName, String phoneNumber) {
        this(clientId, lastName, phoneNumber);
        this.firstName = firstName;
    }


    public Client(int clientId, String firstName, String surName, String lastName, String phoneNumber) {
        this(clientId, firstName, lastName, phoneNumber);
        this.surName = surName;
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
        return String.format("%s %s.(%s)", this.lastName, this.firstName.charAt(0), this.phoneNumber); 
    }
    
    
    
}

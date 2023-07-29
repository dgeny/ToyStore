package model.clients;
import java.util.ArrayList;


public class ClientList {

    private ArrayList<Client> clients;
    public Client addClient(String firstName, String lastName, String phoneNumber){
        Client cl = new Client(generateId(firstName, lastName, phoneNumber), firstName ,phoneNumber);
        clients.add(cl);
        return cl;
    }

    private int generateId(String firstName, String lastName, String phoneNumber){
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
        return result;
    }
}

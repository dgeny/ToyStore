package model.clients;
import java.util.ArrayList;



public class ClientList implements Iterable<Client> {

    private final ArrayList<Client> clients = new ArrayList<>();
    public Client addClient(String firstName, String lastName, String phoneNumber){
        Client cl = new Client(generateId(firstName, lastName, phoneNumber), firstName, lastName ,phoneNumber);
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

    @Override
    public ClientIterator iterator() {
        return new ClientIterator(this.clients);
    }

    @Override
    public String toString() {
        return "Список пользователей: " + this.clients.size() + " пользователей";
    }

    public int getCount(){
        return this.clients.size();
    }

    public Client getClientByIndex(int index){
        return this.clients.get(index);
    }
}

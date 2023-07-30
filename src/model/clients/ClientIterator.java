package model.clients;

import java.util.ArrayList;
import java.util.Iterator;

public class ClientIterator implements Iterator<Client>{

    private ArrayList<Client> list;
    private int currentIndex;

    public ClientIterator(ArrayList<Client> clients) {
        this.list = clients;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < this.list.size();
    }

    @Override
    public Client next() {
        if(hasNext()){
            return this.list.get(currentIndex++);
        }
        throw new RuntimeException("Client out of range");
    }

}

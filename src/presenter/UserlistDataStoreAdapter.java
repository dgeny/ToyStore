package presenter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import model.clients.Client;
import model.clients.ClientList;

public class UserlistDataStoreAdapter {
    private String filePath;

    public UserlistDataStoreAdapter(String filePath) {
        this.filePath = filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void saveData(ClientList list) throws IOException{
        File f= new File(this.filePath);
        if(!f.isDirectory()){
            Writer wr = new FileWriter(this.filePath);
            Gson gson = new Gson();
            ArrayList<Client> clientsData = new ArrayList<>();
            for (Client client : list) {
                clientsData.add(client);
            }
            gson.toJson(clientsData,new TypeToken<ArrayList<Client>>(){}.getType(),wr);
            wr.close();
        }
    }

    public ClientList loadData() throws IOException{
        File f= new File(this.filePath);
        ClientList list  = new ClientList();
        if(f.exists() && !f.isDirectory()){
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(f));
            ArrayList<Client> clientsData = gson.fromJson(reader,new TypeToken<ArrayList<Client>>(){}.getType());
            
            for (int i = 0; i < clientsData.size(); i++) {
                
                list.addClient(clientsData.get(i).getFirstName(), 
                    clientsData.get(i).getLastName(), clientsData.get(i).getPhoneNumber());
            }
            reader.close();
        }
        return list;
    }
}

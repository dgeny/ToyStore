package model.actions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.clients.Client;
import model.clients.ClientList;
import model.goods.Catalog;
import model.goods.CatalogItem;

public class LotteryEvent implements LotteryAction {

    /**
     * Название события
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Дата начала события
     */
    private LocalDateTime periodStart;

    public LocalDateTime getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(LocalDateTime periodStart) {
        this.periodStart = periodStart;
    }

    /**
     * Дата окончания события
     */
    private LocalDateTime periodEnd;

    public LocalDateTime getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(LocalDateTime periodEnd) {
        this.periodEnd = periodEnd;
    }

    private ClientList participants = new ClientList();
    public void addParticipant(String firstName, String lastName, String phoneNumber){
        this.participants.addClient(firstName, lastName, phoneNumber);
    }

    @Override
    public void setParticipants(ClientList participants) {
        this.participants = participants;
    }

    @Override
    public String getParticipants() {
        StringBuilder sb = new StringBuilder();
        for (Client client : this.participants) {
            sb.append(client.toString());
            sb.append('\n');
        }
        return sb.toString();
    }

    private Catalog prizeFund;
    @Override
    public void setPrizeFund(Catalog goods) {
        this.prizeFund = goods;
    }

    @Override
    public String prizeFundToString() {
        StringBuilder sb = new StringBuilder();
        for (CatalogItem produCatalogItem : this.prizeFund) {
            sb.append(produCatalogItem.toString() + '\n');
        }
        return sb.toString();
    }

   
    private HashMap<Client, Catalog> winners = new HashMap<Client, Catalog>();

    @Override
    public void applyAction() {
        for (CatalogItem prize : this.prizeFund) {
            while (prize.getAmount() > 0) {
                int winner = (int) ( Math.random() * this.participants.getCount() );
                Client clWin = this.participants.getClientByIndex(winner);
                Catalog wins;
                if(this.winners.containsKey(clWin)){
                    wins = this.winners.get(clWin);
                }
                else {
                    wins = new Catalog();
                    this.winners.put(clWin, wins);
                }
                wins.addProductAmount(new CatalogItem(prize), 1);
                prize.setAmount(prize.getAmount() - 1);
            }        
        }
    }
        
    @Override
    public String showWinners() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Client, Catalog> item : this.winners.entrySet()) {
            sb.append(item.getKey().toString() + "\t");
            for (CatalogItem prize : item.getValue()) {
                sb.append(String.format("%s\t%s", prize.getInfo(), prize.getAmount()));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public ArrayList<Client> getWinners(){
    
        return new ArrayList<Client>(this.winners.keySet());
    }

    public ArrayList<CatalogItem> getAWin(Client client){
        ArrayList<CatalogItem> retWins = new ArrayList<>();
        if(this.winners.containsKey(client)){
            Catalog wins = this.winners.remove(client);
            for (CatalogItem catalogItem : wins) {
                retWins.add(catalogItem);
            }
        }
        return retWins;
    }

    @Override
    public String getPrizeFund() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPrizeFund'");
    }
}
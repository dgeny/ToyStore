package model.actions;

import model.clients.ClientList;
import model.goods.Catalog;

public interface LotteryAction extends Actions{
    public void setPrizeFund(Catalog goods);
    public String getPrizeFund();
    public String prizeFundToString();
    public void setParticipants(ClientList participants);
    public String getParticipants();
    public String showWinners();
}

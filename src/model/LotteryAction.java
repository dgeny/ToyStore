package model;

import java.util.ArrayList;

public interface LotteryAction extends Actions{
    public void setPrizeFund(Catalog array);
    public String getPrizeFund();
    public void setParticipants(ArrayList array);
    public String getParticipants();
    public String showWinners();
}

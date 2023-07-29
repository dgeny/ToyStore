package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

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

    @Override
    public void applyAction() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'applyAction'");
    }

    @Override
    public void setPrizeFund(ArrayList array) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPrizeFund'");
    }

    @Override
    public String getPrizeFund() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPrizeFund'");
    }

    @Override
    public void setParticipants(ArrayList array) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setParticipants'");
    }

    @Override
    public String getParticipants() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getParticipants'");
    }

    @Override
    public String showWinners() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showWinners'");
    }

}

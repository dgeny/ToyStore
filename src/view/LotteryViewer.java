package view;

public class LotteryViewer {

    public void printLotteryName(String name, String startDate, String endDate) {
        System.out.println(String.format("Внимание лотерея %s проведена в период с %s по %s.", 
            name, startDate, endDate));
    }

    public void printLotteryPrizes(String prizeFundToString) {
        System.out.println("Призовой фонд лотереи:\n" + prizeFundToString);
    }

    public void printLotteryWinners(String winners) {
        System.out.println("Список победителей лотереи:\n"+ winners + "\n Поздравляем участников!");
    }

    
}

import presenter.LotteryPresenter;

public class App {
    public static void main(String[] args) throws Exception {
        LotteryPresenter presenter = new LotteryPresenter("test_data");
        presenter.runExampleLottery();
    }
}

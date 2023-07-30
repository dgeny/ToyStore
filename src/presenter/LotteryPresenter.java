package presenter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

import model.Store;
import model.Toy;
import model.ToyStore;
import model.actions.LotteryEvent;
import model.clients.Client;
import model.clients.ClientList;
import model.goods.Catalog;
import model.goods.CatalogItem;
import model.goods.Product;
import model.goods.ProductCategory;
import model.goods.MeasureUnit;
import view.LotteryViewer;

public class LotteryPresenter {
    private String dataPath; 
    LotteryViewer viewer;
    LotteryEvent event;
    private ArrayList<Double> probability;
    
    public LotteryPresenter(String dataPath) throws Exception {
        this.dataPath = dataPath;
        this.viewer  = new LotteryViewer();
        this.event = new LotteryEvent();
        this.probability = new ArrayList<>();
    }

    public void run() {
        
    }

    public void runExampleLottery(){
        this.event.setName("Супер-лотерея");
        UserlistDataStoreAdapter ad = new UserlistDataStoreAdapter(dataPath + "/user_list.json");
        ClientList clientList;
        try {
            clientList = ad.loadData();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        /* for(int i=0; i<3; i++)
            this.event.addParticipant(
                clientList.getClientByIndex(i).getFirstName(), 
                clientList.getClientByIndex(i).getLastName(), 
                clientList.getClientByIndex(i).getPhoneNumber()); */
        this.event.setParticipants(clientList);

        Store store = createExampleStore();
        Catalog allGoods = createExampleStoreCatalog();
        store.addStoreCategory(ProductCategory.toys);
        for (CatalogItem catalogItem : allGoods) {
            try{
                store.receiptOfProduct(catalogItem, catalogItem.getPrice(), catalogItem.getAmount());
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }               
        }
        this.event.setPrizeFund(generatePrizeFund(store, 20));
        this.event.setPeriodStart(LocalDateTime.now());
        this.event.setPeriodEnd(this.event.getPeriodStart());
        this.viewer.printLotteryName(this.event.getName(), 
            this.event.getPeriodStart().toLocalDate().toString(), 
            this.event.getPeriodEnd().toLocalDate().toString());
        this.viewer.printLotteryPrizes(this.event.prizeFundToString());
        this.event.applyAction();
        this.viewer.printLotteryWinners(this.event.showWinners());
        giveAllPrizes(this.dataPath + "/prizes.log");
    }

    private void giveAllPrizes(String giveLogPath){
        File f = new File(giveLogPath);
        if(f.isDirectory()) {
            throw new RuntimeException("Проверьте правильность настроек журнала выдачи призов!");
        }

        try {
            FileWriter fwr = new FileWriter(f, true);
            
            fwr.write(String.format("Выдача призов в лотерее %s (от  %s ) началась!!!",this.event.getName(), this.event.getPeriodEnd().toString()));
            ArrayList<Client> winners = this.event.getWinners();
            for (int i = 0; i < winners.size(); i++) {
                ArrayList<CatalogItem> prizes = this.event.getAWin(winners.get(i));
                if(prizes.size() == 0)
                {
                    fwr.write("Призы победителю " + winners.get(i).toString() + " уже выданы. Участвуйте ещё!\n");
                    //
                }
                else {
                    fwr.write("Выдача призов победителю " + winners.get(i).toString() + "началась:\n");
                    for (CatalogItem el : prizes) {
                        fwr.write(String.format("- выдается %s в количестве %s\n", el.getProduct().getShortName(), (int)el.getAmount()));
                    }
                }

                fwr.flush();
            }

            ArrayList<CatalogItem> prizes = this.event.getAWin(winners.get(0));
                if(prizes.size() == 0)
                {
                    fwr.write("Призы победителю " + winners.get(0).toString() + " уже выданы. Участвуйте ещё!\n");
                    //
                }
                else {
                    fwr.write("Выдача призов победителю " + winners.get(0).toString() + "началась:\n");
                    for (CatalogItem el : prizes) {
                        fwr.write(String.format("- выдается %s в количестве %s\n", el.getProduct().getShortName(), (int)el.getAmount()));
                    }
                }

            fwr.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }
        
    }

    private Store createExampleStore(){
        Store store = new ToyStore(1, "Store with lottery");
        store.setWebAddress("http://internetstore.example.com");
        return store;
    }
    

    /**
     * Генерация призового фонда.
     * Условия: призовой фонд должен состоять из товаров магазина. Он фиксирован, поэтому
     * вероятность выпадения любой игрушки пропорцинальна количеству этой игрушки в призовом фонде.
     * При этом, если количество игрушек не целое число, то это решается в пользу магазина 
     * отбрасыванием дробной части. 
     * Сначала получим список игрушек, далее установим вероятность выпадения каждой из них.
     * Сумма вероятностей == 1, некоторые игрушки в лотерее не участвуют.  
     * @param store магазин, проводящий лотерею
     * @param prizeCount количество призов
     * @return
     */
    private Catalog generatePrizeFund(Store store, int prizeCount) {
        Catalog prizes = new Catalog();
        ArrayList<ProductCategory> prizeCats = new ArrayList<>();
        prizeCats.add(ProductCategory.toys);
        ArrayList<CatalogItem> listOfGoods = store.getProductsByCategories(prizeCats);
        for(int i=0; i<listOfGoods.size();i++){
            int amountPrize = (int)(this.probability.get(i)*prizeCount);
            if(amountPrize > 0){
                prizes.addProduct(new CatalogItem(listOfGoods.get(i)));
                try {
                    store.removeProductAmount(listOfGoods.get(i), prizeCount);
                    prizes.addProductAmount(listOfGoods.get(i), amountPrize);
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
        return prizes;
    }

    private Catalog createExampleStoreCatalog(){
        Catalog exampleCatalog = new Catalog();
        ArrayList<Product> goods = createExampleProductList();

        for (int i = 0; i < goods.size(); i++) {
            goods.get(i).setCategory(ProductCategory.toys);
            goods.get(i).setUnit(MeasureUnit.piece);
            CatalogItem item = new CatalogItem<Product>(goods.get(i), Math.random() * 10000);
            item.setAmount((int)(Math.random() * 5000));
            exampleCatalog.addProduct(item);
        }
        return exampleCatalog;
    }


    private ArrayList<Product> createExampleProductList(){
        ArrayList<Product> goods = new ArrayList<>(); 
        goods.add(new Toy(1, "Бумажный кролик"));
        goods.add(new Toy(2, "Машинка с красными колесами"));
        goods.add(new Toy(3, "Железная дорога"));
        goods.add(new Toy(4, "Плюшевый мишка"));
        goods.add(new Toy(5, "Кубики"));
        goods.add(new Toy(6, "Кукла Катя"));
        goods.add(new Toy(7, "Слоник"));
        this.probability.add(0.125d);//12,5%
        this.probability.add(0.125d);//12,5%
        this.probability.add(0d);
        this.probability.add(0d);
        this.probability.add(0.5d);//50%
        this.probability.add(0.125d);//12,5%
        this.probability.add(0.125d);//12,5%
        return goods;
    }

}

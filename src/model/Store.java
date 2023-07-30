package model;

import java.util.ArrayList;

import model.goods.Catalog;
import model.goods.CatalogItem;
import model.goods.ProductCategories;
import model.goods.ProductCategory;



public abstract class Store<T> {
    
    /**
     * Конструктор
     * @param id идентификатор магазина
     * @param name название магазина
      */
    public Store(int id, String name) {
        this.id = id;
        this.name = name;
        this.categories = new ProductCategories();
        this.catalog = new Catalog();
    }

    /**
     * Идентификатор магазина
      */
    private int id;

    public int getId() {
        return id;
    }

    /**
     * Название магазина
      */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Адрес магазина
      */
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Страница магазина в интернет
      */
    private String webAddress;

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    protected Catalog catalog;
        
    /**
     * Добавить товар в магазин
     * @param product товар в виде элемента каталога
     * @param price цена на момент поступления
     * @param amount количество товара в единицах измерения
     * @throws Exception если товар в неразрешенных категориях
     */
    public void receiptOfProduct(CatalogItem product, double price, double amount) throws Exception{
        if(categories.findCategory(product.getCategory()) > -1){
            this.catalog.addProduct(product);
            this.catalog.changeProductPrice(product, price);
            this.catalog.addProductAmount(product, amount);
        }
        else{
            throw new RuntimeException("Product not in permitted categories");
        }
    }

    /**
     * Убрать некоторое количество товара из магазина, можно использовать при реализации продажи товара
     * @param product товар в виде элемента каталога
     * @param amount количество товара в единицах измерения
     * @throws Exception если товара недостаточно или он не найден
     */
    public void removeProductAmount(CatalogItem product, double amount) throws Exception{
        this.catalog.removeProductAmount(product, amount);
    }

    /**
     * Список категорий товара в каталоге.
     * Полезно применять при фильтрации, акциях и прочих обращениях 
     */
    protected ProductCategories categories;
    
    
    /**
     * Разрешить категорию товара для магазина 
     * @param category 
     */
    public void addStoreCategory(ProductCategory category){
        this.categories.addProductCategory(category);
    }


    /**
     * Пример фильтрации по категориям, не использовать
     * @param categories
     * @return
     */
    public ArrayList<CatalogItem> getProductsByCategories(ArrayList<ProductCategory> categories){
        ArrayList<CatalogItem> returnResult = new ArrayList<>();
        for (CatalogItem item : catalog) {
            if(categories.contains(item.getCategory())){
                returnResult.add(item);
            }
        }
        return returnResult;
    }

    /**
     * @return не реализованный метод
     */
    public ArrayList<CatalogItem> findProductByName(){
        // TODO: product by name filter 
        throw new UnsupportedOperationException("Unimplemented method 'findProductByName'");
    }


}

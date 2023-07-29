package model.goods;

import java.util.ArrayList;

public class Catalog implements Iterable<CatalogItem> {
    /**
     * Товар в магазине
     */
    private ArrayList<CatalogItem> products;
    /**
     * Внутренний метод для поиска товара
     * @param pItem элемент для поиска
     * @return индекс элемента в каталоге
     * @throws Exception генерирует исключение, если элемент не найден
      */
    protected int findProduct(CatalogItem<Product> pItem) throws Exception{
        for (int i = 0; i < this.products.size(); i++) {
            if(this.products.get(i).equals(pItem)){
                return i;
            }
        }
        throw new Exception("Catalog item not found");
    }
    /**
     * Добавить продукт в каталог
     * @param product
     * @throws Exception
      */
    public void addProduct(CatalogItem<Product> product) throws Exception {
        if(this.findProduct(product) < 0)
            this.products.add(product);
    }

    /**
     * Удалить продукт из каталога
     * @param product экземпляр продукта
     * @throws Exception генерирует исключение, если продукт не найден
      */
    public void deleteProduct(CatalogItem<Product> product) throws Exception{
        int deleted = findProduct(product);
        if(deleted > -1) this.products.remove(deleted);        
    }

    /**
     * Добавить некоторое количество продукта 
     * @param product продукт
     * @param amount количество 
     * @throws Exception генерирует исключение, если продукт не найден
      */
    public void addProductAmount(CatalogItem<Product> product, double amount )  throws Exception{
        int pItem = findProduct(product);
        this.products.get(pItem).setAmount(
        this.products.get(pItem).getAmount() + amount);
    }

    /**
     * Убрать некоторое количество товара из магазина 
     * @param product продукт 
     * @param amount количество 
     * @throws Exception генерирует исключение, если продукт не найден или его недостаточно
      */
    public void removeProductAmount(CatalogItem<Product> product, double amount) throws Exception{
        int pItem = findProduct(product);
        double nowAmount =this.products.get(pItem).getAmount();
        if(nowAmount < amount) throw new Exception("Недостаточно товара в магазине");
        this.products.get(pItem).setAmount(nowAmount - amount);
    }

    /**
     * Изменить цену продукта
     * @param product продукт
     * @param price цена
     * @throws Exception генерирует исключение, если продукт не найден
      */
    public void changeProductPrice(CatalogItem<Product> product, double price) throws Exception{
        int pItem = findProduct(product);
        this.products.get(pItem).setPrice(price);
    }
    
    @Override
    public CatalogIterator<CatalogItem> iterator() {
        return new CatalogIterator<CatalogItem>(this.products);
    }

}

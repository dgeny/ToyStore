package model.goods;

/**
 * Товар в магазине
  */
public class CatalogItem<T extends Product> {
    
    public CatalogItem(CatalogItem item){
        this.product = (T)item.getProduct();
        this.price = item.getPrice();
    }

    public CatalogItem(T product, double price) {
        this.product = product;
        this.price = price;
    }
    /**
     * Товар
     * Изменяется только количество или цена
     * Сам продукт либо есть, либо нет. Менять бессмысленно
     */
    private T product;
    
    public T getProduct(){
        return this.product;
    }

    public ProductCategory getCategory(){
        return this.product.getCategory();
    }
    
    /**
     * Количество
      */
    private double amount;
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    /**
     * Цена в магазине
      */
    private double price;
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CatalogItem<T> other = (CatalogItem<T>) obj;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        return true;
    }
    
    public String getInfo(){
        return this.product.getShortName();
    }
    
    @Override
    public String toString() {
        return "CatalogItem [product=" + product.getShortName() + ", amount=" + amount + ", price=" + price + "]";
    }
    
}

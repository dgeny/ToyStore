package model;

import model.goods.Product;

public interface SaleAction extends Actions {
    public void getProducts();
    public void addProduct(Product product, double salePrice, double count);
    public void getSalePrice(Product product);
    public void showRemains(Product product);
    public void showAllRemains();
}

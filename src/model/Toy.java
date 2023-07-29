package model;

import model.goods.Product;
import model.goods.ProductCategory;

public class Toy extends Product{

    public Toy(int sku, String shortName) {
        super(sku, shortName);
        this.setCategory(ProductCategory.toys);
    }
}

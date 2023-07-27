package model;


public class Toy extends Product{

    public Toy(int sku, String shortName) {
        super(sku, shortName);
        this.setCategory(ProductCategory.toys);
    }
}

package model;

import model.goods.ProductCategory;

public class ToyStore extends Store<Toy> {

    public ToyStore(int id, String name) {
        super(id, name);
        this.addStoreCategory(ProductCategory.toys);
    }
    
}

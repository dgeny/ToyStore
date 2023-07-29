package model.goods;

import java.util.ArrayList;

public class ProductCategories {
    private ArrayList<ProductCategory> categories;

    public ArrayList<ProductCategory> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<ProductCategory> categories) {
        this.categories = categories;
    }

    public int findCategory(ProductCategory category){
        for (int i = 0; i < this.categories.size() -1; i++) {
            if (this.categories.get(i).equals(category)) return i; 
        }
        return -1;
    }

    public void addProductCategory(ProductCategory category){
        if(this.findCategory(category) < 0)
            this.categories.add(category);
    }

    public void removeProductCategory(ProductCategory category) throws Exception{ 
        int catIdx = this.findCategory(category);
        if(catIdx > -1)
            this.categories.remove(catIdx);
        else{
            throw new RuntimeException("Category not found");
        }
        
    }    
}

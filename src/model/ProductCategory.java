package model;

public enum ProductCategory {
    toys("Toys"),
    carParts("Car parts"),
    drinks("Drinks"),
    foods("Food"),
    officeSupplies("Office supplies"),
    other("Other");

    public final String text;
    ProductCategory(final String text){
        this.text = text;
    }

    @Override
    public String toString(){
        return this.text;
    }    
}

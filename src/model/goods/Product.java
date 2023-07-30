package model.goods;

public abstract class Product {
    public Product(int sku, String shortName) {
        this.sku = sku;
        this.shortName = shortName;
    }

    /**
     * Артикул, он же id
     */
    private int sku;
    
    public int getSku() {
        return sku;
    }
    public void setSku(int sku) {
        this.sku = sku;
    }

    /**
     * Короткое имя товара
     */
    private String shortName;
    public String getShortName() {
        return shortName;
    }
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * Текстовое описание товара
     */
    private String description;
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    private MeasureUnit unit;
    public MeasureUnit getUnit() {
        return unit;
    }
    public void setUnit(MeasureUnit unit) {
        this.unit = unit;
    }

    private ProductCategory category;

    public ProductCategory getCategory() {
        return category;
    }
    public void setCategory(ProductCategory category) {
        this.category = category;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if (((Product)obj).sku == this.sku && ((Product)obj).shortName == this.shortName)
            return true;
        return false;
    }

}

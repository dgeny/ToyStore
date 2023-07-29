package model.goods;

import java.util.Iterator;
import java.util.List;

public class CatalogIterator<CatalogItem> implements Iterator<CatalogItem> {
    private List<CatalogItem> list;
    private int currentIndex;
    
    public CatalogIterator(List<CatalogItem> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < this.list.size();
    }

    @Override
    public CatalogItem next() {
        if(hasNext()){
            return this.list.get(currentIndex++);
        }
        throw new RuntimeException("Products out of range");
    }

}

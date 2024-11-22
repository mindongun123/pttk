package model.ordering;
import java.util.HashMap;
import java.util.Map;
import model.item.ImageItem230;


public class ShoppingCart230 {
    private Map<OrderedItem230, ImageItem230> items;

    public ShoppingCart230() {
        items = new HashMap<>();
    }

    public Map<OrderedItem230, ImageItem230> getItems() {
        return items;
    }
    
    public void addItem(OrderedItem230 orderedItem, ImageItem230 image) {
        items.put(orderedItem, image);
    }
    
    public void resetShoppingCart() {
        items.clear();
    }
    
}

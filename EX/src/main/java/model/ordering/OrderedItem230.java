package model.ordering;

import model.item.Item230;


public class OrderedItem230 extends Item230 {
    private int orderedQuantity;

    public OrderedItem230(Item230 item230, int orderedQuantity) {
        super(
                item230.getId(),
                item230.getNameItem(),
                item230.getBrand(),
                item230.getType(),
                item230.getUnit(),
                item230.getExportedPrice()
        );
        this.orderedQuantity = orderedQuantity;
    }
    
    

    

    // Getters and Setters
    public int getOrderedQuantity() { 
        return orderedQuantity; 
    }
    
    public void setOrderedQuantity(int orderedQuantity) { 
        this.orderedQuantity = orderedQuantity; 
    }
    
    public double getTotalPrice() {
        return orderedQuantity * this.getExportedPrice();
    }
}


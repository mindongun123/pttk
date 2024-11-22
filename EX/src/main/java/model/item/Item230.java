package model.item;


public class Item230 {
    private String id;
    private String nameItem;
    private String brand;
    private Type230 type;
    private String unit;
    private float exportedPrice;

    public Item230() {
    }

    public Item230(String id, String nameItem, String brand, Type230 type, String unit, float exportedPrice) {
        this.id = id;
        this.nameItem = nameItem;
        this.brand = brand;
        this.type = type;
        this.unit = unit;
        this.exportedPrice = exportedPrice;
    }

    public String getId() {
        return id;
    }

    public Item230 setId(String id) {
        this.id = id;
        return this;
    }

    public String getNameItem() {
        return nameItem;
    }

    public Item230 setNameItem(String nameItem) {
        this.nameItem = nameItem;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public Item230 setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public Type230 getType() {
        return type;
    }

    public Item230 setType(Type230 type) {
        this.type = type;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public Item230 setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public float getExportedPrice() {
        return exportedPrice;
    }

    public Item230 setExportedPrice(float exportedPrice) {
        this.exportedPrice = exportedPrice;
        return this;
    }
    
}

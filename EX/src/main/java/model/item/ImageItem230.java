package model.item;


public class ImageItem230 {
    private String id;
    private String idItem;
    private String link;

    public ImageItem230() {
    }

    public ImageItem230(String id, String idItem, String link) {
        this.idItem = idItem;
        this.link = link;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public ImageItem230 setId(String id) {
        this.id = id;
        return this;
    }

    public String getIdItem() { 
        return idItem; 
    }
    public ImageItem230 setIdItem(String idItem) {
        this.idItem = idItem; 
        return this;
    }

    public String getLink() { 
        return link; 
    }
    public void setLink(String link) {
        this.link = link; 
    }
}


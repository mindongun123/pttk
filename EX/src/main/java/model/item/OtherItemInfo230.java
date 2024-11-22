package model.item;


public class OtherItemInfo230 {
    private String id;
    private String idItem;
    private String nameInfo;
    private String detailInfo;

    public OtherItemInfo230() {
    }
    
    
    public OtherItemInfo230(String id, String idItem, String nameInfo, String detailInfo) {
        this.id = id;
        this.idItem = idItem;
        this.nameInfo = nameInfo;
        this.detailInfo = detailInfo;
    }

    // Getters and Setters
    public String getId() { 
        return id; 
    }
    public OtherItemInfo230 setId(String id) {
        this.id = id; 
        return this;
    }

    public String getIdItem() { 
        return idItem; 
    }
    public OtherItemInfo230 setIdItem(String idItem) {
        this.idItem = idItem; 
        return this;
    }

    public String getNameInfo() { 
        return nameInfo; 
    }
    public OtherItemInfo230 setNameInfo(String nameInfo) {
        this.nameInfo = nameInfo; 
        return this;
    }

    public String getDetailInfo() { 
        return detailInfo; 
    }
    public OtherItemInfo230 setDetailInfo(String detailInfo) {
        this.detailInfo = detailInfo;
        return this;
    }
}


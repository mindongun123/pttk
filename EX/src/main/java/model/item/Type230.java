package model.item;


public class Type230 {
    private String id;
    private String name;
    private Type230 parentType = null;

    public Type230() {
    }

    public Type230(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public Type230 setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Type230 setName(String name) {
        this.name = name;
        return this;
    }

    public Type230 getParentType() {
        return parentType;
    }

    public void setParentType(Type230 parentType) {
        this.parentType = parentType;
    }
    
    
}

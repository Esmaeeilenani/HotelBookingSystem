package jedd_project_1;

public class Service {
    private String Type;
    private int Price;

    public Service(String Type, int Price) {
        this.Type = Type;
        this.Price = Price;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }
    
}

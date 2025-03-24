package fashion;

public class Product{
    private String name;
    private String size;
    private double price;

    public Product(String name, String size, double price){
        this.name = name;
        this.size = size;
        this.price = price;
    }

    public void displayDetails(){
        System.out.println("Product Name: " + name);
        System.out.println("Size: " + size);
        System.out.println("Price: Rs" + price);
    }

    public boolean isAvailable() {
        return price > 0;
    }
}

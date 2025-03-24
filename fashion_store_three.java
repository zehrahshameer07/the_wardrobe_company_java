
// Superclass
class Clothes {
    private String color;
    private double price;

    public Clothes(String color, double price) {
        this.color = color;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("Color: " + color);
        System.out.println("Price: Rs" + price);
    }
}

// Subclass 
class Jeans extends Clothes {
    private String size;

    public Jeans(String color, double price, String size) {
        super(color, price); 
        this.size = size;
    }

    @Override
    public void displayDetails() {
        super.displayDetails(); 
        System.out.println("Size: " + size);
    }
}

// Subclass
class Top extends Clothes {
    private String material;

    public Top(String color, double price, String material) {
        super(color, price); 
        this.material = material;
    }

    @Override
    public void displayDetails() {
        super.displayDetails(); 
        System.out.println("Material: " + material);
    }
}

// Subclass 
class Kurthi extends Clothes {
    private String pattern;

    public Kurthi(String color, double price, String pattern) {
        super(color, price);
        this.pattern = pattern;
    }

    @Override
    public void displayDetails() {
        super.displayDetails(); 
        System.out.println("Pattern: " + pattern);
    }
}

public class fashion_store_three {
    public static void main(String[] args) {
    	Jeans[] jeansArray = {
                new Jeans("Blue", 900, "32"),
                new Jeans("Black", 950, "34")
            };
            
            Top[] topArray = {
                new Top("Red", 599, "Cotton"),
                new Top("Yellow", 499, "Velvet")
            };
            
            Kurthi[] kurthiArray = {
                new Kurthi("Green", 680, "Floral"),
                new Kurthi("Pink", 750, "Embroidery")
            };

            System.out.println("\nWelcome to The Wardrobe Company!");
            System.out.println("Owned by Zehrah Shameer");
            System.out.println("------------------------------------------------");

            System.out.println("\nJeans Details:");
            for (Jeans jeans : jeansArray) {
                jeans.displayDetails();
                System.out.println("------------------------------------------------");
            }

            System.out.println("\nTop Details:");
            for (Top top : topArray) {
                top.displayDetails();
                System.out.println("------------------------------------------------");
            }

            System.out.println("\nKurthi Details:");
            for (Kurthi kurthi : kurthiArray) {
                kurthi.displayDetails();
                System.out.println("------------------------------------------------");
            }
        }
    }
    	
       
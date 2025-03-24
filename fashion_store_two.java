import java.util.Scanner;

public class fashion_store_two {
    private String name;
    private double price;
    private String size;

    // Default Constructor
    public fashion_store_two() {
        this.name = "Unknown";
        this.price = 0.0;
        this.size = "Free Size";
    }

    // Parameterised Constructor
    public fashion_store_two(String name, double price, String size) {
        this.name = name;
        this.price = price;
        this.size = size;
    }

    
    public fashion_store_two(String name, double price) {
        this.name = name;
        this.price = price;
        this.size = "Free Size";
    }

    public void displayItem() {
        System.out.println("Item: " + name);
        System.out.println("Price: Rs" + price);
        System.out.println("Size: " + size);
        System.out.println();
    }

    public double calculateDiscount(double percentage) {
        return price - (price * percentage / 100);
    }

    public double calculateDiscount(double minamt, double discountAmount) {
        if (price >= minamt) {
            return price - discountAmount;
        }
        return price; 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of items in the store:");
        int n = scanner.nextInt();
        scanner.nextLine(); 

        fashion_store_two[] items = new fashion_store_two[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for item " + (i + 1) + ":");

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Price: Rs ");
            double price = scanner.nextDouble();

            System.out.print("Enter yes if the item has a specific size, else enter no: ");
            scanner.nextLine();
            String hasSize = scanner.nextLine();

            if (hasSize.equalsIgnoreCase("yes")) {
                System.out.print("Size: ");
                String size = scanner.nextLine();
                items[i] = new fashion_store_two(name, price, size);
            } else {
                items[i] = new fashion_store_two(name, price);
            }
        }

        System.out.println("\nItems in the Fashion Store:");
        for (fashion_store_two item : items) {
            item.displayItem();
        }

        System.out.println("\nCalculating discounts for items:");
        for (fashion_store_two item : items) {
            System.out.println("Original price of " + item.name + ": Rs" + item.price);
            System.out.println("Price after 20% discount: Rs" + item.calculateDiscount(20));
            System.out.println("Price after Rs200 discount if price is above Rs1000: Rs" 
                               + item.calculateDiscount(1000, 200));
            System.out.println();
        }

        scanner.close();
    }
}

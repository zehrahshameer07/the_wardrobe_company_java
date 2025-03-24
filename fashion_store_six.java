//#07 Implement the concept of Exception Handing in java.
import java.util.Scanner;

class OutOfStockException extends Exception {
    public OutOfStockException(String message) {
        super(message);
    }
}

class InvalidPriceException extends Exception {
    public InvalidPriceException(String message) {
        super(message);
    }
}

class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
}

class FashionItem {
    private String name;
    private double price;
    private int stock;

  
    public FashionItem(String name, double price, int stock) throws InvalidPriceException {
        if (price <= 0) {
            throw new InvalidPriceException("Price must be greater than Rs.0 for " + name);
        }
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    
    public double purchase(int quantity) throws OutOfStockException, InvalidQuantityException {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Purchase quantity must be greater than 0.");
        }
        if (quantity > stock) {
            throw new OutOfStockException("Only " + stock + " items of " + name + " are available.");
        }
        stock -= quantity;
        System.out.println(quantity + " " + name + "(s) purchased successfully.");
        return price * quantity;
    }


    public void displayItem() {
        System.out.println("Item: " + name + " | Price: Rs." + String.format("%.2f", price) + " | Stock: " + stock);
    }

    public String getName() {
        return name;
    }

    public int getStock() {
    	
        return stock;
    }
}

public class fashion_store_six {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double totalAmount = 0;
        try {

            FashionItem[] items = {
                new FashionItem("Evening Gown", 4500.75, 5),
                new FashionItem("High Heels", 3200.50, 2),
                new FashionItem("Designer Handbag", 8500, 3),
                new FashionItem("Luxury Watch", 15000, 4)
            };

            while (true) {
               
                System.out.println("\n--- Welcome to the Fashion Store ---");
                System.out.println("1. View Available Items");
                System.out.println("2. Purchase an Item");
                System.out.println("3. Checkout");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a number between 1 and 4.");
                    scanner.next(); 
                    continue;
                }

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
     
                        System.out.println("\n--- Available Items ---");
                        for (int i = 0; i < items.length; i++) {
                            System.out.print((i + 1) + ". ");
                            items[i].displayItem();
                        }
                        break;

                    case 2:
             
                        System.out.println("\nEnter the item number to purchase: ");
                        if (!scanner.hasNextInt()) {
                            System.out.println("Invalid input! Please enter a valid item number.");
                            scanner.next();
                            continue;
                        }

                        int itemNumber = scanner.nextInt();

                        if (itemNumber < 1 || itemNumber > items.length) {
                            System.out.println("Invalid item number. Please try again.");
                            break;
                        }

                        FashionItem selectedItem = items[itemNumber - 1];

                        if (selectedItem.getStock() == 0) {
                            System.out.println("Sorry, " + selectedItem.getName() + " is out of stock.");
                            break;
                        }

                        System.out.println("Enter quantity to purchase: ");
                        if (!scanner.hasNextInt()) {
                            System.out.println("Invalid input! Please enter a valid quantity.");
                            scanner.next();
                            continue;
                        }

                        int quantity = scanner.nextInt();

                        try {
                            totalAmount += selectedItem.purchase(quantity);
                        } catch (OutOfStockException | InvalidQuantityException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 3:
                
                        if (totalAmount == 0) {
                            System.out.println("You have not purchased anything yet.");
                            break;
                        }

                        System.out.println("\n--- Checkout Summary ---");
                        System.out.println("Total Amount before Discount: Rs." + String.format("%.2f", totalAmount));

                        double discount = 0;
                        if (totalAmount > 10000) {
                            discount = totalAmount * 0.10; 
                        } else if (totalAmount > 5000) {
                            discount = totalAmount * 0.05; 
                        }

                        double finalAmount = totalAmount - discount;
                        System.out.println("Discount Applied: Rs." + String.format("%.2f", discount));
                        System.out.println("Final Amount to Pay: Rs." + String.format("%.2f", finalAmount));
                        System.out.println("Thank you for shopping with us!");

                        totalAmount = 0; 
                        break;

                    case 4:
               
                        System.out.println("Thank you for visiting! Goodbye.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            }
        } catch (InvalidPriceException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close(); 
        }
    }
}

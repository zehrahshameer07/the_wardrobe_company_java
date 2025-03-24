import java.util.Scanner;
import fashion.Product;
import fashion.Transaction;

public class MainApp{
  public static void main(String[] args){
     Scanner scanner = new Scanner(System.in);
     Product product = null;
     Transaction transaction = null;
      while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. View Product Details");
            System.out.println("3. Process Transaction");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter product size: ");
                    String size = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    double price = scanner.nextDouble();
                    product = new Product(name, size, price);
                    System.out.println("Product added successfully!");
                    break;

                case 2:
                    if (product != null){
                        product.displayDetails();
                    } else{
                        System.out.println("No product available. Please add a product first.");
                    }
                    break;

                case 3:
                    if (product != null){
                        System.out.print("Enter customer name: ");
                        String customerName = scanner.nextLine();
                        transaction = new Transaction(product, customerName);
                        transaction.processTransaction();
                    } else{
                        System.out.println("No product available to purchase. Please add a product first.");
                    }
                    break;

                case 4:
                    System.out.println("Exiting the program...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

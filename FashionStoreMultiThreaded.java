//#8. Implement the concept of Multithreading
import java.util.Scanner;

class FashionStore {
    private final String[] clothingItems = {"T-shirt", "Jeans", "Dress", "Jacket", "Shoes"};
    private final double[] prices = {500.0, 1200.0, 1500.0, 2500.0, 2000.0};

    public synchronized void buyClothing(String customerName, int orderId, int itemIndex) {
        switch (itemIndex) {
            case 0, 1, 2, 3, 4 -> System.out.printf("Customer: %-10s | Order ID: %-5d | Item: %-10s | Price: Rs. %.2f\n", 
                                                      customerName, orderId, clothingItems[itemIndex], prices[itemIndex]);
            default -> System.out.printf("Customer: %-10s | Order ID: %-5d | Invalid item selection!\n", 
                                         customerName, orderId);
        }
    }
}

class Customer extends Thread {
    private final String customerName;
    private final FashionStore store;
    private final int orderId;
    private final int itemIndex;

    public Customer(String customerName, int orderId, FashionStore store, int itemIndex) {
        this.customerName = customerName;
        this.orderId = orderId;
        this.store = store;
        this.itemIndex = itemIndex;
    }

    @Override
    public void run() {
        store.buyClothing(customerName, orderId, itemIndex);
    }
}

public class FashionStoreMultiThreaded {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FashionStore store = new FashionStore();

        System.out.print("Enter the number of customers: ");
        int numCustomers = scanner.nextInt();
        scanner.nextLine(); 

        Thread[] customers = new Thread[numCustomers];

        for (int i = 0; i < numCustomers; i++) {
            System.out.print("Enter customer name: ");
            String name = scanner.nextLine();

            System.out.print("Enter order ID: ");
            int orderId = scanner.nextInt();
            
            System.out.println("Choose an item index (0: T-shirt, 1: Jeans, 2: Dress, 3: Jacket, 4: Shoes): ");
            int itemIndex = scanner.nextInt();
            scanner.nextLine(); 

            customers[i] = new Customer(name, orderId, store, itemIndex);
        }
        
                for (Thread customer : customers) {
            customer.start();
        }
        
                for (Thread customer : customers) {
            try {
                customer.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }

        scanner.close();
    }
}

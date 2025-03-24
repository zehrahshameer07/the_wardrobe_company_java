//#06 Implement the concept of String and String Buffer classes
import java.util.Scanner;

public class fashion_store_five {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] products = { "Denim Jacket", "Handbag", "Sneakers", "Floral Dress", "Watch" };
        int[] prices = { 3000, 5000, 2500, 4000, 7000 };

        System.out.println("Welcome to The Wardrobe Company!");
        System.out.println("\nAvailable Products for Purchase:");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " - Price: Rs" + prices[i]);
        }

        System.out.print("\nEnter the number of the product you want to purchase: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        String selectedProduct;
        int productPrice;

        if (choice >= 1 && choice <= products.length) {
            selectedProduct = products[choice - 1];
            productPrice = prices[choice - 1];
        } else {
            System.out.println("Invalid choice. Please restart the program and try again.");
            return;
        }

        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter your mobile number: ");
        String mobileNumber = scanner.nextLine();

        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        // Masking the Account Number using StringBuffer
        StringBuffer maskedAccountNumber = new StringBuffer(accountNumber);
        for (int i = 0; i < maskedAccountNumber.length() - 4; i++) {
            maskedAccountNumber.setCharAt(i, '*');
        }

        // Reverse the product name to create a unique product code
        String reversedProductCode = new StringBuilder(selectedProduct.replaceAll(" ", "")).reverse().toString();
        System.out.println("\nUnique Product Code: #" + reversedProductCode.toUpperCase());

        // Reverse customer name 
        String reversedCustomerName = new StringBuilder(customerName).reverse().toString();
        if (customerName.equalsIgnoreCase(reversedCustomerName)) {
            System.out.println("\nVIP Alert! Enjoy special offers!");
        }

        // Branding Prefix Check
        System.out.print("\nCheck if a product belongs to 'Wardrobe' collection (Enter a prefix): ");
        String prefix = scanner.nextLine();
        if (selectedProduct.startsWith(prefix)) {
            System.out.println("Yes! '" + selectedProduct + "' is part of the '" + prefix + "' collection.");
        } else {
            System.out.println("No! '" + selectedProduct + "' is NOT part of the '" + prefix + "' collection.");
        }

        // Product Description using StringBuffer
        StringBuffer productDescription = new StringBuffer("Exclusive ");
        productDescription.append(selectedProduct).append(" - Now available at The Wardrobe Company!");
        System.out.println("\nProduct Description:\n" + productDescription);

        // Receipt Details using StringBuffer
        StringBuffer receiptDetails = new StringBuffer();
        receiptDetails.append("Customer Name: ").append(customerName).append("\n");
        receiptDetails.append("Mobile Number: ").append(mobileNumber).append("\n");
        receiptDetails.append("Product Purchased: ").append(selectedProduct).append("\n");
        receiptDetails.append("Product Code: #").append(reversedProductCode.toUpperCase()).append("\n");
        receiptDetails.append("Account Number: ").append(maskedAccountNumber).append("\n");
        receiptDetails.append("Amount Paid: Rs").append(productPrice).append("\n");

        receiptDetails.insert(0, "Thank you for shopping with us!\n");
        System.out.println("\n--- Receipt ---");
        System.out.println(receiptDetails);

        int replaceIndex = receiptDetails.indexOf("Thank you");
        if (replaceIndex != -1) {
            receiptDetails.replace(replaceIndex, replaceIndex + 9, "We appreciate");
            System.out.println("\nUpdated Receipt with Replacement:\n" + receiptDetails);
        }

        scanner.close();
    }
}

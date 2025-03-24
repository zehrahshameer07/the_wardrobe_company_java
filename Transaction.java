package fashion;

public class Transaction {
    private Product product;
    private String customerName;

    public Transaction(Product product, String customerName) {
        this.product = product;
        this.customerName = customerName;
    }

    public void processTransaction() {
        if (product.isAvailable()) {
            System.out.println("Transaction Completed Successfully!");
            System.out.println("Customer Name: " + customerName);
            System.out.println("Purchased Product Details:");
            product.displayDetails();
        } else {
            System.out.println("This product is not available for transaction.");
        }
    }
}

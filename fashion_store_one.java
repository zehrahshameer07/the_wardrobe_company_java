import java.util.Scanner;
public class fashion_store_one {
	
	private String[] itemNames = new String[100]; 
    private double[] itemPrices = new double[100]; 
    private int itemCount; 
    private double total=0;
    	
    public void enterDetails() {
         Scanner scan = new Scanner(System.in);
         System.out.print("Enter the number of items: ");
         itemCount = scan.nextInt();
         scan.nextLine();
         System.out.println("Enter details of items:");
            for (int i = 0; i < itemCount; i++) {
                System.out.print("Item " + (i + 1) + " Name: ");
                itemNames[i] = scan.nextLine();
                System.out.print("Item " + (i + 1) + " Price: ");
                itemPrices[i] = scan.nextDouble();
                scan.nextLine();
            }
        }

        public void displayDetails() {
            System.out.println("\nWelcome to The Wardrobe Company!");
            System.out.println("Owned by Zehrah Shameer");
            System.out.println("------------------------------------------------");
            System.out.println("Item Name\tPrice");
            for (int i = 0; i < itemCount; i++) {
                System.out.println(itemNames[i] + "\t" + itemPrices[i]);
            }
        }
        
        public void calcTotal() {
        	for (int i = 0; i < itemCount; i++) {
        	 total=total+ itemPrices[i];
        	}
        	System.out.println("Total:"+total);
        }
        
  public static void main(String[] args) {
        fashion_store_one store = new fashion_store_one();
        System.out.println("\n--------Item Details-------------");
        store.enterDetails();
        store.displayDetails();
        System.out.println("\n------------------------------------------------");
        store.calcTotal();
    }
}
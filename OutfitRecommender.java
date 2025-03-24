//9.Collections
import java.util.*;

class OutfitRecommender {
    private Map<String, List<String>> occasionOutfits;
    private Set<String> userPreferences;

    public OutfitRecommender() {
        occasionOutfits = new HashMap<>();
        userPreferences = new HashSet<>();
    }

    public void addOutfit(String occasion, String outfit) {
        occasion = capitalize(occasion).trim();
        outfit = outfit.trim();
        
        if (occasion.isEmpty() || outfit.isEmpty()) {
            System.out.println("Error: Occasion and outfit cannot be empty.");
            return;
        }

        occasionOutfits.putIfAbsent(occasion, new ArrayList<>());

        if (!occasionOutfits.get(occasion).contains(outfit)) {
            occasionOutfits.get(occasion).add(outfit);
            System.out.println("Outfit added for " + occasion);
        } else {
            System.out.println("Outfit already exists for " + occasion);
        }
    }

    public void addUserPreference(String style) {
        style = style.trim();
        if (style.isEmpty()) {
            System.out.println("Error: Style cannot be empty.");
            return;
        }
        userPreferences.add(style);
        System.out.println("Style added successfully!");
    }

    public void displayOutfits(String occasion) {
        occasion = capitalize(occasion).trim();

        if (occasionOutfits.containsKey(occasion)) {
            System.out.println("\n===== Recommended Outfits for " + occasion + " =====");
            for (String outfit : occasionOutfits.get(occasion)) {
                System.out.println("- " + outfit);
            }
        } else {
            System.out.println("\nNo outfit recommendations available for " + occasion);
        }
    }

    public void displayUserPreferences() {
        System.out.println("\n===== User's Preferred Styles =====");
        if (userPreferences.isEmpty()) {
            System.out.println("No preferences set.");
        } else {
            for (String style : userPreferences) {
                System.out.println("- " + style);
            }
        }
    }

    private String capitalize(String str) {
        if (str == null || str.trim().isEmpty()) {
            return "Unknown";
        }
        str = str.trim();
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OutfitRecommender recommender = new OutfitRecommender();

        recommender.addOutfit("Casual", "Jeans and T-Shirt");
        recommender.addOutfit("Casual", "Summer Dress");
        recommender.addOutfit("Formal", "Business Suit");
        recommender.addOutfit("Formal", "Evening Gown");
        recommender.addOutfit("Workout", "Athletic Wear");
        recommender.addOutfit("Party", "Cocktail Dress");

        while (true) {
            System.out.println("\n===== Personalized Outfit Recommender =====");
            System.out.println("1. Add a preferred style");
            System.out.println("2. View recommended outfits for an occasion");
            System.out.println("3. View your preferred styles");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        System.out.print("Enter your preferred style: ");
                        String style = scanner.nextLine().trim();
                        recommender.addUserPreference(style);
                        break;
                    
                    case 2:
                        System.out.print("Enter an occasion: ");
                        String occasion = scanner.nextLine().trim();
                        recommender.displayOutfits(occasion);
                        break;
                    
                    case 3:
                        recommender.displayUserPreferences();
                        break;
                    
                    case 4:
                        System.out.println("Thank you for using the Personalized Outfit Recommender!");
                        scanner.close();
                        return;
                    
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            }
        }
    }
}

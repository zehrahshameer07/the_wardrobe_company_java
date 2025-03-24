//#10. Implement the concept of swing package
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FashionStore {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StoreUI().setVisible(true));
    }
}

class StoreUI extends JFrame {
    private DefaultListModel<String> cartModel;
    private JList<String> cartList;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    
    public StoreUI() {
        setTitle("Fashion Store");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
     
        JPanel navPanel = new JPanel();
        navPanel.add(new JLabel("Welcome to The Wardrobe Company!"));
        add(navPanel, BorderLayout.NORTH);
        
      
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
       
        JPanel categoryPanel = new JPanel();
        categoryPanel.setLayout(new GridLayout(2, 2, 10, 10));
        String[] categories = {"Clothing", "Accessories", "Footwear"};
        for (String category : categories) {
            JButton categoryButton = new JButton(category);
            categoryButton.addActionListener(e -> showProducts(category));
            categoryPanel.add(categoryButton);
        }
        
        mainPanel.add(categoryPanel, "Categories");
      
        cartModel = new DefaultListModel<>();
        cartList = new JList<>(cartModel);
        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.add(new JLabel("Shopping Cart"), BorderLayout.NORTH);
        cartPanel.add(new JScrollPane(cartList), BorderLayout.CENTER);
        JButton orderButton = new JButton("Place Order");
        orderButton.addActionListener(e -> placeOrder());
        cartPanel.add(orderButton, BorderLayout.SOUTH);
        
        mainPanel.add(cartPanel, "Cart");
        
        add(mainPanel, BorderLayout.CENTER);
        
      
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Categories"));
        JButton cartButton = new JButton("View Cart");
        cartButton.addActionListener(e -> cardLayout.show(mainPanel, "Cart"));
        buttonPanel.add(backButton);
        buttonPanel.add(cartButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        cardLayout.show(mainPanel, "Categories");
    }
    
    private void showProducts(String category) {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(2, 2, 10, 10));
        
        String[] products;
        switch (category) {
            case "Clothing":
                products = new String[]{"T-Shirt", "Jeans", "Kurti Set"};
                break;
            case "Accessories":
                products = new String[]{"Sunglasses", "Watch", "Handbag"};
                break;
            case "Footwear":
                products = new String[]{"Sneakers", "Heels", "Loafers"};
                break;
            default:
                products = new String[]{};
        }
        
        for (String product : products) {
            JButton button = new JButton("Buy " + product);
            button.addActionListener(e -> addToCart(product));
            productPanel.add(button);
        }
        
        mainPanel.add(productPanel, "Products");
        cardLayout.show(mainPanel, "Products");
    }
    
    private void addToCart(String item) {
        cartModel.addElement(item);
    }
    
    private void placeOrder() {
        if (cartModel.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your cart is empty!");
        } else {
            JOptionPane.showMessageDialog(this, "Order placed successfully!");
            cartModel.clear();
        }
    }
}

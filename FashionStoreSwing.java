import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FashionStoreSwing extends JFrame {
    private JList<String> categoryList;
    private DefaultListModel<String> itemListModel;
    private JList<String> itemList;

    public FashionStoreSwing() {
        this.setTitle("Fashion Store");
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Categories Panel
        String[] categories = {"Men", "Women", "Accessories"};
        categoryList = new JList<>(categories);
        categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        categoryList.addListSelectionListener(e -> updateItems());

        // Item Display Panel
        itemListModel = new DefaultListModel<>();
        itemList = new JList<>(itemListModel);
        JScrollPane itemScrollPane = new JScrollPane(itemList);

        // Add to Cart Button
        JButton addToCart = new JButton("Add to Cart");
        addToCart.addActionListener(e -> JOptionPane.showMessageDialog(this, "Item added to cart!"));

        // Layout
        this.add(new JScrollPane(categoryList), BorderLayout.WEST);
        this.add(itemScrollPane, BorderLayout.CENTER);
        this.add(addToCart, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    private void updateItems() {
        itemListModel.clear();
        String selectedCategory = categoryList.getSelectedValue();
        
        if (selectedCategory != null) {
            switch (selectedCategory) {
                case "Men":
                    itemListModel.addElement("T-Shirt");
                    itemListModel.addElement("Jeans");
                    itemListModel.addElement("Shirt");
                    break;
                case "Women":
                    itemListModel.addElement("Kurti");
                    itemListModel.addElement("Dress");
                    itemListModel.addElement("Skirt");
                    break;
                case "Accessories":
                    itemListModel.addElement("Sunglasses");
                    itemListModel.addElement("Handbag");
                    itemListModel.addElement("Belt");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        new FashionStoreSwing();
    }
}
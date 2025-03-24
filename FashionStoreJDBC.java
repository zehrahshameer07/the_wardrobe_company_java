import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class FashionStoreJDBC {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/2bcajava?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Simran@123";

    private JFrame frame;
    private JTextField nameField, categoryField, priceField, stockField, idField, updateStockField;
    private JTable itemTable;
    private DefaultTableModel tableModel;

    public FashionStoreJDBC() {
        frame = new JFrame("Fashion Store Inventory");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout(10, 10));

        frame.getContentPane().setBackground(new Color(34, 40, 49));
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(34, 40, 49)); // Dark gray panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Color panelColor = new Color(57, 62, 70); // Slightly lighter dark gray
        Color textColor = new Color(238, 238, 238); // Light gray
        Color buttonColor = new Color(0, 173, 181); // Teal color

    
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.WHITE, 2), "Add New Item"));
        inputPanel.setBackground(panelColor);
        inputPanel.setForeground(textColor);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(textColor);
        nameField = new JTextField();
        styleTextField(nameField);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setFont(labelFont);
        categoryLabel.setForeground(textColor);
        categoryField = new JTextField();
        styleTextField(categoryField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(labelFont);
        priceLabel.setForeground(textColor);
        priceField = new JTextField();
        styleTextField(priceField);

        JLabel stockLabel = new JLabel("Stock Quantity:");
        stockLabel.setFont(labelFont);
        stockLabel.setForeground(textColor);
        stockField = new JTextField();
        styleTextField(stockField);

        JButton insertButton = new JButton("Add Item");
        styleButton(insertButton);
        insertButton.addActionListener(e -> insertItem());

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(categoryLabel);
        inputPanel.add(categoryField);
        inputPanel.add(priceLabel);
        inputPanel.add(priceField);
        inputPanel.add(stockLabel);
        inputPanel.add(stockField);
        inputPanel.add(insertButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(inputPanel, gbc);


        JPanel updatePanel = new JPanel(new GridLayout(3, 2, 10, 10));
        updatePanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.WHITE, 2), "Update Stock Quantity"));
        updatePanel.setBackground(panelColor);

        JLabel idLabel = new JLabel("Item ID:");
        idLabel.setFont(labelFont);
        idLabel.setForeground(textColor);
        idField = new JTextField();
        styleTextField(idField);

        JLabel updateStockLabel = new JLabel("New Stock Quantity:");
        updateStockLabel.setFont(labelFont);
        updateStockLabel.setForeground(textColor);
        updateStockField = new JTextField();
        styleTextField(updateStockField);

        JButton updateButton = new JButton("Update Stock");
        styleButton(updateButton);
        updateButton.addActionListener(e -> updateStockQuantity());

        JButton refreshButton = new JButton("Refresh Table");
        styleButton(refreshButton);
        refreshButton.addActionListener(e -> displayItems());

        updatePanel.add(idLabel);
        updatePanel.add(idField);
        updatePanel.add(updateStockLabel);
        updatePanel.add(updateStockField);
        updatePanel.add(updateButton);
        updatePanel.add(refreshButton);

        gbc.gridy = 1;
        mainPanel.add(updatePanel, gbc);

        frame.add(mainPanel, BorderLayout.NORTH);


        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Category", "Price", "Stock"}, 0);
        itemTable = new JTable(tableModel);
        itemTable.setRowHeight(30);
        itemTable.setFont(new Font("Arial", Font.PLAIN, 14));
        itemTable.setForeground(Color.BLACK);
        itemTable.setGridColor(Color.LIGHT_GRAY);
        itemTable.setBackground(Color.WHITE);

        JScrollPane tableScroll = new JScrollPane(itemTable);
        tableScroll.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.WHITE, 2), "Item List"));
        frame.add(tableScroll, BorderLayout.CENTER);

        frame.setVisible(true);
        displayItems();
    }

    private void insertItem() {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "INSERT INTO items (name, category, price, stock) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, nameField.getText());
                pstmt.setString(2, categoryField.getText());
                pstmt.setDouble(3, Double.parseDouble(priceField.getText()));
                pstmt.setInt(4, Integer.parseInt(stockField.getText()));
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(frame, "Item added successfully.");
                displayItems();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateStockQuantity() {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "UPDATE items SET stock = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, Integer.parseInt(updateStockField.getText()));
                pstmt.setInt(2, Integer.parseInt(idField.getText()));
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(frame, "Stock updated successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "No item found with the given ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                displayItems();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayItems() {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "SELECT * FROM items";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                tableModel.setRowCount(0);
                while (rs.next()) {
                    tableModel.addRow(new Object[]{rs.getInt("id"), rs.getString("name"), rs.getString("category"), rs.getDouble("price"), rs.getInt("stock")});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void styleTextField(JTextField field) {
        field.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(0, 173, 181));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FashionStoreJDBC::new);
    }
}
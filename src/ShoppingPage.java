import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingPage extends JFrame implements ActionListener{
    JFrame frame = new JFrame();

    JButton cart;
    JTextArea productDetail;
    JComboBox categories;
    DefaultTableModel productTableModel;

    String [] columns = {"ProductID","Name","Category","Price($)","Info"};
    WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

    ShoppingPage() {
        Product product1 = new Electronics("1", "Laptop", 10, 1000, "Apple", 2);
        Product product2 = new Clothing("2", "Shirt", 3, 100, "S", "blue");
        Product product3 = new Electronics("3", "Mobile", 11, 1100, "Samsung", 4);
        Product product4 = new Clothing("4", "T-Shirt", 4, 120, "M", "red");


        shoppingManager.getProductList().add(product1);
        shoppingManager.getProductList().add(product2);
        shoppingManager.getProductList().add(product3);
        shoppingManager.getProductList().add(product4);


        //set frame
        frame.setTitle("Westminster Shopping Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);


        //Create Components
        JLabel label = new JLabel("Select Product Category");
        categories = new JComboBox(new String[]{"ALL", "ELECTRONICS", "CLOTHING"});
        categories.addActionListener(this);

        cart = new JButton("Shopping Cart");
        cart.addActionListener(this);

        productTableModel = new DefaultTableModel();
        productTableModel.setColumnIdentifiers(columns);

        productDetail = new JTextArea(5, 20);
        productDetail.setEditable(false);

        for (Product product : shoppingManager.getProductList()) {
            Object[] rowData = {product.getpID(), product.getpName(), product.getCategory(), product.getpPrice(), "Info"};
            productTableModel.addRow(rowData);
        }

        JTable productTable = new JTable(productTableModel);
        JScrollPane scroll = new JScrollPane(productTable);


        //Top bar panel
        JPanel topBar = new JPanel();
        topBar.add(label);
        topBar.add(categories);
        topBar.add(cart);

        //Product Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scroll, BorderLayout.CENTER);

        //Product Details panel
        JPanel detailPanel = new JPanel();
        detailPanel.add(productDetail);


        //set frame
        frame.setLayout(new BorderLayout());
        frame.add(topBar, BorderLayout.NORTH);
        frame.add(tablePanel, BorderLayout.CENTER);
        frame.add(detailPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        productTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = productTable.getSelectedRow();
                    if (selectedRow != -1) {
                        displayProductInfo(selectedRow);
                    }
                }
            }

        });
    }
        @Override
        public void actionPerformed (ActionEvent e){
            if (e.getSource() == cart) {
                this.dispose();
                ShoppingCartPage shoppingCart = new ShoppingCartPage();
            }
            if (e.getSource() == categories) {
                if (categories.getSelectedItem().equals("ALL")) {
                    productTableModel.setRowCount(0);
                    for (Product product : shoppingManager.getProductList()) {
                        Object[] rowData = {product.getpID(), product.getpName(), product.getCategory(), product.getpPrice(), "Info"};
                        productTableModel.addRow(rowData);
                    }
                }
                if (categories.getSelectedItem().equals("ELECTRONICS")) {
                    productTableModel.setRowCount(0);
                    for (Product product : shoppingManager.getProductList()) {
                        if (product.getCategory().equals("Electronics")) {
                            Object[] rowData = {product.getpID(), product.getpName(), product.getCategory(), product.getpPrice(), "Info"};
                            productTableModel.addRow(rowData);
                        }
                    }
                }
                if (categories.getSelectedItem().equals("CLOTHING")) {
                    productTableModel.setRowCount(0);
                    for (Product product : shoppingManager.getProductList()) {
                        if (product.getCategory().equals("Clothing")) {
                            Object[] rowData = {product.getpID(), product.getpName(), product.getCategory(), product.getpPrice(), "Info"};
                            productTableModel.addRow(rowData);
                        }
                    }
                }
            }
        }

        public void displayProductInfo ( int selectedRow){
            String productID = (String) productTableModel.getValueAt(selectedRow, 0);
            for (Product product : shoppingManager.getProductList()) {
                if (product.getpID().equals(productID)) {
                    productDetail.setText(product.toString());
                    break;
                }
            }
        }

    public static void main(String[] args) {
        new ShoppingPage();
    }
}
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

public class ShoppingPage extends JFrame implements ActionListener{
    private JFrame frame = new JFrame();
    private JButton cartButton;
    private JButton addToCart;
    private JTable productTable;
    private JTextArea productDetail;
    private JComboBox categories;
    private DefaultTableModel productTableModel;
    private ShoppingCart shoppingCart;
    private String [] columns = {"ProductID","Name","Category","Price(€)","Info"};
    private WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
    private ShoppingCartPage shoppingCartInterface;
    User user;

    ShoppingPage(WestminsterShoppingManager shoppingManager, User user) {
        this.user = user;
        this.shoppingCart = new ShoppingCart(user);
        this.shoppingManager = shoppingManager;

        //set frame
        frame.setTitle("Westminster Shopping Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);


        //Create Components
        JLabel label = new JLabel("Select Product Category");

        categories = new JComboBox(new String[]{"ALL", "ELECTRONICS", "CLOTHING"});
        categories.addActionListener(this);

        cartButton = new JButton("Shopping Cart");
        cartButton.addActionListener(this);

        addToCart = new JButton("Add to Shopping Cart");
        addToCart.addActionListener(this);

        productTableModel = new DefaultTableModel(); // Table
        productTableModel.setColumnIdentifiers(columns);

        productTable = new JTable(productTableModel);
        JScrollPane scroll = new JScrollPane(productTable);

        productDetail = new JTextArea(10, 30);
        productDetail.setEditable(false);


        // Initial table data
        for (Product product : shoppingManager.getProductList()) {
            Object[] rowData = {product.getpID(), product.getpName(), product.getCategory(), product.getpPrice(), infoCol(product)};
            if (product.getAvailableStock()<3){
                lowAvailability(rowData,product);
            }
            productTableModel.addRow(rowData);
        }


        //Top bar panel
        JPanel topBar = new JPanel();
        topBar.add(label);
        topBar.add(categories);
        topBar.add(cartButton);


        //Product Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scroll, BorderLayout.CENTER);


        //Product Details panel
        JPanel detailPanel = new JPanel();
        detailPanel.add(productDetail);
        detailPanel.add(addToCart);


        //set frame
        frame.setLayout(new BorderLayout());
        frame.add(topBar, BorderLayout.NORTH);
        frame.add(tablePanel, BorderLayout.CENTER);
        frame.add(detailPanel, BorderLayout.SOUTH);
        sortTableByProductId();
        frame.setVisible(true);


        // Table Listener
        productTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow != -1) {
                    int selectedModelRow = productTable.convertRowIndexToModel(selectedRow);
                    displayProductInfo(selectedModelRow);
                }
            }
        });

    }

    // Action Listeners
    @Override
    public void actionPerformed (ActionEvent e){
        // If shopping cart button is clicked
        if (e.getSource() == cartButton) {
            shoppingCartInterface = new ShoppingCartPage(shoppingCart, user);
        }

        // If add to cart button is clicked
        if (e.getSource()==addToCart){
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow != -1) {
                String productID = (String) productTableModel.getValueAt(selectedRow, 0);
                for (Product product : shoppingManager.getProductList()) {
                    if (product.getpID().equals(productID)) {
                        shoppingCart.addProducts(product);
                        JOptionPane.showMessageDialog(this, "The Product has been added successfully");
                        break;
                    }
                }
            }else {JOptionPane.showMessageDialog(this, "Please select a product to be added");}
        }


        // Filter category
        if (e.getSource() == categories) {
            if (categories.getSelectedItem().equals("ALL")) {
                productTableModel.setRowCount(0);
                for (Product product : shoppingManager.getProductList()) {
                    Object[] rowData = {
                            product.getpID(), product.getpName(), product.getCategory(), product.getpPrice(), infoCol(product)
                    };
                    if (product.getAvailableStock()<3){
                        lowAvailability(rowData,product);
                    }
                    productTableModel.addRow(rowData);
                }
            }
            if (categories.getSelectedItem().equals("ELECTRONICS")) {
                productTableModel.setRowCount(0);
                for (Product product : shoppingManager.getProductList()) {
                    if (product.getCategory().equals("Electronics")) {
                        Object[] rowData = {product.getpID(), product.getpName(), product.getCategory(), product.getpPrice(), infoCol(product)
                        };
                        if (product.getAvailableStock()<3){
                            lowAvailability(rowData,product);
                        }
                        productTableModel.addRow(rowData);
                    }
                }
            }
            if (categories.getSelectedItem().equals("CLOTHING")) {
                productTableModel.setRowCount(0);
                for (Product product : shoppingManager.getProductList()) {
                    if (product.getCategory().equals("Clothing")) {
                        Object[] rowData = {
                                product.getpID(), product.getpName(), product.getCategory(), product.getpPrice(), infoCol(product)
                        };
                        if (product.getAvailableStock()<3){
                            lowAvailability(rowData,product);
                        }
                        productTableModel.addRow(rowData);
                    }
                }
            }
        }
    }

    // Display product details
    public void displayProductInfo (int selectedRow){
        String productID = (String) productTableModel.getValueAt(selectedRow, 0);
        for (Product product : shoppingManager.getProductList()) {
            if (product.getpID().equals(productID)) {
                productDetail.setText("Selected Product-Details\n\n" + product.toString());
                break;
            }
        }
    }

    // Fill info column in table
    public String infoCol (Product product){
        String retString=null;
        if (product instanceof Electronics) {
            retString = ((Electronics) product).getBrand() + ", " + ((Electronics) product).getWarrantyPeriod()+" months warranty";
        }
        if (product instanceof Clothing) {
            retString = ((Clothing) product).getSize() + ", " + ((Clothing) product).getColor();
        }
        return retString;
    }

    // Mark availability
    public void lowAvailability(Object[] rowData, Product product){
        rowData[0] = product.getpID();
        rowData[1] = "<html><font color='red'>" + product.getpName() + "</font></html>";
        rowData[2] = "<html><font color='red'>" + product.getCategory() + "</font></html>";
        rowData[3] = "<html><font color='red'>" + product.getpPrice() + "</font></html>";
        rowData[4] = "<html><font color='red'>" + infoCol(product) + "</font></html>";
    }

    // Sort table alphabetically
    public void sortTableByProductId() {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(productTableModel);
        productTable.setRowSorter(sorter);

        Comparator<String> productIdComparator = Comparator.naturalOrder();
        sorter.setComparator(0, productIdComparator);
    }

}
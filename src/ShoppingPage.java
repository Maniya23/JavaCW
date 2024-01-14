import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

public class ShoppingPage extends JFrame implements ActionListener{
    JFrame frame = new JFrame();
    JButton cartButton;
    JButton addToCart;
    JTable productTable;
    JTextArea productDetail;
    JComboBox categories;
    DefaultTableModel productTableModel;
    ShoppingCart shoppingCart = new ShoppingCart();
    String [] columns = {"ProductID","Name","Category","Price(€)","Info"};
    WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

//    ArrayList<Product> cart= new ArrayList<>(); //Create shopping cart
//    ArrayList<Product> productList = new ArrayList<>();

    ShoppingPage(WestminsterShoppingManager shoppingManager) {
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

        addToCart = new JButton("Add to cart");
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

        // Sort Table
        sortTableByProductId();

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
        // If shopping cart button is clicked
        if (e.getSource() == cartButton) {
            this.dispose();
            ShoppingCartPage shoppingCartInterface = new ShoppingCartPage(shoppingCart);
        }

        // If add to cart button is clicked
        if (e.getSource()==addToCart){
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow != -1) {
                String productID = (String) productTableModel.getValueAt(selectedRow, 0);
                for (Product product : shoppingManager.getProductList()) {
                    if (product.getpID().equals(productID)) {
//                        int count = product.getAvailableStock();
                        shoppingCart.addProducts(product);
                        JOptionPane.showMessageDialog(this, "The Product has been added successfully");
                        break;
                    }
                }
            }
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

    public void displayProductInfo (int selectedRow){
        String productID = (String) productTableModel.getValueAt(selectedRow, 0);
        for (Product product : shoppingManager.getProductList()) {
            if (product.getpID().equals(productID)) {
                productDetail.setText("Selected Product-Details\n\n" + product.toString());
                break;
            }
        }
    }

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

    public void lowAvailability(Object[] rowData, Product product){
        rowData[0] = product.getpID();
        rowData[1] = "<html><font color='red'>" + product.getpName() + "</font></html>";
        rowData[2] = "<html><font color='red'>" + product.getCategory() + "</font></html>";
        rowData[3] = "<html><font color='red'>" + product.getpPrice() + "</font></html>";
        rowData[4] = "<html><font color='red'>" + infoCol(product) + "</font></html>";
    }

    public void sortTableByProductId() {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(productTableModel);
        productTable.setRowSorter(sorter);

        Comparator<String> productIdComparator = Comparator.naturalOrder();
        sorter.setComparator(0, productIdComparator);
        sorter.toggleSortOrder(0);  // Initially, sort ascending
    }

    private Product findProductById(String productID) {
        for (Product product : shoppingManager.getProductList()) {
            if (product.getpID().equals(productID)) {
                return product;
            }
        }
        return null; // Product not found
    }

}
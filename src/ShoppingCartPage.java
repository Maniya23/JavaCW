import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.util.HashSet;

public class ShoppingCartPage extends JFrame{
    private User user;
    JFrame frame = new JFrame();
    DefaultTableModel ProductTableModel;
    HashSet<String> uniqueProducts = new HashSet<>();
    private ShoppingCart shoppingCart;
    JTable ChosenProductsTable;
    JTextArea FinalAmountDesc;

    ShoppingCartPage(ShoppingCart shoppingCart, User user) {
        this.user = user;
        this.shoppingCart = shoppingCart;

        frame.setTitle("Shopping Cart");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(600, 600);


        ProductTableModel = new DefaultTableModel();
        String[] tableColumns = {"Product", "Quantity","Price(€)"};
        ProductTableModel.setColumnIdentifiers(tableColumns);

        for (Product product : shoppingCart.getItemsCart()) {
            if (!uniqueProducts.contains(product.getpID())){
                Object[] rowData = {ProductInfo(product),shoppingCart.getQuantity(product),shoppingCart.getProductTotal(product)+" €"};
                ProductTableModel.addRow(rowData);
                uniqueProducts.add(product.getpID());
            }
        }

        ChosenProductsTable=new JTable(ProductTableModel);
        JScrollPane scroll = new JScrollPane(ChosenProductsTable);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scroll);

        FinalAmountDesc = new JTextArea("First Purchase Discount(10%) :"+shoppingCart.firstPurchaseDiscount(shoppingCart, user.isUserFound())+
                "\n3 Items in the same Category Discount(20%) :"+shoppingCart.sameProductDiscount(shoppingCart)+" €"+
                "\nTotal Amount :"+shoppingCart.cartTotal()+" €");

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                shoppingCart.setCartTotalAmt(0);
//                frame.dispose();
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(tablePanel, BorderLayout.NORTH);

        frame.add(FinalAmountDesc, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public String ProductInfo(Product product){
        String info = "";
        if (product instanceof Electronics){
            info = product.getpID()+", "+product.getpName()+", "+((Electronics) product).getBrand()+", "+((Electronics) product).getWarrantyPeriod()+" months warranty";
        }
        if (product instanceof Clothing){
            info = product.getpID()+", "+product.getpName()+", "+((Clothing) product).getColor()+", "+((Clothing) product).getSize();
        }
        return info;
    }



    private Product findProductById(String productID) {
        for (Product product : shoppingCart.getItemsCart()) {
            if (product.getpID().equals(productID)) {
                return product;
            }
        }
        return null; // Product not found
    }
}

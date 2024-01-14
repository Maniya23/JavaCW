import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashSet;

public class ShoppingCartPage {
    JFrame frame = new JFrame();
    DefaultTableModel ProductTableModel;
    HashSet<String> uniqueProducts = new HashSet<>();
    ShoppingCart shoppingCart;
    JTable ChosenProductsTable;

    JTextArea FinalAmountDesc;

    ShoppingCartPage(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;

        frame.setTitle("Shopping Cart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        ProductTableModel = new DefaultTableModel();
        String[] tableColumns = {"Product", "Quantity,","Price(€)"};
        ProductTableModel.setColumnIdentifiers(tableColumns);

        for (Product product : shoppingCart.itemsCart) {
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

        FinalAmountDesc = new JTextArea("First Purchase Discount(10%) :"+shoppingCart.itemsCart.size()+
                "\n3 Items in the same Category Discount(20%) :"+sameProductDiscount()+" €"+
                "\nTotal Amount :"+shoppingCart.cartTotal()+" €");


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

    public double firstPurchaseDiscount(){
        double discount = 0;
        if (shoppingCart.itemsCart.size()>=5){
            discount = shoppingCart.cartTotal()*0.1;
        }
        return discount;
    }

    public double sameProductDiscount() {
        double discount = 0;
        for (Product product:shoppingCart.itemsCart){
            if (shoppingCart.getQuantity(product)>=3){
                discount += product.getpPrice()*0.2;
            }
        }
        return discount;
    }
}

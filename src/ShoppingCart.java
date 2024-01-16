import java.util.ArrayList;

public class ShoppingCart {
    private User user;
    private ArrayList<Product>itemsCart;
    private double cartTotalAmt=0; //Total amount of the cart

    ShoppingCartPage shoppingCartPage;

    ShoppingCart(User user){
        this.user = user;
        itemsCart=new ArrayList<Product>();
    }

    public void addProducts(Product item){
        itemsCart.add(item);
    }

    // Calculate cart total
    public double cartTotal(){
        for (Product i : itemsCart) {
            cartTotalAmt = cartTotalAmt+i.getpPrice();
        }
        cartTotalAmt = cartTotalAmt - sameProductDiscount(this);
        cartTotalAmt = cartTotalAmt - firstPurchaseDiscount(this, user.isUserFound());
        return cartTotalAmt;
    }

    public void setCartTotalAmt(double cartTotalAmt) {
        this.cartTotalAmt = cartTotalAmt;
    }

    public ArrayList<Product> getItemsCart() {
        return itemsCart;
    }

    // Get quantity of product
    public int getQuantity(Product product){
        int quantity = 0;
        for (Product productNew:this.getItemsCart()){
            if (product.getpID().equals(productNew.getpID())){
                quantity++;}}
        return quantity;
    }

    public double getProductTotal(Product product){
        double productTotal = 0;
        for (Product product2:this.getItemsCart()){
            if (product.getpID().equals(product2.getpID())){
                productTotal = product.getpPrice()*getQuantity(product);
            }
        }
        return productTotal;
    }

    // Same category product discount
    public double sameProductDiscount(ShoppingCart shoppingCart){
        double discount = 0;
        for (Product product:shoppingCart.itemsCart){
            if (shoppingCart.getQuantity(product)>=3){
                discount += product.getpPrice()*0.2;
            }
        }
        return discount;
    }

    // First time user discount
    public double firstPurchaseDiscount(ShoppingCart shoppingCart, boolean firstPurchase){
        double discount = 0;
        if (firstPurchase){
            for (Product product : shoppingCart.itemsCart) {
                discount += product.getpPrice()*0.1;
            }
        }
        return discount;
    }
}


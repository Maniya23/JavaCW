import java.util.ArrayList;

public class ShoppingCart {
    protected ArrayList<Product>itemsCart;
    private double cartTotalAmt=0; //Total amount of the cart

    ShoppingCart(){
        itemsCart=new ArrayList<Product>();
    }

    public void addProducts(Product item){
        itemsCart.add(item);
    }

    public void removeProduct(Product item){
        itemsCart.remove(item);
    }

    public double cartTotal(){
        for (Product i : itemsCart) {
            cartTotalAmt = cartTotalAmt+i.getpPrice();
        }
        return cartTotalAmt;
    }

    public ArrayList<Product> viewCart() {
        return itemsCart;
    }

    public int getQuantity(Product product){
        int quantity = 0;
        for (Product product2:this.viewCart()){
            if (product.getpID().equals(product2.getpID())){
                quantity++;
            }
        }
        return quantity;

    }
    public double getProductTotal(Product product){
        double productTotal = 0;
        for (Product product2:this.viewCart()){
            if (product.getpID().equals(product2.getpID())){
                productTotal = product.getpPrice()*getQuantity(product);
            }
        }
        return productTotal;
    }
}


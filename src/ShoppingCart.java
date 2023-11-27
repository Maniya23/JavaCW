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
            cartTotalAmt=cartTotalAmt+i.getpPrice();
        }
        return cartTotalAmt;
    }

    public ArrayList<Product> viewCart() {
        return itemsCart;
    }

}


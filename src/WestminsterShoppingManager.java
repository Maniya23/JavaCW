import java.util.ArrayList;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager{
    private ArrayList<Product> productList;

    WestminsterShoppingManager(){
        productList=new ArrayList<Product>();
    }
    @Override
    public void addProducts(Product product) {

        if (productList.size()<50){
            productList.add(product);
            System.out.println("Added product"+product.getpName()+"successfully");
        }
        else {
            System.out.println("Sorry product cannot be added due reaching maximum number of products");
        }
    }

    @Override
    public void deleteProducts(String pID) {
        Scanner scanner = new Scanner(System.in);
        for (Product j:productList) {
            if (j.getpID().equals(pID)){
                System.out.println("Product"+j.getpID()+" : "+j.getpName()+"will be removed");
                while(true){
                    System.out.print("Enter 'y' to delete or 'n' to cancel");
                    String selection = scanner.nextLine();
                    selection.toLowerCase();
                    if (selection.equals("y")|| selection.equals("n")){
                        break;
                    }
                    else {
                        System.out.println("please enter valid input");
                        System.out.println("-------------------------");
                    }
                }
                productList.remove(j);
                System.out.println("Product successfully removed");
            }
        }
    }

    @Override
    public void printProductList() {

    }
}

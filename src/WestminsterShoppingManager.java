import java.util.ArrayList;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager{
    private ArrayList<Product> productList;

    WestminsterShoppingManager(){
        productList=new ArrayList<Product>();
    }
    @Override
    public void addProducts(Product product) {
        Scanner scanner = new Scanner(System.in);
        String productType;
        while(true){
            if (productList.size()<50){

                while (true){
                    System.out.print("Enter 'E' if your adding an electronic product and enter 'C' if your adding a clothing product : ");
                    productType = scanner.nextLine().toUpperCase();
                    if (productType.equals("E") || productType.equals("C")){
                        break;
                    }else {continue;}
                }

                System.out.print("Enter product ID : ");
                String pID= scanner.nextLine();

                System.out.print("Enter the name of product : ");
                String pName = scanner.nextLine();

                System.out.print("Enter how many products : ");
                int noOfProducts = scanner.nextInt();

                System.out.print("Enter the price for product : ");
                double pPrice = scanner.nextDouble();

                if (productType.equals("E")){
                    System.out.print("Enter the brand name of the "+pName+" : ");
                    String brand = scanner.nextLine();

                    System.out.print("Enter the warranty period in months for "+pName+" : ");
                    int warranty = scanner.nextInt();

                    //Create new electronic product
                    Electronics electronicProduct = new Electronics(pID,pName,noOfProducts,pPrice,brand,warranty);
                    productList.add(electronicProduct);
                }
                else {
                    System.out.print("Enter the size of "+pName+": ");
                    String size = scanner.nextLine();

                    System.out.println("Enter the colour of the "+pName+" : ");
                    String color = scanner.nextLine();

                    //Create new clothing product
                    Clothing clothingProduct = new Clothing(pID,pName,noOfProducts,pPrice,size,color);
                    productList.add(clothingProduct);
                }

            }
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
                        System.out.println("-------------------------\n");
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

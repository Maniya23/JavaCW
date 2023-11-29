import java.util.ArrayList;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager{
    private ArrayList<Product> productList;

    WestminsterShoppingManager(){
        productList=new ArrayList<Product>();
    }
    @Override
    public void addProducts() {
        Scanner scanner = new Scanner(System.in);
        String productType;

        while(true){
            try{
                if (productList.size()<50){

                    // Getting product type the product to be added
                    while (true){
                        System.out.print("Enter 'E' if your adding an electronic product and enter 'C' if your adding a clothing product : ");
                        productType = scanner.nextLine().toUpperCase();
                        if (productType.equals("E") || productType.equals("C")){
                            break;
                        }
                    }

                    // Getting required product details
                    System.out.print("Enter product ID : ");
                    String pID= scanner.nextLine();

                    System.out.print("Enter the name of product : ");
                    String pName = scanner.nextLine();

                    System.out.print("Enter how many products : ");
                    int noOfProducts = scanner.nextInt();

                    System.out.print("Enter the price for product : ");
                    double pPrice = scanner.nextDouble();

                    // Checking if product type is electronic or clothing
                    if (productType.equals("E")){ // If product is electronic
                        System.out.print("Enter the brand name of the "+pName+" : ");
                        String brand = scanner.nextLine();

                        System.out.print("Enter the warranty period in months for "+pName+" : ");
                        int warranty = scanner.nextInt();

                        //Create new electronic product
                        Electronics electronicProduct = new Electronics(pID,pName,noOfProducts,pPrice,brand,warranty);
                        productList.add(electronicProduct);
                        System.out.println("Product is added successfully");
                    }
                    else { //If the product is clothing
                        System.out.print("Enter the size of "+pName+": ");
                        String size = scanner.nextLine();

                        System.out.println("Enter the colour of the "+pName+" : ");
                        String color = scanner.nextLine();

                        //Create new clothing product
                        Clothing clothingProduct = new Clothing(pID,pName,noOfProducts,pPrice,size,color);
                        productList.add(clothingProduct);
                        System.out.println("Product is added successfully");
                    }
                }
                else {
                    System.out.println("Cannot add product");
                }
                break;

            //}
            }catch(Exception e){
                System.out.println("Input error occurred. Please use correct inputs");
            }
        }
    }

    @Override
    public void deleteProducts() { // Use instance of  to find out whether the electronic or clothing
        Scanner scanner = new Scanner(System.in);
        String selection;

        //Get product ID
        System.out.print("Enter the product ID : ");
        String pID = scanner.nextLine();

        for (Product j:productList) {
            if (j.getpID().equals(pID)){
                System.out.println("Product"+j.getpID()+" : "+j.getpName()+"will be removed");
                while(true){
                    System.out.print("Enter 'y' to delete or 'n' to cancel");
                    selection = scanner.nextLine().toLowerCase();
                    if (selection.equals("y")|| selection.equals("n")){
                        break;
                    }
                    else {
                        System.out.println("please enter valid input");
                        System.out.println("-------------------------\n");
                    }
                }

                // If selection is yes
                if (selection.equals("y")){
                    productList.remove(j); // Remove product from list
                    System.out.println("Product successfully removed");
                }
            }
        }
    }

    @Override
    public void printProductList() {

    }
}

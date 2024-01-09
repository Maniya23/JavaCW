import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
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
                    scanner.nextLine(); // Reset the scanner

                    // Checking if product type is electronic or clothing
                    if (productType.equals("E")){// If product is electronic
                        System.out.print("Enter the brand name of the "+pName+" : ");
                        String brand = scanner.nextLine();

                        System.out.print("Enter the warranty period in months for "+pName+" : ");
                        int warranty = scanner.nextInt();

                        //Create new electronic product
                        Electronics electronicProduct = new Electronics(pID,pName,noOfProducts,pPrice,brand,warranty);
                        productList.add(electronicProduct);
                        System.out.println("Product is added successfully");
                    }
                    else {
                        //If the product is clothing

                        System.out.print("Enter the size of "+pName+": ");
                        String size = scanner.nextLine();

                        System.out.print("Enter the colour of the "+pName+" : ");
                        String color = scanner.nextLine();

                        //Create new clothing product
                        Clothing clothingProduct = new Clothing(pID,pName,noOfProducts,pPrice,size,color);
                        productList.add(clothingProduct);
                        System.out.println("Product is added successfully");
                    }
                }
                else {
                    System.out.println("Product list size exceeded\nConsider deleting products before adding new products");
                }
                break;

            //}
            }catch(Exception e){
                System.out.println("Input error occurred. Please use correct inputs");
            }
        }
    }

    @Override
    public void deleteProducts() {
        Scanner scanner = new Scanner(System.in);
        String selection;

        //Get product ID
        System.out.print("Enter the product ID : ");
        String pID = scanner.nextLine();

        // Loop productList
        for (Product deletingProduct :productList) {
            if (deletingProduct.getpID().equals(pID)){

                //Print product details if product is found
                System.out.println(deletingProduct.toString());

                while(true){
                    System.out.print("Enter 'y' to delete or 'n' to cancel");
                    selection = scanner.nextLine().toLowerCase();
                    if (selection.equals("y")|| selection.equals("n")){
                        // If selection is yes
                        if (selection.equals("y")){
                            productList.remove(deletingProduct); // Remove product from list
                            System.out.println("Product successfully removed");
                            System.out.println(productList.size()+" products are remaining");
                        }
                        break;
                    }
                    else {
                        System.out.println("please enter valid input");
                        System.out.println("-------------------------\n");
                    }
                }
                break;
            }
        }
    }

    @Override
    public void printProductList() {
        Collections.sort(productList, Comparator.comparing(Product::getpID));

        for (Product p : productList){
            System.out.println(p.toString());
            System.out.println();
        }
    }

    @Override
    public void saveToFile(){
        try{
            FileWriter readableTxt = new FileWriter("Products.txt"); // Create readable file
            FileOutputStream binFile = new FileOutputStream("productBin.txt"); // Create binary file (objects)
            ObjectOutputStream productBinOut = new ObjectOutputStream(binFile); // serializer


            //Write human-readable file
            for (Product each: productList) {
                readableTxt.write(each.toString()+"\n\n");
            }
            readableTxt.close();

            //Write binary file for object storing
            productBinOut.writeObject(productList);


        } catch (IOException e){
            System.out.println("Error writing to file");
        }
    }

    public ArrayList<Product> readFromFile(){
        productList = new ArrayList<>();// Create a new productList

        try {
            FileInputStream productBinIn = new FileInputStream("productBin.txt");
            ObjectInputStream productBinList = new ObjectInputStream(productBinIn);

            productList = (ArrayList<Product>) productBinList.readObject();
        }catch (Exception e){
            System.out.println("Error");
        }


        return productList;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String repeater;
        String userMenu =
                "1 : Add products to system\n" +
                "2 : Delete products from system\n" +
                "3 : Print the list of products\n" +
                "4 : Save products to file\n" +
                "5 : Load saved products\n" +
                "Enter the number for the function you want to do : ";

        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        System.out.println("Welcome to Westminster Shopping Management System");
        System.out.println("*--------------------------------------------------*");

        //Loop the menu for exceptions and repetitions
        while (true) {
            try {
                System.out.print(userMenu);
                int user_selection = scanner.nextInt();
                if (user_selection == 1 || user_selection == 2 || user_selection == 3 || user_selection == 4 || user_selection == 5) {
                    if (user_selection==1){
                        shoppingManager.addProducts();
                    } else if (user_selection==2) {
                        shoppingManager.deleteProducts();
                    } else if (user_selection==3){
                        shoppingManager.printProductList();
                    } else if (user_selection==4) {
                        shoppingManager.saveToFile();
                    } else if (user_selection==5) {
                        shoppingManager.productList=shoppingManager.readFromFile();
                    } else {
                        System.out.println("Condition OK");
                    }

                    scanner.nextLine(); // Clear scanner buffer
                    //Ask user if to repeat the process
                    while (true){
                        System.out.print("\nDo you want to do any other function. Press 'Y' for yes and 'N' for no : ");
                        repeater = scanner.nextLine().toUpperCase();
                        if (repeater.equals("Y") || repeater.equals("N")){break;}
                    }
                    if (repeater.equals("Y")){
                        continue;
                    }
                    break;
                }
                else {
                    System.out.println("Please enter a valid number from 1 - 5\n");
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid input\n");
                scanner.next();
            }
        }
    }

}

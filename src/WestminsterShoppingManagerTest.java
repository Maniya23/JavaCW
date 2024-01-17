import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class WestminsterShoppingManagerTest {
        Product p1 = new Electronics("E001","TV",10,1500,"Panasonic",24);
        Product p2 = new Electronics("E002","Mobile",15,1200,"Samsung",10);
        Product p3 = new Clothing("C001","T-Shirt",25,10,"S","Red");
        Product p4 = new Clothing("C002","Denim",30,30,"L","Blue");

        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

    @Test
    void addedNewProduct() {

        //add new products to list
        shoppingManager.getProductList().add(p1);
        shoppingManager.getProductList().add(p2);
        shoppingManager.getProductList().add(p3);
        shoppingManager.getProductList().add(p4);

        // check list size
        assertEquals(4,shoppingManager.getProductList().size());

        // check if list contains added products
        assertTrue(shoppingManager.getProductList().contains(p1));
        assertTrue(shoppingManager.getProductList().contains(p2));
        assertTrue(shoppingManager.getProductList().contains(p3));
        assertTrue(shoppingManager.getProductList().contains(p4));
    }

    @Test
    void deletedProducts() {

        //add new products to list
        shoppingManager.getProductList().add(p1);
        shoppingManager.getProductList().add(p2);

        // check list size
        assertEquals(2,shoppingManager.getProductList().size());

        // Remove product 1
        shoppingManager.getProductList().remove(p1);

        // check list size
        assertEquals(1,shoppingManager.getProductList().size());

        // check if list contains deleted products
        assertFalse(shoppingManager.getProductList().contains(p1));

        // check if list contains other added products
        assertTrue(shoppingManager.getProductList().contains(p2));

    }

    @Test
    void productSorting() {

        //add new products to list
        shoppingManager.getProductList().add(p1);
        shoppingManager.getProductList().add(p2);

        // check if list contains added products
        assertTrue(shoppingManager.getProductList().contains(p1));
        assertTrue(shoppingManager.getProductList().contains(p2));

        // sort list
        Collections.sort(shoppingManager.getProductList(), Comparator.comparing(Product::getpID));

        //Product should be sorted
        assertEquals(p1,shoppingManager.getProductList().get(0));

    }

    @Test
    void checkProductCategory() {
        //add new products to list
        shoppingManager.getProductList().add(p1);
        shoppingManager.getProductList().add(p3);

        // check list size
        assertEquals(2,shoppingManager.getProductList().size());

        // check if list contains added products
        assertTrue(shoppingManager.getProductList().contains(p1));
        assertTrue(shoppingManager.getProductList().contains(p3));

        // check if product category is correct
        assertEquals("Electronics",p1.getCategory());
        assertEquals("Clothing",p3.getCategory());
    }

    @Test
    void validateToString() {
        //add new products to list
        shoppingManager.getProductList().add(p1);
        shoppingManager.getProductList().add(p4);

        assertEquals("""
                Product ID         : E001
                Product Name       : TV
                Available stock    : 10
                Price              : 1500.0
                Brand              : Panasonic
                Warranty period    : 24
                Product category   : Electronics""",p1.toString());

        assertEquals("""
                Product ID         : C002
                Product Name       : Denim
                Available stock    : 30
                Price              : 30.0
                size               : L
                color              : Blue
                Product category   : Clothing""",p4.toString());
    }

    @Test
    void savingAndReadingFile() {

        //add new products to list
        shoppingManager.getProductList().add(p1);
        shoppingManager.getProductList().add(p2);

        // check list size
        assertEquals(2,shoppingManager.getProductList().size());

        // save list to file
        shoppingManager.saveToFile();
        shoppingManager.getProductList().clear();

        // check list size
        assertEquals(0,shoppingManager.getProductList().size());

        // read list from file
        shoppingManager.readFromFile();
        assertEquals(2,shoppingManager.getProductList().size());

        // check if list contains added products
        assertEquals(p1.getpID(),shoppingManager.getProductList().get(0).getpID());
        assertEquals(p2.getpID(),shoppingManager.getProductList().get(1).getpID());
    }




}
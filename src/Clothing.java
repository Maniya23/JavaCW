public class Clothing extends Product{
    protected String size;
    Clothing(String pName, String pDescription, int availableStock, double pPrice) {
        super.pName=pName;
        super.pDescription=pDescription;
        super.availableStock=availableStock;
        super.pPrice=pPrice;
    }
}

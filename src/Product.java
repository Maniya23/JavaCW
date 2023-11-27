abstract class Product {

    protected int pID;
    protected String pName;
    protected String pDescription;
    protected int availableStock;
    protected double pPrice;

    //Default constructor
    Product(int pID,String pName, String pDescription, int availableStock, double pPrice){
        this.pID=pID;
        this.pName=pName;
        this.pDescription=pDescription;
        this.availableStock=availableStock;
        this.pPrice=pPrice;
    }

    //Getters

    public int getpID() {
        return pID;
    }

    public String getpName() {
        return pName;
    }

    public String getpDescription() {
        return pDescription;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public double getpPrice() {
        return pPrice;
    }

    //Setters

    public void setpID(int pID) {
        this.pID = pID;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public void setpDescription(String pDescription) {
        this.pDescription = pDescription;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    public void setpPrice(double pPrice) {
        this.pPrice = pPrice;
    }
}

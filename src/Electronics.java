public class Electronics extends Product{

    protected String brand;

    protected int warrantyPeriod;

    Electronics(String pID,String pName, int availableStock, double pPrice, String brand, int warrantyPeriod){
       super(pID,pName,availableStock,pPrice);
       this.brand=brand;
       this.warrantyPeriod=warrantyPeriod;
    }

    //Getters
    public String getBrand() {
        return brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    //Setters
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
}

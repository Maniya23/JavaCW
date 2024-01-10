public class Clothing extends Product{
    protected String size;
    protected String color;
//    Clothing(int pID, String pName, String pDescription, int availableStock, double pPrice) {
//        super.pID=pID;
//        super.pName=pName;
//        super.pDescription=pDescription;
//        super.availableStock=availableStock;
//        super.pPrice=pPrice;
//    }

    Clothing(String pID,String pName, int availableStock, double pPrice, String size, String color){
        super(pID,pName,availableStock,pPrice);
        this.size=size;
        this.color=color;
    }

    //Getters

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getCategory(){
        return "Clothing";
    }

    //Setters

    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String toString() {
        String productdetails = super.toString()+"\n"+
                "size               : "+size+"\n"+
                "color              : "+color+"\n"+
                "Product category   : Clothing";

        return productdetails;
    }
}

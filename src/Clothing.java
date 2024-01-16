public class Clothing extends Product{
    protected String size;
    protected String color;

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
        String productDetails = super.toString()+"\n"+
                "size               : "+size+"\n"+
                "color              : "+color+"\n"+
                "Product category   : "+getCategory();

        return productDetails;
    }
}

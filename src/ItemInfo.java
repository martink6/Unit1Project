public class ItemInfo{
    private double itemCost;
    private int itemCount;


    public ItemInfo(double itemCost, int itemCount){
        this.itemCost = itemCost;
        this.itemCount = itemCount;
    }

    public double getItemCost(){
        return itemCost;
    }

    public int getItemCount(){
        return itemCount;
    }
}
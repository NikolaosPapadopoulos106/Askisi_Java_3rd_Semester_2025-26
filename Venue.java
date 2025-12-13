public class Venue {
    private static double dailyPrice = 400.0;

    private String name;
    public Venue(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public double getDailyPrice(){
        return dailyPrice;
    }

    public static void setDailyPrice(double price){
        dailyPrice = price;
    }
}

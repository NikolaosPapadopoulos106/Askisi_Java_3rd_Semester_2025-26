public class Vehicles {
    public enum VehicleType{
        TWO_WHEELED,
        FOUR_WHEELED,
    }

    private static int nextPlateNumber = 1000;

    private String licensePlate;
    private VehicleType type;
    private boolean available;


    private static double skouter = 20.0;
    private static double gourouna = 30.0;
    private static double auto = 50.0;

    private double dailyPrice;

    public Vehicles(VehicleType type, double dailyPrice){
        this.licensePlate = "VEH-" + nextPlateNumber++;
        this.type = type;
        this.dailyPrice = dailyPrice;
        this.available = true;
    }

    public static Vehicles createSkouter(){
        return new Vehicles(VehicleType.TWO_WHEELED, skouter);
    }

    public static Vehicles createGourouna(){
        return new Vehicles(VehicleType.FOUR_WHEELED, gourouna);
    }

    public static Vehicles createAuto(){
        return new Vehicles(VehicleType.FOUR_WHEELED, auto);
    }

    public boolean isAvailable(){
        return available;
    }

    public void rentToCustomer(){
        if(available){
            available = false;
        }
    }

    public void returnVehicle(){
        available = true;
    }

    public static void setPrices(double newSkouter, double newGourouna, double newAuto){
        skouter = newSkouter;
        gourouna = newGourouna;
        auto = newAuto;
    }

    public String getLicensePlate(){
        return licensePlate;
    }

    public VehicleType getType(){
        return type;
    }

    public double getDailyPrice(){
        return dailyPrice;
    }

}

public class Room {

    public enum RoomType{
        SINGLE,
        DOUBLE,
        TRIPLE,
        SUITE
    }
    private static int nextRoomId = 1;
    private RoomType type;
    private int id;
    private double nightlyRate;

    private static double baseprice = 100.0;
    private static double doubleMult = 1.60;
    private static double tripleMult = 2.10;
    private static double windowExtra = 0.10;
    private static double suiteMult = 1.50;


    public Room(RoomType type){
        this.type = type;
        this.id = id;
        this.nightlyRate = nightlyRate;
    }

    private double calculatePrice(RoomType type){
        switch (type){
            case SINGLE:
                return baseprice;

            case DOUBLE:
                return baseprice *doubleMult;

            case TRIPLE:
                return baseprice * tripleMult;

            case SUITE:
                return (baseprice * doubleMult) * suiteMult;

            default:
                return baseprice;
        }
    }

    public static void setBaseprice(double price){
        baseprice = price;
    }

    public static void setMultipliers(double dbl, double triple, double suite){
        doubleMult = dbl;
        tripleMult = triple;
        suiteMult = suite;
    }

    public RoomType getType(){
        return type;
    }

    public int getId(){
        return id;
    }

    public double getNightlyRate(){
        return nightlyRate;
    }

    @Override
    public String toString(){
        return "Room " + id + " (€" + nightlyRate + "/night)";
    }
}

public class Room {
    private static int nextRoomId = 1;

    private String id;
    private double nightlyRate;

    public Room(String id, double nightlyRate){
        this.id = id;
        this.nightlyRate = nightlyRate;
    }

    public String getId(){
        return id;
    }

    public double getNightlyRate(){
        return nightlyRate;
    }

    @Override
    public String toString(){
        return "Room " + id + " ($" + nightlyRate + "/night)";
    }
}

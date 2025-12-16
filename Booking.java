import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking {
    private static int nextId = 1;

    private int id;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;
    private String customerName;
    private boolean massage;

    public Booking(Room room, String customerName, LocalDate startDate, LocalDate endDate, boolean massage){
        this.id = nextId++;
        this.customerName = customerName;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.massage = massage;
    }

    public int getId(){
        return id;
    }

    public Room getRoom(){
        return room;
    }

    public String getCustomerName(){
        return customerName;
    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }

    public boolean hasMassage(){
        return massage;
    }

    public long getNights(){
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    public double getTotalCost(){
        double baseCost = getNights() * room.getNightlyRate();

        if (massage && room.getType() == Room.RoomType.SUITE){
            baseCost *= 1.10;
        }
        return baseCost;
    }

}

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking {
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;

    public Booking(Room room, LocalDate startDate, LocalDate endDate){
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Room getRoom(){
        return room;
    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }

    public long getNights(){
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    public double getTotalCost(){
        return getNights() * room.getNightlyRate();
    }
}

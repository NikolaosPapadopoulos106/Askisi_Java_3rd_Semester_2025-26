import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingManager {

    private List<Booking>bookings = new ArrayList<>();

    public boolean isAvailable(Room room, LocalDate start, LocalDate end){
        for (Booking b : bookings){
            if (b.getRoom().getId().equals(room.getId())){
                boolean overlap =
                        start.isBefore(b.getEndDate()) &&
                        end.isAfter(b.getStartDate());

                if (overlap){
                    return false; //check if room not available
                }
            }
        }
        return true; // room is available
    }

    public Booking createBooking(Room room, LocalDate start, LocalDate end){
        if(!isAvailable(room,start,end)){
            return null;
        }
        
        Booking booking = new Booking(room, start, end);
        bookings.add(booking);
        return booking;
    }
    
    public List<Booking> getBookings(){
        return bookings;
    }
}

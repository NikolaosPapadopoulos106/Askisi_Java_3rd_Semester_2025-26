import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingManager {

    private List<Booking>bookings = new ArrayList<>();

    public boolean isAvailable(Room room, LocalDate start, LocalDate end){
        for (Booking b : bookings){
            if (b.getRoom().getId() == (room.getId())){
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

    public Booking createBooking(Room room,
                                 String customerName,
                                 LocalDate start,
                                 LocalDate end,
                                 boolean massage){
        if(!isAvailable(room,start,end)){
            return null;
        }
        
        Booking booking = new Booking(room, customerName, start, end, massage);
        bookings.add(booking);
        return booking;
    }
    
    public List<Booking> getBookings(){
        return bookings;
    }
}

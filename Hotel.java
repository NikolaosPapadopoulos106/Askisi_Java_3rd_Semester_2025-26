import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private List<Room> rooms = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    public Room addRoom(double nightlyRate){
        Room room = new Room(nightlyRate);
        rooms.addd(room);
        return room;
    }

    public Room getRoomById(int roomId){
        for (Room r : rooms){
            if(r.getId() == roomId){
                return r;
            }
        }
        return null;
    }

    private boolean isROomAvailable(Room room, LocalDate start, LocalDate end){
        for (Booking b :bookings){
            if(b.getRoom().getId() == room.getId()){

                boolean overlap =
                        start.isBefore(b.getEndDate()) &&
                        end.isAfter(b.getStartDate());

                if (overlap){
                    return false;
                }
            }
        }
        return true;
    }
    public Booking createBooking(int roomId, String customerName, LocalDate start, LocalDate end){
        Room room = getRoomById(roomId);
        if (room == null)
            return null;
        if (!isROomAvailable(room, start, end))
            return null;

        Booking b = new Booking(room, customerName, start, end);
        bookings.add(b);
        return b;
    }

    public Booking getBookingById(int id){
        for (Booking b : bookings){
            if (b.getId() == id){
                return b;
            }
        }
        return null;
    }

    public List<Booking> searchBookingByCustomer(String name){
        List<Booking> result = new ArrayList<>();

        for (Booking.b : bookings){
            if(b.getCustomerName().equalsIgnoreCase(name)){
                result.add(b);
            }
        }
        return result;
    }

    public List<Booking> searchBookingsByDate(LocalDate start, LocalDate end){
        List<Booking> result = new ArrayList<>();

        for (Booking b :bookings){
            boolean overlad =
                    start.isBefore(b.getEndDate()) &&
                    end.isAfter(b.getStartDate());
            if(overlad){
                result.add(b);
            }
        }
        return result;
    }

    public boolean deleteBooking(int id){
        Booking b = getBookingById(id);
        if (b == null)
            return false;

        bookings.remove(b);
        return true;
    }

    public List<Room> getRooms(){
        return rooms;
    }
    public List<Booking> getBookings(){
        return bookings;
    }
}

package jedd_project_1;

import java.util.ArrayList;
import java.util.Date;

public class Room {

    private int FloorNum;
    private int RoomNum;
    private int Price;
    private ArrayList<Booking> booking = new ArrayList<>();

    public Room(int FloorNum, int RoomNum, int Price) {
        this.FloorNum = FloorNum;
        this.RoomNum = RoomNum;
        this.Price = Price;

    }

    public int getFloorNum() {
        return FloorNum;
    }

    public void setFloorNum(int FloorNum) {
        this.FloorNum = FloorNum;
    }

    public int getRoomNum() {
        return RoomNum;
    }

    public void setRoomNum(int RoomNum) {
        this.RoomNum = RoomNum;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public void AddBooking(Booking booking) {
        this.booking.add(booking);
    }

    public boolean isAvailable(Date check_in) {
       
        return (booking.isEmpty() || check_in.after(booking.get(booking.size() - 1).getCheck_out()));
    }

    @Override
    public String toString() {
        return "Room number: " + this.RoomNum + ", Floor: " + this.FloorNum + " This Room ";
    }

}

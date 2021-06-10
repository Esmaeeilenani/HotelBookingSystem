package jedd_project_1;

import java.util.Date;

public class Bungalow extends Room {

    private int NumOfRooms;
    private String Outdoor;

    public Bungalow(int FloorNum, int RoomNum, int Price, int NumOfRooms, String Outdoor) {
        super(FloorNum, RoomNum, Price);
        this.NumOfRooms = NumOfRooms;
        this.Outdoor = Outdoor;

    }

    public int getNumOfRooms() {
        return NumOfRooms;
    }

    public void setNumOfRooms(int NumOfRooms) {
        this.NumOfRooms = NumOfRooms;
    }

    public String getOutdoor() {
        return Outdoor;
    }

    public void setOutdoor(String Outdoor) {
        this.Outdoor = Outdoor;
    }

    @Override
    public String toString() {

        return super.toString() + "is a Bungalow with a " + this.Outdoor;
    }

    public String INFO() {

        return this.getRoomNum() + " with a " + this.Outdoor;
    }
    
    public boolean VisitorNeedes(int NumOfRooms, String Outdoor, Date Check_in) {
        return this.NumOfRooms == NumOfRooms && this.Outdoor.equalsIgnoreCase(Outdoor) && isAvailable(Check_in);
    }
}

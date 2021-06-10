package jedd_project_1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Booking {

    private String RefrenceNum;
    private Date Check_in;
    private Date Check_out;
    private ArrayList<Service> Service = new ArrayList<>();
    private Room room;
    private Visitor visitor;

    public Booking(Date Check_in, Date Check_out, Room room, Visitor visitor) {
        int random = (int) (Math.random() * 1000);
        this.Check_in = Check_in;
        this.Check_out = Check_out;
        this.room = room;
        this.visitor = visitor;
        this.RefrenceNum = "" + visitor.getName().charAt(0) + visitor.getID() + random;

    }

    public String getRefrenceNum() {
        return RefrenceNum;
    }

    public Date getCheck_in() {
        return Check_in;
    }

    public void setCheck_in(Date Check_in) {
        this.Check_in = Check_in;
    }

    public Date getCheck_out() {
        return Check_out;
    }

    public void setCheck_out(Date Check_out) {
        this.Check_out = Check_out;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public void AddService(Service service) {
        this.Service.add(service);
    }

    public ArrayList<Service> getService() {
        return Service;
    }

    public static Visitor getVisitorID(ArrayList<Booking> booking, int VisitorID) {

        for (int i = 0; i < booking.size(); i++) {

            if (booking.get(i).getVisitor().getID() == VisitorID) {

                return booking.get(i).getVisitor();
            }

        }

        return null;
    }

    @Override
    public String toString() {
        String in_Dates[] = new SimpleDateFormat("d-M-yyyy").format(Check_in).split("-");
        String out_Dates[] = new SimpleDateFormat("d-M-yyyy").format(Check_out).split("-");

        String INFO = "******Booking Refrence number : " + this.RefrenceNum + "\r\n"
                + "******Guest information : +  " + visitor.toString() + "\r\n"
                + "******Check in Day : " + in_Dates[0]
                + " Month : " + in_Dates[1]
                + " Year : " + in_Dates[2]
                + " Check out Day : " + out_Dates[0]
                + " Month : " + out_Dates[1]
                + " Year : " + out_Dates[2] + "\r\n"
                + "******Room information : " + room.toString() + "\r\n";

        return INFO;
    }

}

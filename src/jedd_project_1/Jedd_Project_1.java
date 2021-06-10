package jedd_project_1;

import java.io.*;
import java.text.*;
import java.util.*;

public class Jedd_Project_1 {

    public static void main(String[] args) throws FileNotFoundException, ParseException {

        Run();
    }

    public static void Run() throws FileNotFoundException, ParseException {
        ArrayList<Room> Rooms = new ArrayList<>();
        ArrayList<Service> Services = new ArrayList<>();
        ArrayList<Booking> booking = new ArrayList<>();
        ArrayList<File> Invoice = new ArrayList<>();
        PrintWriter out = new PrintWriter("BookingStatus.txt");
        
        File FR = checkFile("inputRoom");

        Scanner in = new Scanner(FR);

        InputRooms(in, Rooms, Services);

        FR = checkFile("inputprocedure");

        in = new Scanner(FR);

        Procedure(Rooms, Services, booking, Invoice, in, out);

    }

//----------------------input Room to Data base System-----------------------------------------------------------    
    public static void InputRooms(Scanner in, ArrayList<Room> Rooms, ArrayList<Service> Services) {
        String Command;

        while (in.hasNext()) {
            Command = in.next();

            switch (Command) {
                case "AddRoom":
                    Rooms.add(new StandardRoom(in.nextInt(), in.nextInt(), in.nextInt(), in.next(), in.next()));
                    break;

                case "AddBungalo":
                    Rooms.add(new Bungalow(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.next()));
                    break;

                case "AddService":
                    Services.add(new Service(in.next(), in.nextInt()));
                    break;

                case "Quit":
                    return;
            }

        }
    }
//---------------------------------------------------------------------------------------------------------------

//---------------------take Commands From File-------------------------------------------------------------------   
    public static void Procedure(ArrayList<Room> Rooms, ArrayList<Service> Services, ArrayList<Booking> booking, ArrayList<File> Invoice, Scanner in, PrintWriter out) throws ParseException, FileNotFoundException {
        String Line[];
        out.println("--------------- Welcome to CS Hotel Management System ---------------\n"
                + "--------------- Display All System Procedures ---------------\r\n\r\n");
        while (in.hasNext()) {
            Line = in.nextLine().split(" ");

            switch (Line[0]) {
                case "Reserve":
                    Reserve(Rooms, booking, Line, out);
                    break;

                case "Service":
                    Service(booking, Services, Line, out);
                    break;

                case "Print_Invoce":
                    Print_Invoce(booking, Invoice, out);
                    break;

                default:
                    out.flush();
                    out.close();
                    break;
            }

        }

    }
//---------------------------------------------------------------------------------------------------------------

//---------------------Reserve Room For Visitor------------------------------------------------------------------    
    public static void Reserve(ArrayList<Room> Rooms, ArrayList<Booking> booking, String Line[], PrintWriter out) throws ParseException {
        Visitor visitor;
        SimpleDateFormat std = new SimpleDateFormat("d/M/yyyy");
        Date check_in = std.parse(Line[6] + "/" + Line[5] + "/" + Line[4]);
        Date check_out = std.parse(Line[9] + "/" + Line[8] + "/" + Line[7]);

        visitor = (Line[10].equals("V")) ? new VIP(Integer.parseInt(Line[11]), Line[12], Line[13], Line[14], Line[15].substring(1), Integer.parseInt(Line[16]))
                : new Visitor(Integer.parseInt(Line[11]), Line[12], Line[13], Line[14], Line[15].substring(1));

        Room room = SearchRoom(Rooms, Line, check_in);

        if (room != null) {

            Booking B = new Booking(check_in, check_out, room, visitor);
            booking.add(B);
            room.AddBooking(B);
            out.println("Command BOOKING: Reserve a " + Line[1]);
            out.println(B.toString() + "\r\n");
            return;
        }

        String in_Date[] = std.format(check_in).split("/");
        String out_Date[] = std.format(check_out).split("/");

        out.println("Command BOOKING: CONFLICT");
        out.println(" Sorry ! There is no available " + Line[1] + "   \r\n"
                + " From Day : " + in_Date[0]
                + " Month : " + in_Date[1]
                + " Year : " + in_Date[2]
                + " To Day : " + out_Date[0]
                + " Month : " + out_Date[1]
                + " Year : " + out_Date[2] + "\r\n\r\n");
    }
//---------------------------------------------------------------------------------------------------------------

//---------------------Add Service For Visitor-------------------------------------------------------------------    
    public static void Service(ArrayList<Booking> booking, ArrayList<Service> Services, String Line[], PrintWriter out) {

        int Vid = Integer.parseInt(Line[1]);

        out.println("Command Add service:\r\n");

        Service service = SearchService(Services, Line[2]);

        
        for (int i = 0; i < booking.size(); i++) {

            if (booking.get(i).getVisitor().getID() == Vid) {

                booking.get(i).AddService(service);
                out.println(" The Guest:" + booking.get(i).getVisitor().getName()
                        + " in Room/Bungalow: " + booking.get(i).getRoom().getRoomNum()
                        + " floor: " + booking.get(i).getRoom().getFloorNum()
                        + " Ask for Service: " + service.getType() + "\r\n\r\n");
                return;
            }

        }

    }
//---------------------------------------------------------------------------------------------------------------

//---------------------Print all Booking Detiles-----------------------------------------------------------------
    public static void Print_Invoce(ArrayList<Booking> booking, ArrayList<File> Invoice, PrintWriter out) throws FileNotFoundException {
        String INFO;
        Visitor visitor;
        Room room;
        int roomPrice;
        double total;

        for (int i = 0; i < booking.size(); i++) {
            INFO = "";
            visitor = booking.get(i).getVisitor();
            room = booking.get(i).getRoom();
            Invoice.add(new File(visitor.getName().substring(0, 2) + visitor.getID() + "_Guest_Invoce.txt"));
            out = new PrintWriter(Invoice.get(i));

            out.println("--------------- Welcome to CS Hotel Management System ---------------\r\n"
                    + "--------- Current Date :  " + new Date() + "--------\r\n"
                    + "Command: Print_Invoice\r\n"
                    + " Guest name : " + visitor.getName() + "\r\n"
                    + " Guest ID : " + visitor.getID());

            INFO += (room instanceof Bungalow) ? " Guest Bungalow # : " + ((Bungalow) room).INFO() + "\r\n"
                    : " Guest Room # : " + ((StandardRoom) room).INFO() + "\r\n";

            roomPrice = room.getPrice();

            INFO += " Check in date : " + new SimpleDateFormat("d/M/yyyy").format(booking.get(i).getCheck_in()) + "\r\n";

            int Days = booking.get(i).getCheck_out().getDate() - booking.get(i).getCheck_in().getDate();
            INFO += " Number of nights : " + Days + "\r\n";
//------------------------------------------------------------------------------

            out.println(INFO);
            out.println("--------------- Invoice Details ---------------");
            total = roomPrice * Days;
            out.println(" Room Price in " + Days + " nights : " + total);
            out.println(" Services:");

//-----------if this Booking has a Service--------------------------------------            
            total += ServicePrice(booking.get(i).getService(), out);

            out.println("------------------------------");
            out.println("Total : " + total);

            if (visitor instanceof VIP) {
                out.println("------------------------------");
                out.println("You are a VIP ");
                total -= total * 0.25;
                out.println("Total after 25% discount: " + total);
            }

            out.close();
        }

    }
//---------------------------------------------------------------------------------------------------------------    

    public static double ServicePrice(ArrayList<Service> Services, PrintWriter out) {
        double total = 0;
        for (int i = 0; i < Services.size(); i++) {

            total += Services.get(i).getPrice();
            out.println(Services.get(i).getType() + " : " + (double) Services.get(i).getPrice());
        }
        return total;
    }

    public static Room SearchRoom(ArrayList<Room> Rooms, String Line[], Date check_in) {

        for (int i = 0; i < Rooms.size(); i++) {

            if (Line[1].equals("StandardRoom") && Rooms.get(i) instanceof StandardRoom) {

                StandardRoom room = (StandardRoom) Rooms.get(i);
                //Check Bed Type and View if it match User needes
                if (room.VisitorNeedes(Line[2], Line[3], check_in)) {
                    return room;
                }

            } else if (Line[1].equalsIgnoreCase("Bungalow") && Rooms.get(i) instanceof Bungalow) {

                Bungalow room = (Bungalow) Rooms.get(i);
                //Check Number of Rooms and OutDoor Type if it match User needes
                if (room.VisitorNeedes(Integer.parseInt(Line[2]), Line[3], check_in)) {
                    return room;
                }

            }
        }
        return null;
    }

    public static Service SearchService(ArrayList<Service> Services, String ServiceType) {
        for (int i = 0; i < Services.size(); i++) {

            if (Services.get(i).getType().equalsIgnoreCase(ServiceType)) {//Search for A Requaird Service
                return Services.get(i);
            }
        }

        return null;
    }

    public static File checkFile(String FileName) {
        File FR = new File(FileName + ".txt");
        if (!FR.exists()) {
            System.out.println("File \"" + FileName + "\" Not Exists");
            System.exit(0);
        }

        return FR;
    }

}

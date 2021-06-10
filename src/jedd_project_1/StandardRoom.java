package jedd_project_1;

import java.util.Date;

public class StandardRoom extends Room {

    private String View;
    private String BedType;

    public StandardRoom(int FloorNum, int RoomNum, int Price, String View, String BedType) {
        super(FloorNum, RoomNum, Price);
        this.View = View;
        this.BedType = BedType;

    }

    public String getView() {
        return View;
    }

    public void setView(String View) {
        this.View = View;
    }

    public String getBedType() {
        return BedType;
    }

    public void setBedType(String BedType) {
        this.BedType = BedType;
    }

    public boolean VisitorNeedes(String BedType, String View, Date Check_in) {
        return this.BedType.equalsIgnoreCase(BedType) && this.View.equalsIgnoreCase(View) && isAvailable(Check_in);
    }

    @Override
    public String toString() {

        return super.toString() + "has a " + this.View + " View";
    }

    public String INFO() {

        return this.getRoomNum() + " in Floor : " + this.getFloorNum() + " view : " + this.View;
    }
}

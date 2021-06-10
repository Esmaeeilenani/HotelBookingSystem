package jedd_project_1;

public class VIP extends Visitor {

    private int VipId;

    public VIP(int ID, String Name, String Nationality, String Email, String PhoneNum, int VipId) {
        super(ID, Name, Nationality, Email, PhoneNum);

        this.VipId = VipId;
    }

    public int getVipId() {
        return VipId;
    }

    public void setVipId(int VipId) {
        this.VipId = VipId;
    }

    public String toString() {

        return "This Visitor is a VIP " + super.toString() + " VIPID: " + VipId;
    }
}

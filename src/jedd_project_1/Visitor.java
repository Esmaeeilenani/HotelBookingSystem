package jedd_project_1;

public class Visitor {


    private int ID;
    private String Name;
    private String Nationality;
    private String Email;
    private String PhoneNum;

    public Visitor(int ID, String Name, String Nationality, String Email, String PhoneNum) {
        
        this.ID = ID;
        this.Name = Name;
        this.Nationality = Nationality;
        this.Email = Email;
        this.PhoneNum = PhoneNum;
    }

   

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String Nationality) {
        this.Nationality = Nationality;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String PhoneNum) {
        this.PhoneNum = PhoneNum;
    }
public String toString(){




return "ID: " + ID + ", Name: " + Name + ", Nationality: " +Nationality+ ", Email: " + Email+ ", Phone: " + PhoneNum;
}
}

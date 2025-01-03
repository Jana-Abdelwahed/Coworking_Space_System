import java.util.Date;
import java.util.Objects;
public class Slot {
    public String time;
    public String date;
    public double fees;
    public String availability;
    public int Id_Reservation;
    public String roomName;
    public   int slotCount ;

    public Slot(int slotCount,String date, String time, double fees, String availability,String roomName, int Id_Reservation) {
        this.date = date;
        this.time = time;
        this.fees = fees;
        this.availability = availability;
        this.roomName = roomName;
        this.slotCount = slotCount;
        this.Id_Reservation = Id_Reservation;
    }
    public Slot(String date,String time){
        this.date= date;
        this.time = time;
    }
    public Slot(){

    }

    public String getTime() {
        return time;
    }

    public void setTime(String newTime) {
        this.time = newTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String newDate) {
        this.date = newDate;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double newFees) {
        this.fees = newFees;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

}
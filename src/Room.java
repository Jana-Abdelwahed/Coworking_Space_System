import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Room {
    private  int ID ;
    private String name;
    public    ArrayList<Slot> slots = new ArrayList<>();
    public    ArrayList<Visitor> visitors = new ArrayList<>();

    public Room(String name, int ID) {
        this.name = name;
        this.ID = ID;

    }
    public Room(){

    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    public void displayAvailableSlots() {
        System.out.println("Room name"+"\t"+"\tDate"+"\t"+"\ttime"+"\tfees"+"\t"+"Slot number");
        for (Slot slot : this.slots) {
            if (slot.availability.equalsIgnoreCase("available")) {
                System.out.println(slot.getRoomName() + "\t" + slot.getDate() + "\t" + slot.getTime() + "\t" + slot.getFees() + "\t" + slot.slotCount);
            }
        }
    }

}

import java.io.*;

public class File_writer {

    public void VisitorData() throws IOException {
        String file="VisitorData";
        BufferedWriter writerr=new BufferedWriter(new FileWriter(file));
        String line="";
        for (Visitor v1: WorkSpace.visitorList) {
            String []row=new String[5];
            row[0] = v1.getType();
            row[1]= v1.getName();
            row[2] = v1.getPassword();
            row[3]=Integer.toString(v1.ID);
            row[4] =Integer.toString(v1.getHoursBooked());
            line=String.join(",",row);
            writerr.write(line);
            writerr.newLine();
        }
        writerr.close();
    }
    public void SlotsWrite() throws IOException {
        String file="SlotsofRooms";
        BufferedWriter writerr=new BufferedWriter(new FileWriter(file));
        String line="";
        for (Slot slot: WorkSpace.AllSlots) {
            String []row=new String[7];
            row[0] = Integer.toString(slot.slotCount);
            row[1]= slot.date;
            row[2] = slot.time;
            row[3]=Double.toString(slot.fees);
            row[4]= slot.availability;
            row[5]= slot.roomName;
            row[6]= Integer.toString(slot.Id_Reservation);
            line=String.join(",",row);
            writerr.write(line);
            writerr.newLine();
        }
        writerr.close();
    }
    public void GeneralRoomData() throws IOException {
        String file="GeneralRooms";
        BufferedWriter writerr=new BufferedWriter(new FileWriter(file));
        String line="";
        for (Room r1: WorkSpace.generalRooms) {
            String []row=new String[2];
            row[0] = r1.getName();
            row[1]= Integer.toString(r1.getID()) ;
            line=String.join(",",row);
            writerr.write(line);
            writerr.newLine();
        }
        writerr.close();
    }


}

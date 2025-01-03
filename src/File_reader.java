import java.io.*;

public class File_reader {

    public static void VisitorDataToRead() throws IOException {

        String file="VisitorData";
        BufferedReader reader = null;
        String line2 = "";

        reader = new BufferedReader(new FileReader(file));
        while((line2 = reader.readLine()) != null) {
            String[] row = line2.split(",");
            Visitor v = new Visitor(row[0],row[1],row[2],Integer.parseInt(row[3]), Integer.parseInt(row[4]));
            WorkSpace.visitorList.add(v);
        }
        reader.close();
    }
    public void GeneralRoomsData() throws IOException {

        String file="GeneralRooms";
        BufferedReader reader = null;
        String line2 = "";


        reader = new BufferedReader(new FileReader(file));
//        while((line2 = reader.readLine()) != null) {
        line2 = reader.readLine();
        String[] row = line2.split(",");
        GeneralRoom r = new GeneralRoom(row[0],Integer.parseInt(row[1]));
        WorkSpace.generalRooms.add(r);
        line2 = reader.readLine();
        String[] row2 = line2.split(",");
        GeneralRoom r2 = new GeneralRoom(row2[0],Integer.parseInt(row2[1]));
        WorkSpace.generalRooms.add(r2);


//        }
        reader.close();
    }

    public void MeetingRoomData() throws IOException {

        String file="MeetingRooms";
        BufferedReader reader = null;
        String line2 = "";


        reader = new BufferedReader(new FileReader(file));
//        while((line2 = reader.readLine()) != null) {
        line2 = reader.readLine();
        String[] row = line2.split(",");
        MeetingRoom r = new MeetingRoom(row[0],Integer.parseInt(row[1]));
        WorkSpace.meetingRooms.add(r);
        line2 = reader.readLine();
        String[] row1 = line2.split(",");
        MeetingRoom r1 = new MeetingRoom(row1[0],Integer.parseInt(row1[1]));
        WorkSpace.meetingRooms.add(r1);
        line2 = reader.readLine();
        String[] row3 = line2.split(",");
        MeetingRoom r3 = new MeetingRoom(row3[0],Integer.parseInt(row3[1]));
        WorkSpace.meetingRooms.add(r3);
//        }
        reader.close();
    }

    public void TeachingRoomData() throws IOException {

        String file="TeachingRooms";
        BufferedReader reader = null;
        String line2 = "";


        reader = new BufferedReader(new FileReader(file));
//        while((line2 = reader.readLine()) != null) {
        line2 = reader.readLine();
        String[] row = line2.split(",");
        TeachingRoom r = new TeachingRoom(row[0],Integer.parseInt(row[1]),row[2],row[3]);
        WorkSpace.teachingRooms.add(r);
        line2 = reader.readLine();
        String[] row1 = line2.split(",");
        TeachingRoom r1 = new TeachingRoom(row1[0],Integer.parseInt(row1[1]),row1[2],row1[3]);
        WorkSpace.teachingRooms.add(r1);line2 = reader.readLine();
        String[] row2 = line2.split(",");
        TeachingRoom r2 = new TeachingRoom(row2[0],Integer.parseInt(row2[1]),row2[2],row2[3]);
        WorkSpace.teachingRooms.add(r2);
//        }
        reader.close();
    }




    public  void RoomSlots() {
        String file = "SlotsofRooms";
        BufferedReader reader = null;
        String line2 = "";

        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line2 = reader.readLine()) != null) {
                String[] row = line2.split(",");
                if(row.length >= 7) {
                    Slot s = new Slot(Integer.parseInt(row[0]), row[1],row[2], Double.parseDouble(row[3]), row[4], row[5],Integer.parseInt(row[6]));
                    WorkSpace.AllSlots.add(s);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

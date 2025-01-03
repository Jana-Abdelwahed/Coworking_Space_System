import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkSpace {
    static ArrayList<Visitor> visitorList = new ArrayList<>();
    static ArrayList<Slot> AllSlots = new ArrayList<>();
    //    static List<Slot>reservations = new ArrayList<>();
    public static ArrayList<TeachingRoom>teachingRooms = new ArrayList<>();
    public static ArrayList<MeetingRoom>meetingRooms=new ArrayList<>();
    public static ArrayList<GeneralRoom> generalRooms = new ArrayList<>();


    public static void VisitorsRoom() {
        for (int i = 0; i < visitorList.size(); i++) {
            if (visitorList.get(i).getType().equalsIgnoreCase("General")) {
                for (int j = 0; j < 4; j++) {
                    if (generalRooms.get(j).visitors.size() < 20) {
                        generalRooms.get(j).visitors.add(visitorList.get((i)));
                        break;
                    }
                }
            }
            else  if (visitorList.get(i).getType().equalsIgnoreCase("formal")) {
                for (int j = 0; j < 5; j++) {
                    if (meetingRooms.get(j).visitors.size() < 10) {
                        meetingRooms.get(j).visitors.add(visitorList.get((i)));
                        break;
                    }
                }
            }
            else  if (visitorList.get(i).getType().equalsIgnoreCase("Instructor")) {
                for (int j = 0; j < 5; j++) {
                    if (teachingRooms.get(j).visitors.size() < 10) {
                        teachingRooms.get(j).visitors.add(visitorList.get((i)));
                        break;
                    }
                }
            }
        }
    }

    public static void slotsofRooms(){
        for (int i = 0 ; i < AllSlots.size();i++){
            if(AllSlots.get(i).getRoomName().equals(generalRooms.get(0).getName())){
                generalRooms.get(0).slots.add(AllSlots.get(i));
            } else if (AllSlots.get(i).getRoomName().equals(generalRooms.get(1).getName())) {
                generalRooms.get(1).slots.add(AllSlots.get(i));
            }

            else if(AllSlots.get(i).getRoomName().equals(meetingRooms.get(0).getName())){
                meetingRooms.get(0).slots.add(AllSlots.get(i));
            } else if (AllSlots.get(i).getRoomName().equals(meetingRooms.get(1).getName())) {
                meetingRooms.get(1).slots.add(AllSlots.get(i));
            }
            else if (AllSlots.get(i).getRoomName().equals(meetingRooms.get(2).getName())) {
                meetingRooms.get(2).slots.add(AllSlots.get(i));
            }
            else if(AllSlots.get(i).getRoomName().equals(teachingRooms.get(0).getName())){
                teachingRooms.get(0).slots.add(AllSlots.get(i));
            } else if (AllSlots.get(i).getRoomName().equals(teachingRooms.get(1).getName())) {
                teachingRooms.get(1).slots.add(AllSlots.get(i));
            }
            else if (AllSlots.get(i).getRoomName().equals(teachingRooms.get(2).getName())) {
                teachingRooms.get(2).slots.add(AllSlots.get(i));
            }
        }
    }

}




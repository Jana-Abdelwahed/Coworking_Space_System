import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends User {
    Scanner scanner = new Scanner(System.in);
    Room selectedroom = null;

    public Admin(String name, String password) {
        super(name, password);
    }

    public Admin() {
        super();
    }

    public void login(String name, String enteredPassword) throws IOException {
        if (name.equalsIgnoreCase("Admin") && enteredPassword.equalsIgnoreCase("Admin")) {
            System.out.println("-----------------------------------------------------");
            System.out.println("            Admin logged in successfully !           ");
            System.out.println("-----------------------------------------------------");
            Main.adminMenu();
        } else {
            System.out.println("-----------------------------------------------------");
            System.out.println("       the username , or password is incorrect       ");
            System.out.println("-----------------------------------------------------");

        }
    }

    public void displayAllVisitorsData() {
            System.out.println("-----------------------------------------------------");
        for (Visitor v : WorkSpace.visitorList) {
            System.out.print("\t" + v.getType());
            System.out.print("\t" + v.getName());
            System.out.print("\t" + v.ID);
            System.out.print("\t" + v.getHoursBooked());
            System.out.println(" ");
            System.out.println("-----------------------------------------------------");
        }
    }

    public void addSlot() {

        System.out.println("Enter date in (dd-mm-yyyy) format :");
        String date = scanner.next();
        System.out.println("Enter time in (hh:mm) format :");
        String time = scanner.next();
        System.out.println("Enter the room fees:");
        double fees = scanner.nextDouble();
        String availability = "available";
        System.out.println("Enter room name:");
        String roomName;
        int Id_Reservation = -1;
        while (true) {
            System.out.println("The rooms name are\n" + "General:" + "\t" + "Generalroom1" + "\t" + "Generalroom2");
            System.out.println("Teaching : " + "\t" + "Teachingroom1" + "\t" + "Teachingroom2" + "\t" + "Teachingroom3");
            System.out.println("Meeting: " + "\t" + "Meetingroom1" + "\t" + "Meetingroom2" + "\t" + "Meetingroom3");
            roomName = scanner.next();
            if (roomName.equals("Generalroom1") || roomName.equals("Generalroom2") || roomName.equals("Teachingroom1") || roomName.equals("Teachingroom2") || roomName.equals("Teachingroom3")
                    || roomName.equals("Meetingroom1") || roomName.equals("Meetingroom2") || roomName.equals("Meetingroom3")) {
                break;
            }

        }
        int last_index = WorkSpace.AllSlots.size() - 1;
        int counter = WorkSpace.AllSlots.get(last_index).slotCount;
        Slot slot = new Slot(++counter, date, time, fees, availability, roomName, Id_Reservation);
        WorkSpace.AllSlots.add(slot);
    }

    public void displayAllAvailableSlotsForAllRooms() {
        for (Room room : WorkSpace.generalRooms) {
            System.out.println("-----------------------------------------------------");
            System.out.println("Available slots for " + room.getID() + "\t" + room.getName());
            room.displayAvailableSlots();
            System.out.println("-----------------------------------------------------");
        }
        for (Room room : WorkSpace.meetingRooms) {
            System.out.println("-----------------------------------------------------");
            System.out.println("Available slots for " + room.getID() + "\t" + room.getName());
            room.displayAvailableSlots();
            System.out.println("-----------------------------------------------------");
        }
        for (Room room : WorkSpace.teachingRooms) {
            System.out.println("-----------------------------------------------------");
            System.out.println("Available slots for " + room.getID() + "\t" + room.getName());
            room.displayAvailableSlots();
            System.out.println("-----------------------------------------------------");
        }

    }

    public void SelectRoom(int ID) {
        for (Room room : WorkSpace.generalRooms) {
            if (room.getID() == ID) {
                selectedroom = room;
                break;
            }
        }
        for (Room room : WorkSpace.teachingRooms) {
            if (room.getID() == ID) {
                TeachingRoom roomTeachingRoom = (TeachingRoom) room;
                System.out.println("-----------------------------------------------------");
                System.out.println(roomTeachingRoom.getInstructorName() + "\t" + roomTeachingRoom.getBoardType() + "\t" + roomTeachingRoom.getProjectorType());
                System.out.println("-----------------------------------------------------");

                selectedroom = room;
                break;
            }
        }
        for (Room room : WorkSpace.meetingRooms) {
            if (room.getID() == ID) {
                selectedroom = room;

                break;
            }
        }
        displayRoomData(selectedroom);
    }

    public void displayRoomData(Room selectedRoom) {
        System.out.println("-----------------------------------------------------");
        System.out.println("                   Available slots:                  ");
        selectedRoom.displayAvailableSlots();
        System.out.println("-----------------------------------------------------");
        System.out.println("                    Reserved slots:                  " );
        for (Visitor visitor : selectedRoom.visitors) {
            for (Slot slot : visitor.reservations) {
                System.out.println(slot.date + "\t" + slot.time + "\t" + slot.fees + "\t");
                System.out.println("-----------------------------------------------------");
            }
        }


        System.out.println("              total amount of money is               ");
        CalulatetotalFess(selectedRoom.getID());
        System.out.println("-----------------------------------------------------");
    }


    public void displayAllInstructorsData() {
        System.out.println("-----------------------------------------------------");
        System.out.println("    Name   ID                                        ");
        System.out.println("-----------------------------------------------------");
        for (Room room : WorkSpace.teachingRooms) {
            for (Visitor visitor : room.visitors) {
                if (visitor.getType().equalsIgnoreCase("Instructor")) {
                    System.out.println("\t" + visitor.getName() + "\t" + visitor.ID);
                    System.out.println("-----------------------------------------------------");
                }
            }
        }
    }

    public void CalulatetotalFess(int id) {
        double totalfees = 0.0;

        for (GeneralRoom room : WorkSpace.generalRooms) {
            if (room.getID() == id) {
                for (Slot slot : room.slots) {
                    if (slot.availability.equalsIgnoreCase("unavailable")) {
                        totalfees += slot.fees;
                    }
                }
            }
        }

        for (MeetingRoom room : WorkSpace.meetingRooms) {
            if (room.getID() == id) {
                for (Slot slot : room.slots) {
                    if (slot.availability.equalsIgnoreCase("unavailable")) {
                        totalfees += slot.fees;
                    }
                }
            }
        }
        for (TeachingRoom room : WorkSpace.teachingRooms) {
            if (room.getID() == id) {
                for (Slot slot : room.slots) {
                    if (slot.availability.equalsIgnoreCase("unavailable")) {
                        totalfees += slot.fees;
                    }
                }
            }
        }
        System.out.println(totalfees);
    }

    public void deleteVisitor(int id) {
        for (int i = 0; i < WorkSpace.visitorList.size(); i++) {
            if (id == WorkSpace.visitorList.get(i).ID) {
                WorkSpace.visitorList.remove(i);
                break;
            }
        }
        System.out.println("-----------------------------------------------------");
        System.out.println("      this visitor has been deleted successfully     ");
        System.out.println("-----------------------------------------------------");

    }

    public void deleteRoom(int id) {
        for (int i = 0; i < WorkSpace.generalRooms.size(); i++) {
            if (id == WorkSpace.generalRooms.get(i).getID()) {
                WorkSpace.generalRooms.remove(i);
                break;
            }
        }
        for (int i = 0; i < WorkSpace.meetingRooms.size(); i++) {
            if (id == WorkSpace.meetingRooms.get(i).getID()) {
                WorkSpace.meetingRooms.remove(i);
                break;
            }
        }
        for (int i = 0; i < WorkSpace.teachingRooms.size(); i++) {
            if (id == WorkSpace.teachingRooms.get(i).getID()) {
                WorkSpace.teachingRooms.remove(i);
                break;
            }
        }
        System.out.println("-----------------------------------------------------");
        System.out.println("       this room has been deleted successfully       ");
        System.out.println("-----------------------------------------------------");
    }

    public void deleteSlot(int number) {
        for (int i = 0; i < WorkSpace.AllSlots.size(); i++) {
            if (number == WorkSpace.AllSlots.get(i).slotCount) {
                WorkSpace.AllSlots.remove(i);
                break;
            }
        }
        System.out.println("-----------------------------------------------------");
        System.out.println("       this Slot has been deleted successfully       ");
        System.out.println("-----------------------------------------------------");
    }

    public void updateSlot(int number) {
        for (Slot slot : WorkSpace.AllSlots) {
            if (slot.slotCount == number) {
                int choice;
                do {

                    System.out.println("1.Update Date : ");
                    System.out.println("2.Update Time : ");
                    System.out.println("3.Update RoomName : ");
                    System.out.println("4.Update fees:");
                    System.out.println("0.Save updates : ");
                    System.out.println("Enter your choice:");
                    choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("-----------------------------------------------------");
                            System.out.println("       Enter the date in this format (dd-MM-YYYY     ");
                            System.out.println("-----------------------------------------------------");
                            WorkSpace.AllSlots.get(number - 1).date = scanner.next();
                            break;
                        case 2:
                            System.out.println("Enter the date in this format (HH:MM");
                            WorkSpace.AllSlots.get(number - 1).time = scanner.next();
                            break;
                        case 3:
                            String roomName;
                            while (true) {
                                System.out.println("Enter the room name :");
                                System.out.println("The rooms name are\n" + "General:" + "\t" + "Generalroom1" + "\t" + "Generalroom2");
                                System.out.println("Teaching : " + "\t" + "Teachingroom1" + "\t" + "Teachingroom2" + "\t" + "Teachingroom3");
                                System.out.println("Meeting: " + "\t" + "Meetingroom1" + "\t" + "Meetingroom2" + "\t" + "Meetingroom3");
                                roomName = scanner.next();
                                if (roomName.equals("Generalroom1") || roomName.equals("Generalroom2") || roomName.equals("Teachingroom1") || roomName.equals("Teachingroom2") || roomName.equals("Teachingroom3")
                                        || roomName.equals("Meetingroom1") || roomName.equals("Meetingroom2") || roomName.equals("Meetingroom3")) {
                                    break;
                                }
                            }
                            WorkSpace.AllSlots.get(number - 1).roomName = roomName;

                            break;
                        case 4:
                            System.out.println("Enter fees:");
                            WorkSpace.AllSlots.get(number - 1).fees = scanner.nextDouble();
                            break;
                        case 0:
                            System.out.println("-----------------------------------------------------");
                            System.out.println("      your updates has been saved successfully       ");
                            System.out.println("-----------------------------------------------------");
                            break;
                        default:
                            System.out.println("Invalid choice .");
                            break;
                    }
                } while (choice != 0);
            }
        }
    }

    public void updateVisitor(int id) {
        for (Visitor visitor : WorkSpace.visitorList) {
            if (visitor.ID == id) {
                System.out.println("Enter visitor name :");
                visitor.setName(scanner.next());

            }
        }
    }

    public void updateRoom(int id) {
        SelectRoom(id);
        int choice;
        do {
            System.out.println(" update room details");
            System.out.println("1.update room name");
            System.out.println("2.update room id");
            System.out.println("3.update room reservations");
            System.out.println("0.Save updates : ");
            System.out.println("Enter you choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    String name = scanner.next();
                    selectedroom.setName(name);
                    break;
                case 2:
                    selectedroom.setID(scanner.nextInt());
                    break;
                case 3:
                    System.out.println("Reserved slots");
                    for (Visitor visitor : selectedroom.visitors) {
                        for (Slot slot : visitor.reservations) {
                            System.out.println(slot.date + "\t" + slot.time + "\t" + slot.fees + "\t");
                        }

                    }
                    break;
                case 0:
                    System.out.println("-----------------------------------------------------");
                    System.out.println("        Updates has been saved successfully !        ");
                    System.out.println("-----------------------------------------------------");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while (choice != 0);
    }

}




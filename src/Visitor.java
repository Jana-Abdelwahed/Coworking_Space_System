import java.io.IOException;
import java.util.*;
import java.time.LocalDate;
import java.time.temporal.WeekFields;


class Visitor extends User {
    public int ID;
    private String type;
    private int hoursBooked;
    private boolean penaltyDue = false;
    public List<Slot> reservations = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private static int lastWeek = -1;



    public Visitor(String type, String name, String password, int ID, int hoursBooked) {
        super(name, password);
        this.ID = ID;
        this.type = type;
        this.hoursBooked = hoursBooked;
    }

    public Visitor(String name, String pass) {
        super(name, pass);
        this.reservations = new ArrayList<>();
    }

    public Visitor() {
        super();

    }

    public int getID() {
        return ID;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHoursBooked() {
        return hoursBooked;
    }

    public void setHoursBooked(int hoursBooked) {
        this.hoursBooked = hoursBooked;
    }

    public boolean isPenaltyDue() {
        return penaltyDue;
    }

    public void setPenaltyDue(boolean penaltyDue) {
        this.penaltyDue = penaltyDue;
    }

    public List<Slot> getReservations() {
        return reservations;
    }

    public void incrementHoursBooked() {
        this.hoursBooked += 1;
    }

    public int login(String name, String enteredPassword) {
        for (int i = 0; i < WorkSpace.visitorList.size(); i++) {
            Visitor visitor = WorkSpace.visitorList.get(i);
            if (visitor.getName().equalsIgnoreCase(name) && visitor.getPassword().equals(enteredPassword)) {
                System.out.println("-----------------------------------------------------");
                System.out.println("                Logged in successfully               ");
                System.out.println("-----------------------------------------------------");

                return i;
            }
        }
        System.out.println("-----------------------------------------------------");
        System.out.println("    No matching data. Register to use the system.    ");
        System.out.println("-----------------------------------------------------");

        return -1;
    }

    public static void register(List<Visitor> visitors) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your Username: ");
        String username = scanner.next();
        System.out.println("Enter your Password: ");
        String password = scanner.next();
        System.out.println("Enter your Type (General, Formal, Instructor): ");
        String type = scanner.next();

        while (!(type.equalsIgnoreCase("General") || type.equalsIgnoreCase("Formal") || type.equalsIgnoreCase("Instructor"))) {
            System.out.println("Invalid type. Enter one of (General, Formal, Instructor): ");
            type = scanner.next();
        }

        System.out.println("Enter your ID: ");
        int ID = scanner.nextInt();
        for (Visitor v : visitors) {
            if (v.getID() == ID) {
                System.out.println("-----------------------------------------------------");
                System.out.println("          This ID is already taken. Try again.       ");
                System.out.println("-----------------------------------------------------");
                ID = scanner.nextInt();
            }
        }

        Visitor visitor = new Visitor(type, username, password, ID, 0);
        visitors.add(visitor);
        System.out.println("-----------------------------------------------------");
        System.out.println("               Registered successfully!              ");
        System.out.println("-----------------------------------------------------");
        Main.loginMenu();
    }

    public void makeReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------------------------------------");

        if (penaltyDue) {
            System.out.println("A penalty fee of 10 will be applied to this reservation.");
            System.out.println("-----------------------------------------------------");
        }

        if (this.getType().equalsIgnoreCase("General")) {
            WorkSpace.generalRooms.forEach(GeneralRoom::displayAvailableSlots);
        } else if (this.getType().equalsIgnoreCase("Formal")) {
            WorkSpace.meetingRooms.forEach(MeetingRoom::displayAvailableSlots);
        } else if (this.getType().equalsIgnoreCase("Instructor")) {
            WorkSpace.teachingRooms.forEach(TeachingRoom::displayAvailableSlots);
        } else {
            System.out.println("-----------------------------------------------------");
            System.out.println("    Invalid type. Cannot proceed with reservation.   ");
            System.out.println("-----------------------------------------------------");
            return;
        }
        System.out.println("-----------------------------------------------------");
        System.out.println("Enter slot number:");
        int slotNumber = scanner.nextInt();
        List<? extends Room> roomList = switch (this.getType().toLowerCase())
        {
            case "general" -> WorkSpace.generalRooms;
            case "formal" -> WorkSpace.meetingRooms;
            case "instructor" -> WorkSpace.teachingRooms;
            default -> null;
        };

        if (roomList != null) {
            for (Room room : roomList) {
                for (Slot slot : room.slots) {
                    if (slotNumber == slot.slotCount && slot.availability.equalsIgnoreCase("available")) {
                        reservations.add(slot);
                        slot.availability = "unavailable";
                        if (penaltyDue) {
                            slot.fees += 10;
                            penaltyDue = false;
                        }
                        slot.Id_Reservation = this.getID();

                        System.out.println("             Reservation successfully made!          ");
                        System.out.println("-----------------------------------------------------");
                        System.out.println("-----------------------------------------------------");
                        applyReward();
                        this.incrementHoursBooked();
                        return;

                    }
                }
            }


        }
        System.out.println("-----------------------------------------------------");
        System.out.println("   Failed to make a reservation. Please try again.   ");
        System.out.println("-----------------------------------------------------");

    }

    public void cancelReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------------------------------------");
        System.out.println("A penalty of 10 will be applied to the next reservation.");
        System.out.println("-----------------------------------------------------");
        System.out.println("Enter the index of the reservation to cancel:");
        int choice = scanner.nextInt();

        System.out.println(reservations.size());

        if (choice >= 0 && choice < reservations.size()) {
            reservations.get(choice).availability = "available";
            reservations.get(choice).Id_Reservation = -1;

            reservations.remove(choice);
            penaltyDue = true;
            System.out.println("-----------------------------------------------------");
            System.out.println("Reservation canceled. Penalty will apply to the next reservation.");
            System.out.println("-----------------------------------------------------");
        } else {
            System.out.println("-----------------------------------------------------");
            System.out.println("        Invalid choice. No reservation canceled.     ");
            System.out.println("-----------------------------------------------------");
        }
    }

    public void displayVisitorReservation() {
        String visitorName = this.getName();
        int visitorID = this.getID();
        System.out.println("-----------------------------------------------------");
        System.out.println("Reservations made by Visitor : " + visitorName);
        System.out.println("-----------------------------------------------------");
        boolean hasReservation = false;

        for (int i = 0; i < WorkSpace.AllSlots.size(); i++) {
            Slot slot = WorkSpace.AllSlots.get(i); // Access the slot at index i
            if (slot.Id_Reservation == visitorID) {
                hasReservation = true;
                System.out.println(i + " -> " +slot.date + "\t" + slot.time + "\t" + slot.fees + "\t" + slot.roomName);
                System.out.println("-----------------------------------------------------");
            }
        }

        if (!hasReservation) {
            System.out.println(" No reservations found for Visitor :  " + visitorName);
            System.out.println("-----------------------------------------------------");
        }
    }



    public void displayReservationsData() {
        System.out.println("Your Reservation is :");
        System.out.println("-----------------------------------------------------");
        for (int i = 0; i < reservations.size(); i++) {
            System.out.println(i + " -> " + reservations.get(i).date + "\t" + reservations.get(i).time + "\t" + reservations.get(i).fees + "\t" + reservations.get(i).roomName+ "\t" );
            System.out.println("-----------------------------------------------------");
        }
    }

    public void updateReservation ()  {

        int choice;
        do {
            System.out.println("-----------------------------------------------------");
            System.out.println("                Update reservations:                 ");
            System.out.println("                 1.Update Date :                     ");
            System.out.println("                 2.Update Time :                     ");
            System.out.println("                 3.Update RoomName :                 ");
            System.out.println("                 0.Save updates :                    ");
            System.out.println("-----------------------------------------------------");
            System.out.println("Enter your choice:");
            choice = scanner.nextInt();
            System.out.println("-----------------------------------------------------");
            int z;
            switch (choice) {
                case 1:
                    displayReservationsData();
                    System.out.println("Enter the index of the reservation you want to update:");
                    z = scanner.nextInt();
                    if (z >= 0 && z < reservations.size()) {
                        System.out.println("Enter the new date in this format (dd-MM-YYYY):");
                        reservations.get(z).date = scanner.next();
                    } else {
                        System.out.println("-----------------------------------------------------");
                        System.out.println("           Invalid index. Please try again.          ");
                        System.out.println("-----------------------------------------------------");
                    }
                    break;

                case 2:
                    displayReservationsData();
                    System.out.println("Enter the index of the reservation you want to update:");
                    z = scanner.nextInt();
                    if (z >= 0 && z < reservations.size()) {
                        System.out.println("Enter the new time in this format (HH:MM):");
                        reservations.get(z).time = scanner.next();
                    } else {
                        System.out.println("-----------------------------------------------------");
                        System.out.println("           Invalid index. Please try again.          ");
                        System.out.println("-----------------------------------------------------");
                    }
                    break;

                case 3:
                    displayReservationsData();
                    System.out.println("Enter the index of the reservation you want to update:");
                    z = scanner.nextInt();
                    if (z >= 0 && z < reservations.size()) {
                        System.out.println("Enter the new room name:");
                        reservations.get(z).roomName = scanner.next();
                    } else {
                        System.out.println("-----------------------------------------------------");
                        System.out.println("           Invalid index. Please try again.          ");
                        System.out.println("-----------------------------------------------------");
                    }
                    break;

                case 0:
                    System.out.println("-----------------------------------------------------");
                    System.out.println("       Your updates have been saved successfully.    ");
                    System.out.println("-----------------------------------------------------");
                    break;

                default:
                    System.out.println("-----------------------------------------------------");
                    System.out.println("                    Invalid choice.                  ");
                    System.out.println("-----------------------------------------------------");
                    break;
            }
        } while (choice != 0);

        System.out.println("-----------------------------------------------------");
    }

    public static int getCurrentWeek() {

        return LocalDate.now().get(WeekFields.of(Locale.getDefault()).weekOfYear());
    }

    public static boolean isNewWeek() {
        int currentWeek = getCurrentWeek();
        if (currentWeek != lastWeek) {
            lastWeek = currentWeek;
            resetLeaderboard();
        }
        return false;
    }
    public static void leaderBoard() {

        List<Visitor> sortedVisitors = new ArrayList<>(WorkSpace.visitorList);
        sortedVisitors.sort((v1, v2) -> Integer.compare(v2.getHoursBooked(), v1.getHoursBooked()));
        System.out.println("-----------------------------------------------------");
        System.out.println("-------------------- Leaderboard --------------------");
        rewardTopVisitorWithFreeHours(WorkSpace.visitorList);
        for (int i = 0; i < sortedVisitors.size(); i++) {
            Visitor visitor = sortedVisitors.get(i);
            System.out.println("-----------------------------------------------------");
            System.out.println((i + 1) + ". Name: " + visitor.getName() + ", Hours Booked: " + visitor.getHoursBooked());
            System.out.println("-----------------------------------------------------");
        }

    }
    public static void resetLeaderboard() {
        if (isNewWeek()) {
            System.out.println("New week detected. Resetting leaderboard...");
            for (Visitor visitor : WorkSpace.visitorList) {
                visitor.setHoursBooked(0);
            }
            System.out.println("Leaderboard has been reset for the new week.");
        }
    }

    @Override
    public final String toString() {
        return "Visitor{" +
                "ID=" + ID +
                ", type='" + type + '\'' +
                ", hoursBooked=" + hoursBooked +
                ", penaltyDue=" + penaltyDue +
                '}';
    }


    public static void rewardTopVisitorWithFreeHours(List<Visitor> visitorList) {
        Visitor v = new Visitor();

        Visitor topVisitor = null;
        for (Visitor visitor : visitorList) {
            if (topVisitor == null || visitor.getHoursBooked() > topVisitor.getHoursBooked()) {
                topVisitor = visitor;
            }
        }

        if (topVisitor != null && topVisitor.getHoursBooked() > 0) {

            topVisitor.setHoursBooked(topVisitor.getHoursBooked() + 7);
            System.out.println("Visitor " + topVisitor.getName() + " with type " + topVisitor.getType() + " has been rewarded with 7 free hours!");
            System.out.println("Compatible free hours applied for visitor type: " + topVisitor.getType());


        }
        else {
            System.out.println("-----------------------------------------------------");
            System.out.println("        No top visitor available to get reward       ");
            System.out.println("-----------------------------------------------------");
        }



    }
    public void applyReward() {
        if (this.getType().equalsIgnoreCase("General") || this.getType().equalsIgnoreCase("Formal")) {
            if (this.getHoursBooked() >= 5) {
                System.out.println(this.getType() + " visitor " + this.getName() + " has completed 6 hours and received 1 free hour!");
                this.incrementHoursBooked();

            }
        }

        if (this.getType().equalsIgnoreCase("Instructor")) {
            if (this.getHoursBooked() >= 11) {
                System.out.println("Instructor " + this.getName() + " has completed 12 hours and received 1 free hour!");
                this.incrementHoursBooked();

            }
        }
    }

}



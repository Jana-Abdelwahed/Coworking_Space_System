import java.io.IOException;
import java.util.Scanner;
import java.lang.String;

public class Main {

    public static int x;

    public static void main(String[] args) throws IOException {
        File_reader fileReader = new File_reader();
        File_writer fileWriter = new File_writer();
        fileReader.VisitorDataToRead();
        fileReader.MeetingRoomData();
        fileReader.GeneralRoomsData();
        fileReader.TeachingRoomData();
        WorkSpace.VisitorsRoom();
        fileReader.RoomSlots();
        WorkSpace.slotsofRooms();
        loginMenu();
        fileWriter.VisitorData();
        fileWriter.SlotsWrite();
        fileWriter.GeneralRoomData();

    }

    public static void loginMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("");
            System.out.println("-----------------------------------------------------");
            System.out.println("           Welcome to Our Coworking Space!           ");
            System.out.println("         Kindly, Enter your choice to begin          ");
            System.out.println("-----------------------------------------------------");
            System.out.println("                        Menu:                        ");
            System.out.println("                  1. Login as Admin                  ");
            System.out.println("                  2. Login as Visitor                ");
            System.out.println("                  3. Register as Visitor             ");
            System.out.println("                  0. Exit                            ");
            System.out.println("-----------------------------------------------------");
            System.out.print("Enter your choice: ");

            try {

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Admin admin = new Admin();
                    System.out.print("Enter your username: ");
                    String name = scanner.next();
                    System.out.print("Enter your password: ");
                    String password = scanner.next();
                    admin = new Admin(name, password);
                    admin.login(name, password);
                    break;

                case 2:
                    System.out.print("Enter your username: ");
                    String visitorName = scanner.next();
                    System.out.print("Enter your password: ");
                    String visitorPassword = scanner.next();
                    Visitor v = new Visitor(visitorName, visitorPassword);
                    x = v.login(visitorName, visitorPassword);
                    if (x == -1) {
                        Visitor.register(WorkSpace.visitorList);
                    } else {
                        if (WorkSpace.visitorList.get(x).getType().equalsIgnoreCase("General")) {
                            GeneralVisitorMenu();
                        } else if (WorkSpace.visitorList.get(x).getType().equalsIgnoreCase("Formal")) {
                            FormalVisitorMenu();
                        } else if (WorkSpace.visitorList.get(x).getType().equalsIgnoreCase("Instructor")) {
                            InstructorVisitorMenu();
                        }
                    }
                    break;

                case 3:
                    System.out.println("-----------------------------------------------------");
                    System.out.println("               Enter your data to register           ");
                    File_writer f = new File_writer();
                    Visitor.register(WorkSpace.visitorList);
                    f.VisitorData();
                    System.out.println("-----------------------------------------------------");
                    break;

                case 0:
                    System.out.println("-----------------------------------------------------");
                    System.out.println("            Exiting the program. Goodbye!            ");
                    System.out.println("-----------------------------------------------------");
//                    System.exit(0);
                    break;
                default:
                    System.out.println("-----------------------------------------------------");
                    System.out.println("     Invalid choice. Please enter a valid option.    ");
                    System.out.println("-----------------------------------------------------");
                    break;
            }
            } catch (Exception e) {
                System.out.println("-----------------------------------------------------");
                System.out.println("     Invalid choice. Please enter a valid option.    ");
                System.out.println("-----------------------------------------------------");
//                scanner.nextLine();
            }
        } while (choice !=0);
    }

    public static void GeneralVisitorMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("-----------------------------------------------------");
            System.out.println("                 General Visitor Menu:               ");
            System.out.println("                1. Make Reservation :                ");
            System.out.println("                2. Update Reservation :              ");
            System.out.println("                3. Cancel Reservation :              ");
            System.out.println("                4. Display your reservations :       ");
            System.out.println("                0. Log out :                         ");
            System.out.println("-----------------------------------------------------");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    WorkSpace.visitorList.get(x).makeReservation();
                    WorkSpace.visitorList.get(x).displayReservationsData();
                    break;

                case 2:
                    WorkSpace.visitorList.get(x).displayVisitorReservation();
                    WorkSpace.visitorList.get(x).updateReservation();
                    break;
                case 3:
                    WorkSpace.visitorList.get(x).displayVisitorReservation();
                    WorkSpace.visitorList.get(x).cancelReservation();
                    break;
                case 4:
                    WorkSpace.visitorList.get(x).displayVisitorReservation();
                    break;
                case 0:
                    System.out.println("-----------------------------------------------------");
                    System.out.println("            Exiting the program. Goodbye!            ");
                    System.out.println("-----------------------------------------------------");
                    break;
                    default:
                    System.out.println("-----------------------------------------------------");
                    System.out.println("     Invalid choice. Please enter a valid option.    ");
                    System.out.println("-----------------------------------------------------");

                    break;
            }
        } while (choice != 0);
    }

    public static void FormalVisitorMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("-----------------------------------------------------");
            System.out.println("                Formal Visitor Menu:                 ");
            System.out.println("               1. Make Reservation :                 ");
            System.out.println("               2. Update Reservation :               ");
            System.out.println("               3. Cancel Reservation :               ");
            System.out.println("               4. Display Reservation :              ");
            System.out.println("               0. Log out :                          ");
            System.out.println("-----------------------------------------------------");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    WorkSpace.visitorList.get(x).makeReservation();
                    WorkSpace.visitorList.get(x).displayReservationsData();
                    break;
                case 2:
                    WorkSpace.visitorList.get(x).displayVisitorReservation();
                    WorkSpace.visitorList.get(x).updateReservation();
                    break;
                case 3:
                    WorkSpace.visitorList.get(x).displayVisitorReservation();
                    WorkSpace.visitorList.get(x).cancelReservation();
                    break;
                case 4:
                    WorkSpace.visitorList.get(x).displayVisitorReservation();
                    break;
                case 0:
                    System.out.println("-----------------------------------------------------");
                    System.out.println("            Exiting the program. Goodbye!            ");
                    System.out.println("-----------------------------------------------------");
                    break;
                default:
                    System.out.println("-----------------------------------------------------");
                    System.out.println("     Invalid choice. Please enter a valid option.    ");
                    System.out.println("-----------------------------------------------------");
                    break;
            }
        } while (choice != 0);
    }

    public static void InstructorVisitorMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("-----------------------------------------------------");
            System.out.println("              Instructor Visitor Menu:               ");
            System.out.println("             1. Make Reservation :                   ");
            System.out.println("             2. Update Reservation :                 ");
            System.out.println("             3. Cancel Reservation :                 ");
            System.out.println("             4. Display Reservation :                ");
            System.out.println("             0. Log out.. :                          ");
            System.out.println("-----------------------------------------------------");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    WorkSpace.visitorList.get(x).makeReservation();
                    WorkSpace.visitorList.get(x).displayReservationsData();
                    break;
                case 2:
                    WorkSpace.visitorList.get(x).displayVisitorReservation();
                    WorkSpace.visitorList.get(x).updateReservation();
                    break;
                case 3:
                    WorkSpace.visitorList.get(x).displayVisitorReservation();
                    WorkSpace.visitorList.get(x).cancelReservation();
                    break;
                case 4:
                    WorkSpace.visitorList.get(x).displayVisitorReservation();
                    break;
                case 0:
                    System.out.println("-----------------------------------------------------");
                    System.out.println("            Exiting the program. Goodbye!            ");
                    System.out.println("-----------------------------------------------------");
                    break;
                default:
                    System.out.println("-----------------------------------------------------");
                    System.out.println("     Invalid choice. Please enter a valid option.    ");
                    System.out.println("-----------------------------------------------------");
                    break;
            }
        } while (choice != 0);
    }

    public static void adminMenu() {
        Admin admin = new Admin();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("-----------------------------------------------------");
            System.out.println("                     Admin Menu:                     ");
            System.out.println("                   1. Add Slots                      ");
            System.out.println("                   2. Delete Entity                  ");
            System.out.println("                   3. Display All Available Slots    ");
            System.out.println("                   4. Display All Visitors Data      ");
            System.out.println("                   5. Display All Rooms Data         ");
            System.out.println("                   6. Display All Instructors Data   ");
            System.out.println("                   7. Calculate and Display Total Amount of fees");
            System.out.println("                   8. Update Any Data of Any Entity  ");
            System.out.println("                   9. Display Leader Board           ");
            System.out.println("                   0. Log out :                      ");
            System.out.println("-----------------------------------------------------");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    admin.addSlot();
                    break;
                case 2:
                    System.out.println("enter your choice");
                    int c;
                    do {
                        int id;
                        System.out.println("-----------------------------------------------------");
                        System.out.println("                  1.Delete visitor :                 ");
                        System.out.println("                  2.Delete room :                    ");
                        System.out.println("                  3.Delete slot :                    ");
                        System.out.println("                  0.Exit :                           ");
                        System.out.println("-----------------------------------------------------");

                        c = scanner.nextInt();
                        switch (c) {
                            case 1:
                                System.out.println("Enter visitor ID");
                                id = scanner.nextInt();
                                admin.deleteVisitor(id);
                                break;
                            case 2:
                                System.out.println("Enter room ID");
                                id = scanner.nextInt();
                                admin.deleteRoom(id);
                                break;
                            case 3:
                                System.out.println("Slot number");
                                id = scanner.nextInt();
                                admin.deleteSlot(id);
                                break;
                            case 0:
                                break;
                            default:
                                break;
                        }
                    } while (c != 0);
                    break;
                case 3:
                    admin.displayAllAvailableSlotsForAllRooms();
                    break;
                case 4:
                    admin.displayAllVisitorsData();
                    break;
                case 5:
                    System.out.println("Enter room ID : ");
                    int ID = scanner.nextInt();
                    admin.SelectRoom(ID);

                    break;
                case 6:
                    admin.displayAllInstructorsData();
                    break;
                case 7:
                    System.out.println("Enter room id:");
                    int id = scanner.nextInt();
                    admin.CalulatetotalFess(id);
                    break;
                case 8:
                    int C;
                    do {
                        int IDtoUpdate;
                        System.out.println("-----------------------------------------------------");
                        System.out.println("               1.Update visitor data :               ");
                        System.out.println("               2.Update room data :                  ");
                        System.out.println("               3.Update slot data:                   ");
                        System.out.println("               0.Save your updates :                 ");
                        System.out.println("               Enter your choice :                   ");
                        System.out.println("-----------------------------------------------------");
                        C = scanner.nextInt();
                        switch (C) {
                            case 1:
                                System.out.println("Enter visitor ID :");
                                IDtoUpdate = scanner.nextInt();
                                admin.updateVisitor(IDtoUpdate);
                                break;
                            case 2:
                                System.out.println("Enter room id");
                                IDtoUpdate = scanner.nextInt();
                                admin.updateRoom(IDtoUpdate);
                                break;
                            case 3:
                                System.out.println("Enter slot number");
                                IDtoUpdate = scanner.nextInt();
                                admin.updateSlot(IDtoUpdate);

                                break;
                            case 0:
                                System.out.println("-----------------------------------------------------");
                                System.out.println("                  Exiting Admin Menu.                ");
                                System.out.println("-----------------------------------------------------");
                                break;
                            default:
                                System.out.println("-----------------------------------------------------");
                                System.out.println("           Invalid choice. Please try again.         ");
                                System.out.println("-----------------------------------------------------");
                        }
                    } while (C != 0);
                case 9:
                    Visitor.leaderBoard();
                    break;

            }
        } while (choice != 0);
    }

}





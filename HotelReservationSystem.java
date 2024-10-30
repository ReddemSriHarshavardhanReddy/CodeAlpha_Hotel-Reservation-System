
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Room class to represent a room in the hotel
class Room {
    private int roomNumber;
    private String roomType;
    private double pricePerNight;
    private boolean isAvailable;

    public Room(int roomNumber, String roomType, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + roomType + ") - Rs/-" + pricePerNight + " per night";
    }
}

// Reservation class to handle room reservations
class Reservation {
    private Room room;
    private String guestName;
    private int nights;
    private double totalAmount;

    public Reservation(Room room, String guestName, int nights) {
        this.room = room;
        this.guestName = guestName;
        this.nights = nights;
        this.totalAmount = room.getPricePerNight() * nights;
    }

    public Room getRoom() {
        return room;
    }

    public String getGuestName() {
        return guestName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Reservation for " + guestName + " in " + room.getRoomType() + " (Room " + room.getRoomNumber() +
               ") for " + nights + " nights. Total amount: Rs/-" + totalAmount;
    }
}

// HotelReservationSystem class to manage reservations and rooms
public class HotelReservationSystem {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public HotelReservationSystem() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        initializeRooms();
    }

    private void initializeRooms() {
        rooms.add(new Room(101, "Single", 1000.00));
        rooms.add(new Room(102, "Double", 1500.00));
        rooms.add(new Room(103, "Suite", 3000.00));
        rooms.add(new Room(104, "Single", 1000.00));
        rooms.add(new Room(105, "Double", 1500.00));
    }

    public void searchAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println(room);
            }
        }
    }

    public void makeReservation(String guestName, int roomNumber, int nights) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable()) {
                room.setAvailable(false);
                Reservation reservation = new Reservation(room, guestName, nights);
                reservations.add(reservation);
                System.out.println("Reservation successful!");
                System.out.println(reservation);
                return;
            }
        }
        System.out.println("Room not available or does not exist.");
    }

    public void viewReservations() {
        System.out.println("All Reservations:");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    public static void main(String[] args) {
        HotelReservationSystem hotelSystem = new HotelReservationSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    hotelSystem.searchAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter guest name: ");
                    scanner.nextLine(); // consume newline
                    String guestName = scanner.nextLine();
                    System.out.print("Enter room number to reserve: ");
                    int roomNumber = scanner.nextInt();
                    System.out.print("Enter number of nights: ");
                    int nights = scanner.nextInt();
                    hotelSystem.makeReservation(guestName, roomNumber, nights);
                    break;
                case 3:
                    hotelSystem.viewReservations();
                    break;
                case 4:
                    System.out.println("Thank you for using the Hotel Reservation System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}

import java.util.*;

public class MovieTheaterRunner {
    static final String WARN = "\u001B[31m"; // Warning color
    static final String GOOD = "\u001B[32m"; // Success color
    static final String NORMAL = "\u001B[33m"; // Normal color
    static final String WHITE = "\u001B[37m"; // White color
    static Scanner scanner = new Scanner(System.in);
    static final int SEATNUM = 11; // Number of seats
    enum SeatStatus { BOOKED, UNBOOKED } // Enum for seat status
    static SeatStatus[] seats; // Array to store seat status


    public static void main(String[] args) {
        seats = new SeatStatus[SEATNUM];
        Boolean running = true;
        int input;
        for(int i = 0; i < SEATNUM; i++) seats[i] = SeatStatus.UNBOOKED; // Initialize all seats as unbooked
        while(running){
            input = displayMenu(); // Display menu and get user input
            switch (input) {
                case 1: bookSeat(); break; // Book a seat
                case 2: cancelSeat(); break; // Cancel a booking
                case 3: displaySeats(); break; // Show seat status
                case 4:
                    running = false; // Exit the program
                    System.out.println("Thank you for using the Movie Seat Booking System!");
                    break;
                default:
                    System.out.println("Invalid Choice! Command: " + input + " not found!"); // Invalid input
                    System.out.println("Press Enter to continue: ");
                    scanner.nextLine();
                    clearConsole();
                    break;
            }
        }
    }


    public static Integer getInput(int max, String question, String errorMsg) {
        System.out.print(question);
        String userInput = scanner.nextLine();
        if (!userInput.matches("^[1-9]|10$") || Integer.parseInt(userInput) > max) { // Validate user input
            clearConsole();
            System.out.println(errorMsg);
            System.out.println("User's input: " + userInput);
            System.out.print("Press Enter to continue: ");
            scanner.nextLine();
            clearConsole();
            return null;
        }
        return Integer.parseInt(userInput);
    }


    public static int displayMenu(){
        Integer input;
        do {
            clearConsole();
            System.out.println(NORMAL);
            System.out.println("╔═══════════════════════════════════════╗");
            System.out.println("║  >>>  Movie Seat Booking System  <<<  ║");
            System.out.println("╠═══════════════════════════════════════╣");
            System.out.println("║                                       ║");
            System.out.println("║ 1. Book a Seat                        ║");
            System.out.println("║ 2. Cancel a Booking                   ║");
            System.out.println("║ 3. Show Seats                         ║");
            System.out.println("║ 4. Exit                               ║");
            System.out.println("║                                       ║");
            System.out.println("╚═══════════════════════════════════════╝\n");
            input = getInput(4, " Enter Your Choice: ", WARN + "!!! Invalid Choice! Please use a proper command! !!!" + NORMAL);
            System.out.println(NORMAL);
        } while (input == null);
        return input;
    }


    public static void bookSeat() {
        int input;
        clearConsole();
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║                                       ║");
        System.out.println("║         >>>  Seat Booking  <<<        ║");
        System.out.println("║                                       ║");
        System.out.println("╚═══════════════════════════════════════╝\n");
        input = getInput(SEATNUM, NORMAL + "Enter the Seat #: ", WARN + "!!! Invalid Seat Number! Please choose a seat from 1 to " + SEATNUM + "!!!\n" + NORMAL);
        if(seats[input - 1] == SeatStatus.BOOKED){
            System.out.println("\n  ┌─────────────────────────────────┐");
            System.out.println("  │       "+ WARN +"Seat NOT Available!"+ NORMAL +"       │");
            System.out.println("  └─────────────────────────────────┘\n");
        } else{
            System.out.println("\n  ┌─────────────────────────────────┐");
            System.out.println("  │  " + GOOD + "Seat Available. Seat Booked!" + NORMAL + "   │");
            System.out.println("  └─────────────────────────────────┘\n");
            seats[input - 1] = SeatStatus.BOOKED; // Book the seat
        }
        System.out.print(NORMAL + "Press Enter to continue: ");
        scanner.nextLine();
    }


    public static void cancelSeat() {
        int input;
        clearConsole();
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║                                       ║");
        System.out.println("║        >>>  Cancel Booking  <<<       ║");
        System.out.println("║                                       ║");
        System.out.println("╚═══════════════════════════════════════╝\n");
        input = getInput(SEATNUM, NORMAL + "Enter the Seat #: ", WARN + "!!! Invalid Seat Number! Please choose a seat from 1 to " + SEATNUM + "!!!\n" + NORMAL);
        if (seats[input - 1] == SeatStatus.BOOKED) {
            System.out.println("\n  ┌─────────────────────────────────┐");
            System.out.println("  │     " + GOOD + "Cancelled Seat Booking!" + NORMAL + "     │");
            System.out.println("  └─────────────────────────────────┘\n");
            seats[input - 1] = SeatStatus.UNBOOKED; // Cancel the seat booking
        } else {
            System.out.println("\n  ┌─────────────────────────────────┐");
            System.out.println("  │        " + WARN + "Seat NOT Booked!" + NORMAL + "         │");
            System.out.println("  └─────────────────────────────────┘\n");
        }
        System.out.print(NORMAL + "Press Enter to continue: ");
        scanner.nextLine();
    }


    public static void displaySeats(){
        clearConsole();
        System.out.println("\n╔═══════════════════════════╗");
        System.out.println("║  Seat #           Status  ║");
        System.out.println("╠═══════════════════════════╣");
        for (int seat = 0; seat < SEATNUM; seat++) {
            if(seats[seat] == SeatStatus.BOOKED)
                System.out.printf("║ "+ GOOD +"%5d         %10s"+ NORMAL +"  ║\n", seat + 1, seats[seat]);
            else
                System.out.printf("║ " + WHITE + "%5d         %10s" + NORMAL + "  ║\n", seat + 1, seats[seat]);
        }
        System.out.println("╚═══════════════════════════╝\n");
        System.out.print(NORMAL + "Press Enter to continue: ");
        scanner.nextLine();
    }


    public static void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // Clear the console
        } catch (Exception e) {
            System.out.println("Unable to clearConsole the screen.");
        }
    }
}

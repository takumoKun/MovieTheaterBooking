package OOP_Version.code;

import java.util.Scanner;

public class TheaterRunner implements TextProperties{
    static private int seatNum = 10;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Theater theater = new Theater(seatNum);
        boolean run = true;
        int seat, choice;

        while(run){
            do{
                displayMenu();
                choice = getInput("Enter choice: ", 4);
            } while(choice == -1);

            switch(choice){
                case 1:
                    do{
                        clearConsole();
                        promptMaker("Book a Seat", true, NORMAL);
                        seat = getInput("Enter seat number: ", seatNum);
                    } while(seat == -1);

                    if(theater.bookSeat(seat) == Response.Success){
                        promptMaker("Seat " + seat + " is Successfully booked!", false, GOOD);
                        displaySeats(theater);
                    } else {
                        promptMaker("Seat " + seat + " is already booked!", false, WARN);
                    }
                    pause();
                    break;
                case 2:
                    do{
                        clearConsole();
                        promptMaker("Cancel a Booking", true, NORMAL);
                        seat = getInput("Enter seat number: ", seatNum);
                    } while(seat == -1);

                    if(theater.unBookSeat(seat) == Response.Success){
                        promptMaker("Seat " + seat + " was Successfuly Unbooked", false, GOOD);
                        displaySeats(theater);

                    } else {
                        promptMaker("Seat " + seat + " is not yet Booked!", false, WARN);
                    }
                    pause();
                    break;
                case 3:
                    displaySeats(theater);
                    pause();
                    break;
                case 4:
                    do{
                        clearConsole();
                        promptMaker("Change the number of Seats", true, NORMAL);
                        seatNum = getInput("Enter the number of seats: ", 100);
                    } while(seatNum == -1);

                    theater.changeSeatNum(seatNum);
                    promptMaker("Changed the seats to " + seatNum, false, GOOD);
                    pause();
                    break;
                case 5:
                    clearConsole();
                    promptMaker("Thank you for using our code!", true, GOOD);
                    run = false;
                    break;
            }
        }
        

        
    }

    public static void displayMenu(){
        clearConsole();
        System.out.println(NORMAL);
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║  >>>  Movie Seat Booking System  <<<  ║");
        System.out.println("╠═══════════════════════════════════════╣");
        System.out.println("║                                       ║");
        System.out.println("║ 1. Book a Seat                        ║");
        System.out.println("║ 2. Cancel a Booking                   ║");
        System.out.println("║ 3. Show Seats                         ║");
        System.out.println("║ 4. Change the number of Seats         ║");
        System.out.println("║ 5. Exit                               ║");
        System.out.println("║                                       ║");
        System.out.println("╚═══════════════════════════════════════╝\n");
    }
    
    public static void displaySeats(Theater theater){
        System.out.println("\n╔═══════════════════════════╗");
        System.out.println("║  Seat #           Status  ║");
        System.out.println("╠═══════════════════════════╣");
        for (int seat = 0; seat < seatNum; seat++) {
            if(theater.checkStatus(seat) == SeatStatus.Booked)
                System.out.printf("║ "+ GOOD +"%5d         %10s"+ NORMAL +"  ║\n", seat + 1, "Booked");
            else
                System.out.printf("║ " + WHITE + "%5d         %10s" + NORMAL + "  ║\n", seat + 1, "Unbooked");
        }
        System.out.println("╚═══════════════════════════╝\n");
    }

    public static void promptMaker(String prompt, Boolean isTitle, String color){
        //get the length of the prompt given
        System.out.print(NORMAL);
        int len = prompt.length();

        //count how many spaces that needs to be printed
        int maxSpace;
        
        //determine the type of prompt and change border types
        if (isTitle){
            //39 is the number of spaces inside the box for titles
            maxSpace = 39 - len;
            System.out.println("\n╔═══════════════════════════════════════╗");
            System.out.println("║                                       ║");
            System.out.print("║");
        } else {
            //33 is the number of spaces inside the box of ordinary prompts
            maxSpace = 33 - len;
            System.out.println("\n   ┌─────────────────────────────────┐");
            System.out.print("   │");
        }
        
        //calculate where the title needs to be printed on
        int titleStart = maxSpace / 2;
        
        //loop through the number of spaces
        for(int spaceCtr = 1; spaceCtr <= maxSpace; spaceCtr++){
            if(spaceCtr == titleStart)
                // print the title if space counter is equal to the start title
                System.out.print(color + " " + prompt);
            else
                System.out.print(" ");
        }
        
        if(isTitle){
            System.out.print(NORMAL + "║\n");
            System.out.println("║                                       ║");
            System.out.println("╚═══════════════════════════════════════╝\n");
        } else{
            System.out.print(NORMAL + "│\n");
            System.out.println("   └─────────────────────────────────┘\n");
        }
    }

    public static Integer getInput(String prompt, int limit){
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        try{
            int input = scanner.nextInt();

            if(input > 0 && input <= limit){
                
                return input;
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and " + limit);
                    
            }
        } catch (Exception e){
            System.out.println("Invalid input. Please enter a number!");
        }
        pause();
        
        return -1;
    }

    public static void pause(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Press Enter to continue: ");
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
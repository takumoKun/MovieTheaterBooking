/*
 * Main/Runner class for the Code
 */

package OOP_Version.code;

import java.util.Scanner;

public class TheaterRunner implements TextProperties{
    // set the number of seats to 10 as default
    static private int seatNum = 10;
    public static void main(String[] args) {
        // create a scanner object and a theater object and initialize the number of seats for the current theater
        Scanner scanner = new Scanner(System.in);
        Theater theater = new Theater(seatNum);
        boolean run = true;
        int seat, choice;

        while(run){
            // display the menu and get the user's choice
            do{ 
                //call the displayMenu() method to display the menu
                displayMenu();
                choice = getInput("Enter choice: ", 4);
                // check if the getInput method returns an invalid input and stay in the loop until a valid input is given
            } while(choice == -1);

            // switch case for the user's choice
            switch(choice){
                case 1:
                    do{
                        // clear the console and display the title for booking a seat using promptMaker method
                        clearConsole();
                        promptMaker("Book a Seat", true, NORMAL);
                        //ask the user for the seat number they wish to book
                        seat = getInput("Enter seat number: ", seatNum);
                        //stay on the loop if getInput method returns an invalid response
                    } while(seat == -1);

                    //check if the desired seat is already booked or not by calling the bookSeat method from the theater object
                    if(theater.bookSeat(seat) == Response.Success){
                        //if the seat is not yet booked, book the seat and display a success message using promptMaker method
                        promptMaker("Seat " + seat + " is Successfully booked!", false, GOOD);
                        //display the updated seats using the displaySeats method
                        displaySeats(theater);
                    } else {
                        //if the seat is already booked, display a warning message using promptMaker method
                        promptMaker("Seat " + seat + " is already booked!", false, WARN);
                    }
                    //call the pause method to pause the program until the user presses enter
                    pause();
                    break;
                case 2:
                    do{
                        clearConsole();
                        //display the title for canceling a booking using promptMaker method
                        promptMaker("Cancel a Booking", true, NORMAL);
                        //ask the user for the seat number they wish to unbook
                        seat = getInput("Enter seat number: ", seatNum);
                        //stay on the loop if getInput method returns an invalid response
                    } while(seat == -1);

                    //check if the desired seat is already unbooked or not by calling the unBookSeat method from the theater object
                    if(theater.unBookSeat(seat) == Response.Success){
                        //if the seat is already booked, unbook the seat and display a success message using promptMaker method
                        promptMaker("Seat " + seat + " was Successfuly Unbooked", false, GOOD);
                        //display the updated seats using the displaySeats method
                        displaySeats(theater);
                    } else {
                        //if the seat is already unbooked, display a warning message using promptMaker method
                        promptMaker("Seat " + seat + " is not yet Booked!", false, WARN);
                    }
                    //call the pause method to pause the program until the user presses enter
                    pause();
                    break;
                case 3:
                    //display all the seats using the displaySeats method
                    displaySeats(theater);
                    //call the pause method to pause the program until the user presses enter
                    pause();
                    break;
                case 4:
                    do{
                        clearConsole();
                        //display the title for changing the number of seats using promptMaker method
                        promptMaker("Change the number of Seats", true, NORMAL);
                        //ask the user for the number of seats they wish to change
                        seatNum = getInput("Enter the number of seats: ", 100);
                    } while(seatNum == -1);
                    //change the number of seats using the changeSeatNum method from the theater object
                    theater.changeSeatNum(seatNum);
                    //display a success message using promptMaker method
                    promptMaker("Changed the seats to " + seatNum, false, GOOD);
                    //call the pause method to pause the program until the user presses enter
                    pause();
                    break;
                case 5:
                    clearConsole();
                    //display a thank you message using promptMaker method
                    promptMaker("Thank you for using our code!", true, GOOD);
                    run = false;
                    break;
            }
        }
        

        
    }

    //method for displaying the menu
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
    
    //method for displaying the seats
    public static void displaySeats(Theater theater){
        //display the header for the seat number and status
        System.out.println("\n╔═══════════════════════════╗");
        System.out.println("║  Seat #           Status  ║");
        System.out.println("╠═══════════════════════════╣");

        //use a loop to display the seat number and status of each seat with their borders and colors
        for (int seat = 0; seat < seatNum; seat++) {
            if(theater.checkStatus(seat) == SeatStatus.Booked)
                System.out.printf("║ "+ GOOD +"%5d         %10s"+ NORMAL +"  ║\n", seat + 1, "Booked");
            else
                System.out.printf("║ " + WHITE + "%5d         %10s" + NORMAL + "  ║\n", seat + 1, "Unbooked");
        }
        //display the footer for the seats
        System.out.println("╚═══════════════════════════╝\n");
    }

    //method for creating a prompt
    public static void promptMaker(String prompt, Boolean isTitle, String color){
        //set font color to normal
        System.out.print(NORMAL);
        //get the length of the prompt
        int len = prompt.length();
        int maxSpace;
        
        //determine the type of prompt and change border types and length of spaces accordingly
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
        
        //determine the type of prompt and change border types accordingly and reverting the color to normal
        if(isTitle){
            System.out.print(NORMAL + "║\n");
            System.out.println("║                                       ║");
            System.out.println("╚═══════════════════════════════════════╝\n");
        } else{
            System.out.print(NORMAL + "│\n");
            System.out.println("   └─────────────────────────────────┘\n");
        }
    }

    //method for getting user input
    public static Integer getInput(String prompt, int limit){
        Scanner scanner = new Scanner(System.in);
        //display the prompt
        System.out.print(prompt);

        //use try-catch block to catch invalid inputs
        try{
            //get the input from the user
            int input = scanner.nextInt();

            //check if the input is within the limit
            if(input > 0 && input <= limit){
                return input;
            } else {
                //display an error message if the input is not within the limit
                System.out.println("Invalid input. Please enter a number between 1 and " + limit);
            }
        } catch (Exception e){
            //display an error message if the input is not a number
            System.out.println("Invalid input. Please enter a number!");
        }
        //call the pause method to pause the program until the user presses enter
        pause();
        //return -1 to indicate an invalid input
        return -1;
    }

    //method for pausing the program
    public static void pause(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Press Enter to continue: ");
        scanner.nextLine();   
    }

    //method for clearing the console (stolen from the internet :D)
    public static void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // Clear the console
        } catch (Exception e) {
            System.out.println("Unable to clearConsole the screen.");
        }
    }

}
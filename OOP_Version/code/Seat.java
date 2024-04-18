/*
 * Class seats which is a parent class for Theater. It has a seat status which can be booked or unbooked.
 */

package OOP_Version.code;

class Seat {
    // Status of the seat
    protected SeatStatus seatStatus;

    //method for setting the seat to booked
    public void setBooked(){
        seatStatus = SeatStatus.Booked;
    }

    //method for setting the seat to unbooked
    public void setUnbooked(){
        seatStatus = SeatStatus.Unbooked;
    }
}

// Enum for the status of the seat
enum SeatStatus{
    Booked,
    Unbooked
}
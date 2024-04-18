/*
 * Class theater is a subclass of Seats. It has a list of seats and can book and unbook seats.
 */

package OOP_Version.code;

class Theater extends Seats{
    // Number of seats in the theater
    protected int seatNum;
    // Array of seats in the theater
    protected Seats[] seats;

    // Constructor to initialize the theater
    public Theater(int seatNum){
        this.seatNum = seatNum;

        // Initialize the array of seats
        seats = new Seats[seatNum];

        // Initialize each seat in the array
        for(int index = 0; index < seatNum; index++){
            //set each object in the array to a new seat
            seats[index] = new Seats();
            //set each seat to unbooked
            seats[index].setUnbooked();
        }
    }

    //method for changing the number of seats in the theater (same as the Constructor)
    public void changeSeatNum(int seatNum){
        this.seatNum = seatNum;

        seats = new Seats[seatNum];

        for(int index = 0; index < seatNum; index++){
            seats[index] = new Seats();
            seats[index].setUnbooked();
        }
    }

    //method for booking a seat
    public Response bookSeat(int seatNum){
        //check if the seat number is valid
        if(seatNum == 0 || seatNum > this.seatNum){
            //return error if the seat number is invalid
            return Response.Error;
        }

        if (seats[seatNum - 1].seatStatus == SeatStatus.Booked){
            //return failure if the seat is already booked
            return Response.Failure;
        }

        //set the seat to booked
        seats[seatNum - 1].seatStatus = SeatStatus.Booked;
        //return success if the seat is successfully booked
        return Response.Success;
    }

    //method for unbooking a seat
    public Response unBookSeat(int seatNum){
        //check if the seat number is valid
        if(seatNum == 0 || seatNum > this.seatNum){
            //return error if the seat number is invalid
            return Response.Error;
        }
        
        //check if the seat is already unbooked
        if (seats[seatNum - 1].seatStatus == SeatStatus.Unbooked){
            //return failure if the seat is already unbooked
            return Response.Failure;
        }

        //set the seat to unbooked
        seats[seatNum - 1].seatStatus = SeatStatus.Unbooked;
        //return success if the seat is successfully unbooked
        return Response.Success;
    }

    //method for checking the status of a seat
    public SeatStatus checkStatus(int seatNum){
        //check if the seat number is valid
        if (seats[seatNum].seatStatus == SeatStatus.Unbooked){
            //return unbooked if the seat is unbooked
            return SeatStatus.Unbooked;
        }
        //return booked if the seat is booked as default
        return SeatStatus.Booked;
    }
}

//enum for the response of the methods
enum Response{
    Success,
    Failure,
    Error
}
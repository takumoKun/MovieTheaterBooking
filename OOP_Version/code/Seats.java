package OOP_Version.code;

class Seats {
    protected SeatStatus seatStatus;

    public void setBooked(){
        seatStatus = SeatStatus.Booked;
    }

    public void setUnbooked(){
        seatStatus = SeatStatus.Unbooked;
    }
}

enum SeatStatus{
    Booked,
    Unbooked
}
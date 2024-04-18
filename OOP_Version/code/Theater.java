package OOP_Version.code;

class Theater extends Seats{
    protected int seatNum;
    protected Seats[] seats;

    public Theater(int seatNum){
        this.seatNum = seatNum;

        seats = new Seats[seatNum];

        for(int index = 0; index < seatNum; index++){
            seats[index] = new Seats();
            seats[index].setUnbooked();
        }
    }

    public void changeSeatNum(int seatNum){
        this.seatNum = seatNum;

        seats = new Seats[seatNum];

        for(int index = 0; index < seatNum; index++){
            seats[index] = new Seats();
            seats[index].setUnbooked();
        }
    }

    public Response bookSeat(int seatNum){
        if(seatNum == 0 || seatNum > this.seatNum){
            return Response.Error;
        }

        if (seats[seatNum - 1].seatStatus == SeatStatus.Booked){
            return Response.Failure;
        }

        seats[seatNum - 1].seatStatus = SeatStatus.Booked;
        return Response.Success;
    }

    public Response unBookSeat(int seatNum){
        if(seatNum == 0 || seatNum > this.seatNum){
            return Response.Error;
        }
        
        if (seats[seatNum - 1].seatStatus == SeatStatus.Unbooked){
            return Response.Failure;
        }

        seats[seatNum - 1].seatStatus = SeatStatus.Unbooked;
        return Response.Success;
    }

    public SeatStatus checkStatus(int seatNum){
        
        if (seats[seatNum].seatStatus == SeatStatus.Unbooked){
            return SeatStatus.Unbooked;
        }

        return SeatStatus.Booked;
    }
}

enum Response{
    Success,
    Failure,
    Error
}
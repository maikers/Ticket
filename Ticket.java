
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;


public class Ticket 
{
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private double price;
    private int arrivalStationId;
    private int departureStationId;
    private int trainid;

    public Ticket() {
    }

    public Ticket(LocalTime departureTime, LocalTime arrivalTime, double price, int arrivalStationId, int departureStationId, int trainid) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.arrivalStationId = arrivalStationId;
        this.departureStationId = departureStationId;
        this.trainid = trainid;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }   

    public double getPrice() {
        return price;
    }

    public int getArrivalStationId() {
        return arrivalStationId;
    }

    public int getDepartureStationId() {
        return departureStationId;
    }

    public int getTrainid() {
        return trainid;
    }

    public void setArrivalStationId(int arrivalStationId) {
        this.arrivalStationId = arrivalStationId;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setDepartureStationId(int departureStationId) {
        this.departureStationId = departureStationId;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTrainid(int trainid) {
        this.trainid = trainid;
    }
    public long getTime()
    {
        long time=ChronoUnit.SECONDS.between(arrivalTime,departureTime);
       if(time<0)
       {
           time+=86400;
       }
       return time;
    }
    public void print(){
        System.out.print(this.getTrainid()+" "+this.getDepartureStationId()+" "+this.getArrivalStationId()+" "+this.getPrice()+" ");
        System.out.println(this.getArrivalTime()+" "+this.getDepartureTime());
    }
}

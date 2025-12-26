package org.example;
import java.util.*;
class Customer {
         private  int customerId;
         private  char pickup;
         private  char drop;
         private  int pickupTime;

         Customer(int customerId,char pickup,char drop,int pickupTime){
             this.customerId=customerId;
             this.pickup=pickup;
             this.drop=drop;
             this.pickupTime=pickupTime;
         }

         public int getCustomerId(){
              return customerId;
         }
         public char getPickup(){
             return pickup;
         }
         public char getDrop(){
             return drop;
         }
         public int getPickupTime(){
             return pickupTime;
         }
}
class Booking{
     private int bookingId;
     private int dropTime;
     private int amount;
     private Customer customer;

     Booking(int bookingId,int dropTime,int amount,Customer customer){
         this.bookingId=bookingId;
         this.dropTime=dropTime;
         this.amount=amount;
         this.customer=customer;
     }
     public int getBookingId(){
         return bookingId;
     }
     public int getDropTime(){
         return dropTime;
     }
     public int getAmount(){
         return amount;
     }
     public Customer getCustomer(){
         return customer;
     }
     public int getCustomerId(){
        return this.customer.getCustomerId();
     }
     public char getPickup(){
        return this.customer.getPickup();
     }
     public char getDrop(){
         return this.customer.getDrop();
     }
     public int getPickupTime(){
         return this.customer.getPickupTime();
     }


}
class Taxi{
    private int taxiId;
    private char currentSpot;
    private int freeTime;
    private int earnings;
    private List<Booking>bookings;

    Taxi(int taxiId){
        this.taxiId=taxiId;
        this.currentSpot='A';
        this.freeTime=0;
        this.earnings=0;
        this.bookings=new ArrayList<>();
    }
    boolean isfree(char pickSpot,int pickupTime){
        int travelTime=Math.abs(currentSpot-pickSpot);
        if(freeTime+travelTime<=pickupTime)return true;
        return false;
    }
    void assigns(Booking b){
        bookings.add(b);
    }
    public int getTaxiId(){
        return taxiId;
    }
    public char getCurrentSpot(){
        return currentSpot;
    }
    public int getfreeTime(){
        return freeTime;
    }
    public int getEarnings(){
        return earnings;
    }
    public List<Booking> getbookings(){
        return bookings;
    }
    public void setCurrentSpot(char currentspot){
        this.currentSpot=currentspot;
    }

    public void setFreeTime(int freetime){
        this.freeTime=freetime;
    }
    public void setEarnings(int earn){
        this.earnings=earn;
    }
}
class BookingSystem{
    private List<Taxi>taxis;
    int bookingId=1;
    BookingSystem(int taxicount){
        this.taxis=new ArrayList<>();
        for(int i=1;i<=taxicount;i++){
            taxis.add(new Taxi(i));
        }
    }
    public int calculatecharges(char pickupSpot,char drop){
        int charges=100;
        int distance=Math.abs(pickupSpot-drop)*15;
        distance-=5;
        charges+=distance*10;
        return charges;
    }
    public Taxi taxifind(char pickupSpot, int pickupTime){
        List<Taxi>availabletaxis=new ArrayList<>();
        for(Taxi t:taxis){
            if(t.isfree(pickupSpot,pickupTime)){
                availabletaxis.add(t);
            }
        }
        if(availabletaxis.isEmpty()){
            return null;
        }
        int mindistance=Integer.MAX_VALUE;
        for(Taxi t:availabletaxis){
            int distance=Math.abs(Math.abs(pickupSpot-t.getCurrentSpot()));
            if(distance<mindistance) {
                mindistance = distance;
            }
        }
        List<Taxi>closetdistance=new ArrayList<>();
        for(Taxi t:availabletaxis){
            int distance=Math.abs(Math.abs(pickupSpot-t.getCurrentSpot()));
            if(mindistance==distance){
                closetdistance.add(t);
            }
        }
        Taxi selected=closetdistance.get(0);
        for(Taxi t:closetdistance) {
            if (t.getEarnings() < selected.getEarnings()) {
                selected = t;
            }
        }
        return selected;
    }
    void booktaxi(Customer c){
        Taxi selected=taxifind(c.getPickup(),c.getPickupTime());
        if(selected==null){
            System.out.println("Taxi's are Not available");
        }
        int traveltime=Math.abs(c.getPickup()-c.getDrop());
        int droptime=c.getPickupTime()+traveltime;
        int charges=calculatecharges(c.getPickup(),c.getDrop());
        Booking booking=new Booking(bookingId,droptime,charges,c);
        bookingId++;

        selected.assigns(booking);
        selected.setFreeTime(droptime);
        selected.setEarnings(charges+ selected.getEarnings());
        selected.setCurrentSpot(c.getDrop());
        System.out.println("Taxi-"+selected.getTaxiId()+"is Allocated");
    }
    void display(){
        System.out.println();
        for(Taxi t:taxis){
            System.out.println("Taxi-"+t.getTaxiId()+"   Earnings"+t.getEarnings());
            System.out.println("BookingID   Cus-Id   From    To    PickupTime    DropTime   Charge");
            for(Booking b:t.getbookings()){
                System.out.println(" "+b.getBookingId()+"             "+b.getCustomerId()+"  " +
                        "    "+b.getPickup()+"      "+b.getDrop() +
                        "       "+b.getPickupTime()+"            "+b.getDropTime()+"          "+b.getAmount());
                System.out.println();
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Customer c1=new Customer(1,'A','B',9);
        Customer c2=new Customer(2,'B','D',9);
        Customer c3=new Customer(3,'D','C',12);
        Customer c4=new Customer(4,'A','D',9);
        BookingSystem b=new BookingSystem(4);
         b.booktaxi(c1);
         b.booktaxi(c2);
         b.booktaxi(c3);
         b.booktaxi(c4);
         b.display();
        }
    }
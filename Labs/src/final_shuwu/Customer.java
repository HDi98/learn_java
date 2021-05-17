package final_shuwu;

import java.util.Random;

public class Customer implements Comparable<Customer>{
    static int customerCount;
    int id;
    int numberOfTickets;

    Customer() {
        customerCount++;
        this.id = customerCount;

        Random random = new Random();
        //MIN_TICKETS ----- MAX_TICKETS;
        numberOfTickets = random.nextInt(MovieHall.MAX_TICKETS - MovieHall.MIN_TICKETS) + MovieHall.MIN_TICKETS;

    }

    boolean joinQueue(){
        synchronized (MovieHall.customerQueue){
            //???????this?????????
            MovieHall.customerQueue.offer(this);
        }
        System.out.println("Customer " + this.id + " joined Q");
        return true;
    }

    @Override
    public int compareTo(Customer o) {
        return o.numberOfTickets - this.numberOfTickets;
    }
}

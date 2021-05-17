package final_shuwu;

import java.util.ArrayList;
import java.util.List;

public class TicketWindow implements Runnable {
    static int ticketSoldCount;
    static boolean isWindowOpen = true;
    int ticketProcessingTime;
    List<Customer> customerList = new ArrayList<>();

    TicketWindow(int ticketProcessingTime) {
        this.ticketProcessingTime = ticketProcessingTime;
    }

    @Override
    public void run() {
        while (isWindowOpen) {
            Customer customer = null;
            synchronized (MovieHall.customerQueue) {
                customer = MovieHall.customerQueue.poll();
            }
            if (customer != null) {

                try {
                    Thread.sleep(ticketProcessingTime * customer.numberOfTickets);
                    if(customer instanceof ImpatientCustomer){
                        System.out.println("\t\t\t\t\t\tImpatientCustomer " + customer.id + " bought " + customer.numberOfTickets + " tickets");
                    }else {
                        System.out.println("\t\t\t\t\t\tCustomer " + customer.id + " bought " + customer.numberOfTickets + " tickets");
                    }
                    ticketSoldCount += customer.numberOfTickets;
                    customerList.add(customer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (ticketSoldCount >= MovieHall.maxSeats) {
                    isWindowOpen = false;

                }
            }
        }
    }
}

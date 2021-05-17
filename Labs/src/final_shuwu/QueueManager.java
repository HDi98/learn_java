package final_shuwu;

import java.util.Random;

public class QueueManager implements Runnable{
    int customerDelay;
    int balkCount;

    QueueManager(int customerDelay){
        this.customerDelay = customerDelay;
    }

    @Override
    public void run() {
        Random random = new Random();
        if(MovieHall.examPart == 1){
            while(TicketWindow.isWindowOpen){
                try{
                    Customer customer = new Customer();
                    customer.joinQueue();
                    Thread.sleep(random.nextInt(customerDelay + 1));
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }else if(MovieHall.examPart == 2){

            while(TicketWindow.isWindowOpen){
                try{
                    int choice = random.nextInt(2);
                    Customer customer = null;
                    if(choice == 0){
                        customer = new Customer();
                    }else if(choice == 1){
                        customer = new ImpatientCustomer();
                    }

                    boolean flag = customer.joinQueue();
                    if(!flag){
                        balkCount++;
                    }

                    Thread.sleep(customerDelay + 1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }



            }
        }
    }
}

package final_shuwu;

public class ImpatientCustomer extends Customer{
    static int impatientCustomerCount;

    ImpatientCustomer() {
        impatientCustomerCount++;
    }

    @Override
    boolean joinQueue() {
        if(MovieHall.customerQueue.size() > MovieHall.balkQueueLength){
            System.out.println("***ImpatientCustomer " + this.id + " balked");
            return false;
        }else{
            synchronized (MovieHall.customerQueue){
                MovieHall.customerQueue.offer(this);
                System.out.println("ImpatientCustomer " + this.id + " join the queue");
                return true;
            }
        }
    }
}

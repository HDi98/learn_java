package test_only;


public class for_test implements Runnable{
	

	
	
	@Override
    public void run() {
        for (int i = 0; i < 20; i++)
            System.out.println(Thread.currentThread().getName() + " running.");
    }
    public static void main(String[] args) {
         Thread one = new Thread (new for_test());
         Thread two = new Thread (new for_test());
         one.start();
         two.start();
    }

}
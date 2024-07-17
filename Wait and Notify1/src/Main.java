
public class Main {
public static int balance=0;
synchronized public void withdraw(int amount) throws InterruptedException{
    if(balance<=0){
        System.out.println("waiting for amount to be updated.."+ amount);
        wait();
    }
    balance=balance-amount;
    System.out.println("current balance is  " +balance);
}
    public void deposit(int amount) throws InterruptedException{
    synchronized (this)   {
    System.out.println("we are depositing in bank account.."+amount);
        balance=balance+amount;
        notify();}
    }


    public static void main(String[] args) {
Main main = new Main();
Thread thread1= new Thread(new Runnable() {
    @Override
    public void run() {
        try {
            main.withdraw(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
});
thread1.setName("thread 1");
        thread1.start();

        Thread thread2= new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    main.deposit(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        thread2.setName("thread 2");
        thread2.start();



    }
}
package Text_work;

public class MULTI_Treading_work {
    public static void main(String args[]) throws Exception{
        Cinema a = new Cinema();
        a.sun.start();
        Thread.sleep(1000);
        a.zhang.start();


        Thread.sleep(1000);
        a.zhao.start();

    }

}

class TicketSeller {

    int fiveNumber = 3, tenNumber = 0, twentyNumber = 0;

    public synchronized void sellTicket(int receiveMoney) {

        if (receiveMoney == 5) {

            fiveNumber = fiveNumber + 1;

            System.out.println(Thread.currentThread().getName() +

                    "给5元钱，这是您的1张入场卷");

        } else if (receiveMoney == 10) {

            while (fiveNumber < 1) {

                try {
                    Thread.sleep(1000);

                    System.out.println(Thread.currentThread().getName() + "靠边等");

                    wait();

                    System.out.println(Thread.currentThread().getName() + "结束等待");

                } catch (InterruptedException exp) {
                }

            }

            fiveNumber = fiveNumber - 1;

            tenNumber = tenNumber + 1;

            System.out.println(Thread.currentThread().getName() +

                    "给10元钱,找您5元,这是您的1张入场卷");

        } else if (receiveMoney == 20) {

            while (fiveNumber < 1 || tenNumber < 1) {

                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "靠边等");

                    wait();

                    System.out.println(Thread.currentThread().getName() + "结束等待");

                } catch (InterruptedException exp) {
                }

            }

            fiveNumber = fiveNumber - 1;

            tenNumber = tenNumber - 1;

            twentyNumber = twentyNumber + 1;

            System.out.println(Thread.currentThread().getName() +

                    "给20元钱，找您一张5元和一张10元，这是您的1张入场卷");


        }

        notifyAll();

    }

}

class Cinema implements Runnable {
    Thread zhang, sun, zhao;

    TicketSeller seller;

    Cinema() {
        zhang = new Thread(this);

        sun = new Thread(this);

        zhao = new Thread(this);

        zhang.setName("张某");

        sun.setName("孙某");

        zhao.setName("赵某");

        seller = new TicketSeller();

    }

    public void run() {
        if (Thread.currentThread() == zhang) {

            seller.sellTicket(20);
        } else if (Thread.currentThread() == sun) {

            seller.sellTicket(10);
        } else if (Thread.currentThread() == zhao) {

            seller.sellTicket(5);

        }

    }

}

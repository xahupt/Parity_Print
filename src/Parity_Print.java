import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Parity_Print extends Thread{
    volatile int count = 0;
    int Max_Num=99;
    static Lock lock = new ReentrantLock(true);

    @Override
    public void run() {

        for (;count<=Max_Num;count++) {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + ":" + count);
            /**
             *
             * ＠ｐｅｒｃｙ　
             * 虽然是公平锁，但是这里如果不加延时，结果就不是公平锁
             *
             */
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();


        }
    }

    public static void main(String[] args){
        Parity_Print parity_print = new Parity_Print();
        Thread thread1=new Thread(parity_print);
        Thread thread2=new Thread(parity_print);
        thread1.start();
        thread2.start();
    }

}

import java.util.*;
class RunnableExample
{
    public static void main(String args[])
    {
        Count cnt = new Count();
        try
        {
            while(cnt.threads.get(0).isAlive())
            {
                System.out.println("Main thread will be alive till the child thread is live"); 
                Thread.sleep(1500);
            }
        }
        catch(InterruptedException e)
        {
            System.out.println("Main thread interrupted");
        }
        System.out.println("Main thread run is over" );
    }
}

class Count implements Runnable
{
    ArrayList<Thread> threads = new ArrayList<Thread>();
    Count()
    { 
        for(int i=0;i<2;i++){
            threads.add(new Thread(this, "my runnable thread"));
            System.out.println("my thread created" + threads.get(i));
            threads.get(i).start();
        }
    }

    public void run()
    {
        try
        {
            for (int i=0 ;i<10;i++)
            {
                System.out.println("Printing the count " + i);
                Thread.sleep(1000);
            }
        }
        catch(InterruptedException e)
        {
            System.out.println("my thread interrupted");
        }
        System.out.println("mythread run is over" );
    }
}

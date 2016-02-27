import java.util.concurrent.ThreadLocalRandom;
public class BilloKI
{
    public BilloKI()
    {

    }

    public Feld setze(TicTacToe toe)
    {
        int a=0;
        int b=0;
        int c=0;
        int d=0;
        boolean ja = false;
        while(!ja){
            a = ThreadLocalRandom.current().nextInt(0, 5);
            b = ThreadLocalRandom.current().nextInt(0, 5);
            c = ThreadLocalRandom.current().nextInt(0, 5);
            d = ThreadLocalRandom.current().nextInt(0, 5);            
            ja = toe.check(a,b,c,d);
        }
        return new Feld(a,b,c,d,1);
    }
}

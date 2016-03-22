import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
public class BilloKI
{
    public BilloKI()
    {

    }

    public Feld setze(TicTacToe toe)
    {
        int Dim = toe.Felder.get(0).getK().size();
        ArrayList<Integer> Koord = new ArrayList<Integer>();
        boolean ja = false;
        while(!ja){
            Koord.clear();
            for(int i=0;i<Dim;i++){
                Koord.add(ThreadLocalRandom.current().nextInt(0, Dim+1));
            }      
            ja = toe.check(new Feld(Koord));
        }
        return new Feld(Koord);
    }
}
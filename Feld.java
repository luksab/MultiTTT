import java.util.*;
public class Feld
{
    private ArrayList<Integer> Koord = new ArrayList<Integer>();
    private int spieler;

    public Feld(ArrayList<Integer> NKoord,int spielerNeu)
    {
        Koord = NKoord;
        spieler=spielerNeu;
    }
    
    public Feld(ArrayList<Integer> NKoord)
    {
        Koord = NKoord;
    }

    public Feld()
    {

    }

    public ArrayList getK()
    {
        return Koord;
    }

    public int gC(int a){
        return Koord.get(a);
    }

    public int spieler()
    {
        return spieler;
    }

    public int getSpieler()
    {
        return spieler;
    }
    
    public void setSpieler(int NSpieler)
    {
        spieler = NSpieler;
    }

    public String toString(){
        String s = ""+"Spieler: "+spieler+" Koordinaten: "+Koord.get(0)+"|"+Koord.get(1)+"|"+Koord.get(2)+"|"+Koord.get(3);
        return s;
    }
}

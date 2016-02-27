public class Feld
{
    private int a;
    private int b;
    private int c;
    private int d;
    private int spieler;

    public Feld(int aNeu,int bNeu,int cNeu,int dNeu,int spielerNeu)
    {
        a=aNeu;
        b=bNeu;
        c=cNeu;
        d=dNeu;
        spieler=spielerNeu;
    }
    
    public Feld(int aNeu,int bNeu,int cNeu,int dNeu)
    {
        a=aNeu;
        b=bNeu;
        c=cNeu;
        d=dNeu;
    }

    public Feld()
    {
       
    }

    public int A()
    {
        return a;
    }

    public int B()
    {
        return b;
    }

    public int C()
    {
        return c;
    }

    public int D()
    {
        return d;
    }

    public int spieler()
    {
        return spieler;
    }
    
    public int getA()
    {
        return a;
    }

    public int getB()
    {
        return b;
    }

    public int getC()
    {
        return c;
    }

    public int getD()
    {
        return d;
    }

    public int getSpieler()
    {
        return spieler;
    }

    public String toString(){
        String s = ""+"Spieler: "+spieler+" Koordinaten: "+a+"|"+b+"|"+c+"|"+d;
        return s;
    }
}

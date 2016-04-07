import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
import java.util.concurrent.*;
public class SimpleKI
{
    int Dim = 4;
    ArrayList<Feld> Possible;
    TicTacToe toe;
    public SimpleKI()
    {

    }

    public SimpleKI(int Dim)
    {
        this.Dim = Dim;
    }

    public SimpleKI(int Dim, TicTacToe toe)
    {
        this.Dim = Dim;
        this.toe = toe;
    }

    public Feld setze(TicTacToe toe)
    {
        if(toe.Felder.size() > 1){
            Feld FastIch = fast(toe.Felder,2);
            if(FastIch.spieler() == 0){
                System.out.println("FastIch");
                return FastIch;
            }
            Feld FastGegner = fast(toe.Felder,1);
            if(FastGegner.spieler() == 0){
                System.out.println("FastGegner");
                return FastGegner;
            }
            Feld Zwickmühle = Zwickmühle(toe.Felder,0);
            if(Zwickmühle.spieler() == 0){
                System.out.println("Zwickmühle");
                return Zwickmühle;
            }
            Feld LL = FindLargestLine(toe.Felder,2);
            if(LL.spieler() == 0){
                System.out.println("LL");
                return LL;
            }
            else{
                System.out.println("RandFLL");
                return randF(toe);
            }
        }
        else{
            System.out.println("RandF");
            return randF(toe);
        }
    }

    public Feld setze(TicTacToe toe, int sp)
    {
        int gg;
        if(sp == 1)
            gg = 0;
        else
            gg = 1;
        if(toe.Felder.size() > 1){
            Feld FastIch = fast(toe.Felder,2);
            if(FastIch.spieler() == 0){
                System.out.println("FastIch");
                return FastIch;
            }
            Feld FastGegner = fast(toe.Felder,1);
            if(FastGegner.spieler() == 0){
                System.out.println("FastGegner");
                return FastGegner;
            }
            Feld Zwickmühle = Zwickmühle(toe.Felder,gg);
            if(Zwickmühle.spieler() == 0){
                System.out.println("Zwickmühle");
                return Zwickmühle;
            }
            Feld LL = FindLargestLineThread(toe.Felder,sp);
            if(LL.spieler() == 0){
                System.out.println("LL");
                return LL;
            }
            else{
                System.out.println("RandFLL");
                return randF(toe);
            }
        }
        else{
            System.out.println("RandF");
            return randF(toe);
        }
    }    

    public Feld setze(int sp)
    {
        int gg;
        if(sp == 1)
            gg = 0;
        else
            gg = 1;
        if(toe.Felder.size() > 1){
            Feld FastIch = fast(toe.Felder,2);
            if(FastIch.spieler() == 0){
                System.out.println("FastIch");
                return FastIch;
            }
            Feld FastGegner = fast(toe.Felder,1);
            if(FastGegner.spieler() == 0){
                System.out.println("FastGegner");
                return FastGegner;
            }
            Feld Zwickmühle = Zwickmühle(toe.Felder,gg);
            if(Zwickmühle.spieler() == 0){
                System.out.println("Zwickmühle");
                return Zwickmühle;
            }
            Feld LL = FindLargestLineThread(toe.Felder,sp);
            if(LL.spieler() == 0){
                System.out.println("LL");
                return LL;
            }
            else{
                System.out.println("RandFLL");
                return randF(toe);
            }
        }
        else{
            System.out.println("RandF");
            return randF(toe);
        }
    }   

    private Feld randF(TicTacToe toe){
        ArrayList<Integer> Koord = new ArrayList<Integer>();
        boolean ja = false;
        if(Dim%2 == 0){
            //grade Dimensionszahl = es gibt EINE Mitte
            for(int i=0;i<Dim;i++){
                Koord.add(Dim/2);
            }
            if(toe.check(new Feld(Koord))){
                return new Feld(Koord);
            }
        }
        else{
            //UNgrade Dimensionszahl = es gibt 2^Dim "Mitten"
            for(int k=0;k<Math.pow(2,Dim);k++){
                Koord.clear();
                for (int i=0;i<Dim;i++){
                    if( (k/(int)(Math.pow(2,i))) % 2 == 0){Koord.add((int)(Dim/2));}
                    else{Koord.add((int)(Dim/2)+1);}
                }
                ja = toe.check(new Feld(Koord));
                if(ja){
                    return new Feld(Koord);
                }
            }
        }

        for(int j=0; j<(int)(Math.pow(3,Dim-1)/2)+Math.pow(3,Dim-1) ; j++){
            Koord.clear();
            for (int i=0;i<Dim;i++){
                if( (j/(int)(Math.pow(2,i))) % 2 == 0){Koord.add(0);}
                else{Koord.add(Dim+1);}
            }
            if(toe.check(new Feld(Koord))){
                return new Feld(Koord);
            }
        }
        while(!ja){
            Koord.clear();
            for(int i=0;i<Dim;i++){
                Koord.add(ThreadLocalRandom.current().nextInt(0, Dim+1));
            }      
            ja = toe.check(new Feld(Koord));
        }
        return new Feld(Koord);

    }

    private Feld fast(ArrayList<Feld> Felder,int IOD){
        boolean fast = false;
        boolean breaking = false;
        Feld letztesFeld = Felder.get(Felder.size()-IOD);
        int[] P = new int[Felder.get(0).getK().size()];
        int[] D = new int[Felder.get(0).getK().size()];
        int sp = letztesFeld.spieler();
        Feld BitteZiehen = new Feld();
        for(int j=0; j<(int)(Math.pow(3,Dim-1)/2)+Math.pow(3,Dim-1) ; j++){
            for (int i=0;i<Dim;i++){
                if( (j/(int)(Math.pow(3,i))) % 3 == 0){P[i] = 0;D[i] = 1;}
                else if((j/(int)(Math.pow(3,i)))%3==1){P[i] = letztesFeld.gC(i);D[i] = 0;}
                else{P[i] = Dim;D[i] = -1;}
            }
            for(int i=0; i<Felder.size() -1;i++){
                int zähler = 0;
                for(int h=0;h<Dim;h++){
                    if(letztesFeld.gC(h) + D[h] == Felder.get(i).gC(h)){
                        zähler++;
                    }
                }
                int zähler2 = 0;
                for(int h=0;h<Dim;h++){
                    if(letztesFeld.gC(h) - D[h] == Felder.get(i).gC(h)){
                        zähler2++;
                    }
                }
                boolean jaa=false;
                for (int m=0;m<Dim;m++){
                    if( (j/(int)(Math.pow(3,m))) % 3 == 0){P[m] = 0;}
                    else if((j/(int)(Math.pow(3,m)))%3==1){P[m] = letztesFeld.gC(m);}
                    else{P[m] = Dim;}
                }
                int zaehler = 0;
                for(int k=0;k<Dim+1;k++){
                    ArrayList<Integer> PArray = new ArrayList<Integer>();
                    for(int m=0;m<Dim;m++){
                        PArray.add(P[m]);
                    }
                    Feld PFeld = new Feld(PArray);
                    if(Spielfeld(PFeld,Felder) == sp){
                        zaehler ++;
                    }
                    else if(Spielfeld(PFeld,Felder) == -1){
                        jaa=true;
                        BitteZiehen=PFeld;
                    }
                    for(int m=0;m<=Dim-1;m++){
                        P[m] += D[m];
                    }
                }
                if(zaehler == Dim && jaa)
                {
                    fast = true;
                    breaking = true;
                    BitteZiehen.setSpieler(0);
                }
                else{
                    BitteZiehen.setSpieler(-1);
                }
                if(breaking){break;}
            }
            if(breaking){break;}
        }
        return BitteZiehen;
    }

    private Feld FindLargestLine(ArrayList<Feld> Felder,int sp){
        int[] P = new int[Felder.get(0).getK().size()];
        int[] D = new int[Felder.get(0).getK().size()];
        Feld BitteZiehen = new Feld();
        int longestLine = 0;
        BitteZiehen.setSpieler(-1);
        for(int var=1;var<Felder.size()+1;var++){
            Feld letztesFeld = Felder.get(Felder.size()-var);
            for(int j=0; j<(int)(Math.pow(3,Dim-1)/2)+Math.pow(3,Dim-1) ; j++){
                for (int i=0;i<Dim;i++){
                    if( (j/(int)(Math.pow(3,i))) % 3 == 0){P[i] = 0;D[i] = 1;}
                    else if((j/(int)(Math.pow(3,i)))%3==1){P[i] = letztesFeld.gC(i);D[i] = 0;}
                    else{P[i] = Dim;D[i] = -1;}
                }
                for(int i=0; i<Felder.size() -1;i++){
                    int zähler = 0;
                    for(int h=0;h<Dim;h++){
                        if(letztesFeld.gC(h) + D[h] == Felder.get(i).gC(h)){
                            zähler++;
                        }
                    }
                    int zähler2 = 0;
                    for(int h=0;h<Dim;h++){
                        if(letztesFeld.gC(h) - D[h] == Felder.get(i).gC(h)){
                            zähler2++;
                        }
                    }
                    boolean jaa=true;
                    for (int m=0;m<Dim;m++){
                        if( (j/(int)(Math.pow(3,m))) % 3 == 0){P[m] = 0;}
                        else if((j/(int)(Math.pow(3,m)))%3==1){P[m] = letztesFeld.gC(m);}
                        else{P[m] = Dim;}
                    }
                    int zaehler = 0;
                    Feld CacheFeld = new Feld();
                    for(int k=0;k<Dim+1;k++){
                        ArrayList<Integer> PArray = new ArrayList<Integer>();
                        for(int m=0;m<Dim;m++){
                            PArray.add(P[m]);
                        }
                        Feld PFeld = new Feld(PArray);
                        if(Spielfeld(PFeld,Felder) == sp){
                            zaehler ++;
                        }
                        else if(Spielfeld(PFeld,Felder) == -1){
                            //Frei:Ignorieren;
                            CacheFeld = PFeld;
                        }
                        else{
                            //Hier nicht hinsetzen
                            jaa = false;
                            //break;
                        }
                        for(int m=0;m<=Dim-1;m++){
                            P[m] += D[m];
                        }
                    }
                    if(zaehler > longestLine && jaa){
                        longestLine = zaehler;
                        BitteZiehen=CacheFeld;
                        BitteZiehen.setSpieler(0);
                        BitteZiehen.setReihe(longestLine);
                        //System.out.println("LL:"+longestLine);
                    }
                }
            }
        }
        return BitteZiehen;
    }

    private Feld Zwickmühle(ArrayList<Feld> Felder,int sp){
        int[] P = new int[Felder.get(0).getK().size()];
        int[] D = new int[Felder.get(0).getK().size()];
        ArrayList<ZweiFeld> Reihen = new ArrayList<ZweiFeld>();
        Feld BitteZiehen = new Feld();
        BitteZiehen.setSpieler(-1);
        for(int var=1;var<Felder.size()+1;var++){
            Feld letztesFeld = Felder.get(Felder.size()-var);
            if(letztesFeld.spieler() == sp){
                for(int j=0; j<(int)(Math.pow(3,Dim-1)/2)+Math.pow(3,Dim-1) ; j++){
                    for (int i=0;i<Dim;i++){
                        if( (j/(int)(Math.pow(3,i))) % 3 == 0){P[i] = 0;D[i] = 1;}
                        else if((j/(int)(Math.pow(3,i)))%3==1){P[i] = letztesFeld.gC(i);D[i] = 0;}
                        else{P[i] = Dim;D[i] = -1;}
                    }
                    for(int i=0; i<Felder.size() -1;i++){
                        boolean jaa=true;
                        for (int m=0;m<Dim;m++){
                            if( (j/(int)(Math.pow(3,m))) % 3 == 0){P[m] = 0;}
                            else if((j/(int)(Math.pow(3,m)))%3==1){P[m] = letztesFeld.gC(m);}
                            else{P[m] = Dim;}
                        }
                        int zaehler = 0;
                        ZweiFeld CacheFeld = new ZweiFeld();
                        for(int k=0;k<Dim+1;k++){
                            ArrayList<Integer> PArray = new ArrayList<Integer>();
                            for(int m=0;m<Dim;m++){
                                PArray.add(P[m]);
                            }
                            Feld PFeld = new Feld(PArray);
                            //System.out.println("SP"+Spielfeld(PFeld,Felder));
                            if(Spielfeld(PFeld,Felder) == sp){
                                zaehler ++;
                                //System.out.println("Z: "+zaehler);
                            }
                            else if(Spielfeld(PFeld,Felder) == -1){
                                //Frei:Ignorieren;
                                CacheFeld.add(PFeld);
                                //if(zaehler == 3)
                                //    System.out.println("Cache="+PFeld);
                                //jaa = true;
                            }
                            else{
                                //Hier nicht hinsetzen
                                jaa = false;
                                break;
                            }
                            for(int m=0;m<=Dim-1;m++){
                                P[m] += D[m];
                            }
                        }
                        //System.out.println("Z:"+zaehler);
                        if(zaehler == Dim-1 && jaa){
                            Reihen.add(CacheFeld);
                        }
                    }
                }
            }
        }
        System.out.println("Zw: "+Reihen);
        for(int i=0;i<Reihen.size()-1;i++){
            for(int j=0;j<Reihen.size()-1;j++){
                if(!Reihen.get(i).equals(Reihen.get(j))){
                    //System.out.println("!EQ");
                    if(Reihen.get(i).hit(Reihen.get(j)) == 0){
                        BitteZiehen = Reihen.get(i).get(1);
                        System.out.println("ZW0");
                        BitteZiehen.setSpieler(0);
                    }
                    if(Reihen.get(i).hit(Reihen.get(j)) == 1){
                        BitteZiehen = Reihen.get(i).get(0);
                        System.out.println("ZW1");
                        BitteZiehen.setSpieler(0);
                    }
                }
            }
        }
        System.out.println("BZ: "+BitteZiehen);
        return BitteZiehen;
    }

    private Feld FindLargestLineThread(ArrayList<Feld> Felder,int sp){
        Possible = new ArrayList<Feld>();
        Feld BitteZiehen = new Feld();
        BitteZiehen.setSpieler(-1);
        int LL = 0;
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for(int i=0;i<Felder.size();i++){
            threadPool.submit(new LongLine(Felder,sp,i,Dim,this));
        } 
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        for(int i=0;i<Possible.size();i++){
            if(Possible.get(i).gR() > LL){
                LL = Possible.get(i).gR();
                BitteZiehen = Possible.get(i);
            }
        }
        return BitteZiehen;
    }

    private int Spielfeld(Feld feld,ArrayList<Feld> Felder){
        for(int i=0;i<Felder.size();i++){
            int zähler = 0;
            for(int j=0;j<Felder.get(i).getK().size();j++){
                if(Felder.get(i).gC(j) == feld.gC(j))
                    zähler++;
            }
            if(zähler == Felder.get(i).getK().size()){
                //System.out.println(" "+Felder.get(i).spieler());
                return Felder.get(i).spieler();
            }
        }
        return -1;
    }
}

class ZweiFeld{
    public ArrayList<Feld> F = new ArrayList<Feld>();
    public ZweiFeld(){}

    public void add(Feld feld2){
        F.add(feld2);
    }

    public boolean equals(ZweiFeld ander){
        int zähler = 0;
        for(int i=0;i<2;i++){
            for(int j=0;j<F.get(0).getK().size();j++){
                if(F.get(i).gC(j) == ander.F.get(i).gC(j))
                    zähler++;
            }
        }
        if(zähler > ((F.get(0).getK().size())*2)-1){
            return true;
        }
        return false;
    }

    public byte hit(ZweiFeld ander){
        for(int i=0;i<2;i++){
            int zähler = 0;
            for(int j=0;j<F.get(0).getK().size();j++){
                if(F.get(0).gC(j) == ander.F.get(i).gC(j))
                    zähler++;
            }
            if(zähler == F.get(0).getK().size()-1){
                return 0;
            }

            zähler = 0;
            for(int j=0;j<F.get(0).getK().size();j++){
                if(F.get(0).gC(j) == ander.F.get(i).gC(j))
                    zähler++;
            }
            if(zähler == F.get(0).getK().size()-1){
                return 1;
            }
        }
        return -1;
    }

    public Feld get(int i){
        return F.get(i);
    }
}

class LongLine implements Runnable {
    ArrayList<Feld> Felder;
    int sp;
    int var;
    int Dim;
    SimpleKI ki;
    public LongLine(ArrayList<Feld> Felder,int sp,int var,int Dim,SimpleKI ki) {
        this.Felder = Felder;
        this.sp = sp;
        this.var = var;
        this.Dim = Dim;
        this.ki = ki;
    }

    public void run(){
        int[] P = new int[Felder.get(0).getK().size()];
        int[] D = new int[Felder.get(0).getK().size()];
        Feld BitteZiehen = new Feld();
        int longestLine = 0;
        BitteZiehen.setSpieler(-1);
        Feld letztesFeld = Felder.get(Felder.size()-var);
        for(int j=0; j<(int)(Math.pow(3,Dim-1)/2)+Math.pow(3,Dim-1) ; j++){
            for (int i=0;i<Dim;i++){
                if( (j/(int)(Math.pow(3,i))) % 3 == 0){P[i] = 0;D[i] = 1;}
                else if((j/(int)(Math.pow(3,i)))%3==1){P[i] = letztesFeld.gC(i);D[i] = 0;}
                else{P[i] = Dim;D[i] = -1;}
            }
            for(int i=0; i<Felder.size() -1;i++){
                boolean jaa=true;
                for (int m=0;m<Dim;m++){
                    if( (j/(int)(Math.pow(3,m))) % 3 == 0){P[m] = 0;}
                    else if((j/(int)(Math.pow(3,m)))%3==1){P[m] = letztesFeld.gC(m);}
                    else{P[m] = Dim;}
                }
                int zaehler = 0;
                Feld CacheFeld = new Feld();
                for(int k=0;k<Dim+1;k++){
                    ArrayList<Integer> PArray = new ArrayList<Integer>();
                    for(int m=0;m<Dim;m++){
                        PArray.add(P[m]);
                    }
                    Feld PFeld = new Feld(PArray);
                    if(Spielfeld(PFeld,Felder) == sp){
                        zaehler ++;
                        //System.out.println("Z: "+zaehler);
                    }
                    else if(Spielfeld(PFeld,Felder) == -1){
                        //Frei:Ignorieren;
                        CacheFeld = PFeld;
                    }
                    else{
                        //Hier nicht hinsetzen
                        jaa = false;
                        //break;
                    }
                    for(int m=0;m<=Dim-1;m++){
                        P[m] += D[m];
                    }
                }
                if(zaehler > longestLine && jaa){
                    longestLine = zaehler;
                    BitteZiehen=CacheFeld;
                    BitteZiehen.setSpieler(0);
                    BitteZiehen.setReihe(longestLine);
                    //System.out.println("LL:"+longestLine);
                }
            }
        }
        ki.Possible.add(BitteZiehen);
    }

    private int Spielfeld(Feld feld,ArrayList<Feld> Felder){
        for(int i=0;i<Felder.size();i++){
            int zähler = 0;
            for(int j=0;j<Felder.get(i).getK().size();j++){
                if(Felder.get(i).gC(j) == feld.gC(j))
                    zähler++;
            }
            if(zähler == Felder.get(i).getK().size()){
                return Felder.get(i).spieler();
            }
        }
        return -1;
    }
}
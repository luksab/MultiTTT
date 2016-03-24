import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
public class SimpleKI
{
    public SimpleKI()
    {

    }

    public Feld setze(TicTacToe toe)
    {
        int Dim = toe.Felder.get(0).getK().size();
        if(toe.Felder.size() > 3){
            Feld FastIch = fast(toe.Felder,2);
            if(FastIch.spieler() == 0){
                return FastIch;
            }
            Feld FastGegner = fast(toe.Felder,1);
            if(FastGegner.spieler() == 0){
                return FastGegner;
            }
            Feld LL = FindLargestLine(toe.Felder);
            if(LL.spieler() == 0){
                return LL;
            }
            else{
                return randF(toe);
            }
        }
        else{
            return randF(toe);
        }
    }

    private Feld randF(TicTacToe toe){
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

    private Feld fast(ArrayList<Feld> Felder,int IOD){
        boolean fast = false;
        boolean breaking = false;
        int Dim = Felder.get(0).getK().size();
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

    private Feld FindLargestLine(ArrayList<Feld> Felder){
        boolean breaking = false;
        int Dim = Felder.get(0).getK().size();
        int[] P = new int[Felder.get(0).getK().size()];
        int[] D = new int[Felder.get(0).getK().size()];
        int sp = 1;
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
                            jaa=false;
                            BitteZiehen.setSpieler(0);
                            BitteZiehen=PFeld;
                            if(zaehler > longestLine){
                                longestLine = zaehler;
                                jaa=true;
                                //System.out.println("LL:"+longestLine);
                            }
                        }
                        for(int m=0;m<=Dim-1;m++){
                            P[m] += D[m];
                        }
                    }
                }
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
                return Felder.get(i).spieler();
            }
        }
        return -1;
    }

}
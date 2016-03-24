import java.util.*;
import java.lang.Math;
import java.io.*;
public class Checker implements
java.io.Serializable
{
    public Checker()
    {

    }

    public boolean checkWin(ArrayList<Feld> Felder){
        boolean gewonnen = false;
        int Dim = Felder.get(0).getK().size();
        Feld letztesFeld = Felder.get(Felder.size()-1);
        int[] P = new int[Felder.get(0).getK().size()];
        int[] D = new int[Felder.get(0).getK().size()];
        int sp = letztesFeld.spieler();
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
                if(zähler == Dim || zähler2 == Dim){
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
                        for(int m=0;m<=Dim-1;m++){
                            P[m] += D[m];
                        }
                    }
                    if(zaehler == Dim+1)
                    {
                        gewonnen = true;
                    }
                }
            }
        }
        return gewonnen;
    }

    public int Spielfeld(Feld feld,ArrayList<Feld> Felder){
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
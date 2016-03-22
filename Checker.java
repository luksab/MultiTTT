import java.util.*;
import java.lang.Math;
import java.io.*;
public class Checker implements
java.io.Serializable
{
    public Checker()
    {

    }

    public int checkWin(ArrayList<Feld> Felder, TicTacToe toe){
        int gewonnen = -1;
        int Dim = Felder.get(0).getK().size();
        for(int sp = 0; sp < 2; sp++){
            for(int j=0; j<(int)(Math.pow(3,Dim-1)/2)+Math.pow(3,Dim-1) ; j++){
                Feld letztesFeld = Felder.get(Felder.size()-1);
                int[] P = new int[Felder.get(0).getK().size()];
                int[] D = new int[Felder.get(0).getK().size()];

                for (int i=0;i<Dim;i++){
                    if( (j/(int)(Math.pow(3,i))) % 3 == 0){P[i] = 0;D[i] = 1;}
                    else if((j/(int)(Math.pow(3,i)))%3==1){P[i] = letztesFeld.gC(i);D[i] = 0;}
                    else{P[i] = 4;D[i] = -1;}
                }

                for(int i=0; i<Felder.size() -1;i++){
                    if(Felder.get(Felder.size() -1).spieler() == sp){
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
                                else{P[m] = 4;}
                            }
                            int zaehler = 0;
                            for(int k=0;k<5;k++){
                                ArrayList<Integer> PArray = new ArrayList<Integer>();
                                for(int m=0;m<Dim;m++){
                                    PArray.add(P[m]);
                                }
                                Feld PFeld = new Feld(PArray);
                                if(toe.Spielfeld(PFeld) == sp){
                                    zaehler ++;
                                }
                                for(int m=0;m<=Dim-1;m++){
                                    P[m] += D[m];
                                }
                            }
                            if(zaehler == 5)
                            {
                                gewonnen = sp;
                            }
                        }
                    }
                }
            }
        }
        return gewonnen;
    }
}
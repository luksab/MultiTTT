import java.util.*;
import java.lang.Math;
public class Checker
{
    public Checker()
    {

    }

    public int checkWin(ArrayList<Feld> Felder, TicTacToe toe){
        int gewonnen = -1;
        int Dim = Felder.get(0).getK().size();
        for(int sp = 0; sp < 2; sp++){
            for(int j=0; j<40 ; j++){
                Feld letztesFeld = Felder.get(Felder.size()-1);
                int[] P = new int[Felder.get(0).getK().size()];
                int[] D = new int[Felder.get(0).getK().size()];

                System.out.println("j="+j);
                for (int i=0;i<Dim;i++){
                    System.out.println("komplett="+(j/(int)(Math.pow(3,i))) % 3);
                    if( (j/(int)(Math.pow(3,i))) % 3 == 0){P[i] = 0;D[i] = 1;}
                    else if((j/(int)(Math.pow(3,i)))%3==1){P[i] = letztesFeld.gC(1);D[i] = 0;}
                    else{P[i] = 4;D[i] = -1;}
                }
                int Ap,Ad, Bp,Bd, Cp,Cd, Dp,Dd;
                if((j/27)%3 == 0){Ap = 0;Ad = 1;}
                else{Ap = letztesFeld.gC(0);Ad = 0;}

                if((j/9)%3 == 0){Bp = 0;Bd = 1;}
                else if((j/9) % 3 == 1){Bp = letztesFeld.gC(1);Bd = 0;}
                else{Bp = 4;Bd = -1;}

                if((j/3)%3 == 0){Cp = 0;Cd = 1;}
                else if((j/3)%3 == 1){Cp = letztesFeld.gC(2);Cd = 0;}
                else{Cp = 4;Cd = -1;}

                if(j%3 == 0){Dp = 0;Dd = 1;}
                else if(j%3 == 1){Dp = letztesFeld.gC(3);Dd = 0;}
                else{Dp = 4;Dd = -1;}

                //kdfhkuhdf
                /*
                if((j/27)%3 == 0){P[0] = 0;D[0] = 1;}
                else{P[0] = letztesFeld.gC(0);D[0] = 0;}

                if((j/9)%3 == 0){P[1] = 0;D[1] = 1;}
                else if((j/9) % 3 == 1){P[1] = letztesFeld.gC(1);D[1] = 0;}
                else{P[1] = 4;D[1] = -1;}

                if((j/3)%3 == 0){P[2] = 0;D[2] = 1;}
                else if((j/3)%3 == 1){P[2] = letztesFeld.gC(2);D[2] = 0;}
                else{P[2] = 4;D[2] = -1;}

                if(j%3 == 0){P[3] = 0;D[3] = 1;}
                else if(j%3 == 1){P[3] = letztesFeld.gC(3);D[3] = 0;}
                else{P[3] = 4;D[3] = -1;}*/

                for(int i=0; i<Felder.size() -1;i++){
                    if(Felder.get(Felder.size() -1).spieler() == sp){
                        if((letztesFeld.gC(0) + D[0] == Felder.get(i).gC(0) && letztesFeld.gC(1) + D[1] == Felder.get(i).gC(1)) && 
                        letztesFeld.gC(2) + D[2] == Felder.get(i).gC(2) && letztesFeld.gC(3) + D[3] == Felder.get(i).gC(3) ||
                        (letztesFeld.gC(0) - D[0] == Felder.get(i).gC(0) && letztesFeld.gC(1) - D[1] == Felder.get(i).gC(1)) && 
                        letztesFeld.gC(2) - D[2] == Felder.get(i).gC(2) && letztesFeld.gC(3) - D[3] == Felder.get(i).gC(3)){

                            for (int m=0;m<Dim;m++){
                                if( (j/(int)(Math.pow(3,m))) % 3 == 0){P[m] = 0;}
                                else if((j/(int)(Math.pow(3,m)))%3==1){P[m] = letztesFeld.gC(1);}
                                else{P[m] = 4;}
                            }
                            int zaehler = 0;
                            for(int k=0;k<5;k++){
                                if(toe.Spielfeld(P[0],P[1],P[2],P[3]) == sp){
                                    zaehler ++;
                                }
                                P[0] += D[0];
                                P[1] += D[1];
                                P[2] += D[2];
                                P[3] += D[3];
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
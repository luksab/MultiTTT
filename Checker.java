import java.util.*;
public class Checker
{
    public Checker()
    {

    }

    public int checkWin(ArrayList<Feld> Felder, TicTacToe toe){
        int gewonnen = -1;
        for(int sp = 0; sp < 2; sp++){
            for(int j=0; j<40 ; j++){
                int Ap,Ad, Bp,Bd, Cp,Cd, Dp,Dd;
                if((j/27)%3 == 0){Ap = 0;Ad = 1;}
                else{Ap = Felder.get(Felder.size()-1).gC(0);Ad = 0;}

                if((j/9)%3 == 0){Bp = 0;Bd = 1;}
                else if((j/9) % 3 == 1){Bp = Felder.get(Felder.size()-1).gC(1);Bd = 0;}
                else{Bp = 4;Bd = -1;}

                if((j/3)%3 == 0){Cp = 0;Cd = 1;}
                else if((j/3)%3 == 1){Cp = Felder.get(Felder.size()-1).gC(2);Cd = 0;}
                else{Cp = 4;Cd = -1;}

                if(j%3 == 0){Dp = 0;Dd = 1;}
                else if(j%3 == 1){Dp = Felder.get(Felder.size()-1).gC(3);Dd = 0;}
                else{Dp = 4;Dd = -1;}
                
                for(int i=0; i<Felder.size() -1;i++){
                    if(Felder.get(Felder.size() -1).spieler() == sp){
                        if((Felder.get(Felder.size() -1).gC(0) + Ad == Felder.get(i).gC(0) && Felder.get(Felder.size() -1).gC(1) + Bd == Felder.get(i).gC(1)) && 
                        Felder.get(Felder.size() -1).gC(2) + Cd == Felder.get(i).gC(2) && Felder.get(Felder.size() -1).gC(3) + Dd == Felder.get(i).gC(3) ||
                        (Felder.get(Felder.size() -1).gC(0) - Ad == Felder.get(i).gC(0) && Felder.get(Felder.size() -1).gC(1) - Bd == Felder.get(i).gC(1)) && 
                        Felder.get(Felder.size() -1).gC(2) - Cd == Felder.get(i).gC(2) && Felder.get(Felder.size() -1).gC(3) - Dd == Felder.get(i).gC(3)){

                            if((j/27)%3 == 0){Ap = 0;}
                            else{Ap = Felder.get(Felder.size()-1).gC(0);}

                            if((j/9)%3 == 0){Bp = 0;}
                            else if((j/9) % 3 == 1){Bp = Felder.get(Felder.size()-1).gC(1);}
                            else{Bp = 4;}

                            if((j/3)%3 == 0){Cp = 0;}
                            else if((j/3)%3 == 1){Cp = Felder.get(Felder.size()-1).gC(2);}
                            else{Cp = 4;}

                            if(j%3 == 0){Dp = 0;}
                            else if(j%3 == 1){Dp = Felder.get(Felder.size()-1).gC(3);}
                            else{Dp = 4;}
                            int zaehler = 0;
                            for(int k=0;k<5;k++){
                                if(toe.Spielfeld(Ap,Bp,Cp,Dp) == sp){
                                    zaehler ++;
                                }
                                Ap += Ad;
                                Bp += Bd;
                                Cp += Cd;
                                Dp += Dd;
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

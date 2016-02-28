import java.util.*;
public class Checker
{
    public Checker()
    {

    }

    public int checkWin(ArrayList<Feld> Felder,int[][][][] Spielfeld){
        int gewonnen = -1;

        for(int sp = 0; sp < 2; sp++){
            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp && Felder.get(Felder.size() -1).A() == Felder.get(i).A() && Felder.get(Felder.size() -1).C() == Felder.get(i).C()){
                    if((Felder.get(Felder.size() -1).B() + 1 == Felder.get(i).B() && Felder.get(Felder.size() -1).D() + 1 == Felder.get(i).D()) || (Felder.get(Felder.size() -1).B() - 1 == Felder.get(i).B() && Felder.get(Felder.size() -1).D() - 1 == Felder.get(i).D())){
                        int Ap = Felder.get(Felder.size()-1).A();
                        int Bp = 0;
                        int Cp = Felder.get(Felder.size()-1).C();
                        int Dp = 0;
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Bp ++;
                            Dp ++;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }

            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp && Felder.get(Felder.size() -1).A() == Felder.get(i).A() && Felder.get(Felder.size() -1).C() == Felder.get(i).C()){
                    if((Felder.get(Felder.size() -1).B() + 1 == Felder.get(i).B() && Felder.get(Felder.size() -1).D() - 1 == Felder.get(i).D()) || (Felder.get(Felder.size() -1).B() - 1 == Felder.get(i).B() && Felder.get(Felder.size() -1).D() + 1 == Felder.get(i).D())){
                        int Ap = Felder.get(Felder.size()-1).A();
                        int Bp = 0;
                        int Cp = Felder.get(Felder.size()-1).C();
                        int Dp = 4;
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Bp ++;
                            Dp = Dp - 1;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }

            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp && Felder.get(Felder.size() -1).A() == Felder.get(i).A() && Felder.get(Felder.size() -1).C() == Felder.get(i).C()){
                    if((Felder.get(Felder.size() -1).B() == Felder.get(i).B())){
                        int Ap = Felder.get(Felder.size()-1).A();
                        int Bp = Felder.get(Felder.size()-1).B();
                        int Cp = Felder.get(Felder.size()-1).C();
                        int Dp = 0;
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Dp ++;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }

            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp && Felder.get(Felder.size() -1).A() == Felder.get(i).A() && Felder.get(Felder.size() -1).C() == Felder.get(i).C()){
                    if((Felder.get(Felder.size() -1).D() == Felder.get(i).D())){
                        int Ap = Felder.get(Felder.size()-1).A();
                        int Bp = 0;
                        int Cp = Felder.get(Felder.size()-1).C();
                        int Dp = Felder.get(Felder.size()-1).D();
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Bp ++;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }

            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp && Felder.get(Felder.size() -1).B() == Felder.get(i).B() && Felder.get(Felder.size() -1).D() == Felder.get(i).D()){
                    if((Felder.get(Felder.size() -1).A() + 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).C() + 1 == Felder.get(i).C()) || (Felder.get(Felder.size() -1).A() - 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).C() - 1 == Felder.get(i).C())){
                        int Ap = 0;
                        int Bp = Felder.get(Felder.size()-1).B();
                        int Cp = 0;
                        int Dp = Felder.get(Felder.size()-1).D();
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Ap ++;
                            Cp ++;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }

            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp && Felder.get(Felder.size() -1).B() == Felder.get(i).B() && Felder.get(Felder.size() -1).D() == Felder.get(i).D()){
                    if((Felder.get(Felder.size() -1).A() + 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).C() - 1 == Felder.get(i).C()) || (Felder.get(Felder.size() -1).A() - 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).C() + 1 == Felder.get(i).C())){
                        int Ap = 0;
                        int Bp = Felder.get(Felder.size()-1).B();
                        int Cp = 4;
                        int Dp = Felder.get(Felder.size()-1).D();
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Ap ++;
                            Cp = Cp - 1;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }

            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp && Felder.get(Felder.size() -1).B() == Felder.get(i).B() && Felder.get(Felder.size() -1).D() == Felder.get(i).D()){
                    if((Felder.get(Felder.size() -1).B() == Felder.get(i).B())){
                        int Ap = Felder.get(Felder.size()-1).A();
                        int Bp = Felder.get(Felder.size()-1).B();
                        int Cp = 0;
                        int Dp = Felder.get(Felder.size()-1).D();
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Cp ++;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }

            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp && Felder.get(Felder.size() -1).B() == Felder.get(i).B() && Felder.get(Felder.size() -1).D() == Felder.get(i).D()){
                    if((Felder.get(Felder.size() -1).C() == Felder.get(i).C())){
                        int Ap = 0;
                        int Bp = Felder.get(Felder.size()-1).B();
                        int Cp = Felder.get(Felder.size()-1).C();
                        int Dp = Felder.get(Felder.size()-1).D();
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Ap ++;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }

            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp && Felder.get(Felder.size() -1).C() == Felder.get(i).C() && Felder.get(Felder.size() -1).D() == Felder.get(i).D()){
                    if((Felder.get(Felder.size() -1).A() + 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).B() + 1 == Felder.get(i).B()) || (Felder.get(Felder.size() -1).A() - 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).B() - 1 == Felder.get(i).B())){
                        int Ap = 0;
                        int Bp = 0;
                        int Cp = Felder.get(Felder.size()-1).C();
                        int Dp = Felder.get(Felder.size()-1).D();
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Ap ++;
                            Bp ++;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }

            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp && Felder.get(Felder.size() -1).C() == Felder.get(i).C() && Felder.get(Felder.size() -1).D() == Felder.get(i).D()){
                    if((Felder.get(Felder.size() -1).A() + 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).B() - 1 == Felder.get(i).B()) || (Felder.get(Felder.size() -1).A() - 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).B() + 1 == Felder.get(i).B())){
                        int Ap = 0;
                        int Bp = 4;
                        int Cp = Felder.get(Felder.size()-1).C();
                        int Dp = Felder.get(Felder.size()-1).D();
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Ap ++;
                            Bp --;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }

            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp && Felder.get(Felder.size() -1).A() == Felder.get(i).A() && Felder.get(Felder.size() -1).B() == Felder.get(i).B()){
                    if((Felder.get(Felder.size() -1).C() + 1 == Felder.get(i).C() && Felder.get(Felder.size() -1).D() + 1 == Felder.get(i).D()) || (Felder.get(Felder.size() -1).C() - 1 == Felder.get(i).C() && Felder.get(Felder.size() -1).D() - 1 == Felder.get(i).D())){
                        int Ap = Felder.get(Felder.size()-1).A();
                        int Bp = Felder.get(Felder.size()-1).B();
                        int Cp = 0;
                        int Dp = 0;
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Cp ++;
                            Dp ++;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }

            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp && Felder.get(Felder.size() -1).A() == Felder.get(i).A() && Felder.get(Felder.size() -1).B() == Felder.get(i).B()){
                    if((Felder.get(Felder.size() -1).C() + 1 == Felder.get(i).C() && Felder.get(Felder.size() -1).D() - 1 == Felder.get(i).D()) || (Felder.get(Felder.size() -1).C() - 1 == Felder.get(i).C() && Felder.get(Felder.size() -1).D() + 1 == Felder.get(i).D())){
                        int Ap = Felder.get(Felder.size()-1).A();
                        int Bp = Felder.get(Felder.size()-1).B();
                        int Cp = 0;
                        int Dp = 4;
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Cp ++;
                            Dp --;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }

            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp && Felder.get(Felder.size() -1).D() == Felder.get(i).D()){
                    if((Felder.get(Felder.size() -1).A() + 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).B() + 1 == Felder.get(i).B()) &&  Felder.get(Felder.size() -1).C() + 1 == Felder.get(i).C()||
                    (Felder.get(Felder.size() -1).A() - 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).B() - 1 == Felder.get(i).B()&& Felder.get(Felder.size() -1).C() - 1 == Felder.get(i).C())){
                        int Ap = 0;
                        int Bp = 0;
                        int Cp = 0;
                        int Dp = Felder.get(Felder.size()-1).D();
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Ap ++;
                            Bp ++;
                            Cp ++;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }
            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp && Felder.get(Felder.size() -1).D() == Felder.get(i).D()){
                    if((Felder.get(Felder.size() -1).A() + 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).B() - 1 == Felder.get(i).B()) && Felder.get(Felder.size() -1).C() - 1 == Felder.get(i).C() ||
                    (Felder.get(Felder.size() -1).A() - 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).B() + 1 == Felder.get(i).B()) && Felder.get(Felder.size() -1).C() + 1 == Felder.get(i).C()){
                        int Ap = 0;
                        int Bp = 4;
                        int Cp = 4;
                        int Dp = Felder.get(Felder.size()-1).D();
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Ap ++;
                            Bp --;
                            Cp --;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }
            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp && Felder.get(Felder.size() -1).D() == Felder.get(i).D()){
                    if((Felder.get(Felder.size() -1).A() + 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).B() + 1 == Felder.get(i).B()) && Felder.get(Felder.size() -1).C() - 1 == Felder.get(i).C() ||
                    (Felder.get(Felder.size() -1).A() - 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).B() - 1 == Felder.get(i).B()) && Felder.get(Felder.size() -1).C() + 1 == Felder.get(i).C()){
                        int Ap = 0;
                        int Bp = 0;
                        int Cp = 4;
                        int Dp = Felder.get(Felder.size()-1).D();
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Ap ++;
                            Bp ++;
                            Cp --;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }

            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp && Felder.get(Felder.size() -1).C() == Felder.get(i).C()){
                    if((Felder.get(Felder.size() -1).A() + 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).B() + 1 == Felder.get(i).B()) &&  Felder.get(Felder.size() -1).D() + 1 == Felder.get(i).D()||
                    (Felder.get(Felder.size() -1).A() - 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).B() - 1 == Felder.get(i).B()&& Felder.get(Felder.size() -1).D() - 1 == Felder.get(i).D())){
                        int Ap = 0;
                        int Bp = 0;
                        int Cp = Felder.get(Felder.size()-1).C();
                        int Dp = 0;
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Ap ++;
                            Bp ++;
                            Dp ++;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }
            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp && Felder.get(Felder.size() -1).C() == Felder.get(i).C()){
                    if((Felder.get(Felder.size() -1).A() + 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).B() - 1 == Felder.get(i).B()) && Felder.get(Felder.size() -1).C() - 1 == Felder.get(i).D() ||
                    (Felder.get(Felder.size() -1).A() - 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).B() + 1 == Felder.get(i).B()) && Felder.get(Felder.size() -1).C() + 1 == Felder.get(i).D()){
                        int Ap = 0;
                        int Bp = 4;
                        int Cp = Felder.get(Felder.size()-1).C();
                        int Dp = 4;
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Ap ++;
                            Bp --;
                            Dp --;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }
            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp && Felder.get(Felder.size() -1).C() == Felder.get(i).C()){
                    if((Felder.get(Felder.size() -1).A() + 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).B() + 1 == Felder.get(i).B()) && Felder.get(Felder.size() -1).D() - 1 == Felder.get(i).D() ||
                    (Felder.get(Felder.size() -1).A() - 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).B() - 1 == Felder.get(i).B()) && Felder.get(Felder.size() -1).D() + 1 == Felder.get(i).D()){
                        int Ap = 0;
                        int Bp = 0;
                        int Cp = Felder.get(Felder.size()-1).D();
                        int Dp = 4;
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Ap ++;
                            Bp ++;
                            Dp --;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }

            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp && Felder.get(Felder.size() -1).B() == Felder.get(i).B()){
                    if((Felder.get(Felder.size() -1).A() + 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).D() + 1 == Felder.get(i).D()) &&  Felder.get(Felder.size() -1).C() + 1 == Felder.get(i).C()||
                    (Felder.get(Felder.size() -1).A() - 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).D() - 1 == Felder.get(i).D()&& Felder.get(Felder.size() -1).C() - 1 == Felder.get(i).C())){
                        int Ap = 0;
                        int Bp = Felder.get(Felder.size()-1).D();
                        int Cp = 0;
                        int Dp = 0;
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Ap ++;
                            Cp ++;
                            Dp ++;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }
            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp && Felder.get(Felder.size() -1).B() == Felder.get(i).B()){
                    if((Felder.get(Felder.size() -1).A() + 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).D() - 1 == Felder.get(i).D()) && Felder.get(Felder.size() -1).C() - 1 == Felder.get(i).C() ||
                    (Felder.get(Felder.size() -1).A() - 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).D() + 1 == Felder.get(i).D()) && Felder.get(Felder.size() -1).C() + 1 == Felder.get(i).C()){
                        int Ap = 0;
                        int Bp = Felder.get(Felder.size()-1).D();
                        int Cp = 4;
                        int Dp = 4;
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Ap ++;
                            Cp --;
                            Dp --;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }
            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp && Felder.get(Felder.size() -1).B() == Felder.get(i).B()){
                    if((Felder.get(Felder.size() -1).A() + 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).D() + 1 == Felder.get(i).D()) && Felder.get(Felder.size() -1).C() - 1 == Felder.get(i).C() ||
                    (Felder.get(Felder.size() -1).A() - 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).D() - 1 == Felder.get(i).D()) && Felder.get(Felder.size() -1).C() + 1 == Felder.get(i).C()){
                        int Ap = 0;
                        int Bp = Felder.get(Felder.size()-1).D();
                        int Cp = 4;
                        int Dp = 0;
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Ap ++;
                            Cp --;
                            Dp ++;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }

            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp && Felder.get(Felder.size() -1).A() == Felder.get(i).A()){
                    if((Felder.get(Felder.size() -1).D() + 1 == Felder.get(i).D() && Felder.get(Felder.size() -1).B() + 1 == Felder.get(i).B()) &&  Felder.get(Felder.size() -1).C() + 1 == Felder.get(i).C()||
                    (Felder.get(Felder.size() -1).D() - 1 == Felder.get(i).D() && Felder.get(Felder.size() -1).B() - 1 == Felder.get(i).B()&& Felder.get(Felder.size() -1).C() - 1 == Felder.get(i).C())){
                        int Ap = Felder.get(Felder.size()-1).D();
                        int Bp = 0;
                        int Cp = 0;
                        int Dp = 0;
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Bp ++;
                            Cp ++;
                            Dp ++;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }
            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp && Felder.get(Felder.size() -1).A() == Felder.get(i).A()){
                    if((Felder.get(Felder.size() -1).D() + 1 == Felder.get(i).D() && Felder.get(Felder.size() -1).B() - 1 == Felder.get(i).B()) && Felder.get(Felder.size() -1).C() - 1 == Felder.get(i).C() ||
                    (Felder.get(Felder.size() -1).D() - 1 == Felder.get(i).D() && Felder.get(Felder.size() -1).B() + 1 == Felder.get(i).B()) && Felder.get(Felder.size() -1).C() + 1 == Felder.get(i).C()){
                        int Ap = Felder.get(Felder.size()-1).D();
                        int Bp = 4;
                        int Cp = 4;
                        int Dp = 0;
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Dp ++;
                            Bp --;
                            Cp --;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }
            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp && Felder.get(Felder.size() -1).A() == Felder.get(i).A()){
                    if((Felder.get(Felder.size() -1).D() + 1 == Felder.get(i).D() && Felder.get(Felder.size() -1).B() + 1 == Felder.get(i).B()) && Felder.get(Felder.size() -1).C() - 1 == Felder.get(i).C() ||
                    (Felder.get(Felder.size() -1).D() - 1 == Felder.get(i).D() && Felder.get(Felder.size() -1).B() - 1 == Felder.get(i).B()) && Felder.get(Felder.size() -1).C() + 1 == Felder.get(i).C()){
                        int Ap = Felder.get(Felder.size()-1).D();
                        int Bp = 0;
                        int Cp = 4;
                        int Dp = 0;
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Dp ++;
                            Bp ++;
                            Cp --;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }

            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp){
                    if((Felder.get(Felder.size() -1).A() + 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).B() + 1 == Felder.get(i).B()) && Felder.get(Felder.size() -1).C() + 1 == Felder.get(i).C() && Felder.get(Felder.size() -1).D() + 1 == Felder.get(i).C() ||
                    (Felder.get(Felder.size() -1).A() - 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).B() - 1 == Felder.get(i).B()) && Felder.get(Felder.size() -1).C() - 1 == Felder.get(i).C() && Felder.get(Felder.size() -1).D() - 1 == Felder.get(i).C()){
                        int Ap = 0;
                        int Bp = 0;
                        int Cp = 0;
                        int Dp = 0;
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Ap ++;
                            Bp ++;
                            Cp ++;
                            Dp ++;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }
            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp){
                    if((Felder.get(Felder.size() -1).A() + 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).B() - 1 == Felder.get(i).B()) && Felder.get(Felder.size() -1).C() - 1 == Felder.get(i).C() && Felder.get(Felder.size() -1).D() - 1 == Felder.get(i).C() ||
                    (Felder.get(Felder.size() -1).A() - 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).B() + 1 == Felder.get(i).B()) && Felder.get(Felder.size() -1).C() + 1 == Felder.get(i).C() && Felder.get(Felder.size() -1).D() + 1 == Felder.get(i).C()){
                        int Ap = 0;
                        int Bp = 4;
                        int Cp = 4;
                        int Dp = 4;
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Ap ++;
                            Bp --;
                            Cp --;
                            Dp --;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }
            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp){
                    if((Felder.get(Felder.size() -1).A() + 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).B() + 1 == Felder.get(i).B()) && Felder.get(Felder.size() -1).C() - 1 == Felder.get(i).C() && Felder.get(Felder.size() -1).D() - 1 == Felder.get(i).C() ||
                    (Felder.get(Felder.size() -1).A() - 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).B() - 1 == Felder.get(i).B()) && Felder.get(Felder.size() -1).C() + 1 == Felder.get(i).C() && Felder.get(Felder.size() -1).D() + 1 == Felder.get(i).C()){
                        int Ap = 4;
                        int Bp = 4;
                        int Cp = 4;
                        int Dp = 4;
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Ap --;
                            Bp --;
                            Cp --;
                            Dp --;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }
            for(int i=0; i<Felder.size() -1;i++){
                if(Felder.get(Felder.size() -1).spieler() == sp){
                    if((Felder.get(Felder.size() -1).A() + 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).B() + 1 == Felder.get(i).B()) && Felder.get(Felder.size() -1).C() + 1 == Felder.get(i).C() && Felder.get(Felder.size() -1).D() - 1 == Felder.get(i).C() ||
                    (Felder.get(Felder.size() -1).A() - 1 == Felder.get(i).A() && Felder.get(Felder.size() -1).B() - 1 == Felder.get(i).B()) && Felder.get(Felder.size() -1).C() - 1 == Felder.get(i).C() && Felder.get(Felder.size() -1).D() + 1 == Felder.get(i).C()){
                        int Ap = 4;
                        int Bp = 4;
                        int Cp = 4;
                        int Dp = 4;
                        int zaehler = 0;
                        for(int j=0;j<5;j++){
                            if(Spielfeld[Ap][Bp][Cp][Dp] == sp){
                                zaehler ++;
                            }
                            Ap --;
                            Bp --;
                            Cp --;
                            Dp --;
                        }
                        if(zaehler == 5)
                        {
                            gewonnen = sp;
                        }
                    }
                }
            }
        }
        return gewonnen;
    }
}

import java.util.*;
public class TicTacToe
{
    public ArrayList<Feld> Felder = new ArrayList<Feld>();
    public int[][][][] Spielfeld;
    MyFrame frame;
    public TicTacToe(MyFrame Neuframe)
    {
        frame = Neuframe;
        Spielfeld = new int[5][5][5][5];
        for (int i=0; i<5; i++ ) 
        {
            for (int j=0;j<5;j++)
            {
                for (int k=0; k<5; k++ ) 
                {
                    for(int l=0;l<5;l++){
                        Spielfeld[i][j][k][l] = -1;
                    }
                }
            }
        }

    }

    public ArrayList getSpielfeld(){
        return Felder;
    }

    public boolean check(int tic,int tac,int toe,int wtf){
        if(tic < 5 && tac < 5 && toe < 5 && wtf < 5 && Spielfeld[tic][tac][toe][wtf] == -1){
            return true;
        }
        else{
            return false;
        }
    }

    public void addFeld(Feld feld){
        Felder.add(feld);
    }

    public String click(int tic,int tac,int toe,int wtf)
    {
        //Spielfeld[tic][tac][toe][wtf] = frame.spieler();
        //Felder.add(new Feld(tic,tac,toe,wtf,frame.spieler()));
        //Feld feld = new Feld(tic,tac,toe,wtf,frame.spieler());
        String s = "Das solltest du nicht sehen.";
        if(checkWin(/*frame.spieler()*/)){
            s = "Spieler "+frame.spieler()+" hat gewonnen!";
        }
        else{
            s = "Spieler "+frame.spieler()+" hat auf das Feld "+tic+"|"+tac+"|"+toe+"|"+wtf+" gesetzt";
        }
        if(frame.spieler() == 0){
            frame.setSpieler(1);
        }
        else{ 
            frame.setSpieler(0);
        }
        return s;
    }

    public String click(Feld feld)
    {
        Spielfeld[feld.A()][feld.B()][feld.C()][feld.D()] = frame.spieler();
        Felder.add(feld);
        String s = "Das solltest du nicht sehen.";
        checkWin();
        if(checkWin()){
            s = "Spieler "+feld.spieler()+" hat gewonnen!";
        }
        else{
            s = "Spieler "+feld.spieler()+" hat auf das Feld "+feld.A()+"|"+feld.B()+"|"+feld.C()+"|"+feld.D()+" gesetzt";
        }
        if(frame.spieler() == 0){
            frame.setSpieler(1);
        }
        else{ 
            frame.setSpieler(0);
        }
        return s;
    }

    public boolean checkWin(){
        for(int sp = 0; sp < 2; sp++){
            System.out.println("Checking"+sp);
            for(int i=0; i<Felder.size() -1;i++){
                System.out.println("Checking 2D "+sp);
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
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
                            System.out.println("Gewonnen hat "+sp); return true; 
                        }
                    }
                }
            }
        }
        return false;
    }

    public int anzahlZüge(){
        return Felder.size();
    }

    public String zug(int zug){
        return Felder.get(zug).toString();
    }

    public void reset(){
        for (int i=0; i<5; i++ ) 
        {
            for (int j=0;j<5;j++)
            {
                for (int k=0; k<5; k++ ) 
                {
                    for(int l=0;l<5;l++){
                        Spielfeld[i][j][k][l] = -1;
                    }
                }
            }
        }
        Felder.clear();
    }

    public void toJText(MyScrollingTextArea output){
        for(int i=0;i<Felder.size();i++){
            output.writeLine(Felder.get(i).toString());
        }
    }
}

import java.util.*;
public class TicTacToe
{
    public ArrayList<Feld> Felder = new ArrayList<Feld>();
    MyFrame frame;
    public TicTacToe(MyFrame Neuframe){
        frame = Neuframe;
    }

    public int Spielfeld(int A,int B,int C,int D){
        for(int i=0;i<Felder.size();i++){
            if(Felder.get(i).A() == A && Felder.get(i).B() == B && Felder.get(i).C() == C && Felder.get(i).D() == D){
                return Felder.get(i).spieler();
            }
        }
        return -1;
    }
    
    public int Spielfeld(Feld feld){
        for(int i=0;i<Felder.size();i++){
            if(Felder.get(i).A() == feld.A() && Felder.get(i).B() == feld.B() && Felder.get(i).C() == feld.C() && Felder.get(i).D() == feld.D()){
                return Felder.get(i).spieler();
            }
        }
        return -1;
    }

    public ArrayList getSpielfeld(){
        return Felder;
    }

    public boolean check(int A,int B,int C,int D){
        if(A < 5 && B < 5 && C < 5 && D < 5 && Spielfeld(A,B,C,D) == -1){
            return true;
        }
        else{
            return false;
        }
    }

    public void addFeld(Feld feld){
        Felder.add(feld);
    }

    public String click(){
        Feld feld = Felder.get(Felder.size() -1);
        String s = "Das solltest du nicht sehen.";
        if(checkWin()){
            s = "Spieler "+feld.spieler()+" hat gewonnen!";
        }
        else{
            s = "Spieler "+feld.spieler()+" hat auf das Feld "+feld.A()+"|"+feld.B()+"|"+feld.C()+"|"+feld.D()+" gesetzt";
        }
        return s;
    }

    public boolean checkWin(){
        Checker checker = new Checker();
        if(checker.checkWin(Felder,frame.toe) != -1){
            return true;
        }
        else {return false;}
    }

    public int anzahlZüge(){
        return Felder.size();
    }

    public String zug(int zug){
        return Felder.get(zug).toString();
    }

    public void reset(){
        Felder.clear();
    }

    public void toJText(MyScrollingTextArea output){
        for(int i=0;i<Felder.size();i++){
            output.writeLine(Felder.get(i).toString());
        }
    }
}

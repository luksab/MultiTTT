import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class TicTacToe
{
    public ArrayList<Feld> Felder = new ArrayList<Feld>();
    private int Dim;
    public TicTacToe(){
        Dim = 4;
    }

    public TicTacToe(int Dim){
        this.Dim = Dim;
    }

    public int Spielfeld(int A,int B,int C,int D){
        for(int i=0;i<Felder.size();i++){
            if(Felder.get(i).gC(0) == A && Felder.get(i).gC(1) == B && Felder.get(i).gC(2) == C && Felder.get(i).gC(3) == D){
                return Felder.get(i).spieler();
            }
        }
        return -1;
    }

    public int Spielfeld(Feld feld){
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

    public ArrayList getSpielfeld(){
        return Felder;
    }

    public boolean check(int A,int B,int C,int D){
        if(Spielfeld(A,B,C,D) == -1){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean check(Feld feld){
        if(feld.getK().size() == Dim){
            try{
                for(int i=0;i<Dim;i++){
                    if(!(feld.gC(i)>=0&&feld.gC(i)<=Dim)){
                        return false;
                    }
                }
                if(Spielfeld(feld) == -1){
                    return true;
                }
                else{
                    return false;
                }
            }
            catch(ArrayIndexOutOfBoundsException e){
                return false;
            }
        }
        else
            return false;
    }

    public void addFeld(Feld feld){
        Felder.add(feld);
    }

    public String click(){
        Feld feld = Felder.get(Felder.size() -1);
        int Dim=feld.getK().size();
        String s = "Das solltest du nicht sehen.";
        if(checkWin()){
            s = "Spieler "+feld.spieler()+" hat gewonnen!";
        }
        else{
            s = "Spieler "+feld.spieler()+" hat auf das Feld ";
            for(int i=0;i<Dim;i++){
                s += feld.gC(i)+"|";
            }
            s += " gesetzt";
        }
        return s;
    }

    public String click(MyFrameSP frame){
        Feld feld = Felder.get(Felder.size() -1);
        int Dim=feld.getK().size();
        String s = "Das solltest du nicht sehen.";
        if(checkWin()){
            s = "Spieler "+feld.spieler()+" hat gewonnen!";
            ArrayList<Feld> felders = getWin();
            for(int i=0;i<felders.size();i++){
                frame.updateButton(felders.get(i),new Color(100,100,100));
            }
        }
        else{
            s = "Spieler "+feld.spieler()+" hat auf das Feld ";
            for(int i=0;i<Dim;i++){
                s += feld.gC(i)+"|";
            }
            s += " gesetzt";
        }
        return s;
    }

    public boolean checkWin(){
        Checker checker = new Checker();
        if(checker.checkWin(Felder)){
            return true;
        }
        else {return false;}
    }

    public ArrayList<Feld> getWin(){
        Checker checker = new Checker();
        return checker.GetWin(Felder);
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

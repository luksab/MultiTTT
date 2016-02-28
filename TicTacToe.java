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

    public String click(Feld feld)
    {
        Spielfeld[feld.A()][feld.B()][feld.C()][feld.D()] = feld.spieler();
        Felder.add(feld);
        String s = "Das solltest du nicht sehen.";
        if(checkWin()){
            s = "Spieler "+(feld.spieler()+1)+" hat gewonnen!";
        }
        else{
            s = "Spieler "+(feld.spieler()+1)+" hat auf das Feld "+feld.A()+"|"+feld.B()+"|"+feld.C()+"|"+feld.D()+" gesetzt";
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
        Checker checker = new Checker();
        if(checker.checkWin(Felder,Spielfeld) != -1){
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

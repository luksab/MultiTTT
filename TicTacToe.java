import java.util.*;
public class TicTacToe
{
    public ArrayList<Feld> Felder = new ArrayList<Feld>();
    public int[][][][] Spielfeld;
    MyFrame frame;
    public TicTacToe(MyFrame frame)
    {
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
        if(checkWin(/*frame.spieler()*/)){
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

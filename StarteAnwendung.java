/*
 * Kreitz, September 2015
 * Startklasse fuer gui-Programme
 */

import java.awt.*;
import javax.swing.*;

public class StarteAnwendung
{
    static MyFrame f;
    static MyFrameSP s;

    public static void main(String[] args){        
        new StarteAnwendung();
    }

    public StarteAnwendung() 
    {
        boolean SpMp = SpMp();
        if(!SpMp){
            int Dim = Dim();
            if(Dim == 2){
                s = new MyFrameSP(); 
                s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        }
        else{
            f = new MyFrame(); 
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

    public boolean SpMp(){
        String message = "Möchtest du ein MultiplayerSpiel spielen?";
        String title = "MultiPlayer?";
        // display the JOptionPane showConfirmDialog
        int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION)
        {
            return true;
        }
        return false;
    }

    public int Dim(){
        Object[] possibleValues = { "Zwei", "Drei", "Vier", "Mehr" };
        Object selectedValue = JOptionPane.showInputDialog(null,
                "Dimensionen:", "Wie viele?",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
        boolean ja = false;
        for(int i=0;i<possibleValues.length;i++){
            if(possibleValues[i] == selectedValue){
                ja = true;
                return i;
            }
        }
        if(!ja){
            return Dim();
        }
        return -1;
    }
}

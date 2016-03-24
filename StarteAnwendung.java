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
        if(SpMp){
            f = new MyFrame(); 
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        else{
            s = new MyFrameSP(); 
            s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
}

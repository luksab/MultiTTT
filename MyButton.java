import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class MyButton extends JButton
{
    private final int breite;
    private final int hoehe = 25;
    private final int dxy = 10;
    private final Color farbe = new Color(173,216,230);
    private final ArrayList<Integer> Koord = new ArrayList<Integer>();
    
    MyButton(int x,int y,int breite,int hoehe)
    {
        this.breite = breite;
        this.setForeground(Color.black);
        this.setBackground(farbe);
        this.setLocation(y, x);
        this.setSize(breite, hoehe);
        this.setHorizontalAlignment(CENTER);    
    }
    
    MyButton(int x,int y,int breite,int hoehe,ArrayList<Integer> Koord)
    {
        this.breite = breite;
        this.setForeground(Color.black);
        this.setBackground(farbe);
        this.setLocation(y, x);
        this.setSize(breite, hoehe);
        this.setHorizontalAlignment(CENTER);
        for(int i=0;i<Koord.size();i++){
            this.Koord.add(Koord.get(i));
        }
    }
    
    public ArrayList<Integer> gK(){
        return Koord;
    }
    
        public void update(String s){
        this.setText(s);      
    }

    public void update(Color bg){
        this.setBackground(bg);      
    }

}
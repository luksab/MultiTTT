import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 

public class MyButton extends JButton
{
    private final int breite;
    private final int hoehe = 25;
    private final int dxy = 10;
    private final Color farbe = new Color(173,216,230);;

    MyButton(int zeile, String s, int breite)
    {
        this.breite = breite;
        this.setForeground(Color.black);
        this.setBackground(farbe);
        this.setLocation(dxy, dxy + (zeile-1) *(hoehe+dxy));
        this.setSize(breite, hoehe);
        this.setHorizontalAlignment(CENTER);
        this.setText(s);      
    }

    MyButton(int x,int y,String s,int breite,int hoehe)
    {
        this.breite = breite;
        this.setForeground(Color.black);
        this.setBackground(farbe);
        this.setLocation(y, x);
        this.setSize(breite, hoehe);
        this.setHorizontalAlignment(CENTER);
        this.setText(s);      
    }
    
    MyButton(int x,int y,int breite,int hoehe)
    {
        this.breite = breite;
        this.setForeground(Color.black);
        this.setBackground(farbe);
        this.setLocation(y, x);
        this.setSize(breite, hoehe);
        this.setHorizontalAlignment(CENTER);    
    }

    public void update(int x,int y,String s,int breite,int hoehe){
        this.setLocation(x, y);
        this.setSize(breite, hoehe);
        this.setHorizontalAlignment(CENTER);
        this.setText(s);      
    }
    
        public void update(String s){
        this.setText(s);      
    }

    public void update(Color bg){
        this.setBackground(bg);      
    }

}
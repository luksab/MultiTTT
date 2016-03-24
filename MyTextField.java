import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
 
public class MyTextField extends JTextField
{

   static final String blank = " ";
   private Font myFont;
   
   static final int hoehe = 25;
   static final int x0 = 20;
   static final int y0 = 20;
   static final int dy = 10;   
 
   MyTextField( )
   {
      this.setForeground(Color.black);
      this.setBackground(Color.white);
      myFont = new Font("Monospaced",0,12);  
      this.setFont(myFont);
      this.setHorizontalAlignment(JTextField.LEFT);
      this.setText("");
      this.setEditable(true);       
   }
   
   MyTextField(int zeile, int breite)
   {
      this.setForeground(Color.black);
      this.setBackground(Color.white);
      this.setLocation(x0, y0 + zeile *(hoehe+dy));
      this.setSize(breite, hoehe);      
      myFont = new Font("Monospaced",0,12);  
      this.setFont(myFont);
      this.setHorizontalAlignment(JTextField.CENTER);
      this.setText("");
      this.setEditable(true);       
   }
   
   public void clear( )
   {
       this.setText("");
   } 
   
   public boolean isInteger( )
   {
       boolean ergebnis;
       try
       {
           String s = this.getText( ).trim( );
           int zahl = Integer.parseInt(s);
           ergebnis = true;
       }
       catch(java.lang.NumberFormatException e)
       {
           ergebnis = false;
       }
       return ergebnis;
   }
   
   public int readInteger( )
   {
      String s = this.getText( ).trim( );
      int zahl = Integer.parseInt(s);
      return zahl;
   }
  
   public String readString( )
   {
      return this.getText( ).trim( );
   }

   public boolean isLong( )
   {
       boolean ergebnis;
       try
       {
           String s = this.getText( ).trim( );
           long zahl = Long.parseLong(s);
           ergebnis = true;
       }
       catch(java.lang.NumberFormatException e)
       {
           ergebnis = false;
       }
       return ergebnis;
   }
   
   public long readLong( )
   {
      String s = this.getText( ).trim( );
      long zahl = Long.parseLong(s);
      return zahl;
   }
   
 public boolean isDouble( )
   {
       boolean ergebnis;

       String s = this.getText( ).trim( );    
       // Ersetze ggf. Dezimalkomma durch Dezimalpunkt
       s = s.replace(',','.');
       this.setText(s);   
           
       try
       {
           double zahl = Double.parseDouble(s);
           ergebnis = true;
       }
       catch(java.lang.NumberFormatException e)
       {
           ergebnis = false;
       }
       return ergebnis;
   }
   
   public double readDouble( )
   {
      String s = this.getText( ).trim( );
      double zahl = Double.parseDouble(s);
      return zahl;
   }
   
   /*
    * Eine ganze Zahl wird rechtsbuendig eingetragen
    */
   public void writeInt(int i, int breite)
   {
       String s;
       s = String.valueOf(i);
       while(s.length( ) < breite)
           s = blank + s; 
       this.setText(s);
   }
   
   /*
    * Eine ganze Zahl wird eingetragen
    */
   public void writeInt(int i)
   {
       String s;
       s = String.valueOf(i);
       this.setText(s);
   }   
   
   /*
    * Eine Text wird eingetragen
    */
   public void write(String s)
   {
       s = blank + s; 
       this.setText(s);
   }   
   
   
}


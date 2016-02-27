/**
 * HBG - Kreitz
 * Datum: Juni 2010
 * 
 */
 
import java.awt.*; 
import javax.swing.*; 
 
public class MyScrollingTextArea extends JScrollPane
{
    private final String newLine = "\n";
    private final String blank = " ";
    private Font myFont;
    
    
    private JTextArea ausgabe;

    /**
    *   Konstruktor fuer ein scrollendes Ausgabefeld.
    */
           
    MyScrollingTextArea(String title)
    {   
        this.setBorder(BorderFactory.createTitledBorder(title));
        ausgabe = new JTextArea();
        ausgabe.setForeground(Color.black);
        ausgabe.setBackground(Color.white);
        myFont = new Font("Monospaced",0,12);  
        ausgabe.setFont(myFont);
        ausgabe.setEditable(false);
        ausgabe.setText("");      
        this.setViewportView(ausgabe);
     }   
         
    /**
    *   Das Ausgabefeld wird geloescht
    */
    public void clear( )
    {
        ausgabe.setText("");
    }
  
    /**
    *   Der String s wird an der Cursorposition geschrieben.
    */
    public void write(String s)
    {
        ausgabe.append(s);
    }

    /**
    *   Der String s wird rechtsbuendig in ein Feld der Breite
    *   breite geschrieben.
    */
    public void write(String s, int breite)
    {
        while(s.length( ) < breite)
        s = blank + s;       
        ausgabe.append(s);
    }   

    /**
    *   Der String s wird an der Cursorposition geschrieben.
    *   Der Cursor wird an den Anfang der naechsten Zeile gesetzt.
    */
    public void writeLine(String s)
    {
        ausgabe.append(s);
        ausgabe.append(newLine);
    }
   
    /**
    *   Der Cursor wird an den Anfang der naechsten Zeile gesetzt.
    */
    public void newLine( )
    {
        ausgabe.append(newLine);
    }
  
    /**
    *   Die ganze Zahl zahl wird an der Cursorposition geschrieben.
    *   Hinter die Zahl wird eine Leerstelle geschrieben.
    */
    public void writeInt(int zahl)
    {
        String s = String.valueOf(zahl);
        ausgabe.append(s);
    } 
   
    /**
    *   Die ganze Zahl zahl wird rechtsbuendig in ein Feld der Breite
    *   breite geschrieben.
    */
    public void writeInt(int zahl, int breite)
    {
        String s = String.valueOf(zahl);
        while(s.length( ) < breite)
        s = blank + s;
        ausgabe.append(s);
    }  
   
    /**
    *   Die Gleitkommazahl zahl wird an der Cursorposition geschrieben.
    */
    public void writeDouble(double zahl)
    {
        String s = String.valueOf(zahl);
        ausgabe.append(s);
    } 
    
    /**
     * Der Ausgabebildschirmird geleert.
     * Dann wird der Stringarray zeilenweise ausgegeben
     */
   public void showStrings(String[] s, int max)
   {
       ausgabe.setText(blank);
       ausgabe.append(newLine);
       for (int i=0; i<max; i++)
          ausgabe.append(s[i] + newLine);
   }
   
   public JTextArea getTextArea()
   {
      return this.ausgabe;    
   }
   
} 
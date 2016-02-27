/*
 * Kreitz, September 2015
 * Startklasse fuer gui-Programme
 */

import java.awt.*;
import javax.swing.*;

public class StarteAnwendung
{
   static MyFrame f;
    
   public StarteAnwendung() 
   {
       f = new MyFrame(); 
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}

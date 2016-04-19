import java.awt.*;
import javax.swing.*;

public class StarteSP
{
    static MyFrameSP s;
    static MyFrameMulti m;

    public static void main(String[] args){        
        new StarteSP();
    }

    public StarteSP() 
    {
        int Dim = Dim();
        if(Dim < 3){
            int Dimensionen = Dim+2;
            s = new MyFrameSP(Dimensionen); 
            s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        else{
            int Dimensionen = DimMehr();
            m = new MyFrameMulti(Dimensionen); 
            m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

    public int DimMehr(){
        int ans = Integer.parseInt( JOptionPane.showInputDialog(null,"Dimensionen","Wie Viele denn Genau?",JOptionPane.INFORMATION_MESSAGE));
        return ans;
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

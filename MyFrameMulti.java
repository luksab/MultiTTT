import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class MyFrameMulti extends JFrame implements ActionListener
{
    final int dxy = 10;

    final String progName = "TicTacToe";
    final Color farbeHintergrund = new Color(200,200,200);

    MyButton button;
    MyScrollingTextArea output;
    TicTacToe toe;
    ArrayList<MyTextField> inputs = new ArrayList<MyTextField>();

    JMenuBar menueLeiste;
    JMenu datei;
    JMenu TTT;
    JMenuItem beenden;
    JMenuItem showAll;
    JMenuItem reset;

    private int spieler = 0;
    private int ich = 0;
    private int du;
    public long anzahlZüge = 0;
    private boolean darfIch = true;
    private boolean SpielZuende = false;
    private boolean angemeldet = false;
    private JScrollPane scroll;
    private JPanel workspace;
    private int width;
    private int height;
    private int Dim;

    private int xMax;
    private int yMax;
    private int xMitte;
    private int breiteLinks = 200 - 2*dxy;
    private int breiteRechts;
    private int hoeheRechts;
    private SimpleKI KI;

    public MyFrameMulti(int Dim)
    {   
        this.Dim = Dim;
        KI = new SimpleKI(Dim);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        width = gd.getDisplayMode().getWidth();
        height = gd.getDisplayMode().getHeight()-50;
        xMax = width;
        breiteRechts = 390;
        yMax = height - (50+dxy);
        xMitte = xMax - (breiteRechts+dxy+dxy);
        hoeheRechts = yMax;

        this.setTitle(progName);
        this.setSize(width,height);
        this.setLocation(0,0);
        Container cp;
        cp = getContentPane();
        cp.setLayout(null);
        cp.setBackground(farbeHintergrund);

        output = new MyScrollingTextArea("Ausgabe");
        output.setLocation(xMitte,0);
        output.setSize(breiteRechts,hoeheRechts); 
        cp.add(output);

        JPanel workspace = new JPanel(null);
        workspace.setBounds(0,0, 935, (Dim*35)+(2*dxy));
        workspace.setPreferredSize(new Dimension(935,(Dim*35)+(2*dxy)));

        toe = new TicTacToe(Dim);

        for(int i=0;i<Dim;i++){
            inputs.add(new MyTextField(i,xMitte-100));
            inputs.get(i).addActionListener(this);
            workspace.add(inputs.get(i));
        }
        button = new MyButton(dxy,xMitte-75,50,Dim*35);
        button.addActionListener(this);
        workspace.add(button);

        scroll = new JScrollPane(workspace);
        scroll.setSize(xMitte,yMax);
        cp.add(scroll);

        menueLeiste = new JMenuBar();
        datei = new JMenu("Datei");
        TTT = new JMenu("TTT");
        beenden = new JMenuItem("beenden");
        beenden.addActionListener(this);
        reset = new JMenuItem("reset");
        reset.addActionListener(this);
        showAll = new JMenuItem("Zeige Alle Züge");
        showAll.addActionListener(this);
        menueLeiste.add(datei);
        menueLeiste.add(TTT);
        datei.add(beenden);
        datei.add(reset);
        TTT.add(showAll);
        this.setJMenuBar(menueLeiste);

        this.setVisible(true);
    } 

    public static void main(String[] args){        
        new MyFrameMulti(4);
    }

    public void actionPerformed(ActionEvent event)
    {        
        if (event.getSource() == beenden){
            this.dispose();
        }
        if (event.getSource() == showAll){
            toe.toJText(output);
        }
        if (event.getSource() == reset){
            reset();
        }
        if (event.getSource()==button){
            ArrayList<Integer> Koord = new ArrayList<Integer>();
            for(int i=0;i<Dim;i++){
                MyTextField k = inputs.get(i);
                if(k.isInteger()){
                    int z = k.readInteger();
                    Koord.add(z);
                }
            }
            Feld feld = new Feld (Koord,ich);
            if (toe.check(feld) && darfIch && !SpielZuende){
                toe.Felder.add(feld);
                anzahlZüge++;
                if(toe.Felder.size() > 0){
                    output.writeLine(toe.click());
                    if(toe.checkWin()){
                        SpielZuende = true;
                        darfIch = false;
                    }
                }
                if(!SpielZuende){
                    darfIch = false;
                    feld = KI.setze(toe);
                    feld.setSpieler(1);
                    toe.Felder.add(feld);
                    anzahlZüge++;
                    if(toe.Felder.size() > 0){
                        output.writeLine(toe.click());
                        if(toe.checkWin()){
                            SpielZuende = true;
                            darfIch = false;
                        }
                    }
                    darfIch = true;
                }
            }
            else {
                String s = "Du kannst nicht in das Feld ";
                for(int i=0;i<Dim;i++){
                    MyTextField k = inputs.get(i);
                    s += inputs.get(i).readString()+"|";
                }
                s += " setzen!";
                output.writeLine(s);
            }
        } 
    }

    public void reset(){
        anzahlZüge = 0;     
        darfIch = true;
        SpielZuende = false;
        output.clear();
        toe.reset();
    }
} 
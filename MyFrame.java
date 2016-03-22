import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class MyFrame extends JFrame implements ActionListener
{
    final int dxy = 10;

    final String progName = "TicTacToe";
    final Color farbeHintergrund = new Color(200,200,200);
    ArrayList<Color> farbeSpieler = new ArrayList<Color>();

    MyScrollingTextArea output;
    MyButton button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9;
    ArrayList<MyButton> Buttons = new ArrayList<MyButton>();
    TicTacToe toe;

    JMenuBar menueLeiste;
    JMenu datei;
    JMenu TTT;
    JMenuItem beenden;
    JMenuItem showAll;
    JMenuItem resetOutput;
    JMenuItem ChPW;
    JMenuItem DelFire;

    private int spieler = 0;
    private int ich = 0;
    private int du;
    public long anzahlZüge = 0;
    private boolean darfIch = true;
    private boolean SpielZuende = false;
    private boolean angemeldet = false;
    private String Email;
    private String SpielerIch;
    private String SpielerDu;
    private JScrollPane scroll;
    private JPanel workspace;
    private int width;
    private int height;

    private int xMax;
    private int yMax;
    private int xMitte;
    private int breiteLinks = 200 - 2*dxy;
    private int breiteRechts;
    private int hoeheRechts;

    public MyFrame()
    {   
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        width = gd.getDisplayMode().getWidth();
        height = gd.getDisplayMode().getHeight()-50;
        xMax = width;
        breiteRechts = 390;
        yMax = height - (50+dxy);
        xMitte = xMax - (breiteRechts+dxy+dxy);
        hoeheRechts = yMax;
        
        farbeSpieler.add(new Color(200,0,200));
        farbeSpieler.add(new Color(200,200,0));
        farbeSpieler.add(new Color(173,216,230));

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
        workspace.setBounds(0,0, 935, 935);
        workspace.setPreferredSize(new Dimension(935,935));

        toe = new TicTacToe(this);

        for(int i=0;i<625;i++) {
            Buttons.add(new MyButton(dxy + (35*(i/25)+(10*(int)((i/25)/5))),dxy + (35*(i%25)+(10*(int)((i%25)/5))),30,30));
            Buttons.get(i).addActionListener(this);
            workspace.add(Buttons.get(i));
        }

        scroll = new JScrollPane(workspace);
        scroll.setSize(xMitte,yMax);
        cp.add(scroll);

        menueLeiste = new JMenuBar();
        datei = new JMenu("Datei");
        TTT = new JMenu("TTT");
        beenden = new JMenuItem("beenden");
        beenden.addActionListener(this);
        showAll = new JMenuItem("Zeige Alle Züge");
        showAll.addActionListener(this);
        resetOutput = new JMenuItem("reset Ausgabe");
        resetOutput.addActionListener(this);
        menueLeiste.add(datei);
        menueLeiste.add(TTT);
        datei.add(beenden);
        TTT.add(showAll);
        TTT.add(resetOutput);
        this.setJMenuBar(menueLeiste);

        this.setVisible(true);
    } 

    public static void main(String[] args){        
        new MyFrame();
    }

    public void actionPerformed(ActionEvent event)
    {        
        if (event.getSource() == beenden){
            this.dispose();
        }
        if (event.getSource() == showAll){
            toe.toJText(output);
        }
        if (event.getSource() == resetOutput){
            output.clear();
        }
        for(int i=0;i<625;i++){
            if (event.getSource()==Buttons.get(i)){
                if (toe.check((i%25)/5,(i%5),(i/25)/5,((i/25)%5)) && darfIch && !SpielZuende){
                    ArrayList<Integer> Koord = new ArrayList<Integer>();
                    Koord.add((i%25)/5);
                    Koord.add(i%5);
                    Koord.add((i/25)/5);
                    Koord.add((i/25)%5);
                    Feld feld = new Feld (Koord,ich);
                    updateButton(feld);
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
                        BilloKI KI = new BilloKI();
                        feld = KI.setze(toe);
                        feld.setSpieler(1);
                        updateButton(feld);
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
                    String s = "Du kannst nicht in das Feld "+(i%25)/5+"|"+(i%5)+"|"+(i/25)/5+"|"+((i/25)%5)+" setzen";
                    output.writeLine(s);
                }
                break;
            }
        } 
    }

    public void reset(){
        this.setSize(xMax,yMax);
        for(int i=0;i<625;i++) {
            Buttons.get(i).update(new Color(173,216,230));
        }
        output.clear();
        toe.reset();
    }

    public void updateButton(Feld feld){
        Buttons.get((125*feld.gC(2))+(25*feld.gC(3))+(5*feld.gC(0))+feld.gC(1)).update(
            farbeSpieler.get(
                feld.spieler()));
    }
} 
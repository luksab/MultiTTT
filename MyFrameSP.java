import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class MyFrameSP extends JFrame implements ActionListener
{
    final int dxy = 10;

    final String progName = "TicTacToe";
    final Color farbeHintergrund = new Color(200,200,200);
    ArrayList<Color> farbeSpieler = new ArrayList<Color>();

    MyScrollingTextArea output;
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
    private SimpleKI KI = new SimpleKI();
    private int Dim;

    public MyFrameSP(int Dim)
    {   
        this.Dim = Dim;
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

        toe = new TicTacToe(Dim);

        if(Dim == 4){
            int xB=0;
            int yB=0;
            xB=935;
            yB=935;
            workspace.setBounds(0,0, xB, yB);
            workspace.setPreferredSize(new Dimension(xB,yB));
            int i = 0;
            for(int a=0;a<Dim+1;a++){
                for(int b=0;b<Dim+1;b++){
                    for(int c=0;c<Dim+1;c++){
                        for(int d=0;d<Dim+1;d++){
                            ArrayList<Integer> Koord = new ArrayList<Integer>();
                            Koord.add(a);
                            Koord.add(b);
                            Koord.add(c);
                            Koord.add(d);
                            Buttons.add(new MyButton(dxy + ((35*a)+((35*5+dxy)*b)),dxy + ((35*c)+((35*5+dxy)*d)),30,30,Koord));
                            Buttons.get(i).addActionListener(this);
                            workspace.add(Buttons.get(i));
                            i++;
                        }
                    }
                }
            }
        }

        else if(Dim == 3){
            int xB=0;
            int yB=0;
            xB=935;
            yB=300;
            workspace.setBounds(0,0, xB, yB);
            workspace.setPreferredSize(new Dimension(xB,yB));
            int i = 0;
            int b = 0;
            for(int a=0;a<Dim+1;a++){
                for(int c=0;c<Dim+1;c++){
                    for(int d=0;d<Dim+1;d++){
                        ArrayList<Integer> Koord = new ArrayList<Integer>();
                        Koord.add(a);
                        Koord.add(c);
                        Koord.add(d);
                        Buttons.add(new MyButton(dxy + ((35*a)+((35*(Dim+1)+dxy)*b)),dxy + ((35*c)+((35*(Dim+1)+dxy)*d)),30,30,Koord));
                        Buttons.get(i).addActionListener(this);
                        workspace.add(Buttons.get(i));
                        i++;
                    }
                }
            }
        }

        else if(Dim == 2){
            int xB=0;
            int yB=0;
            xB=350;
            yB=350;
            workspace.setBounds(0,0, xB, yB);
            workspace.setPreferredSize(new Dimension(xB,yB));
            int i = 0;
            for(int a=0;a<Dim+1;a++){
                for(int b=0;b<Dim+1;b++){
                    ArrayList<Integer> Koord = new ArrayList<Integer>();
                    Koord.add(a);
                    Koord.add(b);
                    Buttons.add(new MyButton(dxy + (105*a),dxy + (105*b),100,100,Koord));
                    Buttons.get(i).addActionListener(this);
                    workspace.add(Buttons.get(i));
                    i++;
                }
            }
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
        new MyFrameSP(4);
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
            MyButton Button = Buttons.get(i);
            if (event.getSource()==Button){
                ArrayList<Integer> Koord = Button.gK();
                Feld feld = new Feld (Koord,ich);
                System.out.println("F:"+feld);
                if (toe.check(feld) && darfIch && !SpielZuende){
                    updateButton(feld);
                    toe.Felder.add(feld);
                    anzahlZüge++;
                    if(toe.Felder.size() > 0){
                        output.writeLine(toe.click());
                        if(toe.checkWin()){
                            SpielZuende = true;
                            darfIch = false;
                        }
                        else if(toe.Felder.size() >= Math.pow(Dim+1,Dim)){
                            SpielZuende = true;
                            output.writeLine("UnEntSchieden");
                        }
                    }
                    if(!SpielZuende){
                        darfIch = false;
                        System.out.println(""+KI.setze(toe));
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
                    String s = "Du kannst nicht in das Feld ";
                    for(int y=0;y<Dim;y++){
                        s += feld.gC(y)+"|";
                    }
                    s += " setzen!";
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
        System.out.println("F:"+feld);
        int i = 0;
        int v = Dim;
        for(int y=0;y<Dim;y++){
            v--;
            i += Math.pow(Dim+1,v)*feld.gC(y);
        }
        Buttons.get(i).update(farbeSpieler.get(feld.spieler()));
    }
} 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.InetSocketAddress;

public class MyFrame extends JFrame implements ActionListener
{
    final int dxy = 10;
    String[] Login = LoginBox();

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

    //Websocket
    private Client socket;
    private int spieler = 0;
    public  int ich = -1;
    private int du;
    private int TesteNachSpieler = -1;
    private int spiel = -1;
    public long anzahlZüge = 0;
    private boolean darfIch = false;
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
        farbeSpieler.add(new Color(255,0,255));
        farbeSpieler.add(new Color(255,255,0));

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

        toe = new TicTacToe();

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
        DelFire = new JMenuItem("DelFire");
        DelFire.addActionListener(this);
        beenden = new JMenuItem("beenden");
        beenden.addActionListener(this);
        showAll = new JMenuItem("Zeige Alle Züge");
        showAll.addActionListener(this);
        ChPW = new JMenuItem("Ändere Passwort");
        ChPW.addActionListener(this);
        resetOutput = new JMenuItem("reset Ausgabe");
        resetOutput.addActionListener(this);
        menueLeiste.add(datei);
        menueLeiste.add(TTT);
        datei.add(beenden);
        datei.add(DelFire);
        TTT.add(showAll);
        TTT.add(resetOutput);
        TTT.add(ChPW);
        this.setJMenuBar(menueLeiste);

        this.setVisible(true);

        //connect To Websocket Server
        try{
            //socket = new Client(new URI("ws://"+Login[0]+""), this);}
            socket = new Client(new URI("ws://37.201.48.224:8887"), this);}
        catch(URISyntaxException e){}
        //eventListener

    }

    public String[] LoginBox(){
        JTextField EMail = new JTextField();
        JTextField Server = new JTextField();
        JPasswordField Passwd = new JPasswordField();
        Object[] message = {"Server",Server,"E-Mail", EMail, 
                "Passwort", Passwd};
        Server.setText("luksab.de:8887");

        JOptionPane pane = new JOptionPane( message, 
                JOptionPane.PLAIN_MESSAGE, 
                JOptionPane.OK_CANCEL_OPTION);
        pane.createDialog(null, "Login").setVisible(true);
        String[] Login = new String[3];
        if(!Server.equals("")){
            Login[0] = Server.getText();
            Login[1] = EMail.getText();
            Login[2] = new String(Passwd.getPassword());
            return Login;
        }
        else{
            return LoginBox();
        }

    }

    public String[] PasswdChangeBox(){
        JTextField PWAlt = new JTextField();
        JTextField PWNeu = new JTextField();
        Object[] message = {"Altes Passwort", PWAlt, 
                "Neues Passwort", PWNeu};

        JOptionPane pane = new JOptionPane( message, 
                JOptionPane.PLAIN_MESSAGE, 
                JOptionPane.OK_CANCEL_OPTION);
        pane.createDialog(null, "FireBase PW Change").setVisible(true);
        String[] Login = new String[2];
        Login[0] = PWAlt.getText();
        Login[1] = PWNeu.getText();
        return Login;
    }

    public static void main(String[] args) throws URISyntaxException{        
        new MyFrame();
    }

    public void actionPerformed(ActionEvent event)
    {        
        if (event.getSource() == beenden){
            if(SpielerDu == "Leer"){
                //fireSpiel.setValue(null);
            }
            else{
                //fireSpiel.child("Spieler "+ich).setValue("Leer");
            }
            this.dispose();
        }
        if (event.getSource() == showAll){
            toe.toJText(output);
        }
        if (event.getSource() == resetOutput){
            output.clear();
        }
        if (event.getSource() == ChPW){
            String[] PW = PasswdChangeBox();
            /*fire.changePassword(Email, PW[0], PW[1],new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
            output.writeLine("Hat geklappt");
            }

            @Override
            public void onError(FirebaseError firebaseError) {
            output.writeLine(""+firebaseError);
            }
            });*/
        }
        for(int i=0;i<625;i++){
            if (event.getSource()==Buttons.get(i)){
                if (toe.check((i%25)/5,(i%5),(i/25)/5,((i/25)%5)) && ich > -1 /*&& darfIch && !SpielZuende*/){
                    ArrayList<Integer> Koord = new ArrayList<Integer>();
                    Koord.add((i%25)/5);
                    Koord.add(i%5);
                    Koord.add((i/25)/5);
                    Koord.add((i/25)%5);
                    socket.send("P "+Koord.get(0)+" "+Koord.get(1)+" "+Koord.get(2)+" "+Koord.get(3)+" "+ich);
                    //fireSpiel.child("Feld").child(""+anzahlZüge).setValue(new Feld (Koord,ich));
                    darfIch = false;
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
        toe.addFeld(feld);
        Buttons.get((125*feld.gC(2))+(25*feld.gC(3))+(5*feld.gC(0))+feld.gC(1)).update(farbeSpieler.get(feld.spieler()+3));
        if(toe.Felder.size() > 1){
            Feld f = toe.Felder.get(toe.Felder.size()-2);
            Buttons.get((125*f.gC(2))+(25*f.gC(3))+(5*f.gC(0))+f.gC(1)).update(farbeSpieler.get(f.spieler()));
        }
        String s = "ID: "+feld.getSpieler()+"Field: "+feld.gC(0)+" "+feld.gC(1)+" "+feld.gC(2)+" "+feld.gC(3);
        output.writeLine(s);
        if(toe.checkWin()){
            ich = -1;
            ArrayList<Feld> felder = toe.getWin();
            output.writeLine(felder.get(0).getSpieler() + " won");
            for(Feld fld : felder){
                Buttons.get((125*fld.gC(2))+(25*fld.gC(3))+(5*fld.gC(0))+fld.gC(1)).update(new Color(100,100,100));
            }
        }
        System.out.println("setze Button was called");
    }
} 
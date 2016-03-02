import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import com.firebase.client.*;

public class MyFrame extends JFrame implements ActionListener
{
    String[] Login = LoginBox();
    final int xMax = 1345;
    final int yMax = 990;
    final int dxy = 10;

    final int xMitte = 925;
    final int breiteLinks = 200 - 2*dxy;
    final int breiteRechts = 400 - 2*dxy;
    final int hoeheRechts = 930 - 2*dxy;

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

    private Firebase fire;
    private Firebase fireSpiel;
    private int spieler = 0;
    private int ich = -1;
    private int du;
    private int TesteNachSpieler = -1;
    private int spiel = -1;
    public long anzahlZüge = 0;
    private boolean darfIch = false;
    private boolean SpielZuende = false;
    private String Email;
    private String SpielerIch;
    private String SpielerDu;

    public MyFrame()
    {   
        farbeSpieler.add(new Color(200,0,200));
        farbeSpieler.add(new Color(200,200,0));
        farbeSpieler.add(new Color(173,216,230));

        this.setTitle(progName);
        this.setSize(xMax,yMax);
        this.setLocation(35,35);
        Container cp;
        cp = getContentPane();
        cp.setLayout(null);
        cp.setBackground(farbeHintergrund);

        output = new MyScrollingTextArea("Ausgabe");
        output.setLocation(xMitte + dxy,dxy);
        output.setSize(breiteRechts,hoeheRechts); 
        cp.add(output);

        toe = new TicTacToe(this);

        for(int i=0;i<625;i++) {
            Buttons.add(new MyButton(dxy + (35*(i/25)+(10*(int)((i/25)/5))),dxy + (35*(i%25)+(10*(int)((i%25)/5))),30,30));
            Buttons.get(i).addActionListener(this);
            this.add(Buttons.get(i));
        }

        menueLeiste = new JMenuBar();
        datei = new JMenu("Datei");
        TTT = new JMenu("TTT");
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
        TTT.add(showAll);
        TTT.add(resetOutput);
        TTT.add(ChPW);
        this.setJMenuBar(menueLeiste);

        this.setVisible(true);

        fire = new Firebase("https://blistering-fire-5630.firebaseIO.com/");

        fire.authWithPassword(Login[0], Login[1], new Firebase.AuthResultHandler() {
                @Override
                public void onAuthenticated(AuthData authData) {
                    System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                    Email = ""+authData.getProviderData().get("email");
                }

                @Override
                public void onAuthenticationError(FirebaseError firebaseError) {
                    output.writeLine(""+firebaseError);
                    //System.out.println("FireBase is kaputt"+firebaseError);
                }
            });

        //eventListener           

        //listen Once
        fire.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    boolean breaking = false;
                    for(int i=0;i<snapshot.child("Spiele").getChildrenCount()+1;i++){
                        for(int j=0;j<2;j++){
                            if(snapshot.child("Spiele").child(""+i).child("Spieler "+j).getValue() == null){
                                System.out.println("I:"+i);
                                fireSpiel = new Firebase("https://blistering-fire-5630.firebaseIO.com/Spiele/"+i);
                                fireSpiel.child("Spieler "+j).setValue(Email);
                                ich = j;
                                if(ich == 0){
                                    du  = 1;
                                }
                                else{ 
                                    du = 0;
                                }
                                spiel=i;
                                breaking = true;
                                break;
                            }
                        }
                        if(breaking){break;}
                    }
                    output.writeLine("Du bist im Spiel "+spiel+", als Spieler "+ich+" gelandet.");
                    fireSpiel.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                SpielerIch = snapshot.child("Spieler "+ich).getValue(String.class);
                                SpielerDu = snapshot.child("Spieler "+du).getValue(String.class);
                                if(toe.Felder.size() +1 == snapshot.child("Feld").getChildrenCount()){
                                    Feld feld = snapshot.child("Feld").child(""+toe.Felder.size()).getValue(Feld.class);
                                    updateButton(feld);
                                    toe.Felder.add(feld);
                                    toe.Spielfeld[feld.A()][feld.B()][feld.C()][feld.D()] = feld.spieler();
                                    anzahlZüge = snapshot.child("Feld").getChildrenCount();
                                    if(toe.Felder.size() > 0){
                                        output.writeLine(toe.click());
                                        if(toe.checkWin()){
                                            SpielZuende = true;
                                            darfIch = false;
                                        }
                                    }
                                }
                                else if(snapshot.child("Feld").getChildrenCount() == toe.Felder.size()){}
                                else{
                                    for(int i=0;i<625;i++) {
                                        Buttons.get(i).update(farbeSpieler.get(2));
                                    }
                                    for (int i=0; i<5; i++ ) 
                                    {
                                        for (int j=0;j<5;j++)
                                        {
                                            for (int k=0; k<5; k++ ) 
                                            {
                                                for(int l=0;l<5;l++){
                                                    toe.Spielfeld[i][j][k][l] = -1;
                                                }
                                            }
                                        }
                                    }
                                    toe.Felder.clear();
                                    anzahlZüge = snapshot.child("Feld").getChildrenCount();
                                    for(int i=0;i<snapshot.child("Feld").getChildrenCount();i++){
                                        Feld feld = snapshot.child("Feld").child(""+i).getValue(Feld.class);
                                        updateButton(feld);
                                        toe.Felder.add(feld);
                                        toe.Spielfeld[feld.A()][feld.B()][feld.C()][feld.D()] = feld.spieler();
                                    }
                                }
                                if(toe.Felder.size() > 0){
                                    if(toe.Felder.get(toe.Felder.size() - 1).spieler() != ich){darfIch = true;}
                                }
                                if(ich == 0 && toe.Felder.size() == 0){darfIch = true;}
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {
                                System.out.println("The read failed: " + firebaseError.getMessage());
                            }
                        });
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    System.out.println("The read failed: " + firebaseError.getMessage());
                }
            });

    } 

    public String[] LoginBox(){
        JTextField EMail = new JTextField();
        JTextField Passwd = new JTextField();
        Object[] message = {"E-Mail", EMail, 
                "Passwort", Passwd};

        JOptionPane pane = new JOptionPane( message, 
                JOptionPane.PLAIN_MESSAGE, 
                JOptionPane.OK_CANCEL_OPTION);
        pane.createDialog(null, "FireBase Login").setVisible(true);
        String[] Login = new String[2];
        Login[0] = EMail.getText();
        Login[1] = Passwd.getText();
        return Login;
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

    public static void main(String[] args){        
        new MyFrame();
    }

    public void actionPerformed(ActionEvent event)
    {        
        if (event.getSource() == beenden){
            if(SpielerDu == "Leer"){
                fireSpiel.setValue(null);
            }
            else{
                fireSpiel.child("Spieler "+ich).setValue("Leer");
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
            fire.changePassword(Email, PW[0], PW[1],new Firebase.ResultHandler() {
                    @Override
                    public void onSuccess() {
                        output.writeLine("Hat geklappt");
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        output.writeLine(""+firebaseError);
                    }
                });
        }
        for(int i=0;i<625;i++){
            if (event.getSource()==Buttons.get(i)){
                if (toe.check((i%25)/5,(i%5),(i/25)/5,((i/25)%5)) && darfIch && !SpielZuende){
                    fireSpiel.child("Feld").child(""+anzahlZüge).setValue(new Feld((i%25)/5,(i%5),(i/25)/5,((i/25)%5),ich));
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
        Buttons.get((125*feld.C())+(25*feld.D())+(5*feld.A())+feld.B()).update(farbeSpieler.get(feld.spieler()));
        System.out.println("setze Button was called");
    }
} 
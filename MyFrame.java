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

    BilloKI KI;

    JMenuBar menueLeiste;
    JMenu datei;
    JMenu TTT;
    JMenuItem reset;
    JMenuItem beenden;
    JMenuItem showAll;
    JMenuItem resetOutput;
    JMenuItem KIBattle;
    JMenuItem DelFire;

    private Firebase fire;
    private Firebase fireFeld;
    private int spieler = 0;
    private int ich;
    private int du ;
    private int TesteNachSpieler = -1;
    public long anzahlZüge = 0;

    public MyFrame()
    {   

        farbeSpieler.add(new Color(200,0,200));
        farbeSpieler.add(new Color(200,200,0));

        KI = new BilloKI();

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
        reset = new JMenuItem("reset");
        reset.addActionListener(this);
        beenden = new JMenuItem("beenden");
        beenden.addActionListener(this);
        showAll = new JMenuItem("Zeige Alle Züge");
        showAll.addActionListener(this);
        KIBattle = new JMenuItem("KI Kampf");
        KIBattle.addActionListener(this);
        DelFire = new JMenuItem("Delete FireBaseSave");
        DelFire.addActionListener(this);
        resetOutput = new JMenuItem("reset Ausgabe");
        resetOutput.addActionListener(this);
        menueLeiste.add(datei);
        menueLeiste.add(TTT);
        datei.add(reset);
        datei.add(beenden);
        TTT.add(showAll);
        TTT.add(resetOutput);
        TTT.add(KIBattle);
        TTT.add(DelFire);
        this.setJMenuBar(menueLeiste);

        this.setVisible(true);

        fire = new Firebase("https://blistering-fire-5630.firebaseIO.com/");
        fireFeld = new Firebase("https://blistering-fire-5630.firebaseIO.com/Feld");
        
            
        fire.authWithPassword(Login[0], Login[1], new Firebase.AuthResultHandler() {
                @Override
                public void onAuthenticated(AuthData authData) {
                    System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
                }

                @Override
                public void onAuthenticationError(FirebaseError firebaseError) {
                    System.out.println("FireBase is kaputt"+firebaseError);
                }
            });

        //reset Feld
        fire.child("Feld").setValue(null);

        //eventListener           
            
            fireFeld.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    anzahlZüge = snapshot.getChildrenCount();
                    System.out.println("There are " + snapshot.child("Feld").getChildrenCount() + " Felder");
                    if(snapshot.child(String.valueOf(snapshot.getChildrenCount() - 1)).exists()){
                        Feld feld = (snapshot.child(String.valueOf(snapshot.getChildrenCount() - 1)).getValue(Feld.class));
                        if(snapshot.child(String.valueOf(snapshot.getChildrenCount() - 1)).getValue(Feld.class).spieler() == ich && feld.spieler() == ich){
                            spieler = du;
                        }
                        else if(snapshot.child(String.valueOf(snapshot.getChildrenCount() - 1)).exists()){
                            spieler = ich;
                        }
                        setze(feld);
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    System.out.println("The read failed: " + firebaseError.getMessage());
                }
            });
            
            
        //listen Once
        fire.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    System.out.println("Listen Once Called");
                    if(snapshot.child("SpielerAnzahl").getValue(int.class) == 0){
                        output.writeLine("Du bist Spieler 1");
                        ich = 0;
                        du  = 1;
                        fire.child("SpielerAnzahl").setValue(1);
                    }
                    if(snapshot.child("SpielerAnzahl").getValue(int.class) == 1){
                        output.writeLine("Du bist Spieler 2");
                        ich = 1;
                        du  = 0;
                        fire.child("SpielerAnzahl").setValue(2);
                    }
                    if(snapshot.child("SpielerAnzahl").getValue(int.class) == 2){
                        output.writeLine("Das Spiel ist voll");
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                }
            });

    } 
    
    public String[] LoginBox(/*String[] bla*/){

		// Erstellung Array vom Datentyp Object, Hinzufügen der Komponenten		
		JTextField EMail = new JTextField();
		JTextField Passwd = new JTextField();
                Object[] message = {"E-Mail", EMail, 
        		"Passwort", Passwd};

                JOptionPane pane = new JOptionPane( message, 
                                                JOptionPane.PLAIN_MESSAGE, 
                                                JOptionPane.OK_CANCEL_OPTION);
                pane.createDialog(null, "FireBase Login").setVisible(true);

                //System.out.println("Eingabe: " + EMail.getText() + ", " + Passwd.getText());
        String[] Login = new String[2];
        Login[0] = EMail.getText();
        Login[1] = Passwd.getText();
		return Login;
                //System.exit(0);
	}

    public static void main(String[] args){        
        new MyFrame();
    }

    public void actionPerformed(ActionEvent event)
    {        
        if (event.getSource() == reset){
            reset();
        }
        if (event.getSource() == beenden){
            this.dispose();
        }
        if (event.getSource() == showAll){
            toe.toJText(output);
        }
        if (event.getSource() == resetOutput){
            output.clear();
        }
        if (event.getSource() == DelFire){
            fire.setValue(null);
        }
        if (event.getSource() == KIBattle){
            Checker checker = new Checker();
            for(int i=0;i<625;i++){
                setze(KI.setze(toe));
                if(checker.checkWin(toe.Felder,toe.Spielfeld) != -1){break;}
            } 
        }
        for(int i=0;i<625;i++){
            if (event.getSource()==Buttons.get(i)){
                if (toe.check((i%25)/5,(i%5),(i/25)/5,((i/25)%5)) && ich == spieler){
                    fire.child("Feld").child(""+anzahlZüge).setValue(new Feld((i%25)/5,(i%5),(i/25)/5,((i/25)%5),spieler));
                }
                else {
                    String s = "Du kannst nicht in das Feld "+(i%25)/5+"|"+(i%5)+"|"+(i/25)/5+"|"+((i/25)%5)+" setzen";
                    output.writeLine(s);
                }
                break;
            }
        } 
    }

    public void setzeButton(Feld feld){
        Buttons.get((125*feld.C())+(25*feld.D())+(5*feld.A())+feld.B()).update(farbeSpieler.get(spieler));
        System.out.println("setze Button was called");
    }

    public void setze(Feld feld){
        if(toe.check(feld.A(),feld.B(),feld.C(),feld.D())){
            setzeButton(feld);
            String s = toe.click(feld);
            output.writeLine(s);
        }
        else {
            String s = "Du kannst nicht in das Feld "+feld.A()+"|"+feld.B()+"|"+feld.C()+"|"+feld.D()+" setzen";
            output.write(s);
            output.newLine();
        }
    }

    public int spieler(){
        return spieler;
    }

    public void setSpieler(int Neuspieler){
        fire.child("Spieler").setValue(spieler);
    }

    public void reset(){
        this.setSize(xMax,yMax);
        for(int i=0;i<625;i++) {
            Buttons.get(i).update(new Color(173,216,230));
        }
        output.clear();
        toe.reset();
    }
} 
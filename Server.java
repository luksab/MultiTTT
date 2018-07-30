import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.io.Console;
import java.util.*;
import java.io.IOException;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
/**
 * MDTTTWebsocketServer
 * 
 * @author Lukas Sabatschus
 */
public class Server extends WebSocketServer
{
    TicTacToe toe;
    ArrayList<SClient> clients = new ArrayList<SClient>();
    public Server(InetSocketAddress address)
    {
        super(address);
        toe = new TicTacToe();
        this.run();
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        conn.send("Welcome to the server!"); //This method sends a message to the new client
        broadcast( "new connection: " + handshake.getResourceDescriptor() ); //This method sends a message to all clients connected
        clients.add(new SClient(handshake.getResourceDescriptor()));
        if(clients.size() == 1)
            conn.send("ID 0");
        else if(clients.size() == 2)
            conn.send("ID 1");
        else
            conn.send("ID -1");
        for(Feld f:toe.Felder)
            conn.send("P "+f.gC(0)+" "+f.gC(1)+" "+f.gC(2)+" "+f.gC(3)+" "+f.getSpieler());
        System.out.println("new connection to " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("received message from "	+ conn.getRemoteSocketAddress() + ": " + message);
        System.out.println(message.split(" ")[0]);
        if(message.split(" ")[0].equals("P")){
            Feld f = new Feld(Integer.parseInt(message.split(" ")[1]),Integer.parseInt(message.split(" ")[2]),
                    Integer.parseInt(message.split(" ")[3]),Integer.parseInt(message.split(" ")[4]));
            f.setSpieler(Integer.parseInt(message.split(" ")[5]));
            if(toe.check(f)){
                toe.addFeld(f);
                broadcast(message);
            }
        }
    }

    @Override
    public void onMessage( WebSocket conn, ByteBuffer message ) {
        System.out.println("received ByteBuffer from "	+ conn.getRemoteSocketAddress());
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.err.println("an error occured on connection " + conn.getRemoteSocketAddress()  + ":" + ex);
    }

    @Override
    public void onStart() {
        System.out.println("server started successfully");
    }

    public static void main(String[] args) throws IOException{
        String host = "luksab.de";
        int port = 8887;        
        WebSocketServer server = new Server(new InetSocketAddress(port));
        server.run();
    }
}

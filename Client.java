import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

public class Client extends WebSocketClient {
    MyFrame frame;

    public Client(URI serverUri, Draft draft) {
        super(serverUri, draft);
        this.connect();
    }

    public Client(URI serverURI, MyFrame f) {
        super(serverURI);
        frame = f;
        this.connect();
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        send("Hello, it is me. Mario :)");
        System.out.println("new connection opened");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("closed with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received message: " + message);
        if(message.split(" ")[0].equals("P")){
            Feld f = new Feld(Integer.parseInt(message.split(" ")[1]),Integer.parseInt(message.split(" ")[2]),
                    Integer.parseInt(message.split(" ")[3]),Integer.parseInt(message.split(" ")[4]));
            f.setSpieler(Integer.parseInt(message.split(" ")[5]));
            frame.updateButton(f);
        }
        if(message.split(" ")[0].equals("ID")){
            frame.ich = Integer.parseInt(message.split(" ")[1]);
        }
    }

    @Override
    public void onMessage(ByteBuffer message) {
        System.out.println("received ByteBuffer");
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("an error occurred:" + ex);
    }

}
package iostream;

import java.io.IOException;
import java.net.Socket;

public class ClientSocketMain {
    public static void main(String[] args) {
        Socket clientSocket;
        try {
            clientSocket = new Socket("192.168.0.127", 9999);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

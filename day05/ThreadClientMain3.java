import java.io.IOException;
import java.net.Socket;

public class ThreadClientMain3 {
    public static void main(String[] args) {
        Socket clientSocket;
        try {
            clientSocket = new Socket("192.168.0.127", 80);

            ReadThread rt = new ReadThread(clientSocket);
            rt.start();


            WriteThread wt = new WriteThread(clientSocket);
            wt.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

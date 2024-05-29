import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadServerMain {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(80);
            // static으로 저장하면 전달 안해줘도 된다.
            Socket[] socketArray = new Socket[10];
            ReadWriteThread[] rwt = new ReadWriteThread[10];

            int index = 0;
            while (socketArray[9] == null) {
                socketArray[index] = serverSocket.accept();
                System.out.println(socketArray[index]);

                rwt[index] = new ReadWriteThread(socketArray, index);
                rwt[index].start();

                index++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;


public class ReadThread extends Thread {
    Socket clientSocket;
    InputStream inputStream;
    InputStreamReader reader;
    BufferedReader br;


    public ReadThread(Socket clientSocket) {
        try {
            this.clientSocket = clientSocket;
            this.inputStream = clientSocket.getInputStream();
            this.reader = new InputStreamReader(inputStream, "UTF-8");
            this.br = new BufferedReader(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while(true) {
            String result;

            try {
                result = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(result.split("/")[0].equals("getFriends")) {
                System.out.println(result.split("/")[1]);
                continue;
            }
            System.out.print("");
            System.out.println(result);
        }
    }

}

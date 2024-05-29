import java.io.*;
import java.net.Socket;
import java.util.Arrays;

// 서버 전용 쓰레드 (클라이언트의 메시지를 받아서 받은 클라이언트를 제외한 모든 클라이언트에 메시지를 보내줌)
public class ReadWriteThread extends Thread {
    Socket clientSocket;
    InputStream inputStream;
    InputStreamReader reader;
    BufferedReader br;
    OutputStream outputStream;
    PrintStream printStream;
    Socket[] sockets;
    int index;

    // (소켓 배열, 나)
    public ReadWriteThread(Socket[] sockets, int index) {
        try {
            this.index = index;
            this.sockets = sockets;
            this.clientSocket = sockets[index];
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

            // 보낼때는 나를 제외하고
            System.out.print(clientSocket.getPort() + ": ");
            System.out.println(result);
            for(Socket s : sockets) {
                if(s == null) {
                    continue;
                }
                if(!s.equals(clientSocket)) {
                    try {
                        outputStream = s.getOutputStream();
                        printStream = new PrintStream(outputStream);
                        printStream.println(clientSocket.getPort() + ": " + result);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }
    }
}

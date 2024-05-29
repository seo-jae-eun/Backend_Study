import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class WriteThread extends Thread {
    Socket clientSocket;
    OutputStream outputStream;
    PrintStream printStream;
    Scanner sc;

    public WriteThread(Socket clientSocket) {
        try {
            this.clientSocket = clientSocket;
            this.outputStream = clientSocket.getOutputStream();
            this.printStream = new PrintStream(outputStream);
            this.sc = new Scanner(System.in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void run() {
        while(true) {
            System.out.print("메시지 입력: ");
            String message = sc.nextLine();
            printStream.println(message);
        }
    }

    public void outputName(String name) {
        printStream.print("getFriends/");
        printStream.println(name);
    }

}

package iostream;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketMain {
    public static void main(String[] args) {
        // 소켓 통신 : 남의 컴퓨터에 실행중인 남의 프로그램과의 통로를 열어주는 스트림
        //      OSI 4계층까지 알아서 해주는 애
        // 서버 소켓
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(9999);
            Socket clientSocket = serverSocket.accept(); // 하나의 서버에 여러 개 클라이언트 접속 가능
            System.out.println(clientSocket.getInetAddress());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

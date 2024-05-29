import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class kakaoServer {
    public static void main(String[] args) {
// 서버 소켓
        ServerSocket serverSocket;

        InputStream inputStream;
        InputStreamReader reader;
        // Scanner와 유사
        BufferedReader br;

        OutputStream outputStream;
        PrintStream printStream;

        FileInputStream fileInputStream;

        try {
            // 80번 포트로 서버 실행
            serverSocket = new ServerSocket(80);

            // 클라이언트가 접속하면 접속을 허용하는 코드
            // clientSocket에 클라이언트와의 연결 정보를 저장
            Socket clientSocket = serverSocket.accept();

            inputStream = clientSocket.getInputStream();
            reader = new InputStreamReader(inputStream, "UTF-8");
            br = new BufferedReader(reader);
            Scanner sc = new Scanner(System.in);

            outputStream = clientSocket.getOutputStream();
            printStream = new PrintStream(outputStream);

            while(true) {
                String result;
                result = br.readLine(); // 한 줄만 읽음

                System.out.println(result);
//                br.close();


                System.out.print("메시지 입력: ");
                String message = sc.nextLine();
                printStream.println(message);

//                printStream.close();
//                clientSocket.close();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

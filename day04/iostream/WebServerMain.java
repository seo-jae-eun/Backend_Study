package iostream;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServerMain {
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

            String result;
            result = br.readLine(); // 한 줄만 읽음
            
            String total = "";

            // 파일과의 통로를 열어서 바이트 단위로 데이터를 읽어오는 객체
            fileInputStream = new FileInputStream("c:\\zz\\" + result.split(" ")[1].replace("/", ""));
            // 글자 단위로 데이터를 읽어오는 스트림, UTF-8과 같은 글자 인코딩 형식 지정 가능
            InputStreamReader fileReader = new InputStreamReader(fileInputStream, "UTF-8");
            // 데이터를 하나 읽고 바로 하나를 처리하는게 아니라 버퍼에 모았다가 일정 크기가 되면 처리
            BufferedReader fbr = new BufferedReader(fileReader);

            String fileResult;

            while ((fileResult = fbr.readLine()) != null) {
                total = total + fileResult;
            }
            fbr.close();




            System.out.println(total);
            outputStream = clientSocket.getOutputStream();
            printStream = new PrintStream(outputStream);

            printStream.println("HTTP/1.1 200 OK");
            printStream.println("Content-Length: ");
            printStream.println("Content-Type: text/html");
            printStream.println("");
            printStream.println(total);
            printStream.println("");

            printStream.close();
            clientSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

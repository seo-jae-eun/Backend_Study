package iostream;

import java.io.*;
import java.net.Socket;

public class WebBrowser {
    public static void main(String[] args) {
        Socket clientSocket;
        try {
            clientSocket = new Socket("www.naver.com", 80);
//            clientSocket = new Socket("192.168.0.127", 80);

            OutputStream outputStream;
            PrintStream printStream;

            InputStream inputStream;
            InputStreamReader reader;
            BufferedReader br;

            // 아웃풋스트림으로
            outputStream = clientSocket.getOutputStream();
            printStream = new PrintStream(outputStream);

            // GET / HTTP/1.1
            // Host: www.naver.com
            printStream.println("GET /test.html HTTP/1.1");
            printStream.println("Host: www.naver.com");
//            printStream.println("Host: 192.168.0.127");
            printStream.println("");

            
            // 인풋스트림으로
            inputStream = clientSocket.getInputStream();
            // 데이터를 받아와서
            reader = new InputStreamReader(inputStream, "UTF-8");
            br = new BufferedReader(reader);
            String result;
            String total = "";

            while((result = br.readLine()) != null) {
                total = total + result + "\n";
            }
            System.out.println(total);
            // 화면에 출력
            printStream.println(total);


            br.close();
            reader.close();
            inputStream.close();
            printStream.close();
            outputStream.close();
            clientSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

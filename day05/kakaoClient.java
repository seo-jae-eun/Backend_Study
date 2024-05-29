import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class kakaoClient {
    public static void main(String[] args) {
        Socket clientSocket;
        try {
            clientSocket = new Socket("192.168.0.127", 80);

            OutputStream outputStream;
            PrintStream printStream;

            InputStream inputStream;
            InputStreamReader reader;
            BufferedReader br;

            // 아웃풋스트림으로
            outputStream = clientSocket.getOutputStream();
            printStream = new PrintStream(outputStream);

            inputStream = clientSocket.getInputStream();
            // 데이터를 받아와서
            reader = new InputStreamReader(inputStream, "UTF-8");
            br = new BufferedReader(reader);
            Scanner sc = new Scanner(System.in);


            while(true) {
                System.out.print("메시지 입력: ");
                String message = sc.nextLine();
                printStream.println(message);


                // 인풋스트림으로
                String result;
                result = br.readLine();
                System.out.println(result);


//                br.close();
//                reader.close();
//                inputStream.close();
//                printStream.close();
//                outputStream.close();
//                clientSocket.close();
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

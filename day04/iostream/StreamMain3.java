package iostream;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class StreamMain3 {
    public static void main(String[] args) {
        // 파일과의 통로를 열어서 바이트 단위로 데이터를 읽어오는 객체
        FileInputStream fileInputStream;
        // 글자 단위로 데이터를 읽어오는 스트림, UTF-8과 같은 글자 인코딩 형식 지정 가능
        InputStreamReader reader;
        // 데이터를 하나 읽고 바로 하나를 처리하는게 아니라 버퍼에 모았다가 일정 크기가 되면 처리
        BufferedReader br;

        StringBuilder stringBuilder;
        try {
            fileInputStream = new FileInputStream("c:\\zz\\test.html");
            reader = new InputStreamReader(fileInputStream, "UTF-8");
            br = new BufferedReader(reader);
            String result;
            String total = "";

            while((result = br.readLine()) != null) {
                total = total + result;
            }

            System.out.println(total);

            br.close();
            reader.close();
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

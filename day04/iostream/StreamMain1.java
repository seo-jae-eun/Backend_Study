package iostream;

import java.io.*;

public class StreamMain1 {
    public static void main(String[] args) {
        // 입출력 스트림
        // 스트림 = 통로, 하드웨어 장치와의 통로(모니터, 랜카드, 하드디스크)

        // 파일 스트림
        // 파일과의 통로를 열어서 바이트 단위로 데이터를 읽어오는 객체
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        // 글자 단위로 데이터를 읽어오는 스트림, UTF-8과 같은 글자 인코딩 형식 저장 가능
        InputStreamReader reader;
        // 데이터를 하나 읽고 바로 하나를 처리하는게 아니라 버퍼에 모았다가 일정 크기가 되면 처리
        BufferedReader br;

        try {
            fileInputStream = new FileInputStream(args[0]); // 통로를 열기만 함
            // read()는 1바이트를 읽음(아스키코드)
//            Integer result = fileInputStream.read();
//            System.out.println(result);
//
//            result = fileInputStream.read();
//            System.out.println(result);


            // 한번에 읽어오기 (방법2 - 한글가능)
            reader = new InputStreamReader(fileInputStream, "UTF-8");
            br = new BufferedReader(reader);
            // -> BufferedReader를 사용하는 것이 Scanner보다 빠름
            
            int result;
            while((result = br.read()) != -1) {
                System.out.print((char)result);
            }


            // 한번에 읽어오기 (방법1.StreamMain2)
//            byte[] result = fileInputStream.readAllBytes();
//
            // 다른 파일에 출력시키기
//            FileOutputStream fileOutputStream = new FileOutputStream(args[1]);
//            for(int i = 0; i < result.length; i++) {
//                System.out.print((char)result[i]);
//                fileOutputStream.write(result[i]);
//            }
            br.close();
            reader.close();
            fileInputStream.close();
            // -> 열 때랑 역순으로 닫아주기
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            // 예외 발생과 상관없이 무조건 실행됨
        }
    }
}

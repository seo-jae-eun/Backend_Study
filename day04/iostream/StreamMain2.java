package iostream;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class StreamMain2 {
    public static void main(String[] args) {

        FileInputStream fileInputStream;

        try {
            fileInputStream = new FileInputStream("c:\\zzz\\test.txt");
            byte[] result;
            result = fileInputStream.readAllBytes();

            for (int i = 0; i < result.length; i++) {
                System.out.print((char)result[i]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

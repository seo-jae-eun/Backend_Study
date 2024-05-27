// 다른 패키지에 있는 클래스를 사용할 때는
// import 폴더.폴더.클래스이름; 해서 사용
import qwer.PackageTest;

public class Ex06 {
    // 패키지
    public static void main(String[] args) {
        PackageTest pt;
        pt = new PackageTest();

        pt.str = "test";
    }
}

public class Ex07 {
    // 상속
    public static void main(String[] args) {
        // 상속
        // 하나의 클래스에 만든 변수 메소드를
        // 다른 클래스에 그대로 똑같이 만들어주는 것

        Parents p01;
        p01 = new Parents();
        p01.밥먹는다();
        // p01.부동산;

        Child c01;
        c01 = new Child();
        c01.밥먹는다();
        c01.부동산 = "강남 빌딩";
    }
}

public class Ex02 {
    public static void main(String[] args) {
        // 인터페이스
        //      일반적인 메소드는 없고 추상 메소드로만 이루어져있다. (일반적으로 변수도 잘 안만든다.)
        //      인터페이스로 직접 객체를 생성할 수 없고 구현체를 만들어서 객체를 생성

        InterfaceImplements1 il01 = new InterfaceImplements1();
        il01.method01();
        il01.method02();

        InterfaceImplements2 il02 = new InterfaceImplements2();
        il02.method01();
        il02.method02();

        // 다형성 (다운 캐스팅)
        InterfaceTest it01 = new InterfaceImplements1();
        it01.method01();
        it01.method02();

        it01 = new InterfaceImplements2();
        it01.method01();
        it01.method02();
    }
}

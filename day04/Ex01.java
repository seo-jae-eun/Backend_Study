public class Ex01 {
    public static void main(String[] args) {
        // 추상 클래스 -> 잘 안씀, 인터페이스를 많이 씀 -> 틀만 만들어주는 것
        // 추상 메소드를 포함하고 있는 클래스
        // 추상 메소드 : 선언부만 있고 구현부는 없는 메소드

        AbstractClass ac01 = new AbstractClass() { // 구현 가능하지만 잘 안씀
            @Override
            void method02() {

            }
        };

        AbstractChildClass acc01 = new AbstractChildClass();
        acc01.method01();
        acc01.method02();

        AbstractChildClass2 acc02 = new AbstractChildClass2();
        acc02.method01();
        acc02.method02();

    }
}

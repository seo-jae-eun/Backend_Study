public class StaticEx {
    Integer num01;
    static Integer num02;

    void method01() {
        System.out.println(num01);
        System.out.println(num02);
        System.out.println("그냥 메소드");
    }

    static void method02() {
//        객체를 생성하고 사용하는게 아니기 때문에
//        객체 안에 있는 변수를 사용할 수 없다.
//        System.out.println(num01);
        System.out.println(num02);
        System.out.println("static 메소드");
    }
}

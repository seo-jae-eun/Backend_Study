public class Ex10 {
    // singleton
    public static void main(String[] args) {
        // Singleton클래스의 생성자에 private이 달려있어서
        // 객체 생성을 할 수 없음
        // Singleton s;
        // s = new Singleton();

        Singleton s1;
        s1 = Singleton.getObject();
        System.out.println(s1);

        Singleton s2;
        s2 = Singleton.getObject();
        System.out.println(s2);
    }
}

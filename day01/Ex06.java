public class Ex06 {
    public static void main(String[] args) {
        Pokemon p1;
        p1 = new Pokemon();

        p1.name = "피카츄";
        p1.hp = 100;
        p1.level = 10;

        // 메소드 사용하는 법
        // 객체가저장된변수이름.메소드이름();
        p1.attack();
        p1.attack();
        p1.attack();

        System.out.println(p1.hp);
        System.out.println(p1.eat());
        System.out.println(p1.hp);

    }
}

public class Ex01 {
    // 메소드 매개변수
    public static void main(String[] args) {
        Student s01;
        s01 = new Student();

        s01.name = "s01";
        s01.age = 10;
        s01.wis = 0;
        s01.sul = 10;

        Integer result;
        result = s01.study(3);

        System.out.println(result);

        System.out.println(s01.sul);
        s01.drink("소주", 1);
        System.out.println(s01.sul);
        s01.drink("맥주", 3);
        System.out.println(s01.sul);
    }
}

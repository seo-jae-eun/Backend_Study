public class Ex04 {
    // static
    public static void main(String[] args) {
        StaticEx se01;
        se01 = new StaticEx();
        se01.num01 = 10;

        StaticEx se02;
        se02 = new StaticEx();
        se02.num01 = 20;

        StaticEx.num02 = 30;

        se01.method01();
        se02.method01();

        StaticEx.method02();



    }
}

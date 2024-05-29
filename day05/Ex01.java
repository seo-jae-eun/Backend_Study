public class Ex01 {
    public static void main(String[] args) {
        // 쓰레드를 만드는 방법
        // 1. Thread 클래스를 상속받아서 만드는 방법
        // 2. Runnable 인터페이스의 구현체를 만들어서 사용하는 방법


        Thread01 t01 = new Thread01();
        Runnable r02 = new Thread02();
        Thread t02 = new Thread(r02);

        t01.start();
        t02.start();
        
        while(true) {
            System.out.println("메인");
        }
    }
}

public class Thread02 implements Runnable {
    @Override
    public void run() {
        while(true) {
            System.out.println("쓰레드02");
        }
    }
}

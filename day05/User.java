import java.net.Socket;

public class User {
    String name;
    String[] friends;
    Socket socket;

    public User(String name, Socket socket) {
        this.name = name;
        this.socket = socket;
    }

//    String[] showFrieds() {
//        return friends;
//    }
}

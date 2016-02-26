package za.co.ipay;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by F4742443 on 2016/02/24.
 */
public class CreateConnection {

    public static Socket socket;
    private static  String HOST = "www.bizswitch.net";

    public static Socket getSocket() throws IOException {
        socket = new Socket(HOST, 8879);
        return socket;
    }
}

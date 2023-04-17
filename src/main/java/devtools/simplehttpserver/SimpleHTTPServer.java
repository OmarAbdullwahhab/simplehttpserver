/*

 */
package devtools.simplehttpserver;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author omar
 */
public class SimpleHTTPServer {

    public static String rootFolder = "htdocs";

    private static ExecutorService exec = Executors.newFixedThreadPool(100);

    ////folder -> html files.
    ////u1 test1 --> response 2 minutes --> 
    ////u2 test2 --> response 5 seconds --> 
    public static void main(String[] args) throws IOException {
        if (args != null && args.length > 0) {

            rootFolder = args[0];
        }
        System.out.println("Welcome to SimpleHTTP Server");
        ServerSocket server = new ServerSocket(9090);
        System.out.println("Server started on port: " + server.getLocalPort());
        RequestHandler handler = new RequestHandler();
        while (true) {
            Socket client = server.accept();

            exec.execute(new ClientHandler(client,handler));

        }
    }
}

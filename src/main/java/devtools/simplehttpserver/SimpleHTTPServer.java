/*

*/
package devtools.simplehttpserver;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author omar
 */
public class SimpleHTTPServer {

    public static String rootFolder = "htdocs";

    ////folder -> html files.
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
            var is = client.getInputStream();
            PrintStream os = new PrintStream(client.getOutputStream());
            StringBuilder sb = new StringBuilder();
            char c = (char) is.read();
            while (is.available() > 0) {
                sb.append(c);
                c = (char) is.read();
            }
            System.out.println(sb.toString());
            HttpRequest req = new HttpRequest(sb.toString());
            var page = handler.handle(req);
            HttpResponse res = null;
            if (page != null && page.length() > 0) {
              
                res = new HttpResponse(HttpStatusCodes.SUCESS);
            } else {
                res = new HttpResponse(HttpStatusCodes.NOT_FOUND);
            }
            
            res.setContentType(
                    req.getHeaderValue("Accept").split(",")[0], null);
            res.setBody(page);
            os.print(res.getFullResponse());
            os.flush();
            os.close();
        }
    }
}

package devtools.simplehttpserver;

import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author omar
 */
public class ClientHandler implements Runnable {

    private final Socket client;
    private final RequestHandler handler;
    public ClientHandler(Socket client,RequestHandler handler) {
        this.client = client;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            var is = this.client.getInputStream();
            PrintStream os = new PrintStream(this.client.getOutputStream());
            StringBuilder sb = new StringBuilder();
            char c = (char) is.read();
            while (is.available() > 0) {
                sb.append(c);
                c = (char) is.read();
            }
            System.out.println(sb.toString());

            HttpRequest req = new HttpRequest(sb.toString());

            var page = this.handler.handle(req);
            HttpResponse res = null;
            if (page != null && page.length() > 0) {

                res = new HttpResponse(HttpStatusCodes.SUCESS);
            } else {
                res = new HttpResponse(HttpStatusCodes.NOT_FOUND);
            }

            res.setContentType(
                    req.getHeaderValue("Accept")
                            .split(",")[0], null);
            res.setBody(page);
            if (sb.toString().contains("/test")) {
                try {
                    Thread.sleep(20_000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ClientHandler.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
            os.print(res.getFullResponse());
            os.flush();
            os.close();
        } catch (Exception ex) {
            Logger.getLogger(ClientHandler.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

}

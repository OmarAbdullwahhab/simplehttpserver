/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package devtools.simplehttpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 *
 * @author omar
 */
public class HttpRequest {

    private String rawRequest;

    public HttpRequest(String req) {
        this.rawRequest = req;
    }

    
    public String getVersion() {
        var version = "";
        try {
            var sr = new BufferedReader(new StringReader(this.rawRequest));
            var line = sr.readLine();
            sr.close();
            if (line != null) {
                var data = line.split(" ");
                version = data[2].substring(data[2].lastIndexOf("/"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return version;
    }
//GET /css/main.css HTTP/1.1
    public String getMethod() {
        var method = "";
        try {
            var sr = new BufferedReader(new StringReader(this.rawRequest));
            var line = sr.readLine();
            sr.close();
            if (line != null) {
                var data = line.split(" ");
                method = data[0];
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return method;
    }

    public String getUrl() {
        var url = "";
        try {
            var sr = new BufferedReader(new StringReader(this.rawRequest));
            var line = sr.readLine();
            sr.close();
            if (line != null) {
                var data = line.split(" ");

                if (!"/".equals(data[1])) {
                    url = data[1].substring(1);
                } else {
                    url = data[1];
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return url;
    }

    public String getHeaderValue(String header) {
        var val = "";
        try {
            var sr = new BufferedReader(new StringReader(this.rawRequest));
            var line = sr.readLine();
            while (line != null) {
                if (line.startsWith(header + ":")) {
                    val = line.split(":")[1];
                    break;

                }
                line = sr.readLine();
            }
            sr.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return val;
    }
}

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
public class RequestHandler {
    
    
    public String handle(HttpRequest request) throws IOException{
        
            var docReader = new DocumentReader();
            var accepts = request.getHeaderValue("Accept").trim();
            var url = request.getUrl();
            if("/".equals(url))
            {
                url = "index";//welcome index
            }
            
            if(accepts.startsWith(HttpConstants.MIME_TYPE_CSS_FILE)) {
                
                return docReader.readDocument(
                        HttpConstants.MIME_TYPE_CSS_FILE_EXT,url.split("/") );
            }
            if(accepts.startsWith(HttpConstants.MIME_TYPE_HTML_FILE)) {
                
                return docReader.readDocument(
                        HttpConstants.MIME_TYPE_HTML_FILE_EXT,url.split("/") );
            }
            if(accepts.startsWith(HttpConstants.MIME_TYPE_JAVA_SCRIPT_FILE)) {
                
                return docReader.readDocument(
                        HttpConstants.MIME_TYPE_JAVA_SCRIPT_FILE_EXT,url.split("/") );
            }
           
            return "";
    }
    
}

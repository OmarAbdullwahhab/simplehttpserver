/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package devtools.simplehttpserver;

/**
 *
 * @author omar
 */
public class HttpResponse {
    
    
    
    private StringBuffer response;
    
    public HttpResponse(int status){
        this.response = new StringBuffer();
        this.response.append("HTTP/1.1 ").append(status);
        switch(status)
        {
            case HttpStatusCodes.SUCESS:
                this.response.append("OK");
                break;
            case HttpStatusCodes.NOT_FOUND:
                this.response.append("Not found");
                break;
            default:
                this.response.append("Not found");
                break;
        }
        this.response.append("\n");
        
    }
    
    public void setContentType(String type, String charSet){
        this.response.append("Content-Type: ").append(type);
        if(charSet != null){
            this.response.append("; charset=").append(charSet);
        }
        else{
            this.response.append("; charset=UTF-8");
        }
        this.response.append("\n");
    }
    public void setBody(String body){
        this.response.append("Content-Length: ")
                .append(body !=null ? body.length() : 0);
        this.response.append("\n");
        this.response.append("\n");
        this.response.append(body);
    }
    
    public String getFullResponse()
    {
        return this.response.toString();
    }
}

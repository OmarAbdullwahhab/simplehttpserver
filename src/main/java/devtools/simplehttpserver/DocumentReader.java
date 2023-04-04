/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package devtools.simplehttpserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author omar
 */
public class DocumentReader {


            //css/main.css //js/index.js
    public String readDocument(String ext,String... name) {
        //test.html
        StringBuilder sb = new StringBuilder();
        var path = Paths.get(SimpleHTTPServer.rootFolder, name );
        //static 
        var file  = path.toString().endsWith(ext) 
                ? new File(path.toString()) 
                : new File(path.toString() + ext);
        System.out.println("FILE = " + file);
        if (Files.exists(file.toPath())) {
            
            try {
                var br = new BufferedReader(new FileReader(file));
                var line = br.readLine();
                while(line != null){
                    sb.append(line);
                    line = br.readLine();
                }
           
            } catch (Exception ex) {
                Logger.getLogger(DocumentReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

         return sb.toString();
    }
}

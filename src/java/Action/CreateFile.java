/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

/**
 *
 * @author Harshal
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter; 
import java.io.IOException; 
public class CreateFile 
{ 
    public CreateFile(){
        
       
    }
    
    public String storeFile(){
        String value = "";
         try {
            File file = new File("harshal_oep.txt");  
            if (file.createNewFile()) {  
                System.out.print(")hello");
                value ="New File is created!"+file.getAbsolutePath();  
            } else {  
                System.out.println("File already exists.");  
            }  
            FileWriter writer = new FileWriter(file, true);
            writer.write("Hello World");
            writer.write("\r\n");   // write new line
            writer.write("Good Bye!");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}
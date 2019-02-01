/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package direction;
import java.util.*;
import java.io.IOException;


/**
 *
 * @author Viet H. Pham
 */
public class main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        String ori = "1208 Frankford Ave, Philadelphia, PA 19125";
        String des = "Pho 20 Philadelphia";
        String mode = "driving";
        
        direction obj = new direction(ori, des, mode);
        
        System.out.println(obj.getRaw());
    }
    
}

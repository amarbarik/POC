package za.co.test.java4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by F4742443 on 2016/02/22.
 */
public class WriteClass {
    public static void main(String[] args) throws Exception {
        File file =  new File("Data.txt");
        FileOutputStream stream = new FileOutputStream(file);
        for(int i = 1; i < 5; i++) {
            stream.write(i);
            System.out.println("----");
        }
        FileInputStream inputStream = new FileInputStream(file);
        int content;
        while ((content = inputStream.read()) != -1) {
            // convert to char and display it
            System.out.print(content);
        }
    }
}

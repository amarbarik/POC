package za.co.io.poc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by F4742443 on 2016/02/25.
 */
public class CopyBytes {

    public static void main(String[] args) throws IOException {

        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream("testFileInput.txt");
            out = new FileOutputStream("testFileOutput.txt");
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
                System.out.println((char)c);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}

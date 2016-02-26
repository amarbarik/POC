package za.co.io.poc;

import java.io.*;
import java.util.Comparator;

/**
 * Created by F4742443 on 2016/02/25.
 */
public class CopyLines {
    public static void main(String[] args) throws IOException {

        BufferedReader inputStream = null;
        PrintWriter outputStream = null;
        try {
            inputStream = new BufferedReader(new FileReader("testFileInput.txt"));
            outputStream = new PrintWriter(new FileWriter("lineOutput.txt"));

            String l;
            while ((l = inputStream.readLine()) != null) {
                outputStream.println(l);
                System.out.println(l);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}

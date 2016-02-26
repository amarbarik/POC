package za.co.io.poc;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by F4742443 on 2016/02/25.
 */
public class CopyCharacters {

    public static void main(String[] args) throws IOException {

        FileReader inputStream = null;
        FileWriter outputStream = null;

        try {
            inputStream = new FileReader("testFileInput.txt");
            outputStream = new FileWriter("characterOutput.txt");

            int c;
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
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

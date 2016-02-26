package za.co.test.java7;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by F4742443 on 2016/02/22.
 */
public class CLArgs {

    public static void main(String[] args) {
        System.out.println(args[0] + args[1]);
    }

    public void writeFile() {
        Path inF = Paths.get("input.txt");
        Path outF = Paths.get("output.txt");
        BufferedReader reader = null;
        try {
            reader = Files.newBufferedReader(inF, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

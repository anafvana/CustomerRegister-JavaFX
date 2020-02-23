package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    public static void writeString(File selectedFile, String str) throws IOException {
        try {
            FileWriter fw = new FileWriter(selectedFile);

            fw.write(str);
            fw.close();
        } catch (Exception e){
            throw new IOException("Failed to write on this file.");
        }
    }
}

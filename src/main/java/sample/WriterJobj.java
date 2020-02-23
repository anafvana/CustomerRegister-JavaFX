package sample;

import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class WriterJobj {
    public static void jobjSaveFile(Path path, ObservableList<Person> personList) throws IOException{
        try{
            OutputStream os = Files.newOutputStream(path);
            ObjectOutputStream outStream = new ObjectOutputStream(os);
            outStream.writeObject(new ArrayList<>(personList));
        } catch (IOException e) {
            throw new IOException("Something wrong is not right..");
        }
    }
}

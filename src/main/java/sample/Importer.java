package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Importer {

    static ObservableList<Person> readPerson(String path) throws IOException, InvalidPersonFormat{

        ObservableList<Person> personList = FXCollections.observableArrayList();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String line;

            while ((line = reader.readLine()) != null){
                personList.add(ParsePerson.parsePerson(line));
            }
        }
        return personList;
    }
}

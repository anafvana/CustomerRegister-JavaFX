package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Importer {

    static ObservableList<Person> readPeople(ArrayList<Person> personList, String path) throws IOException, InvalidPersonFormat{
        ObservableList<Person> obsListPersons = FXCollections.observableArrayList(personList);
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String line;

            while ((line = reader.readLine()) != null){
                obsListPersons.add(ParsePerson.parsePerson(line));
            }
        }
        return obsListPersons;
    }
}

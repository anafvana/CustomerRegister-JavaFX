package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Importer {

    static List<Person> readPerson(ArrayList<Person> personList, String path) throws IOException, InvalidPersonFormat{

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String line;

            while ((line = reader.readLine()) != null){
                personList.add(ParsePerson.parsePerson(line));
            }
        }
        return personList;
    }
}

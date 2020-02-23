package sample;

import java.util.List;

public class FormatPerson {
    public static String DELIMITER = "; ";

    public static String formatPerson(Person p){
        return p.getName() + DELIMITER + p.getAge() + DELIMITER + p.getDateOfBirth() + DELIMITER + p.getEmail() + DELIMITER + p.getPhone();
    }

    public static String formatPeople(List<Person> peopleList){
        StringBuffer str = new StringBuffer();
        for (Person p : peopleList){
            str.append(formatPerson(p));
            str.append("\n");
        }
        return str.toString();
    }
}

package sample;

public class ParsePerson {
    public static Person parsePerson(String person) throws InvalidPersonFormat {
        try {
            Person newPerson = new Person("test", "test", "test", "test", "test", "test");
            String[] personData = person.split("; ", 5);
            if (personData.length == 5) {
                String[] dateElements = personData[2].split("/", 3);
                if (dateElements.length == 3) {
                    String name = newPerson.getName();
                    String dayOfBirth = newPerson.dayOfBirth;
                    String monthOfBirth = newPerson.monthOfBirth;
                    String yearOfBirth = newPerson.yearOfBirth;
                    String dateOfBirth = NumberValidation.buildDate(dayOfBirth, monthOfBirth, yearOfBirth);
                    String phonenumber = newPerson.getPhone();
                    String email = newPerson.getEmail();

                    personData[0] = name;
                    personData[2] = dateOfBirth;
                    personData[3] = phonenumber;
                    personData[4] = email;

                    return newPerson;
                } else {
                    throw new InvalidPersonFormat("Invalid date format.\nCheck for 'DD/MM/YYYY.");
                }
            } else {
                throw new InvalidPersonFormat("Invalid person format.\nCheck for 'name;date_of_birth;email;phone' format.");
            }
        } catch (InvalidPersonFormat e) {
            throw new InvalidPersonFormat("Invalid person format. Could not read or split string.");
        }
    }

    public static boolean validPerson(String string){
        boolean personIsValid = false;
        try {
            Person testPerson = parsePerson(string);
            personIsValid = true;
        } catch (InvalidPersonFormat e){
            personIsValid = false;
        }
        return personIsValid;
    }
}
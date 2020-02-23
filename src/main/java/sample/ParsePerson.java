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
                    int age = NumberValidation.calcAge(newPerson.dayOfBirth, newPerson.monthOfBirth, newPerson.yearOfBirth);
                    String phonenumber = newPerson.getPhone();
                    String email = newPerson.getEmail();

                    personData[0] = name;
                    if (age == Integer.parseInt(personData[1])){
                        age = Integer.parseInt(personData[1]);
                    } else {
                        throw new InvalidPersonFormat("Age and date of birth do not match.");
                    }
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

    public static String[] parseDate(String dateOfBirth) throws InvalidDate{
        try {
            String[] splitDateArray = dateOfBirth.split("/", 3);
            if (splitDateArray.length == 3) {
                String dayOfBirth = splitDateArray[0];
                String monthOfBirth = splitDateArray[1];
                String yearOfBirth = splitDateArray[2];
                return splitDateArray;
            } else {
                throw new InvalidDate("Invalid date format.\nCheck for DD/MM/YYYY.");
            }
        } catch (InvalidDate e){
            throw new InvalidDate("Invalid date format.\nCheck for DD/MM/YYYY.");
        }
    }
}

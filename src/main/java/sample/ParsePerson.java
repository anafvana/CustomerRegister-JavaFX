package sample;

public class ParsePerson {
    public static Person parsePerson(String person) throws InvalidPersonFormat {
        Person personX = new Person("test", "01", "01", "1920", "0000000", "test@test.com");
        try {
            String[] personData = person.split("; ", 5);
            if (personData.length == 5) {
                String[] dateElements = personData[2].split("/", 3);
                if (dateElements.length == 3) {
                    String name = personData[0];
                    String dayOfBirth = dateElements[0];
                    String monthOfBirth = dateElements[1];
                    String yearOfBirth = dateElements[2];
                    String phonenumber = personData[4];
                    String email = personData[3];

                    personX.setName(name);
                    personX.setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth);
                    personX.setAge(dayOfBirth, monthOfBirth, yearOfBirth);
                    personX.setEmail(email);
                    personX.setPhone(phonenumber);

                    if (personX.getAge() != Integer.parseInt(personData[1])) {
                        System.err.println("Age and date of birth do not match. Age has been replaced.");

                        return personX;
                    } else {
                        throw new InvalidPersonFormat("Invalid date format.\nCheck for 'DD/MM/YYYY.");
                    }
                } else {
                    throw new InvalidPersonFormat("Invalid person format.\nCheck for 'name;date_of_birth;email;phone' format.");
                }
            }
        } catch (InvalidPersonFormat e) {
            throw new InvalidPersonFormat("Invalid person format. Could not read or split string.");
        }
        return personX;
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

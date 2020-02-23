package sample;

public class TextValidation {
    public static String testName(String name){
        String inName = "test";
        if (name.matches("[A-ZÆØÅ][a-zæøå]+") || name.matches("[A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]*.?")
                || name.matches("[A-ZÆØÅ][a-zæøå]+ [A-ZÆØÅ][a-zæøå]*.? [A-ZÆØÅ][a-zæøå]*.?")){
            inName = name;
            return inName;
        } else {
            inName = "";
            return inName;
        }
    }

    public static boolean validName(String name){
        String checkName = testName(name);
        boolean validName;

        if (!checkName.isEmpty()) {
            validName = true;
            return validName;
        } else {
            validName = false;
            return validName;
        }
    }

    public static String buildEmail(String email){
        String inEmail = "test";
        if ((email.matches("[-_\\w.]+[@][-_\\w]+[.][a-z0-9]{1,3}")) || (email.matches("[-_\\w.]+[@][\\w]+[.][a-z0-9]{1,3}[.][a-z0-9]{1,3}")) ){
            inEmail = email;
            return inEmail;
        } else {
            inEmail = "";
            return inEmail;
        }
    }

    public static boolean validEmail(String Email){
        String checkEmail = buildEmail(Email);
        boolean validEmail;

        if (!checkEmail.isEmpty()) {
            validEmail = true;
            return validEmail;
        } else {
            validEmail = false;
            return validEmail;
        }
    }

    public static String buildPhone (String phone){
        String phoneNr = "test";

        if ((phone.matches("[+]?\\d{7,9}")) || phone.matches("[+]?[\\d+[-. ]\\d]{7,16}") ||
                (phone.matches("[(][0-9+]{1,5}[)]\\s?\\d{9,13}")) || phone.matches("[(][0-9+]{1,5}[)]\\s?[\\d+[-. ]\\d]{7,20}")
        ){
            phoneNr = phone;
            return phoneNr;
        } else {
            phoneNr = "";
            return phoneNr;
        }
    }

    public static boolean validPhone(String phone){
        String checkphone = buildPhone(phone);
        boolean validPhone;

        if (!checkphone.isEmpty()) {
            validPhone = true;
            return validPhone;
        } else {
            validPhone = false;
            return validPhone;
        }
    }
}

package sample;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class NumberValidation {

    private static int testYear (String YYYY) throws InvalidDate {
        int year;
        try {
            year = Integer.parseInt(YYYY);
        } catch (NumberFormatException e){
            throw new InvalidDate("Year can only contain numbers.");
        }
        if (year >= 1920 && year <=2020){
            return year;
        } else {
            throw new InvalidDate("Year must be between 1920 and 2020.");
        }
    }

    private static boolean isLeapYear (int year){
        if (year % 4 == 0){
            if(year % 100 == 0){
                return year % 400 == 0;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private static int testMonth (String MM) throws InvalidDate {
        int month;
        try {
            month = Integer.parseInt(MM);
        } catch (NumberFormatException e){
            throw new InvalidDate("Month can only contain numbers.");
        }

        if (month > 0 && month < 13){
            return month;
        } else {
            throw new InvalidDate("Month must be between 01 and 12.");
        }
    }

    private static int nrDays (int month, int year) {
        int nrOfDays;
        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                nrOfDays = 31;
                break;

            case 4:
            case 6:
            case 9:
            case 11:
                nrOfDays = 30;
                break;

            case 2:
                if(isLeapYear(year)){
                    nrOfDays = 29;
                    break;
                } else {
                    nrOfDays = 28;
                    break;
                }
            default:
                nrOfDays = 0;
                break;
        }
        return nrOfDays;
    }

    private static int testDay (String DD, int month, int year) throws InvalidDate {
        int daysInMonth = nrDays(month, year);
        int day;

        try {
            day = Integer.parseInt(DD);
        } catch (NumberFormatException e){
            throw new InvalidDate("Day can only contain numbers.");
        }

        if (day > 0 && day <= daysInMonth){
            return day;
        } else {
            throw new InvalidDate("Day must be between 1 and " + daysInMonth + "for inserted month and year.");
        }
    }

    protected static String buildDate(String DD, String MM, String YYYY){
        String date;
        try {
            int year = testYear(YYYY);
            int month = testMonth(MM);
            int day = testDay(DD, month, year);

            date = String.format("%02d", day) + "/" + String.format("%02d", month) + "/" + year;
        } catch (InvalidDate e) {
            String error = "Invalid date.";
            date = "";
        }
        return date;
    }

    //Calculates age
    private static String getDate() {
        Date today = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = dateFormat.format(today);
        return strDate;
    }

    private static String[] dateArray(){
        String fullDate = getDate();
        String[] dateArray = new String[3];
        dateArray = fullDate.split("-", 3);
        return dateArray;
    }

    private static int todayDD(String[] dateArray){
        int DD = Integer.parseInt(dateArray[0]);
        return DD;
    }

    private static int todayMM(String[] dateArray){
        int MM = Integer.parseInt(dateArray[1]);
        return MM;
    }

    private static int todayYYYY(String[] dateArray){
        int YYYY = Integer.parseInt(dateArray[2]);
        return YYYY;
    }

    protected static int calcAge(String DD, String MM, String YYYY){
        String[] dateArray = dateArray();
        int todayDD = todayDD(dateArray);
        int todayMM = todayMM(dateArray);
        int todayYYYY = todayYYYY(dateArray);

        int age = 0;

        try {
            int year = testYear(YYYY);
            int month = testMonth(MM);
            int day = testDay(DD, month, year);

            if (todayMM < month) {
                age = todayYYYY - year - 1;
            } else if (todayMM == month){
                if (todayDD >= day){
                    age = todayYYYY - year;
                } else {
                    age = todayYYYY - year - 1;
                }
            } else {
                age = todayYYYY - year;
            }
            Person.setAge(DD, MM, YYYY);

        } catch (InvalidDate e) {
            String error = "Invalid date.";
        }

        return age;
    }
}

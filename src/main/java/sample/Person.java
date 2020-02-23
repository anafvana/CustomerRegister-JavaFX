package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class Person implements Serializable {

    private transient SimpleStringProperty name;
    private transient SimpleIntegerProperty age;
    private transient SimpleStringProperty dateOfBirth;
    transient String dayOfBirth;
    transient String monthOfBirth;
    transient String yearOfBirth;
    private transient SimpleStringProperty email;
    private transient SimpleStringProperty phone;

    public Person(String name, String dayOfBirth, String monthOfBirth, String yearOfBirth, String phone, String email) throws IllegalArgumentException{
        this.name = new SimpleStringProperty(TextValidation.testName(name));
        age = new SimpleIntegerProperty(NumberValidation.calcAge(dayOfBirth, monthOfBirth, yearOfBirth));
        this.dateOfBirth = new SimpleStringProperty(NumberValidation.buildDate(dayOfBirth, monthOfBirth, yearOfBirth));
        this.email = new SimpleStringProperty(TextValidation.buildEmail(email));
        this.phone = new SimpleStringProperty(TextValidation.buildPhone(phone));

        if(this.name.getValue().isEmpty() || age.getValue() == 0 || this.dateOfBirth.getValue().isEmpty() || this.phone.getValue().isEmpty() || this.email.getValue().isEmpty()){
            throw new IllegalArgumentException();
        }
    }

    public String getName (){
        return name.getValue();
    }

    public void setName(String name){
        this.name.set(TextValidation.testName(name));
    }

    public int getAge (){
        return age.get();
    }

    public static void setAge(String dayOfBirth, String monthOfBirth, String yearOfBirth){
        age.set(NumberValidation.calcAge(dayOfBirth, monthOfBirth, yearOfBirth));
    }

    public String getDateOfBirth(){
        return dateOfBirth.getValue();
    }

    public void setDateOfBirth(String dayOfBirth, String monthOfBirth, String yearOfBirth){
        this.dateOfBirth.set(NumberValidation.buildDate(dayOfBirth, monthOfBirth, yearOfBirth));
    }

    public String getEmail(){
        return email.getValue();
    }

    public void setEmail(String eMail){
        this.email.set(TextValidation.buildEmail(eMail));
    }

    public String getPhone(){
        return phone.getValue();
    }

    public void setPhone(String phone){
        this.phone.set(TextValidation.buildPhone(phone));
    }

    public void setAgeThroughTV(Integer newValue) {
    }
}

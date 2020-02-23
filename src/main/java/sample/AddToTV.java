package sample;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import java.io.Serializable;
import java.util.stream.Collectors;

public class AddToTV implements Serializable {
    private static final long serialVersionUID = 1;

    private transient ObservableList<Person> list = FXCollections.observableArrayList();

    public void attachTableView(TableView<Person> tv) {
        tv.setItems(list);
    }

    public ObservableList<Person> getTableView(){
        return list;
    }

    public void addElement(Person obj) {
        list.add(obj);
    }

    public void deleteElement(){
        list.clear();
    }

    public ObservableList<Person> nameFilter(String name){
        if(!name.isEmpty()) {
            return list.stream().filter
                    (person -> person.getName().toLowerCase().matches(String.format("%s%s%s", ".*", name.toLowerCase(), ".*"))).collect
                    (Collectors.toCollection(FXCollections::observableArrayList));
        } else {
            return list;
        }
    }

    public ObservableList<Person> emailFilter(String email){
        if(!email.isEmpty()) {
            return list.stream().filter
                    (person -> person.getEmail().contains(email.toUpperCase())).collect(Collectors.toCollection(FXCollections::observableArrayList));
        } else {
            return list;
        }
    }

    public ObservableList<Person> phoneFilter(String phone){
        if(!phone.isEmpty()) {
            return list.stream().filter
                    (person -> person.getPhone().contains(phone.toUpperCase())).collect(Collectors.toCollection(FXCollections::observableArrayList));
        } else {
            return list;
        }
    }
}

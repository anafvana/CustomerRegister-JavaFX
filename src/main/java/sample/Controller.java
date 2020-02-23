package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Controller implements Initializable {

    String nameError;
    String dateError;
    String EmailError;
    String phoneError;
    String errors = "";
    ArrayList<Person> personList = new ArrayList<>();

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtDay;

    @FXML
    private TextField txtMonth;

    @FXML
    private TextField txtYear;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtMail;

    @FXML
    private Label lblErrors;

    @FXML
    private TableView<Person> tableView;

    @FXML
    private TableColumn<Person, Integer> intDataColumn;

    @FXML
    private TableColumn<Person, String> txtNameColumn;

    @FXML
    private TableColumn<Person, String> txtDobColumn;

    @FXML
    private TableColumn<Person, String> txtEmailColumn;

    @FXML
    private TableColumn<Person, String> txtPnrColumn;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField filterText;



    private AddToTV newPerson = new AddToTV();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addChkBoxItems();
        newPerson.attachTableView(tableView);
        txtNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        txtDobColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        txtEmailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        txtPnrColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableView.setEditable(true);
    }

    @FXML
    private void registerPerson(ActionEvent event) {
        Person obj = createPerson();

        if (obj != null) {
            lblErrors.setText("");
            newPerson.addElement(obj);
            resetTxtFields();
        }
    }

    private Person createPerson() {
        String day = txtDay.getText();
        String month = txtMonth.getText();
        String year = txtYear.getText();

        String dateOfBirth = NumberValidation.buildDate(day, month, year);

        String name = TextValidation.testName(txtName.getText());

        String email = TextValidation.buildEmail(txtMail.getText());

        String phone = TextValidation.buildPhone(txtPhone.getText());

        if (!name.isEmpty() && !dateOfBirth.isEmpty() && !email.isEmpty() && !phone.isEmpty()) {
            return new Person(name, day, month, year, phone, email);
        } else {
            if (name.isEmpty()) {
                nameError = "Invalid name";
                errors += nameError + "\n";
            }
            if (dateOfBirth.isEmpty()) {
                dateError = "Invalid date";
                errors += dateError + "\n";
            }
            if (email.isEmpty()) {
                EmailError = "Invalid email";
                errors += EmailError + "\n";
            }
            if (phone.isEmpty()) {
                phoneError = "Invalid phone number";
                errors += phoneError + "\n";
            }
            lblErrors.setText(errors);
        }
        return null;
    }

    private void resetTxtFields() {
        txtName.setText("");
        txtDay.setText("");
        txtMonth.setText("");
        txtYear.setText("");
        txtPhone.setText("");
        txtMail.setText("");
    }

    @FXML
    private void txtNameEdited(TableColumn.CellEditEvent<Person, String> event) {
        event.getRowValue().setName(event.getNewValue());
    }

    @FXML
    private void txtDobEdited(TableColumn.CellEditEvent<Person, String> personStringCellEditEvent){
        try {
            String[] newDate = ParsePerson.parseDate(personStringCellEditEvent.getNewValue());
            personStringCellEditEvent.getRowValue().setAge(newDate[0], newDate[1], newDate[2]);
            personStringCellEditEvent.getRowValue().setDateOfBirth(newDate[0], newDate[1], newDate[2]);
        } catch (InvalidDate e){
            System.err.println(e.getMessage());
        }

    }

    @FXML
    private void txtPnrEdited(TableColumn.CellEditEvent<Person, String> personStringCellEditEvent){
        personStringCellEditEvent.getRowValue().setPhone(personStringCellEditEvent.getNewValue());
    }

    @FXML
    private void txtEmailEdited(TableColumn.CellEditEvent<Person, String> personStringCellEditEvent){
        personStringCellEditEvent.getRowValue().setEmail(personStringCellEditEvent.getNewValue());
    }

    @FXML
    private void btnOpenFile(ActionEvent event) {
        FileChooser fc = new FileChooser();
        String currentDir = Paths.get(".").toAbsolutePath().normalize().toString();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT files", "*.txt"));
        fc.setInitialDirectory(new File(currentDir));

        File selectedFile = fc.showOpenDialog(null);
        String innPath = selectedFile.getPath();

        try {
            ObservableList<Person> personList = Reader.readPeople(innPath);
            tableView.setItems(personList);
        } catch (IOException e) {
            System.err.println("Could not read the requested file. Cause: " + e.getMessage());
        } catch (InvalidPersonFormat e) {
            System.err.println("The data is not formatted correctly. Cause: " + e.getMessage());
        }
    }

    @FXML
    void btnSaveToExisting(ActionEvent event) {
        String str = FormatPerson.formatPeople(tableView.getItems());

        FileChooser fc = new FileChooser();
        String currentDir = Paths.get(".").toAbsolutePath().normalize().toString();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT files", "*.txt"), new FileChooser.ExtensionFilter("JOBJ Files", "*.jobj"));
        fc.setInitialDirectory(new File(currentDir));

        File selectedFile = fc.showOpenDialog(null);

        try {
            Writer.writeString(selectedFile, str);
        } catch (IOException e){
            System.err.print("Failed to write on this file.");
        }
    }

    @FXML
    private void btnSaveToNew(ActionEvent event) {

        String str = FormatPerson.formatPeople(tableView.getItems());

        FileChooser fc = new FileChooser();
        String currentDir = Paths.get(".").toAbsolutePath().normalize().toString();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT files", "*.txt"), new FileChooser.ExtensionFilter("JOBJ Files", "*.jobj"));
        fc.setInitialFileName("personer");
        fc.setInitialDirectory(new File(currentDir));

        File selectedFile = fc.showSaveDialog(null);

        try {
            Writer.writeString(selectedFile, str);
        } catch (Exception e){
            System.err.println("Failed to read file.");
        }
    }

    @FXML
    void btnOpenFileJobj(ActionEvent event) {
        FileChooser fc = new FileChooser();
        String currentDir = Paths.get(".").toAbsolutePath().normalize().toString();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JOBJ Files", "*.jobj"));
        fc.setInitialDirectory(new File(currentDir));

        File selectedFile = fc.showOpenDialog(null);
        String innPath = selectedFile.getPath();
        Path filePath = Paths.get(innPath);

        try (InputStream is = Files.newInputStream(filePath); ObjectInputStream ois = new ObjectInputStream(is)) {
           personList = (ArrayList<Person>) ois.readObject();
           ObservableList<Person> obspList = FXCollections.observableArrayList(personList);
           tableView.setItems(obspList);
        } catch (Exception e) {
            System.err.println("Could not read the requested file. Cause: " + e.getMessage());
        }
    }

    ObservableList<String> chkBoxList = FXCollections.observableArrayList();

    private void addChkBoxItems(){
        chkBoxList.removeAll();
        String chkBoxFilter = "Search Filters";
        String chkBoxName = "Name";
        String chkBoxAge = "Age";
        String chkBoxDob = "Date of birth";
        String chkBoxPnr = "Phonenumber";
        String chkBoxEmail = "Email";

        chkBoxList.addAll(chkBoxFilter, chkBoxName, chkBoxAge, chkBoxDob, chkBoxPnr, chkBoxEmail);
        choiceBox.getItems().addAll(chkBoxList);
    }

    ObservableList<Person> filteredList = FXCollections.observableArrayList();

    @FXML
    private Label lblFilter;

    @FXML
    private void filterTbl(ActionEvent event){
        filteredList.clear();
        lblFilter.setText("");
        lblErrors.setText("");

        String newFilterText = filterText.getText();
        String filterType = choiceBox.getValue();

        if(newFilterText.equals("")){
            lblFilter.setText("Write something idiot.");
        } else {
            if (filterType.equals("Name")) {
                filteredList = newPerson.nameFilter(newFilterText);
                tableView.setItems(filteredList);
                lblFilter.setText("Registry filtered by name.");
                if(filteredList.isEmpty()){
                    lblFilter.setText("No person was found.");
                }
            }
            if(filterType.equals("Age")){
                filteredList = newPerson.ageFilter(newFilterText);
                tableView.setItems(filteredList);
                lblFilter.setText("Registry filtered by age.");
                if(filteredList.isEmpty()){
                    lblFilter.setText("No person was found.");
                }
            }
            if(filterType.equals("Date of birth")){
                filteredList = newPerson.dateOfBirthFilter(newFilterText);
                tableView.setItems(filteredList);
                lblFilter.setText("Registry filtered by date of birth.");
                if(filteredList.isEmpty()){
                    lblFilter.setText("No person was found.");
                }
            }
            if (filterType.equals("Phonenumber")) {
                filteredList = newPerson.phoneFilter(newFilterText);
                tableView.setItems(filteredList);
                lblFilter.setText("Registry filtered by phone.");
                if(filteredList.isEmpty()){
                    lblFilter.setText("No person was found.");
                }
            }
            if (filterType.equals("Email")) {
                filteredList = newPerson.emailFilter(newFilterText);
                tableView.setItems(filteredList);
                lblFilter.setText("Registry filtered by email.");
                if(filteredList.isEmpty()){
                    lblFilter.setText("No person was found.");
                }
            }
        }
    }

    @FXML
    private void resetFilterBtn(ActionEvent event){
        filterText.setText("");
        lblFilter.setText("");
        lblErrors.setText("");
        tableView.setItems(newPerson.getTableView());
    }

}
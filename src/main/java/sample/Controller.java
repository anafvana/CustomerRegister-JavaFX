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
import java.net.URL;
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
    private IntegerStringConverter intStrConverter = new IntegerStringConverter();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addChkBoxItems();
        newPerson.attachTableView(tableView);
        intDataColumn.setCellFactory(TextFieldTableCell.forTableColumn(intStrConverter));
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
        personStringCellEditEvent.getRowValue().setDateOfBirth(personStringCellEditEvent.getNewValue(), personStringCellEditEvent.getNewValue(), personStringCellEditEvent.getNewValue());
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
    private void intDataEdited(TableColumn.CellEditEvent<Person, Integer> integerCellEditEvent){
        if(intStrConverter.wasSuccessful()) {
            try {
                integerCellEditEvent.getRowValue().setAge(integerCellEditEvent.getNewValue());
            } catch (NumberFormatException e) {
                throw new NumberFormatException("You have to enter a number");
            }
        }
    }



    @FXML
    private void btnOpenFile(ActionEvent event) {
        FileChooser fc = new FileChooser();
        String currentDir = Paths.get(".").toAbsolutePath().normalize().toString();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT files", "*.txt"));
        fc.setInitialDirectory(new File(currentDir));

        File selectedFile = fc.showOpenDialog(null);
        ObservableList<Person> newList = FXCollections.observableArrayList();
        /*try {
            newList = Importer.readPerson(personList, selectedFile.getPath());
            tableView.setItems(newList);
        } catch (IOException e){
            ErrorDialogs.showErrorDialog(e.getMessage());
        } catch (InvalidPersonFormat e){
            ErrorDialogs.showErrorDialog(e.getMessage());
        }

         */
    }

    @FXML
    void btnSaveToExisting(ActionEvent event) {
        String str = FormatPerson.formatPeople(tableView.getItems());

        FileChooser fc = new FileChooser();
        String currentDir = Paths.get(".").toAbsolutePath().normalize().toString();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT files", "*.txt"));
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
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT files", "*.txt"));
        fc.setInitialFileName("personer");
        fc.setInitialDirectory(new File(currentDir));

        File selectedFile = fc.showSaveDialog(null);

        try {
            Writer.writeString(selectedFile, str);
        } catch (Exception e){
            System.err.println("Failed to read file.");
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
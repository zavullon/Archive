package project.userinterface;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.database.DTO.ClientDTO;
import project.logic.StaffManager;
import project.logic.StaffManagerForSQLDatabase;

public class AddUserController
{
    private AddUserWindow mainWindow;
    private StaffManager staffManager = new StaffManagerForSQLDatabase();

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField middleNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private ComboBox<Integer> archiveIdComboBox;
    @FXML
    private Button applyButton;

    @FXML
    public void initialize()
    {
        applyButton.setOnAction((action) ->
        {
            if(!firstNameTextField.getText().equals("")
                    && !middleNameTextField.getText().equals("")
                    && !lastNameTextField.getText().equals("")
                    && archiveIdComboBox.getSelectionModel().getSelectedItem() != null)
            {
                staffManager.addClient(new ClientDTO(-1 , firstNameTextField.getText() ,
                        middleNameTextField.getText() ,
                        lastNameTextField.getText() ,
                        archiveIdComboBox.getSelectionModel().getSelectedItem() ,
                        0));
            }
            ((Stage) applyButton.getScene().getWindow()).close();
            mainWindow.getEmployeeWindowController().load();
        });
    }

    public void setMainApp(AddUserWindow mainApp)
    {
        mainWindow = mainApp;
        archiveIdComboBox.setItems(mainWindow.getList());
    }
}

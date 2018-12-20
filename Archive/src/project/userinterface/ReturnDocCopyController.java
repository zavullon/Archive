package project.userinterface;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import project.logic.StaffManager;
import project.logic.StaffManagerForSQLDatabase;
import project.logic.entities.DocumentMoving;
import project.util.Date;

import java.time.LocalDateTime;

public class ReturnDocCopyController
{
    private ReturnDocCopy mainWindow;
    private StaffManager manager = new StaffManagerForSQLDatabase();

    @FXML
    private ComboBox<Integer> copyIdComboBox;
    @FXML
    private Button applyButton;

    @FXML
    public void initialize()
    {
        applyButton.setOnAction((action) ->
        {
            if(copyIdComboBox.getSelectionModel().getSelectedItem() != null)
            {
                DocumentMoving documentMoving = new DocumentMoving(mainWindow.getClient().getId() ,
                        copyIdComboBox.getSelectionModel().getSelectedItem() ,
                        mainWindow.getEmployee().getId() ,
                        "Returned" ,
                        new Date(LocalDateTime.now().getYear() ,
                                 LocalDateTime.now().getMonth().getValue() ,
                                 LocalDateTime.now().getDayOfMonth()));
                manager.addDocumentMoving(documentMoving.toDTO());
            }
            ((Stage) applyButton.getScene().getWindow()).close();
        });
    }

    public void setMainApp(ReturnDocCopy mainApp)
    {
        mainWindow = mainApp;
        copyIdComboBox.setItems(mainWindow.getList());
    }
}

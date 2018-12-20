package project.userinterface;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import project.logic.StaffManager;
import project.logic.StaffManagerForSQLDatabase;
import project.util.Date;

public class TakeDocCopyController
{
    private TakeDocCopy mainWindow;

    private StaffManager staffManager = new StaffManagerForSQLDatabase();

    @FXML
    private ComboBox<Integer> documentIdComboBox;
    @FXML
    private DatePicker returnDatePicker;
    @FXML
    private Button applyButton;
    @FXML
    private Text warningText;

    @FXML
    public void initialize()
    {
        applyButton.setOnAction((action) ->
        {
            if(returnDatePicker.getValue() != null && documentIdComboBox.getSelectionModel().getSelectedItem() != null)
            {
                if(staffManager.takeDocument(mainWindow.getClient().getId() ,
                        documentIdComboBox.getSelectionModel().getSelectedItem() ,
                        mainWindow.getEmployee().getId() ,
                        new Date(returnDatePicker.getValue().toString())))
                {
                    ((Stage) applyButton.getScene().getWindow()).close();
                }
                else
                {
                    warningText.setText("No available copies of this document found");
                    warningText.setFill(Color.RED);
                }
            }
        });
    }

    public void setMainApp(TakeDocCopy mainApp)
    {
        mainWindow = mainApp;
        documentIdComboBox.setItems(mainWindow.getList());
    }
}

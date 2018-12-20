package project.userinterface;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import project.database.ClientDAO;
import project.database.ClientSQLAccessObject;
import project.logic.StaffManager;
import project.logic.StaffManagerForSQLDatabase;
import project.userinterface.entities.Client;

public class EmployeeWindowController
{
    private MainWindow mainWindow;
    private StaffManager manager = new StaffManagerForSQLDatabase();

    @FXML
    private TableView<Client> clientTableView;
    @FXML
    private TableColumn<Client , String> firstNameColumn;
    @FXML
    private TableColumn<Client , String> middleNameColumn;
    @FXML
    private TableColumn<Client , String> lastNameColumn;
    @FXML
    private TableColumn<Client , String> archiveIdColumn;
    @FXML
    private TableColumn<Client , String> fineColumn;
    @FXML
    private Button addUserButton;
    @FXML
    private Button usersButton;
    @FXML
    private Button diagramButton;

    @FXML
    public void initialize()
    {
        setRowFactory();
        setCellValueFactories();
        setButtons();
    }

    private void setCellValueFactories()
    {
        firstNameColumn.setCellValueFactory((data) -> new SimpleStringProperty(data.getValue().getFirstName()));
        middleNameColumn.setCellValueFactory((data) -> new SimpleStringProperty(data.getValue().getMiddleName()));
        lastNameColumn.setCellValueFactory((data) -> new SimpleStringProperty(data.getValue().getLastName()));
        archiveIdColumn.setCellValueFactory((data) -> new SimpleStringProperty(Integer.toString(data.getValue().getId())));
        fineColumn.setCellValueFactory((data) -> new SimpleStringProperty(Integer.toString(data.getValue().getFineSum())));
    }

    private void setRowFactory()
    {
        clientTableView.setRowFactory((row) ->
        {
            TableRow<Client> clientTableRow = new TableRow<>();
            ContextMenu contextMenu = new ContextMenu();
            MenuItem returnDocument = new MenuItem("return document");
            MenuItem takeDocument = new MenuItem("take document");
            returnDocument.setOnAction((action) ->
            {
                try
                {
                    new ReturnDocCopy(new Client(manager.getByClientId(clientTableRow.getItem().getId())) , mainWindow.getLogger()).start(new Stage());
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            });
            takeDocument.setOnAction((action) ->
            {
                try
                {
                    new TakeDocCopy(new Client(manager.getByClientId(clientTableRow.getItem().getId())) , mainWindow.getLogger()).start(new Stage());
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            });
            contextMenu.getItems().addAll(returnDocument , takeDocument);
            clientTableRow.contextMenuProperty().bind(Bindings.when(clientTableRow.emptyProperty()).then((ContextMenu) null).otherwise(contextMenu));
            return clientTableRow;
        });
    }

    public void setButtons()
    {
        usersButton.setDisable(true);

        diagramButton.setOnAction((action) ->
        {
            try
            {
                mainWindow.loadDiagram(mainWindow.getLogger());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        });

        addUserButton.setOnAction((action) ->
        {
            try
            {
                new AddUserWindow(this).start(new Stage());
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        });
    }

    public void setMainApp(MainWindow mainApp)
    {
        mainWindow = mainApp;
        load();
    }

    public void load()
    {
        clientTableView.setItems(mainWindow.getList());
    }
}

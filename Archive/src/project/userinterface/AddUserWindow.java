package project.userinterface;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.database.DTO.ArchiveDTO;
import project.logic.StaffManager;
import project.logic.StaffManagerForSQLDatabase;

import java.util.stream.Collectors;

public class AddUserWindow extends Application
{
    private EmployeeWindowController employeeWindowController;

    public AddUserWindow(EmployeeWindowController employeeWindowController)
    {
        this.employeeWindowController = employeeWindowController;
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("add user");
        FXMLLoader loader = new FXMLLoader(AddUserWindow.class.getResource("/resources/employeeAddUser.fxml"));
        AnchorPane page = loader.load();
        AddUserController addUserController = loader.getController();
        addUserController.setMainApp(this);
        Scene scene = new Scene(page , 534 , 105);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public ObservableList<Integer> getList()
    {
        StaffManager staffManager = new StaffManagerForSQLDatabase();
        return FXCollections.observableArrayList(staffManager.getAllArchives()
                .stream()
                .map(ArchiveDTO::getId)
                .collect(Collectors.toList()));
    }

    public EmployeeWindowController getEmployeeWindowController()
    {
        return employeeWindowController;
    }
}

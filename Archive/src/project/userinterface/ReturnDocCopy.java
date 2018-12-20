package project.userinterface;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.logic.StaffManager;
import project.logic.StaffManagerForSQLDatabase;
import project.userinterface.entities.Client;
import project.userinterface.entities.Employee;

public class ReturnDocCopy extends Application
{
    private Client client;
    private Employee employee;

    public ReturnDocCopy(Client client , Employee employee)
    {
        this.client = client;
        this.employee = employee;
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("return copy");
        FXMLLoader loader = new FXMLLoader(ReturnDocCopy.class.getResource("/resources/returnDocCopy.fxml"));
        AnchorPane page = loader.load();
        ReturnDocCopyController returnDocCopyController = loader.getController();
        returnDocCopyController.setMainApp(this);
        Scene scene = new Scene(page , 338 , 75);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public Client getClient()
    {
        return client;
    }

    public Employee getEmployee()
    {
        return employee;
    }

    public ObservableList<Integer> getList()
    {
        StaffManager staffManager = new StaffManagerForSQLDatabase();
        return FXCollections.observableArrayList(staffManager.getTakenCopiesByClientId(client.getId()));
    }
}

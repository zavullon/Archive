package project.userinterface;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.database.ClientDAO;
import project.database.ClientSQLAccessObject;
import project.logic.StaffManager;
import project.logic.StaffManagerForSQLDatabase;
import project.userinterface.entities.Client;
import project.userinterface.entities.Employee;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MainWindow extends Application
{
    private Stage mainStage;
    private FXMLLoader mainLoader;
    private AnchorPane mainPage;
    private Employee logger;

    private StaffManager staffManager;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        mainStage = primaryStage;
        loadAuth();
    }

    public void loadAuth() throws Exception
    {
        mainStage.setTitle("Authorization");
        mainLoader = new FXMLLoader(MainWindow.class.getResource("/resources/authorization.fxml"));
        mainPage = mainLoader.load();
        AuthController authController = mainLoader.getController();
        authController.setMainApp(this);
        Scene scene = new Scene(mainPage , 600 , 400);
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }

    public void loadEmployeeWindow(Employee logger) throws Exception
    {
        mainStage.setTitle("Home");
        mainLoader = new FXMLLoader(MainWindow.class.getResource("/resources/employee.fxml"));
        mainPage = mainLoader.load();
        EmployeeWindowController employeeWindowController = mainLoader.getController();
        this.logger = logger;
        employeeWindowController.setMainApp(this);
        Scene scene = new Scene(mainPage , 600 , 400);
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }

    public void loadAdminDocumentWindow(Employee logger) throws Exception
    {
        mainStage.setTitle("Home");
        mainLoader = new FXMLLoader(MainWindow.class.getResource("/resources/authorization.fxml")); //TODO
        mainPage = mainLoader.load();
        AdminDocumentWindowController adminDocumentWindowController = mainLoader.getController();
        adminDocumentWindowController.setMainApp(this);
        Scene scene = new Scene(mainPage , 600 , 400);
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }

    public void loadAdminDocumentCopyWindow(Employee logger) throws Exception
    {
        mainStage.setTitle("Home");
        mainLoader = new FXMLLoader(MainWindow.class.getResource("/resources/authorization.fxml")); //TODO
        mainPage = mainLoader.load();
        AdminDocumentCopyController adminDocumentCopyController = mainLoader.getController();
        adminDocumentCopyController.setMainApp(this);
        Scene scene = new Scene(mainPage , 600 , 400);
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }

    public void loadAdminEmployeeWindow(Employee logger) throws Exception
    {
        mainStage.setTitle("Home");
        mainLoader = new FXMLLoader(MainWindow.class.getResource("/resources/authorization.fxml")); //TODO
        mainPage = mainLoader.load();
        AdminEmployeeWindowController adminEmployeeWindowController = mainLoader.getController();
        adminEmployeeWindowController.setMainApp(this);
        Scene scene = new Scene(mainPage , 600 , 400);
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }

    public void loadDiagram(Employee logger) throws Exception
    {
        this.logger = logger;
        mainStage.setTitle("diagram");
        mainLoader = new FXMLLoader(MainWindow.class.getResource("/resources/diagram.fxml")); //TODO
        mainPage = mainLoader.load();
        DiagramController diagramController = mainLoader.getController();
        diagramController.setMainApp(this);
        Scene scene = new Scene(mainPage , 600 , 400);
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public Stage getMainStage()
    {
        return mainStage;
    }

    public FXMLLoader getMainLoader()
    {
        return mainLoader;
    }

    public AnchorPane getMainPage()
    {
        return mainPage;
    }

    public static void main(String args[])
    {
        launch(args);
    }

    public ObservableList<Client> getList()
    {
        StaffManager manager = new StaffManagerForSQLDatabase();
        return FXCollections.observableArrayList(manager.getClients()
                .stream()
                .map((c) -> new Client(c))
                .collect(Collectors.toList()));
    }

    public Employee getLogger()
    {
        return logger;
    }

    public ObservableList<PieChart.Data> getDiagramData()
    {
        staffManager = new StaffManagerForSQLDatabase();
        List<Client> allClients = staffManager.getClients().stream().map((c) -> new Client(c)).collect(Collectors.toList());
        ObservableList<PieChart.Data> pieChartData = FXCollections
                .observableArrayList(new PieChart.Data("Fine" , allClients
                                .stream()
                                .filter((c) -> c.getFineSum() > 0)
                                .collect(Collectors.toList()).size()) ,
                        new PieChart.Data("No Fine" , allClients
                                .stream()
                                .filter((c) -> c.getFineSum() == 0)
                                .collect(Collectors.toList()).size()));
        return pieChartData;
    }
}
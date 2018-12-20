package project.userinterface;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.converter.IntegerStringConverter;
import project.database.DTO.EmployeeDTO;
import project.logic.LoginManager;
import project.logic.LoginManagerForSQL;
import project.userinterface.entities.Employee;

import java.util.function.UnaryOperator;

public class AuthController
{
    private MainWindow mainWindow;
    private LoginManager loginManager = new LoginManagerForSQL();

    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Text smallText;
    @FXML
    private Text bigText;

    @FXML
    public void initialize()
    {
        UnaryOperator<TextFormatter.Change> integerFilter = change ->
        {
            String newText = change.getControlNewText();
            if(newText.matches("-?([1-9][0-9]*)?"))
            {
                return change;
            }
            return null;
        };

        loginField.setTextFormatter(
                new TextFormatter<Integer>(new IntegerStringConverter() , null , integerFilter));

        loginButton.setOnAction((action) ->
        {
            if(loginManager.getEmployeeByID(new Employee(Integer.parseInt(loginField.getText()) , passwordField.getText()).toDTO()) != null)
            {
                Employee logger = new Employee(loginManager
                        .getEmployeeByID(new Employee(Integer.parseInt(loginField.getText()) ,
                                passwordField.getText()).toDTO()));
                try
                {
                    if(logger.getJob().equals("TypicalJob"))
                    {
                        mainWindow.loadEmployeeWindow(logger);
                    }
                    else if(logger.getJob().equals("Admin"))
                    {
                        mainWindow.loadAdminDocumentWindow(logger);
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                smallText.setText("wrong username or password");
                smallText.setLayoutX(208);
                loginField.setText("");
                passwordField.setText("");
                smallText.setFill(Color.RED);
            }
        });
    }

    public void setMainApp(MainWindow mainApp)
    {
        mainWindow = mainApp;
    }
}

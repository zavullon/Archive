package project.userinterface;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

public class DiagramController
{
    private MainWindow mainWindow;

    @FXML
    private Button usersButton;
    @FXML
    private Button diagramButton;
    @FXML
    private PieChart pieChart;

    @FXML
    public void initialize()
    {
        diagramButton.setDisable(true);
        usersButton.setOnAction((action) ->
        {
            try
            {
                mainWindow.loadEmployeeWindow(mainWindow.getLogger());
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
        pieChart.setData(mainWindow.getDiagramData());
    }
}

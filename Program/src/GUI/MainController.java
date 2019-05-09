package GUI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import CA.Cell;
import Files.LoadFile;
import CA.MatrixOfCells;
import sample.Observer.Observer;
import sample.PredefinedItems.AndGate;
import sample.PredefinedItems.OrGate;
import sample.PredefinedItems.PredefinedItem;
import Files.SaveFile;

import java.io.IOException;
import java.io.File;
import java.net.MalformedURLException;

public class MainController implements Observer {

    private MatrixOfCells matrixOfCells;

    public static final double cellSize = 10.0;
    public static final double simulationFieldWidth = 378;
    public static final double simulationFieldHeight = 294;
    public static final double maximalSimulationFieldSize = 525;
    @FXML
    private Slider speedSlider;

    @FXML
    private BorderPane simulationPane;

    @FXML
    private TextField heightTextArea;

    @FXML
    private TextField widthTextArea;

    @FXML
    private TextField numberOfGenerationsTextField;

    @FXML
    private Circle currentCellCircle;

    @FXML
    private Button createSimulationFieldButton;

    private Color currentColor = Color.color(1,1,0);

    private PredefinedItem currentItem;

    private Stage primaryStage;

    private Boolean stopThread = true;

    private GraphicalMatrixOfCells graphicalMatrixOfCells;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


    @FXML
    public void loadFile(ActionEvent event)throws MalformedURLException  {
        if(stopThread==false){
            Message("We are very sad but you can not read the file while the program is running. Press Button Pause to pause program.",Alert.AlertType.ERROR);
            return;
        }
        FileChooser fc = new FileChooser();

        fc.setTitle("Open File Dialog");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG Files", "*.png", "*.PNG", "*.Png"));
        File file = fc.showOpenDialog(null);
        if(file!=null){
            if(graphicalMatrixOfCells!=null){
                graphicalMatrixOfCells=null;
                simulationPane.getChildren().clear();
            }
            LoadFile loadFile = new LoadFile(file);
            int width=loadFile.getwidthimg();
            int height=loadFile.getwidthimg();
            if(width*cellSize>maximalSimulationFieldSize) {
                Message("The width of the file is too large.The file will be counted up to the maximum width of the plate.", Alert.AlertType.INFORMATION);
                width=50;
            }
            if(height*cellSize>maximalSimulationFieldSize) {
                Message("The height of the file is too large.The file will be counted up to the maximum height of the plate.", Alert.AlertType.INFORMATION);
                height=50;
            }
            createSimulationField(width,height, loadFile);

        }else {
            System.out.println("Plik został nie wybrany");
        }
    }

    public synchronized void pauseSimulation() {
        if(!stopThread)
            stopThread = true;
    }

    @FXML
    public void runWireWorld(ActionEvent event) {
            stopThread = false;
            graphicalMatrixOfCells.readGraphicalMatrixofCells(matrixOfCells);
            matrixOfCells.setTimeGap(speedSlider.getValue());
            int numberOfGenerations;
            try {
                numberOfGenerations = Integer.valueOf(numberOfGenerationsTextField.getText());
                System.out.println(numberOfGenerations);
            } catch (NumberFormatException e) {
                Message("Value in filed number of generations have to be numerical.",Alert.AlertType.ERROR);
                return;
            }
            if(numberOfGenerations<0){
                Message("A negative value of the generations is given. Please provide the correct value for generations.",Alert.AlertType.ERROR);
                return;
            }
            matrixOfCells.setNumberOfGenerations(numberOfGenerations);
            matrixOfCells.startWireWorld(this);
    }

    @FXML
    public void saveFile(ActionEvent event) throws IOException {
        if(stopThread==false){
            Message("We are very sad but you can not save the file while the program is running. Press Button Pause to pause program.",Alert.AlertType.ERROR);
            return;
        }
        FileChooser fc = new FileChooser();
        fc.setTitle("Save File Diag");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fc.getExtensionFilters().add(extFilter);
        File file = fc.showSaveDialog(null);
        if (file != null) {
            if (graphicalMatrixOfCells != null) {
                SaveFile saveFile=new SaveFile(graphicalMatrixOfCells.getHeight(),graphicalMatrixOfCells.getWidth(),file);
                for (int i = 0; i < graphicalMatrixOfCells.getHeight(); i++)
                    for (int j = 0; j < graphicalMatrixOfCells.getWidth(); j++) {
                       saveFile.writePixel(i,j,graphicalMatrixOfCells.getColor(j,i));
                    }
                saveFile.writeImage();
            } else {
                Message("File was not saved.",Alert.AlertType.INFORMATION);
                System.out.println("Zapisywanie było nie zakończone.");

            }
        }else {
            System.out.println("Plik nie wybrany");
        }

    }

    @FXML
    public void setConductor(ActionEvent event) {
        currentColor = Color.color(1,1,0);
    }

    @FXML
    public void setElectronHead(ActionEvent event) {
        currentColor = Color.color(0,0,1);
    }

    @FXML
    public void setElectronTail(ActionEvent event) {
        currentColor = Color.color(1,0,0);
    }

    @FXML
    public void setEmpty(ActionEvent event) {
        currentColor = Color.color(0,0,0);
    }


    public void setSpeed(MouseEvent event) {
        if(matrixOfCells != null)
            matrixOfCells.setTimeGap(speedSlider.getValue());
    }

    @FXML
    void buttonMouseEntered(MouseEvent event) {
        ((Button)event.getSource()).setEffect(new Reflection());
    }

    @FXML
    void buttonMouseExited(MouseEvent event) {
        ((Button)event.getSource()).setEffect(null);
    }

    @FXML
    void buttonMousePressed(MouseEvent event) {
        ((Button)event.getSource()).setOpacity(0.7);
        currentCellCircle.setFill(((Button)event.getSource()).getBackground().getFills().get(0).getFill());
    }

    @FXML
    void buttonMouseReleased(MouseEvent event) {
        ((Button)event.getSource()).setOpacity(1);
    }

    private int getMatrixWidth()
    {
        try {
            if(Integer.valueOf(widthTextArea.getText())*cellSize<maximalSimulationFieldSize){
            return Integer.valueOf(widthTextArea.getText());
            }
        } catch (NumberFormatException e) {

        }
        Message("The width is too high. The height value was set to the maximum.",Alert.AlertType.INFORMATION);
        return 50;
    }


    private int getMatrixHeight()
    {
        try {
            if(Integer.valueOf(heightTextArea.getText())*cellSize<maximalSimulationFieldSize)
            return Integer.valueOf(heightTextArea.getText());
        } catch (NumberFormatException e) {


        }

        Message("The height is too high. The width value was set to the maximum.",Alert.AlertType.INFORMATION);
        return 50;
    }


    public void initialize()
    {
        speedSlider.setMax(10);
        speedSlider.setMin(1);

        speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
        public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                try{
                    matrixOfCells.setTimeGap(new_val.doubleValue());
                }catch (NullPointerException e)
                {

                }
            }
        });
    }

    @FXML
    public void setSize(){
        int height=getMatrixHeight();
        int width=getMatrixWidth();
        if(height<0 | width<0){
            Message("The dimensions are erroneous. Please provide the correct height and width dimensions.",Alert.AlertType.ERROR);
            return;
        }
     //   createSimulationFieldButton.setTextFill(Paint.valueOf("#D6DBDF"));
        createSimulationFieldButton.setStyle("-fx-background-color: #B2BABB");
        heightTextArea.editableProperty().setValue(false);
        heightTextArea.setStyle("-fx-background-color: #B2BABB");
        widthTextArea.editableProperty().setValue(false);
        widthTextArea.setStyle("-fx-background-color: #B2BABB");



        createSimulationField(width, height, null);
    }

    public void createSimulationField( int width , int height, LoadFile loadFile)
    {

        if (graphicalMatrixOfCells == null)
        {
            if (width*cellSize > simulationFieldWidth)
            {
                primaryStage.setWidth(primaryStage.getWidth() + (width)*(cellSize+1) - 378);
            }

            if (height*cellSize > simulationFieldHeight)
            {
                primaryStage.setHeight(primaryStage.getHeight() + (height)*(cellSize+1) - 294);
            }
            graphicalMatrixOfCells = new GraphicalMatrixOfCells(height, width, cellSize);

            for (int i = 0; i < height; i++)
                for (int j = 0; j < width; j++) {

                    if( loadFile == null )
                        graphicalMatrixOfCells.setColor(i, j, Color.BLACK);
                    else
                        graphicalMatrixOfCells.setColor(i, j, loadFile.getColor(j,i));

                    EventHandler simulationPaneEventHandler = new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                                graphicalMatrixOfCells.setColorByCoordinate(event.getY(), event.getX(), currentColor);

                        }
                    };

                    simulationPane.addEventHandler(MouseEvent.MOUSE_DRAGGED, simulationPaneEventHandler);
                    simulationPane.addEventHandler(MouseEvent.MOUSE_PRESSED, simulationPaneEventHandler);


                }
            graphicalMatrixOfCells.show(simulationPane);
            matrixOfCells = new MatrixOfCells(graphicalMatrixOfCells.getHeight(), graphicalMatrixOfCells.getWidth());

        }
        else{

            }
    }


    EventHandler addItem = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            graphicalMatrixOfCells.addPredefinedItem(event.getY(), event.getX(), currentItem);
        }
    };



    EventHandler setItemPosition = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            simulationPane.removeEventHandler(MouseEvent.MOUSE_MOVED, addItem);
            graphicalMatrixOfCells.clearLastItemPosition();
            currentItem = null;
        }
    };

    public void addPredefinedItem( PredefinedItem item ) {


        currentColor = Color.color(1,1,0);
        currentCellCircle.setFill(Color.color(1,1,0));


        simulationPane.addEventHandler(MouseEvent.MOUSE_MOVED, addItem);
        simulationPane.addEventHandler(MouseEvent.MOUSE_CLICKED, setItemPosition);


    }

    public void addOrGate(){
        if (currentItem == null)
            currentItem = new OrGate();
        else
        {
            graphicalMatrixOfCells.clearLastInsertedItem(currentItem);
            currentItem = new OrGate();
        }
        addPredefinedItem(currentItem);
    }

    public void addAndGate() {
        if (currentItem == null)
            currentItem = new AndGate() ;
        else
        {
            graphicalMatrixOfCells.clearLastInsertedItem(currentItem);
            currentItem = new AndGate() ;
        }
        addPredefinedItem(currentItem);
        }

    public void update( Cell[][] matrixOfCells )
    {
        if(stopThread)
            this.matrixOfCells.pauseThread();
        else
              graphicalMatrixOfCells.setGraphicalMatrixOfCells(matrixOfCells);
    }

    private void Message(String errorMasseg, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setTitle("Messeg from developers");
        alert.setHeaderText("Oh my God! This window appears when something happened not according to plan!");
        alert.setContentText(errorMasseg);

        alert.showAndWait();
    }
}

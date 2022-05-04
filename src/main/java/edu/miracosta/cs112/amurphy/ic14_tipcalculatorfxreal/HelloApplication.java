package edu.miracosta.cs112.amurphy.ic14_tipcalculatorfxreal;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.ref.Cleaner;
import java.text.NumberFormat;

public class HelloApplication extends Application {

    //Define fields (instance variables) for each of the
    // Nodes that change or are interacted with by the user

  private TextField billAmountTF = new TextField();
  private Slider tipPercentSlider = new Slider(0,30,15); //min max default
    private Label tipPercentageLabel = new Label("15%");
  private TextField tipAmountTF = new TextField();
  private TextField totalAmountTF = new TextField();
  private TextField test = new TextField();
  private Button clearButton = new Button("Clear");
  private Button calcButton = new Button("Calculate");




    @Override
    public void start(Stage stage) throws IOException {
        GridPane gridPane = new GridPane();

        // Set the horizontal and vertical gap of the GridPane(affects all nodes inside)
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        //Set alignment to center (default is topleft)
        gridPane.setAlignment(Pos.CENTER);

        // add Label "Bill Amount" to gridPane
        //left side
        gridPane.add(new Label("Bill Amount:"),0,0);
        gridPane.add(tipPercentageLabel,0,1);
        gridPane.add(new Label("Tip Amount:"),0,2);
        gridPane.add(new Label("Total Amount:"),0,3);


        //right side
        gridPane.add(billAmountTF,1,0);
        gridPane.add(tipPercentSlider,1,1);
        tipPercentSlider.setShowTickMarks(true);
        tipPercentSlider.setShowTickLabels(true);
        tipPercentSlider.setMajorTickUnit(5f);
        tipPercentSlider.setBlockIncrement(5f);
        tipPercentSlider.setSnapToTicks(true);



        gridPane.add(tipAmountTF,1,2);
        tipAmountTF.setEditable(false); //disable input
        tipAmountTF.setFocusTraversable(false); //cant hit tab through them
        tipAmountTF.setDisable(true);//grayed out
        gridPane.add(totalAmountTF,1,3);
        totalAmountTF.setEditable(false); //disable input
        totalAmountTF.setFocusTraversable(false); //cant hit tab through them
        totalAmountTF.setDisable(true);//grayed out


        //HBox - horizontal box of Nodes
        HBox hBox = new HBox(clearButton,calcButton);
        hBox.setSpacing(5);
        hBox.setAlignment(Pos.BASELINE_RIGHT);
        gridPane.add(hBox,1, 4);

        ///Wire up the clearButton with thr clear() method using lambda
        // Parameter of the setOnAction method will be a new, anonymous inner class
        // that implements the handle() method
        //handle() calls our clear() method

        // lambda expression is a shortcut for code:
        // Syntax: (->) means lambda
        // parameter -> method call
        // actionEvent -> clear()
        clearButton.setOnAction(e -> clear());
        calcButton.setOnAction(e -> calculate());

        //wire up slider and bill amount text field
        //slider listening to its value
        //text field, listening to text
        //Listener is an interface, watches for changes in a node
        tipPercentSlider.valueProperty().addListener((obsVal, oldVal, newVal)  -> calculate()); //3 params, differs from actionevent e
        billAmountTF.textProperty().addListener((obsVal, oldVal, newVal)  -> calculate());


        Scene scene = new Scene(gridPane, 320, 240);
        stage.setTitle("Tip Calculator");
        stage.setScene(scene);
        stage.show();
    }

    // Clear method which will be wired up to the ClearButton
    public void clear() {
        billAmountTF.clear();
        tipAmountTF.clear();
        totalAmountTF.clear();
        tipPercentSlider.setValue(15); //set slider back to default
        //return focus back to BillAmountTF
        billAmountTF.requestFocus();
    }

    /// Calculate method will be wired up to calculateButton, billAmountTF and TipPercentSlider
    public void calculate()
    {
        //Update the sliderlabel with the sliderval

        tipPercentageLabel.setText((int) tipPercentSlider.getValue() + "%");


        //billAmount

        if(billAmountTF.getText().isEmpty())
            return;
        double billAmount = Double.parseDouble(billAmountTF.getText());
        //TipPercent
        double tipPercent = tipPercentSlider.getValue() / 100;
        //TipAmount
        double tipAmount = billAmount *tipPercent;
        // TotalAmount
        double totalAmount = billAmount + tipAmount;

        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tipAmountTF.setText(currency.format(tipAmount));
        totalAmountTF.setText(currency.format(totalAmount));
    }

    public static void main(String[] args) {
        launch();
    }
}
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MetricConverterFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Set up the primary stage
        primaryStage.setTitle("Metric Converter");

        // Create input and output fields
        TextField valueInput = new TextField();
        TextField resultOutput = new TextField();
        resultOutput.setEditable(false);

        // Create dropdowns for units
        ComboBox<String> fromUnit = new ComboBox<>();
        fromUnit.getItems().addAll("kg", "g", "km", "mm");
        fromUnit.setValue("kg");

        ComboBox<String> toUnit = new ComboBox<>();
        toUnit.getItems().addAll("lb", "oz", "mile", "inch");
        toUnit.setValue("lb");

        // Create a button to perform the conversion
        Button convertButton = new Button("Convert");

        // Display conversion result
        convertButton.setOnAction(e -> {
            try {
                double value = Double.parseDouble(valueInput.getText());
                String from = fromUnit.getValue();
                String to = toUnit.getValue();

                double result = performConversion(value, from, to);
                if (result == -1) {
                    resultOutput.setText("Conversion not supported.");
                } else {
                    resultOutput.setText(String.format("%.4f", result));
                }
            } catch (NumberFormatException ex) {
                resultOutput.setText("Invalid number format.");
            }
        });

        // Set up the layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));

        // Add components to the layout
        grid.add(new Label("Value:"), 0, 0);
        grid.add(valueInput, 1, 0);
        grid.add(new Label("From:"), 0, 1);
        grid.add(fromUnit, 1, 1);
        grid.add(new Label("To:"), 0, 2);
        grid.add(toUnit, 1, 2);
        grid.add(convertButton, 1, 3);
        grid.add(new Label("Result:"), 0, 4);
        grid.add(resultOutput, 1, 4);

        // Create a scene and add it to the stage
        Scene scene = new Scene(grid, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private double performConversion(double value, String fromUnit, String toUnit) {
        switch (fromUnit + "_" + toUnit) {
            case "kg_lb":
                return value * 2.20462;
            case "g_oz":
                return value * 0.035274;
            case "km_mile":
                return value * 0.621371;
            case "mm_inch":
                return value * 0.0393701;
            default:
                return -1; // Unsupported conversion
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


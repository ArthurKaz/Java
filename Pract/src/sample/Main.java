package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Label label0  = new Label("Введіть радіус");
        TextField textField0 = new TextField();
        Label label  = new Label("Введіть X");

        TextField textField = new TextField();
        Label label2  = new Label("Введіть Y");
        TextField textField2 = new TextField();

        textField2.setMaxWidth(80);
        textField.setMaxWidth(80);
        textField0.setMaxWidth(80);

        Button button = new Button("Розрахувати");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Розрахунок");
                alert.setHeaderText(null);

                try {
                    double r = Double.parseDouble(textField0.getText());
                    double x = Double.parseDouble(textField.getText());
                    double y = Double.parseDouble(textField2.getText());

                    if (Math.pow(r,2) >Math.pow(y,2)+Math.pow(x,2)) alert.setContentText("Координати ("+ x + ","+y + ") входять в середину кола радіусом "+r);
                    else alert.setContentText("Координати ("+ x + ","+y + ") не входять в середину кола радіусом "+r);

                } catch (NumberFormatException e) {
                    alert.setContentText("Для роозрахунку потрібно ввести цифри!");
                }
                alert.show();
            }});

        FlowPane root = new FlowPane(10, 10,label0,textField0,label,textField,label2,textField2, button);
        Scene scene = new Scene(root, 200, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

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
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception{

        this.stage = stage;


        stage.setScene(mainMenu());
        stage.setTitle("Hello World");
        stage.setWidth(300);
        stage.setHeight(250);
        stage.show();

    }

    public static Scene mainMenu(){
        Button task1 = new Button("Завдання 1");
        task1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //   stage.hide();

                setScene(task1());
            }});
        Button task2 = new Button("Завдання 2");
        task2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //   stage.hide();

                setScene(task2());
            }});

        FlowPane root = new FlowPane(10, 10,task1,task2);
        return new Scene(root, 300, 250);
    }
    private static void setScene(Scene scene){
        stage.setScene(scene);
    }

    public static Scene task1(){
        Label label2 = new Label("Введіть чотирьохзначне число");
        TextField label = new TextField();
        label.setMaxWidth(70);
        Text info = new Text();
        Button button = new Button("Розрахувати");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Розрахунок");
                alert.setHeaderText(null);

                try {
                    String text = label.getText();
                    if(text.isEmpty()) throw new IOException("Введіть чотирьохзначне число");
                    char[] numbers = text.toCharArray();
                    if (numbers.length != 4) throw new IOException("Введіть чотирьохзначне число");
                    int n = Integer.parseInt(label.getText());

                    int S = 0;
                    int D = 1;
                    for (char num:numbers) {
                        S+= Integer.parseInt(String.valueOf(num));
                        D*= Integer.parseInt(String.valueOf(num));
                    }
                    alert.setContentText("Розрахунок завершено");


                    info.setText("Сума - "+S+ " Добуток - "+D);
                }catch (IOException e){
                    alert.setContentText(e.getMessage());
                }
                catch (NumberFormatException e){
                    alert.setContentText("Для розрахунку потрібно ввести чотирьохзначне число");
                }
                alert.showAndWait();


            }
        });


        Button back = new Button("Назад");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setScene(mainMenu());
            }
        });
        Group group1 = new Group(button);
        FlowPane root = new FlowPane(10, 10,  label2,label,group1,info,back);
        Scene scene = new Scene(root, 300, 250);
        return scene;
    }

    public static Scene task2(){
        Text text = new Text("Введіть число від 1 до 24");
        TextField label = new TextField();
        label.setMaxWidth(70);
        Text info = new Text();
        Button button = new Button("Розрахувати");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Розрахунок");
                alert.setHeaderText(null);
                try {
                    int index = Integer.parseInt(label.getText());
                    if(index > 24 || index < 1) throw new IOException("Для розрахунку потрібно ввести число від 1 до 24");

                    switch (index){
                        case 1 :
                        case 13:
                            info.setText("Перша година");
                            break;
                        case 2:
                        case 14:
                            info.setText("Друга година");
                            break;
                        case 3:
                        case 15:
                            info.setText("Третя година");
                            break;
                        case 4:
                        case 16:
                            info.setText("Четверта година");
                            break;
                        case 5:
                        case 17:
                            info.setText("П'ята година");
                            break;
                        case 6:
                        case 18:
                            info.setText("Шоста година");
                            break;
                        case 7:
                        case 19:
                            info.setText("Сьома година");
                            break;
                        case 8:
                        case 20:
                            info.setText("Восьма година");
                            break;
                        case 9:
                        case 21:
                            info.setText("Дев'ята година");
                            break;
                        case 10:
                        case 22:
                            info.setText("Десята година");
                            break;
                        case 11:
                        case 23:
                            info.setText("Одинадцята година");
                            break;
                        case 12:
                        case 24:
                            info.setText("Дванадцята година");
                            break;

                    }
                }catch (NumberFormatException e){
                    alert.setContentText("Для розрахунку потрібно ввести число");
                    alert.showAndWait();
                } catch (IOException e) {
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }

            }});

        Button back = new Button("Назад");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setScene(mainMenu());
            }
        });
        FlowPane root = new FlowPane(10,10,text,label,button,info,back);
        return new Scene(root,300,250);
    }
    public static void main(String[] args) {
        Application.launch(args);
    }

}

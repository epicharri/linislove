package linislove.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import linislove.logic.RandomLinearSystem;
import linislove.logic.Solution;
import linislove.logic.Instructions;
import linislove.mylittlemath.LinearSystem;

/**
 *
 * Graafinen käyttöliittymä. Ohjelmaa käytetään tämän käyttöliittymän avulla.
 */
public class Gui extends Application {

    /**
     * Metodi luo käyttöliittymän komponentit.
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        //ActionEvent event = new ActionEvent();
        BorderPane layout = new BorderPane();

        VBox menu = new VBox();
        menu.setPadding(new Insets(20, 20, 20, 20));
        menu.setSpacing(10);
        TextArea input = new TextArea();
        Label label = new Label("");
        input.setPromptText("Syötä vektorijono tai yhtälöryhmä.");
        Tooltip tooltipInput = new Tooltip("Syötä tähän vektorijono tai "
                + "yhtälöryhmä.");
        Tooltip.install(input, tooltipInput);

        input.prefColumnCountProperty().bind(input.textProperty().length());

        TextArea answer = new TextArea();
        answer.setWrapText(true);

        Button buttonA = new Button("Vektorijonon vapaus");
        Button buttonB = new Button("Ratkaise yhtälöryhmä");
        Button buttonC = new Button("Arvo yhtälöryhmä");
        Button buttonD = new Button("Tyhjennä kentät");
        Button buttonE = new Button("Käyttöohje");
        buttonA.setMinWidth(200);
        buttonB.setMinWidth(200);
        buttonC.setMinWidth(200);
        buttonD.setMinWidth(200);
        buttonE.setMinWidth(200);
        Tooltip tooltipA = new Tooltip("Ratkaisee onko vektorijono vapaa.");
        Tooltip.install(buttonA, tooltipA);
        Tooltip tooltipB = new Tooltip("Ratkaisee kvadraattisen yhtälöryhmän\njos sille löytyy yksikäsitteinen ratkaisu.");
        Tooltip.install(buttonB, tooltipB);
        Tooltip tooltipC = new Tooltip("Luo satunnainen yhtälöryhmä");
        Tooltip.install(buttonC, tooltipC);
        Tooltip tooltipD = new Tooltip("Tyhjennä kentät");
        Tooltip.install(buttonD, tooltipD);
        Tooltip tooltipE = new Tooltip("Antaa ohjeet");
        Tooltip.install(buttonE, tooltipE);
    

        menu.getChildren().addAll(label, input, buttonA, buttonB, buttonC, buttonD, buttonE, answer);
        layout.setTop(menu);

        buttonA.setOnAction((ActionEvent eventA) -> {
            answer.clear();
            String setOfVectors = input.getText();
            if (!setOfVectors.trim().isEmpty()) {
                answer.setText("Ratkaistaan vektorijonon vapautta.");
                try {
                    String freedom = Solution.solveLinearDependency(setOfVectors);
                    answer.setText(freedom);
                } catch (Exception e) {
                    answer.setText(e.getMessage());
                }
            }
        });

        buttonB.setOnAction((eventB) -> {
            String system = input.getText();
            if (!system.trim().isEmpty()) {
                try {
                    String solution = Solution.solveLinearSystem(system);
                    answer.setText(solution);
                } catch (Exception e) {
                    answer.setText(e.getMessage());
                }
            }
        });

        buttonC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent eventC) {
                answer.clear();
                LinearSystem system = RandomLinearSystem.create(10, 10, 9);
                input.setText(system.toString());
            }
        });

        buttonD.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent eventD) {
                answer.clear();
                input.clear();
            }
        });

        buttonE.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent eventE) {
                answer.clear();
                answer.setText(Instructions.INSTRUCTIONS);
            }
        });        
        
        Scene scene = new Scene(layout, 1000, 600);
        scene.getStylesheets().add(getClass().getResource("/materialdesign.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/elementsofharmony.css").toExternalForm());
        Image heart = new Image(getClass().getResource("/like.png").toExternalForm());
        stage.getIcons().add(heart);
        stage.setTitle("LinisLove");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String[] args) {
        launch(args);
        
}*/
}

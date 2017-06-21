package linislove.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import linislove.logic.RandomLinearSystem;
import linislove.logic.Solution;
import linislove.mylittlemath.Count;
import linislove.mylittlemath.LinearSystem;
import linislove.mylittlemath.Matrix;
import linislove.mylittlemath.Rational;
import linislove.mylittlemath.SetOfVectors;

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

        ActionEvent event = new ActionEvent();

        BorderPane layout = new BorderPane();

        HBox menu = new HBox();
        menu.setPadding(new Insets(20, 20, 20, 20));
        menu.setSpacing(10);
        //TextField input = new TextField();
        TextArea input = new TextArea();
        Label label = new Label("");
        input.setPromptText("Syötä vektorijono tai yhtälöryhmä.");
        Tooltip tooltipInput = new Tooltip("Syötä tähän vektorijono tai "
                + "yhtälöryhmä.");
        Tooltip.install(input, tooltipInput);
        input.setMinWidth(400);
        input.setMaxWidth(300);
        input.prefColumnCountProperty().bind(input.textProperty().length());

        TextArea answer = new TextArea();

        Button buttonA = new Button("Vapaus");
        Button buttonB = new Button("Ratkaise");
        Button buttonC = new Button("Arvo yhtälöryhmä");
        buttonA.setMinWidth(100);
        buttonB.setMinWidth(100);
        buttonC.setMinWidth(100);
        Tooltip tooltipA = new Tooltip("Ratkaisee onko vektorijono vapaa.");
        Tooltip.install(buttonA, tooltipA);
        Tooltip tooltipB = new Tooltip("Ratkaisee kvadraattisen yhtälöryhmän\njos sille löytyy yksikäsitteinen ratkaisu.");
        Tooltip.install(buttonB, tooltipB);
        Tooltip tooltipC = new Tooltip("Luo satunnainen yhtälöryhmä");
        Tooltip.install(buttonC, tooltipC);

        menu.getChildren().addAll(label, input, buttonA, buttonB, buttonC, answer);
        layout.setTop(menu);

        buttonA.setOnAction((eventA) -> {

            // Tässä on logiikkaan kuuluvaa koodia mukana testaamistarkoituksessa.
            // Lopullisessa ohjelmassa logiikkakoodia ei ole käyttöliittymässä.
            answer.setText("Ratkaistaan vektorijonon vapautta.");
            String queue = input.getText();

            try {
                Matrix matrix = new Matrix(new SetOfVectors(queue));
                Rational det = Count.det(matrix);
                String freedom = "Vektorijonosta muodostetun matriisin\n"
                        + "determinantti on " + det + ".\n";
                if (det.equals(Rational.ZERO)) {
                    freedom += "Koska determinantti "
                            + "on 0, on vektorijono lineaarisesti\n riippuvainen "
                            + "eli sidottu.";
                } else {
                    freedom += "Koska determinantti on nollasta eriävä, \n"
                            + "on vektorijono lineaarisesti riippumaton eli vapaa.";
                }
                answer.setText(freedom);
            } catch (Exception e) {
                answer.setText("Väärä syöte vektorijonoksi.");
            }
        });

        buttonB.setOnAction((eventB) -> {
            answer.setText("Ratkaistaan yhtälöryhmää.");
            String system = input.getText();
            try {
                String solution = Solution.solveLinearSystem(system);
                answer.setText(solution);
            } catch (Exception e) {
                answer.setText("Väärä syöte yhtälöryhmäksi.");
            }
        });

        buttonC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent eventC) {
                LinearSystem system = RandomLinearSystem.create(7, 10, 9);
                input.setText(system.toString());
            }
        });

        Scene scene = new Scene(layout, 1000, 500);
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

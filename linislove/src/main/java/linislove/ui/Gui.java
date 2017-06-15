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
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import linislove.logic.Solution;
import linislove.mylittlemath.Count;
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
        Label label = new Label("Syötä vektorijono:");
        input.setPromptText("Syötä vektorijono");        

        input.setMinWidth(400);
        input.setMaxWidth(300);
        input.prefColumnCountProperty().bind(input.textProperty().length());
       
        
        
        
        TextArea answer = new TextArea();

        Button buttonA = new Button("Selvitä vapaus");
        Button buttonB = new Button("Ratkaise yhtälöryhmä");

        menu.getChildren().addAll(label, input, buttonA, buttonB, answer);
        layout.setTop(menu);

        buttonA.setOnAction((eventA) -> {

            // Tässä on logiikkaan kuuluvaa koodia mukana testaamistarkoituksessa.
            // Lopullisessa ohjelmassa logiikkakoodia ei ole käyttöliittymässä.
            String queue = input.getText();
            Matrix matrix = new Matrix(new SetOfVectors(queue));
            Rational det = Count.det(matrix);
            try {
                String freedom = (det.equals(Rational.ZERO)) ? "Jono on sidottu" : "Jono on vapaa";
                answer.setText(freedom);
            } catch (Exception e) {
                answer.setText("Väärä syöte.");
            }
        });
        
        buttonB.setOnAction((eventB) -> {
            String system = input.getText();
            try {
                String solution = Solution.solveLinearSystem(system);
                answer.setText(solution);
            } catch (Exception e) {
                answer.setText("Väärä syöte.");
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

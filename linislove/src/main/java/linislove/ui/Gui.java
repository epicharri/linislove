package linislove.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
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
        TextField text = new TextField();
        TextField textRight = new TextField();
        Label label = new Label("Syötä vektorijono vasemman puoleiseen laatikkoon muodossa (1,0),(1,1).\n"
                + " Ohjelman suoritus loppuu jos syötät useamman vektorin "
                + "kuin avaruuden\n"
                + " dimensio tai eri avaruuksien vektoreita. "
                + "Ohjelma siis ei tarkista vielä onko syöte oikein.\n");
        text.setPromptText("Syötä vektorijono");
        Button buttonA = new Button("Vapaa vai sidottu?");
        //Button buttonB = new Button("Twilight Answer");

        menu.getChildren().addAll(label, text, buttonA, textRight);
        layout.setTop(menu);

        buttonA.setOnAction((event2) -> {

            // Tässä on logiikkaan kuuluvaa koodia mukana testaamistarkoituksessa.
            // Lopullisessa ohjelmassa logiikkakoodia ei ole käyttöliittymässä.
            String queue = text.getText();
            Matrix matrix = new Matrix(new SetOfVectors(queue));
            Rational det = Count.det(matrix);
            try {
                String freedom = (det.equals(Rational.ZERO)) ? "Jono on sidottu" : "Jono on vapaa";
                textRight.setText(freedom);
            } catch (Exception e) {
                textRight.setText("Väärä syöte.");
            }
        });
        Scene scene = new Scene(layout, 400, 300);
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

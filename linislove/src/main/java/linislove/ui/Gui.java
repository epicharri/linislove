/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
 * @author harrikah
 */
public class Gui extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        ActionEvent event = new ActionEvent();
        // 1. Luodaan sovelluksen käyttämä sanakirja
        /*Sanakirja sanakirja = new Sanakirja();

	// 2. Luodaan näkymät ("alinäkymät")
	Harjoittelunakyma harjoittelunakyma = new Harjoittelunakyma(sanakirja)
	Syottonakyma syottonakyma = new Syottonakyma(sanakirja);
         */
        // 3. Luodaan päätason layout
        BorderPane layout = new BorderPane();

        // 3.1. Luodaan päätason asettelun menu
        HBox menu = new HBox();
        menu.setPadding(new Insets(20, 20, 20, 20));
        menu.setSpacing(10);
        TextField text = new TextField();
        TextField textRight = new TextField();

        // 3.2. Luodaan valikon napit
        Button buttonA = new Button("LinisLove <3");
        Button buttonB = new Button("Twilight Answer");

        // 3.3. Lisätään napit valikkoon
        menu.getChildren().addAll(text, buttonA, textRight);
        layout.setTop(menu);

        buttonA.setOnAction((event2) -> {

            String queue = text.getText();
            Matrix matrix = new Matrix(new SetOfVectors(queue));
            Rational det = Count.det(matrix);
            String freedom = (det.equals(Rational.ZERO)) ? "Jono on sidottu" : "Jono on vapaa";
            textRight.setText(freedom);

        });

        // 4. Liitetään alinäkymät nappeihin. Napin painaminen vaihtaa alinäkymää.
        //lisaanappi.setOnAction((event) -> layout.setCenter(syottonakyma.getNakyma()));
        //harjoittelenappi.setOnAction((event) -> layout.setCenter(harjoittelunakyma.getNakyma()));
        // 5. Näytetään ensin syöttönäkymä
        //asettelu.setCenter(syottonakyma.getNakyma());
        // 6. Luodaan päänäkymä ja asetetaan päätason layout siihen
        Scene scene = new Scene(layout, 400, 300);
        scene.getStylesheets().add(getClass().getResource("/materialdesign.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/elementsofharmony.css").toExternalForm());
        Image heart = new Image(getClass().getResource("/like.png").toExternalForm());
        stage.getIcons().add(heart);
        stage.setTitle("LinisLove");
        // 7. Näytetään sovellus
        stage.setScene(scene);
        stage.show();
        // layout.setStyle("-fx-background-color: blue");

    }

    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String[] args) {
        launch(args);
        
}*/
}

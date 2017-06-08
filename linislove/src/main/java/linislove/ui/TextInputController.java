/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.ui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * Controller -luokka tekstin syöttöön.
 */
public class TextInputController {

    public TextField inputField;
    public Button logButton;

    /**
     * Metodi käsittelee annettua tekstisyötettä.
     *
     * @param event ActionEvent
     */
    public void handle(ActionEvent event) {
        String input = inputField.getText();
        System.out.printf("Syötteesi on %s", inputField);
    }

}

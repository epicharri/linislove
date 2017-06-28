/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linislove.linislove;

import javafx.application.Application;
import linislove.ui.Gui;

/**
 *
 * main luokka. Tämä luokka pelkästään käynnistää graafisen käyttöliittymän.
 */
public class LinisLove {

    public static void main(String[] args) throws InterruptedException, Exception {

        Application.launch(Gui.class, args);
    }
}

package org.acme.game;

import org.acme.game.domain.GameDisplayControl;

/**
 * Main class to start the application
 */
public class GameApp {

    public static void main(final String[] args) {
        System.out.println("Paper-Rock-Scissors");
        System.out.println("Let's start the gaming season");
        new GameDisplayControl().start();
    }

}

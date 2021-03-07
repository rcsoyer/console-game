package org.acme.game;

import org.acme.game.domain.GamePvM;

public class GameApp {

    public static void main(final String[] args) {
        System.out.println("Paper-Rock-Scissors");
        System.out.println("Let's start the gaming season");
        new GamePvM().start();
    }

}

package org.acme.game;

import java.util.Map;
import java.util.Scanner;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import static org.acme.game.GameApp.HandOptions.FINISH;
import static org.acme.game.GameApp.HandOptions.PAPER;
import static org.acme.game.GameApp.HandOptions.ROCK;
import static org.acme.game.GameApp.HandOptions.SCISSORS;

public class GameApp {

    public static void main(final String[] args) {
        System.out.println("Paper-Rock-Scissors");
        System.out.println("Let's start the gaming season");
        new Gaming().run();
    }

    private static class Gaming {

        private final Map<Integer, HandOptions> possibleOptions = Map.of(PAPER.getOption(), PAPER,
                                                                         ROCK.getOption(), ROCK,
                                                                         SCISSORS.getOption(), SCISSORS,
                                                                         FINISH.getOption(), FINISH);

        private void run() {
            final Scanner input = new Scanner(System.in);
            int handSymbol;

            do {
                System.out.println("Show your hand \n\tYour Options: " + HandOptions.options());
                handSymbol = input.nextInt();
                System.out.println("You entered string " + handSymbol);
            } while (handSymbol != 0);
        }
    }

    @Getter
    @ToString
    @RequiredArgsConstructor
    enum HandOptions {
        PAPER(1),
        ROCK(2),
        SCISSORS(3),
        FINISH(0);

        private final int option;

        public static String options() {
            return PAPER.getOption() + ". " + PAPER.name() + "\n\t\t\t\t"
                     + ROCK.getOption() + ". " + ROCK.name() + "\n\t\t\t\t"
                     + SCISSORS.getOption() + ". " + SCISSORS.name() + "\n\t\t\t\t"
                     + FINISH.getOption() + ". " + FINISH.name();
        }
    }
}

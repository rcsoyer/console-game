package org.acme.game.domain;

import java.util.Scanner;

import static org.acme.game.domain.Hand.PAPER;
import static org.acme.game.domain.Hand.ROCK;
import static org.acme.game.domain.Hand.SCISSORS;

/**
 * Class to control flow of user input for a game PvM(player versus machine)
 */
public final class GamePvM {

    private static final int END_GAME = 0;

    private final Scanner input = new Scanner(System.in);
    private final GameScore score = new GameScore();
    private final Match match = new Match();

    /**
     * Only accepts as input from console int numbers on range 0 to 3.
     *
     * @throws java.util.InputMismatchException if typed input is not an int
     * @throws IllegalArgumentException         if the int value informed is not on range 1 to 3
     */
    public void start() {
        System.out.println("\nShow your hand \n\tYour Options: " + options());
        final int userHand = input.nextInt();

        if (userHand == END_GAME) {
            score.showScore();
        } else {
            score.addMatchResult(match.showHands(userHand));
            start();
        }
    }

    private String options() {
        return PAPER.getHand() + ". " + PAPER.name() + "\t\t"
                 + ROCK.getHand() + ". " + ROCK.name() + "\t\t"
                 + SCISSORS.getHand() + ". " + SCISSORS.name() + "\t\t"
                 + END_GAME + ". Endgame\t\t";
    }
}

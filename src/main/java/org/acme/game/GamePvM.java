package org.acme.game;

import java.util.Scanner;

import static org.acme.game.Hand.PAPER;
import static org.acme.game.Hand.ROCK;
import static org.acme.game.Hand.SCISSORS;

public final class GamePvM {

    private static final int END_GAME = 0;

    private final Scanner input = new Scanner(System.in);
    private final GameScore score = new GameScore();
    private final MachineHand machine = new MachineHand();

    public void start() {
        System.out.println("\nShow your hand \n\tYour Options: " + options());
        final int userHand = input.nextInt();

        if (userHand == END_GAME) {
            score.showScore();
        } else {
            showHands(userHand);
            start();
        }
    }

    private void showHands(final int handSymbol) {
        final Hand machineHand = machine.random();
        final Hand userHand = Hand.parse(handSymbol);
        final MatchOutcome outcome = userHand.showDown(machineHand);
        score.addMatchResult(new MatchResult(userHand, machineHand, outcome));
    }

    private String options() {
        return PAPER.getHand() + ". " + PAPER.name() + "\t\t"
                 + ROCK.getHand() + ". " + ROCK.name() + "\t\t"
                 + SCISSORS.getHand() + ". " + SCISSORS.name() + "\t\t"
                 + END_GAME + ". Endgame\t\t";
    }
}

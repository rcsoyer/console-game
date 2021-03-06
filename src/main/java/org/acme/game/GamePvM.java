package org.acme.game;

import java.util.Random;
import java.util.Scanner;

import static org.acme.game.Hands.PAPER;
import static org.acme.game.Hands.ROCK;
import static org.acme.game.Hands.SCISSORS;

public final class GamePvM {

    private static final int END_GAME = 0;

    private final Random random = new Random();
    private final Scanner input = new Scanner(System.in);
    private final GameScore score = new GameScore();

    public void start() {
        System.out.println("\nShow your hand \n\tYour Options: " + options());
        int userHand = input.nextInt();

        if (userHand == END_GAME) {
            score.showScore();
        } else {
            showHands(userHand);
            start();
        }
    }

    private void showHands(final int handSymbol) {
        final Hands machineHand = randomMachineHand();
        final Hands userHand = Hands.parse(handSymbol);
        final MatchOutcome outcome = userHand.beat(machineHand);
        score.addMatchResult(new MatchResult(userHand, machineHand, outcome));
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private Hands randomMachineHand() {
        return Hands.parse(random.ints(1, 4)
                                 .findFirst()
                                 .getAsInt());
    }

    private String options() {
        return PAPER.getHand() + ". " + PAPER.name() + "\t\t"
                 + ROCK.getHand() + ". " + ROCK.name() + "\t\t"
                 + SCISSORS.getHand() + ". " + SCISSORS.name() + "\t\t"
                 + END_GAME + ". Endgame\t\t";
    }
}

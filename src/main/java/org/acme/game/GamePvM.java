package org.acme.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

import static java.util.stream.Collectors.groupingBy;
import static org.acme.game.Hands.PAPER;
import static org.acme.game.Hands.ROCK;
import static org.acme.game.Hands.SCISSORS;

public final class GamePvM {

    private static final int END_GAME = 0;

    private final Map<Integer, Hands> possibleHands = Map.of(PAPER.getHand(), PAPER,
                                                             ROCK.getHand(), ROCK,
                                                             SCISSORS.getHand(), SCISSORS);
    private final Random random = new Random();
    private final Scanner input = new Scanner(System.in);
    private final List<GameResults> results = new LinkedList<>();

    public void start() {
        System.out.println("\nShow your hand \n\tYour Options: " + options());
        int userHand = input.nextInt();

        if (userHand == END_GAME) {
            presentScore();
        } else {
            results.add(matchResult(userHand));
            start();
        }
    }

    private GameResults matchResult(final int handSymbol) {
        final Hands against = randomMachineHand();
        final Hands userHand = userOption(handSymbol);
        final MatchOutcome result = userHand.beat(against);
        System.out.println("Match result: " + result.name());
        System.out.println("You played: " + userHand.name());
        System.out.println("Machine played: " + against.name());
        return new GameResults(userHand, against, result);
    }

    private void presentScore() {
        System.out.println("Endgame");
        System.out.println("Score: ");
        final var groupedResults =
          results.stream().collect(groupingBy(GameResults::result));
        groupedResults.entrySet().forEach(System.out::println);
    }

    private Hands userOption(final int hand) {
        return Optional
                 .ofNullable(possibleHands.get(hand))
                 .orElseThrow(() -> new IllegalArgumentException("Invalid option"));
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private Hands randomMachineHand() {
        return possibleHands.get(random
                                   .ints(1, 3)
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

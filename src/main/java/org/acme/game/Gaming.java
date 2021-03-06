package org.acme.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

import static java.util.stream.Collectors.groupingBy;
import static org.acme.game.HandOptions.PAPER;
import static org.acme.game.HandOptions.ROCK;
import static org.acme.game.HandOptions.SCISSORS;

public final class Gaming {

    private static final int END_GAME = 0;

    private final Map<Integer, HandOptions> possibleHands = Map.of(PAPER.getHand(), PAPER,
                                                                   ROCK.getHand(), ROCK,
                                                                   SCISSORS.getHand(), SCISSORS);
    private final Random random = new Random();
    private final Scanner input = new Scanner(System.in);
    private final List<GameResults> results = new LinkedList<>();

    public void start() {
        System.out.println("\nShow your hand \n\tYour Options: " + options());
        int handSymbol = input.nextInt();

        if (handSymbol != END_GAME) {
            results.add(matchResult(handSymbol));
            start();
        } else {
            presentScore();
        }
    }

    private GameResults matchResult(final int handSymbol) {
        final HandOptions against = machineOption();
        final HandOptions userHand = userOption(handSymbol);
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

    private HandOptions userOption(final int hand) {
        return Optional
                 .ofNullable(possibleHands.get(hand))
                 .orElseThrow(() -> new IllegalArgumentException("Invalid option"));
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private HandOptions machineOption() {
        return possibleHands.get(random
                                   .ints(1, 3)
                                   .findFirst()
                                   .getAsInt());
    }

    private String options() {
        return PAPER.getHand() + ". " + PAPER.name() + "\t\t"
                 + ROCK.getHand() + ". " + ROCK.name() + "\t\t"
                 + SCISSORS.getHand() + ". " + SCISSORS.name() + "\t\t"
                 + "0. Endgame\t\t";
    }
}

package org.acme.game;

import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;

public final class GameScore {

    private final List<MatchResult> results = new LinkedList<>();

    void addMatchResult(final MatchResult result) {
        System.out.println("Match result: " + result.outcome().name());
        System.out.println("You played: " + result.userHand().name());
        System.out.println("Machine played: " + result.machineHand().name());
        results.add(result);
    }

    void showScore() {
        System.out.println("Endgame");
        System.out.println("Score: ");
        final var groupedResults =
          results.stream().collect(groupingBy(MatchResult::outcome));
        groupedResults.entrySet().forEach(System.out::println);
    }
}

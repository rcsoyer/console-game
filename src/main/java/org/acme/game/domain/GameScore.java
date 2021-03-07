package org.acme.game.domain;

import java.util.LinkedList;
import java.util.List;

import org.acme.game.domain.Match.MatchResult;

import static java.util.Comparator.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Holds the game session score per each match
 */
final class GameScore {

    private final List<MatchResult> results = new LinkedList<>();

    /**
     * Adds results of each match
     */
    void addMatchResult(final MatchResult result) {
        System.out.println("Match result: " + result.outcome().name());
        System.out.println("You played: " + result.userHand().name());
        System.out.println("Machine played: " + result.machineHand().name());
        results.add(result);
    }

    /**
     * Print final result of the gaming session from accumulated matches
     */
    void showScore() {
        System.out.println("Endgame\n");
        System.out.println("Session Game Score: ");
        results.stream()
               .collect(groupingBy(MatchResult::outcome, counting()))
               .entrySet()
               .stream()
               .sorted(comparingByValue(reverseOrder()))
               .forEach(result -> System.out.println("\t" + result));
    }
}

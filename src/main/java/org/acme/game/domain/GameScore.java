package org.acme.game.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.acme.game.domain.Match.MatchOutcome;
import org.acme.game.domain.Match.MatchResult;

import static java.util.Comparator.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * Holds the game session score per each match
 */
final class GameScore {

    private final List<MatchResult> results = new LinkedList<>();

    /**
     * Adds results of each match
     */
    void addMatchResult(final MatchResult result) {
        results.add(result);
    }

    /**
     * Calculate result of the gaming session from accumulated matches and grouped by {@link MatchOutcome}
     */
    List<Entry<MatchOutcome, Long>> gameScore() {
        return results.stream()
                      .collect(groupingBy(MatchResult::outcome, counting()))
                      .entrySet()
                      .stream()
                      .sorted(comparingByValue(reverseOrder()))
                      .collect(toList());
    }
}

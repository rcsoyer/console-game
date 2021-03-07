package org.acme.game.domain;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.acme.game.domain.Match.MatchOutcome;
import org.acme.game.domain.Match.MatchResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.Comparator.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toList;
import static org.acme.game.domain.Hand.PAPER;
import static org.acme.game.domain.Hand.ROCK;
import static org.acme.game.domain.Hand.SCISSORS;
import static org.acme.game.domain.Match.MatchOutcome.TIE;
import static org.acme.game.domain.Match.MatchOutcome.USER_LOST;
import static org.acme.game.domain.Match.MatchOutcome.USER_WIN;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class GameScoreTest {

    private GameScore score;

    @BeforeEach
    void setUp() {
        score = new GameScore();
    }

    @Test
    void gameScore() {
        final var matchResult1 = new MatchResult(PAPER, ROCK, USER_WIN);
        final var matchResult2 = new MatchResult(SCISSORS, PAPER, USER_WIN);
        final var matchResult3 = new MatchResult(ROCK, SCISSORS, USER_WIN);
        final var matchResult4 = new MatchResult(ROCK, ROCK, TIE);
        final var matchResult5 = new MatchResult(SCISSORS, ROCK, USER_LOST);
        final var matchResult6 = new MatchResult(ROCK, PAPER, USER_LOST);

        score.addMatchResult(matchResult1);
        score.addMatchResult(matchResult2);
        score.addMatchResult(matchResult3);
        score.addMatchResult(matchResult4);
        score.addMatchResult(matchResult5);
        score.addMatchResult(matchResult6);

        final List<Entry<MatchOutcome, Long>> expectedResult =
          Map.of(USER_WIN, 3L, USER_LOST, 2L, TIE, 1L)
             .entrySet()
             .stream()
             .sorted(comparingByValue(reverseOrder()))
             .collect(toList());

        final List<Entry<MatchOutcome, Long>> result = score.gameScore();

        assertIterableEquals(expectedResult, result);
    }
}
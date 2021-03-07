package org.acme.game.domain;

import java.util.List;
import java.util.Map.Entry;

import org.acme.game.domain.Match.MatchOutcome;
import org.acme.game.domain.Match.MatchResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.acme.game.domain.Hand.PAPER;
import static org.acme.game.domain.Hand.ROCK;
import static org.acme.game.domain.Hand.SCISSORS;
import static org.acme.game.domain.Match.MatchOutcome.TIE;
import static org.acme.game.domain.Match.MatchOutcome.USER_LOST;
import static org.acme.game.domain.Match.MatchOutcome.USER_WIN;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameScoreTest {

    private GameScore score;

    @BeforeEach
    void setUp() {
        score = new GameScore();
    }

    @Test
    void calculateScore() {
        score.addMatchResult(new MatchResult(PAPER, ROCK, USER_WIN));
        score.addMatchResult(new MatchResult(SCISSORS, PAPER, USER_WIN));
        score.addMatchResult(new MatchResult(ROCK, SCISSORS, USER_WIN));
        score.addMatchResult(new MatchResult(ROCK, ROCK, TIE));
        score.addMatchResult(new MatchResult(SCISSORS, ROCK, USER_LOST));
        score.addMatchResult(new MatchResult(ROCK, PAPER, USER_LOST));

        final List<Entry<MatchOutcome, Long>> results = score.calculateScore();

        assertEquals(3, results.size());

        final Entry<MatchOutcome, Long> result1 = results.get(0);
        assertEquals(USER_WIN, result1.getKey());
        assertEquals(3L, result1.getValue());

        final Entry<MatchOutcome, Long> result2 = results.get(1);
        assertEquals(USER_LOST, result2.getKey());
        assertEquals(2L, result2.getValue());

        final Entry<MatchOutcome, Long> result3 = results.get(2);
        assertEquals(TIE, result3.getKey());
        assertEquals(1L, result3.getValue());
    }
}
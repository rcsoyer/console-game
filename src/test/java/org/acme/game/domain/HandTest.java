package org.acme.game.domain;

import org.junit.jupiter.api.Test;

import static org.acme.game.domain.Hand.PAPER;
import static org.acme.game.domain.Hand.ROCK;
import static org.acme.game.domain.Hand.SCISSORS;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {

    @Test
    void parse() {
    }

    @Test
    void showDown_PAPER() {
        assertEquals(MatchOutcome.TIE, PAPER.showDown(PAPER));
        assertEquals(MatchOutcome.USER_WIN, PAPER.showDown(ROCK));
        assertEquals(MatchOutcome.USER_LOST, PAPER.showDown(SCISSORS));
    }

    @Test
    void showDown_ROCK() {
        assertEquals(MatchOutcome.TIE, ROCK.showDown(ROCK));
        assertEquals(MatchOutcome.USER_LOST, ROCK.showDown(PAPER));
        assertEquals(MatchOutcome.USER_WIN, ROCK.showDown(SCISSORS));
    }

    @Test
    void showDown_SCISSORS() {
        assertEquals(MatchOutcome.USER_LOST, SCISSORS.showDown(ROCK));
        assertEquals(MatchOutcome.USER_WIN, SCISSORS.showDown(PAPER));
        assertEquals(MatchOutcome.TIE, SCISSORS.showDown(SCISSORS));
    }

}
package org.acme.game.domain;

import org.junit.jupiter.api.Test;

import static org.acme.game.domain.Hand.PAPER;
import static org.acme.game.domain.Hand.ROCK;
import static org.acme.game.domain.Hand.SCISSORS;
import static org.acme.game.domain.MatchOutcome.TIE;
import static org.acme.game.domain.MatchOutcome.USER_LOST;
import static org.acme.game.domain.MatchOutcome.USER_WIN;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {

    @Test
    void parse() {
    }

    @Test
    void showDown_PAPER() {
        assertEquals(TIE, PAPER.showDown(PAPER));
        assertEquals(USER_WIN, PAPER.showDown(ROCK));
        assertEquals(USER_LOST, PAPER.showDown(SCISSORS));
    }

    @Test
    void showDown_ROCK() {
        assertEquals(TIE, ROCK.showDown(ROCK));
        assertEquals(USER_LOST, ROCK.showDown(PAPER));
        assertEquals(USER_WIN, ROCK.showDown(SCISSORS));
    }

    @Test
    void showDown_SCISSORS() {
        assertEquals(USER_LOST, SCISSORS.showDown(ROCK));
        assertEquals(USER_WIN, SCISSORS.showDown(PAPER));
        assertEquals(TIE, SCISSORS.showDown(SCISSORS));
    }

}
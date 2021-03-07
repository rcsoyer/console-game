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
    void showDown_HAND() {
        assertEquals(MatchOutcome.TIE, ROCK.showDown(ROCK));
        assertEquals(MatchOutcome.USER_LOST, ROCK.showDown(PAPER));
        assertEquals(MatchOutcome.USER_WIN, ROCK.showDown(SCISSORS));
    }
}
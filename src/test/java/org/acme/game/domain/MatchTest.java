package org.acme.game.domain;

import org.acme.game.domain.Match.MatchResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.acme.game.domain.Hand.PAPER;
import static org.acme.game.domain.Hand.ROCK;
import static org.acme.game.domain.Match.MatchOutcome.USER_WIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MatchTest {

    private Match match;
    private MachineHand machine;

    @BeforeEach
    void setUp() {
        machine = mock(MachineHand.class);
        match = new Match(machine);
    }

    @Test
    void showHands() {
        when(machine.random()).thenReturn(ROCK);

        final MatchResult result = match.showHands(PAPER.getHand());

        assertEquals(PAPER, result.userHand());
        assertEquals(ROCK, result.machineHand());
        assertEquals(USER_WIN, result.outcome());
    }
}
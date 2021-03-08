package org.acme.game.domain;

import java.util.Random;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.acme.game.domain.Hand.PAPER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MachineHandTest {

    private MachineHand machineHand;
    private Random random;

    @BeforeEach
    void setUp() {
        random = mock(Random.class);
        machineHand = new MachineHand(random);
    }

    @Test
    void random() {
        when(random.ints(anyInt(), anyInt()))
          .thenReturn(IntStream.of(PAPER.getHand()));

        final Hand result = machineHand.random();

        assertEquals(PAPER, result);
    }
}
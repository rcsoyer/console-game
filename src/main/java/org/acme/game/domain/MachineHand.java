package org.acme.game.domain;

import java.util.Random;

import static org.acme.game.domain.Hand.PAPER;
import static org.acme.game.domain.Hand.SCISSORS;

final class MachineHand {

    private final Random random = new Random();

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    Hand random() {
        return Hand.parse(random.ints(PAPER.getHand(), SCISSORS.getHand() + 1)
                                .findFirst()
                                .getAsInt());
    }
}

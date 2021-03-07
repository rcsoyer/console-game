package org.acme.game.domain;

import java.util.Random;

import static org.acme.game.domain.Hand.PAPER;
import static org.acme.game.domain.Hand.SCISSORS;

/**
 * Holds the logic the defines the hand played by the machine
 */
final class MachineHand {

    private final Random random = new Random();

    /**
     * A random hand defined at runtime
     *
     * @implNote Completely random. No logic in predicting over past moves or current move of the player
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    Hand random() {
        return Hand.parse(random.ints(PAPER.getHand(), SCISSORS.getHand() + 1)
                                .findFirst()
                                .getAsInt());
    }
}

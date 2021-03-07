package org.acme.game.domain;

import java.util.Random;

final class MachineHand {

    private final Random random = new Random();

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    Hand random() {
        return Hand.parse(random.ints(1, 4)
                                .findFirst()
                                .getAsInt());
    }
}

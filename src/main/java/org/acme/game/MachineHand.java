package org.acme.game;

import java.util.Random;

public final class MachineHand {

    private final Random random = new Random();

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    Hand random() {
        return Hand.parse(random.ints(1, 4)
                                .findFirst()
                                .getAsInt());
    }
}

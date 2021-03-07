package org.acme.game;

public final class Match {

    private final MachineHand machine = new MachineHand();

    MatchResult showHands(final int userOption) {
        final Hand machineHand = machine.random();
        final Hand userHand = Hand.parse(userOption);
        final MatchOutcome outcome = userHand.showDown(machineHand);
        return new MatchResult(userHand, machineHand, outcome);
    }
}

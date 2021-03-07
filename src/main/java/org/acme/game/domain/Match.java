package org.acme.game.domain;

final class Match {

    private final MachineHand machine = new MachineHand();

    MatchResult showHands(final int userOption) {
        final Hand machineHand = machine.random();
        final Hand userHand = Hand.parse(userOption);
        final MatchOutcome outcome = userHand.showDown(machineHand);
        return new MatchResult(userHand, machineHand, outcome);
    }

    static record MatchResult(Hand userHand, Hand machineHand, MatchOutcome outcome) {
    }

    enum MatchOutcome {
        USER_WIN, USER_LOST, TIE
    }
}

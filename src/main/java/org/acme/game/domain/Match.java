package org.acme.game.domain;

/**
 * Each match of the game can use it to define the outcome
 */
final class Match {

    private final MachineHand machine = new MachineHand();

    /**
     * Given the informed user hand and a random machine hand defines calculates the outcome
     */
    MatchResult showHands(final int userOption) {
        final Hand machineHand = machine.random();
        final Hand userHand = Hand.parse(userOption);
        final MatchOutcome outcome = userHand.showDown(machineHand);
        return new MatchResult(userHand, machineHand, outcome);
    }

    /**
     * Data of what was played in a match
     */
    static record MatchResult(Hand userHand, Hand machineHand, MatchOutcome outcome) {
    }

    /**
     * All possible outcomes for a game between 2 hands on rock-paper-scissors
     */
    enum MatchOutcome {
        USER_WIN, USER_LOST, TIE
    }
}

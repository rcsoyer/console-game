package org.acme.game;

public record MatchResult(Hand userHand, Hand machineHand, MatchOutcome outcome) {
}

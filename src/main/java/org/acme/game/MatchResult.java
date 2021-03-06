package org.acme.game;

public record MatchResult(Hands userHand, Hands machineHand, MatchOutcome outcome) {
}

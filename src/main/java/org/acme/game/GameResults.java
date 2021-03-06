package org.acme.game;

public record GameResults(Hands against, Hands userHand, MatchOutcome result) {
}

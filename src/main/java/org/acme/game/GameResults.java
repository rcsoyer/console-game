package org.acme.game;

public record GameResults(HandOptions against, HandOptions userHand, MatchOutcome result) {
}

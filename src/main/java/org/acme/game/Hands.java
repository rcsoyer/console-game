package org.acme.game;

import java.util.Map;
import java.util.Optional;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import static org.acme.game.MatchOutcome.TIE;
import static org.acme.game.MatchOutcome.USER_LOST;
import static org.acme.game.MatchOutcome.USER_WIN;

@Getter
@ToString
@RequiredArgsConstructor
enum Hands {
    PAPER(1) {
        @Override
        MatchOutcome beat(final Hands against) {
            return switch (against) {
                case PAPER -> TIE;
                case SCISSORS -> USER_LOST;
                case ROCK -> USER_WIN;
            };
        }
    },
    ROCK(2) {
        @Override
        MatchOutcome beat(final Hands against) {
            return switch (against) {
                case PAPER -> USER_LOST;
                case SCISSORS -> USER_WIN;
                case ROCK -> TIE;
            };
        }
    },
    SCISSORS(3) {
        @Override
        MatchOutcome beat(final Hands against) {
            return switch (against) {
                case PAPER -> USER_WIN;
                case SCISSORS -> TIE;
                case ROCK -> USER_LOST;
            };
        }
    };

    private static final Map<Integer, Hands> POSSIBLE_HANDS = Map.of(PAPER.getHand(), PAPER,
                                                                     ROCK.getHand(), ROCK,
                                                                     SCISSORS.getHand(), SCISSORS);

    static Hands parse(final int hand) {
        return Optional
                 .ofNullable(POSSIBLE_HANDS.get(hand))
                 .orElseThrow(() -> new IllegalArgumentException("Invalid option"));
    }

    private final int hand;

    abstract MatchOutcome beat(final Hands against);
}

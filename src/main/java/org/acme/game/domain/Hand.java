package org.acme.game.domain;

import java.util.Map;
import java.util.Optional;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.acme.game.domain.Match.MatchOutcome;

import static org.acme.game.domain.Match.MatchOutcome.TIE;
import static org.acme.game.domain.Match.MatchOutcome.USER_LOST;
import static org.acme.game.domain.Match.MatchOutcome.USER_WIN;

/**
 * The acceptable hand signs on a game of rock-paper-scissors.
 * <br> Each enum instance knows the outcome against any other existing hands
 */
@Getter
@RequiredArgsConstructor
enum Hand {
    PAPER(1) {
        @Override
        MatchOutcome showDown(final Hand against) {
            return switch (against) {
                case PAPER -> TIE;
                case SCISSORS -> USER_LOST;
                case ROCK -> USER_WIN;
            };
        }
    },
    ROCK(2) {
        @Override
        MatchOutcome showDown(final Hand against) {
            return switch (against) {
                case PAPER -> USER_LOST;
                case SCISSORS -> USER_WIN;
                case ROCK -> TIE;
            };
        }
    },
    SCISSORS(3) {
        @Override
        MatchOutcome showDown(final Hand against) {
            return switch (against) {
                case PAPER -> USER_WIN;
                case SCISSORS -> TIE;
                case ROCK -> USER_LOST;
            };
        }
    };

    private static final Map<Integer, Hand> POSSIBLE_HANDS = Map.of(PAPER.getHand(), PAPER,
                                                                    ROCK.getHand(), ROCK,
                                                                    SCISSORS.getHand(), SCISSORS);

    /**
     * Parse the given int to a existing hand move
     *
     * @throws IllegalArgumentException in the case the input is not a valid/known hand key
     */
    static Hand parse(final int hand) {
        return Optional
                 .ofNullable(POSSIBLE_HANDS.get(hand))
                 .orElseThrow(() -> new IllegalArgumentException("Invalid option"));
    }

    private final int hand;

    /**
     * Defines the business logic and outcome of each possible showdown within the 2 {@link Hand}s
     */
    abstract MatchOutcome showDown(Hand against);
}

package org.acme.game;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import static org.acme.game.GameApp.HandOptions.PAPER;
import static org.acme.game.GameApp.HandOptions.ROCK;
import static org.acme.game.GameApp.HandOptions.SCISSORS;
import static org.acme.game.GameApp.MatchOutcome.TIE;
import static org.acme.game.GameApp.MatchOutcome.USER_LOST;
import static org.acme.game.GameApp.MatchOutcome.USER_WIN;

public class GameApp {

    public static void main(final String[] args) {
        System.out.println("Paper-Rock-Scissors");
        System.out.println("Let's start the gaming season");
        new Gaming().start();
    }

    private static class Gaming {

        private final Map<Integer, HandOptions> possibleHands = Map.of(PAPER.getHand(), PAPER,
                                                                       ROCK.getHand(), ROCK,
                                                                       SCISSORS.getHand(), SCISSORS);
        private final Random random = new Random();
        private final Scanner input = new Scanner(System.in);

        private void start() {
            System.out.println("\nShow your hand \n\tYour Options: " + options());
            int handSymbol = input.nextInt();

            if (handSymbol != 0) {
                matchResult(handSymbol);
                start();
            } else {
                presentScore();
            }
        }

        private void matchResult(final int handSymbol) {
            final HandOptions against = machineOption();
            final HandOptions userHand = userOption(handSymbol);
            final MatchOutcome result = userHand.beat(against);
            System.out.println("Match result: " + result.name());
            System.out.println("You played: " + userHand.name());
            System.out.println("Machine played: " + against.name());
        }

        private void presentScore() {
            System.out.println("Endgame");
        }

        private HandOptions userOption(final int hand) {
            return Optional
                     .ofNullable(possibleHands.get(hand))
                     .orElseThrow(() -> new IllegalArgumentException("Invalid option"));
        }

        @SuppressWarnings("OptionalGetWithoutIsPresent")
        private HandOptions machineOption() {
            return possibleHands.get(random
                                       .ints(1, 3)
                                       .findFirst()
                                       .getAsInt());
        }

        private String options() {
            return PAPER.getHand() + ". " + PAPER.name() + "\t\t"
                     + ROCK.getHand() + ". " + ROCK.name() + "\t\t"
                     + SCISSORS.getHand() + ". " + SCISSORS.name() + "\t\t"
                     + "0. Endgame\t\t";
        }
    }

    @Getter
    @ToString
    @RequiredArgsConstructor
    enum HandOptions {
        PAPER(1) {
            @Override
            MatchOutcome beat(final HandOptions against) {
                return switch (against) {
                    case PAPER -> TIE;
                    case SCISSORS -> USER_LOST;
                    case ROCK -> USER_WIN;
                };
            }
        },
        ROCK(2) {
            @Override
            MatchOutcome beat(final HandOptions against) {
                return switch (against) {
                    case PAPER -> USER_LOST;
                    case SCISSORS -> USER_WIN;
                    case ROCK -> TIE;
                };
            }
        },
        SCISSORS(3) {
            @Override
            MatchOutcome beat(final HandOptions against) {
                return switch (against) {
                    case PAPER -> USER_WIN;
                    case SCISSORS -> TIE;
                    case ROCK -> USER_LOST;
                };
            }
        };

        private final int hand;

        abstract MatchOutcome beat(final HandOptions against);
    }

    enum MatchOutcome {
        USER_WIN,
        USER_LOST,
        TIE;
    }
}

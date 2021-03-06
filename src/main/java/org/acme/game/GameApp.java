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
        new Gaming().run();
    }

    private static class Gaming {

        private final Map<Integer, HandOptions> possibleOptions = Map.of(PAPER.getOption(), PAPER,
                                                                         ROCK.getOption(), ROCK,
                                                                         SCISSORS.getOption(), SCISSORS);

        private final Random random = new Random();

        private void run() {
            final Scanner input = new Scanner(System.in);
            int handSymbol;

            do {
                System.out.println("Show your hand \n\tYour Options: " + HandOptions.options());
                handSymbol = input.nextInt();
                userOption(handSymbol);
                machineOption();
                System.out.println("You entered string " + handSymbol);
            } while (handSymbol != 0);
        }

        private HandOptions userOption(final int hand) {
            return Optional
                     .ofNullable(possibleOptions.get(hand))
                     .orElseThrow(() -> new IllegalArgumentException("Invalid option"));
        }

        @SuppressWarnings("OptionalGetWithoutIsPresent")
        private HandOptions machineOption() {
            return possibleOptions.get(random
                                         .ints(1, 3)
                                         .findFirst()
                                         .getAsInt());
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

        private final int option;

        abstract MatchOutcome beat(final HandOptions against);

        public static String options() {
            return PAPER.getOption() + ". " + PAPER.name() + "\n\t\t\t\t"
                     + ROCK.getOption() + ". " + ROCK.name() + "\n\t\t\t\t"
                     + SCISSORS.getOption() + ". " + SCISSORS.name() + "\n\t\t\t\t";
        }
    }

    enum MatchOutcome {
        USER_WIN,
        USER_LOST,
        TIE;
    }
}

package com.rorym.chess;

import org.springframework.stereotype.Component;

@Component
class ActorActionChecker {

    boolean canMove(Actor actor, int turn) {

        return switch (actor.getTeam()) {
            case WHITE -> isWhitesTurn(turn);
            case BLACK -> isBlacksTurn(turn);
        };
    }

    private boolean isWhitesTurn(int turn) {
        return turn % 2 == 0;
    }

    private static boolean isBlacksTurn(int turn) {
        return turn % 2 != 0;
    }

}

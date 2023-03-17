package com.rorym.chess;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
class Match {

    private final Chessboard chessboard;
    private final ActorActionChecker actorActionChecker;
    private int turn;

    Match(Chessboard chessboard, ActorActionChecker actorActionChecker, int turn) {
        this.chessboard = chessboard;
        this.actorActionChecker = actorActionChecker;
        this.turn = turn;
    }

    // TODO: Potentially to be used as a controller method, may return a result (e.g. new board state)
    void doMove(Actor actor, Piece piece, Position newPosition) {

        checkActorMove(actor);

        // TODO: Something like MoveResult moveResult = ... (maybe MatchResult)
        //  actor.sendResult(moveResult)
        attemptMove(piece, newPosition);
    }

    private void checkActorMove(Actor actor) {

        if (!actorActionChecker.canMove(actor, turn)) {
            throw new IllegalStateException("Actor " + actor + " cannot move yet. Wait your turn!");
        }
    }

    private void attemptMove(Piece piece, Position newPosition) {
        chessboard.doMove(piece, newPosition);

        // TODO: Only increment turn if successful according to return value of doMove
        turn++;
    }
}

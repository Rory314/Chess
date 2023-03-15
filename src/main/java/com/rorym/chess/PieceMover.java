package com.rorym.chess;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
class PieceMover {

    private final StatelessMoveValidator statelessMoveValidator;

    PieceMover(StatelessMoveValidator statelessMoveValidator) {
        this.statelessMoveValidator = statelessMoveValidator;
    }

    void movePiece(List<Piece> pieces, Piece piece, Position newPosition) {

        ensurePieceInPiecesList(pieces, piece);

        ensureValidMove(pieces, piece, newPosition);

        doMove(piece, newPosition);
    }

    private void ensurePieceInPiecesList(List<Piece> pieces, Piece piece) {

        if (!pieces.contains(piece)) {
            throw new IllegalStateException("Trying to move piece " + piece + " not in pieces list.");
        }
    }

    private void ensureValidMove(List<Piece> pieces, Piece piece, Position newPosition) {

        if (!statelessMoveValidator.isValidMove(piece, newPosition)) {
            throw new IllegalStateException("Cannot move " + piece + " to " + newPosition);
        }
    }

    private void doMove(Piece piece, Position newPosition) {

        piece.moveTo(newPosition);
    }

}

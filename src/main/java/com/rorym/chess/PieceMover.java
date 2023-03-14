package com.rorym.chess;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
class PieceMover {

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
        // TODO: Chess rules here, possibly via another component
    }

    private void doMove(Piece piece, Position newPosition) {
        piece.moveTo(newPosition);
    }

}

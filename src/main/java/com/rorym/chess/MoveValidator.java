package com.rorym.chess;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
class MoveValidator {

    private final BoardMoveValidator boardMoveValidator;
    private final PiecesMoveValidator piecesMoveValidator;

    MoveValidator(BoardMoveValidator boardMoveValidator, PiecesMoveValidator piecesMoveValidator) {
        this.boardMoveValidator = boardMoveValidator;
        this.piecesMoveValidator = piecesMoveValidator;
    }

    boolean isValidMove(List<Piece> pieces, Piece piece, Position newPosition) {

        if (!boardMoveValidator.isValidMove(piece, newPosition)) {
            return false;
        }

        return piecesMoveValidator.isValidMove(pieces, piece, newPosition);
    }
}

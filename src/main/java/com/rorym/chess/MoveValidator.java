package com.rorym.chess;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
class MoveValidator {

    private final BoardMoveValidator boardMoveValidator;

    MoveValidator(BoardMoveValidator boardMoveValidator) {
        this.boardMoveValidator = boardMoveValidator;
    }

    boolean isValidMove(List<Piece> pieces, Piece piece, Position newPosition) {

        return boardMoveValidator.isValidMove(pieces, piece, newPosition);
    }
}

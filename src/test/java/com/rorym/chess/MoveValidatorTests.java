package com.rorym.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MoveValidatorTests {

    @Mock
    BoardMoveValidator boardMoveValidator;
    @InjectMocks
    MoveValidator moveValidator;

    @Test
    void givenBoardMoveValidatorRejectsMoveThenRejectMove() {

        Piece piece = new Piece(PieceType.QUEEN, Team.BLACK, Position.f5);
        List<Piece> pieces = Collections.singletonList(piece);

        Mockito.when(boardMoveValidator.isValidMove(pieces, piece, Position.e3)).thenReturn(false);
        boolean isValidMove = moveValidator.isValidMove(pieces, piece, Position.e3);

        Assertions.assertFalse(isValidMove);
    }

}

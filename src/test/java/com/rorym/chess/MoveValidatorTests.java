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
    @Mock
    PiecesMoveValidator piecesMoveValidator;
    @InjectMocks
    MoveValidator moveValidator;

    @Test
    void givenBoardMoveValidatorRejectsMoveThenRejectMove() {

        Piece piece = new Piece(PieceType.QUEEN, Team.BLACK, Position.f5);

        Mockito.when(boardMoveValidator.isValidMove(piece, Position.e3)).thenReturn(false);
        boolean isValidMove = moveValidator.isValidMove(Collections.singletonList(piece), piece, Position.e3);

        Assertions.assertFalse(isValidMove);
    }

    @Test
    void givenBoardMoveValidatorAcceptsMoveThenRejectIfPiecesMoveValidatorRejects() {

        Piece piece = new Piece(PieceType.QUEEN, Team.BLACK, Position.f5);
        List<Piece> pieces = List.of(piece, new Piece(PieceType.PAWN, Team.BLACK, Position.f4));

        Mockito.when(boardMoveValidator.isValidMove(piece, Position.f4)).thenReturn(true);
        Mockito.when(piecesMoveValidator.isValidMove(pieces, piece, Position.f4)).thenReturn(false);
        boolean isValidMove = moveValidator.isValidMove(pieces, piece, Position.f4);

        Assertions.assertFalse(isValidMove);
    }

    @Test
    void givenBoardMoveValidatorAcceptsMoveThenAcceptIfPiecesMoveValidatorAccepts() {

        Piece piece = new Piece(PieceType.QUEEN, Team.BLACK, Position.f5);
        List<Piece> pieces = List.of(piece, new Piece(PieceType.PAWN, Team.BLACK, Position.a1));

        Mockito.when(boardMoveValidator.isValidMove(piece, Position.f4)).thenReturn(true);
        Mockito.when(piecesMoveValidator.isValidMove(pieces, piece, Position.f4)).thenReturn(true);
        boolean isValidMove = moveValidator.isValidMove(pieces, piece, Position.f4);

        Assertions.assertTrue(isValidMove);
    }

}

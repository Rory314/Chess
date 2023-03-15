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
public class PieceMoverTests {

    @Mock
    StatelessMoveValidator statelessMoveValidator;
    @InjectMocks
    PieceMover pieceMover;

    @Test
    void givenPieceNotInPiecesListWhenMovePieceThrowIllegalStateException() {

        Assertions.assertThrows(
                IllegalStateException.class,
                () -> pieceMover.movePiece(
                        Collections.emptyList(), new Piece(PieceType.PAWN, Team.WHITE, Position.a1), Position.a2
                )
        );
    }

    @Test
    void givenPieceInPiecesListWhenMovePieceRejectedByStatelessMoveValidatorThenThrowIllegalStateExceptionAndDoNotSetNewPositionOfPiece() {

        Position originalPosition = Position.a1;
        Piece piece = new Piece(PieceType.PAWN, Team.WHITE, originalPosition);
        List<Piece> pieces = Collections.singletonList(piece);
        Position newPosition = Position.a2;

        Mockito.when(statelessMoveValidator.isValidMove(piece, newPosition)).thenReturn(false);
        Assertions.assertThrows(IllegalStateException.class, () -> pieceMover.movePiece(pieces, piece, newPosition));

        Assertions.assertTrue(piece.hasPosition(originalPosition));
    }

    @Test
    void givenPieceInPiecesListWhenMovePieceAcceptedByStatelessMoveValidatorThenSetNewPositionOfPiece() {

        Position originalPosition = Position.a1;
        Piece piece = new Piece(PieceType.PAWN, Team.WHITE, originalPosition);
        List<Piece> pieces = Collections.singletonList(piece);
        Position newPosition = Position.a2;

        Mockito.when(statelessMoveValidator.isValidMove(piece, newPosition)).thenReturn(true);
        pieceMover.movePiece(pieces, piece, newPosition);

        Assertions.assertTrue(piece.hasPosition(newPosition));
    }

}

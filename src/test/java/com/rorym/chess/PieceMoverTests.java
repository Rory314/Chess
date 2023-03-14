package com.rorym.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class PieceMoverTests {

    PieceMover pieceMover;

    @BeforeEach
    void setup() {
        pieceMover = new PieceMover();
    }

    @Test
    void givenPieceInPiecesListWhenMovePieceThenSetNewPositionOfPiece() {

        Piece piece = new Piece(PieceType.PAWN, Team.WHITE, Position.a1);
        List<Piece> pieces = Collections.singletonList(piece);
        Position newPosition = Position.a2;

        pieceMover.movePiece(pieces, piece, newPosition);

        Assertions.assertTrue(piece.hasPosition(newPosition));
    }

    @Test
    void givenPieceNotInPiecesListWhenMovePieceThrowIllegalStateException() {

        Assertions.assertThrows(
                IllegalStateException.class,
                () -> pieceMover.movePiece(
                        Collections.emptyList(), new Piece(PieceType.PAWN, Team.WHITE, Position.a1), Position.a2
                )
        );
    }

}

package com.rorym.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class CaptureHandlerTests {

    CaptureHandler captureHandler;

    @BeforeEach
    void setup() {
        this.captureHandler = new CaptureHandler();
    }

    @Test
    void givenTwoOpposingPiecesInSamePositionThenDeclareCapture() {

        Piece sittingPiece = new Piece(PieceType.BISHOP, Team.WHITE, Position.e2);
        Piece movingPiece = new Piece(PieceType.PAWN, Team.BLACK, Position.e2);
        List<Piece> pieces = List.of(
                sittingPiece,
                movingPiece
        );

        Optional<Piece> capturedPiece = captureHandler.handleCapture(pieces, movingPiece);

        Assertions.assertEquals(sittingPiece, capturedPiece.get());
    }

    @Test
    void givenTwoFriendlyPiecesInSamePositionThenNoCapture() {

        Piece sittingPiece = new Piece(PieceType.BISHOP, Team.WHITE, Position.e2);
        Piece movingPiece = new Piece(PieceType.PAWN, Team.WHITE, Position.e2);
        List<Piece> pieces = List.of(
                sittingPiece,
                movingPiece
        );

        Optional<Piece> capturedPiece = captureHandler.handleCapture(pieces, movingPiece);

        Assertions.assertTrue(capturedPiece.isEmpty());
    }

    @Test
    void givenTwoOpposingPiecesInDifferentPositionsThenNoCapture() {

        Piece sittingPiece = new Piece(PieceType.BISHOP, Team.WHITE, Position.e2);
        Piece movingPiece = new Piece(PieceType.PAWN, Team.BLACK, Position.f3);
        List<Piece> pieces = List.of(
                sittingPiece,
                movingPiece
        );

        Optional<Piece> capturedPiece = captureHandler.handleCapture(pieces, movingPiece);

        Assertions.assertTrue(capturedPiece.isEmpty());
    }

    @Test
    void givenTwoFriendlyPiecesInDifferentPositionsThenNoCapture() {

        Piece sittingPiece = new Piece(PieceType.BISHOP, Team.WHITE, Position.e2);
        Piece movingPiece = new Piece(PieceType.PAWN, Team.WHITE, Position.d5);
        List<Piece> pieces = List.of(
                sittingPiece,
                movingPiece
        );

        Optional<Piece> capturedPiece = captureHandler.handleCapture(pieces, movingPiece);

        Assertions.assertTrue(capturedPiece.isEmpty());
    }


}

package com.rorym.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PiecesMoveValidatorTests {

    PiecesMoveValidator piecesMoveValidator;

    @BeforeEach
    void setup() {
        piecesMoveValidator = new PiecesMoveValidator();
    }

    @Test
    void givenPiecesListWithFriendlyPieceInNewPositionThenRejectMove() {

        Position newPosition = Position.d8;
        Team friendlyTeam = Team.WHITE;
        Piece movingPiece = new Piece(PieceType.KING, friendlyTeam, Position.d7);
        Piece friendlyPiece = new Piece(PieceType.PAWN, friendlyTeam, Position.d8);
        List<Piece> pieces = List.of(movingPiece, friendlyPiece);

        Assertions.assertFalse(piecesMoveValidator.isValidMove(pieces, movingPiece, newPosition));
    }

    @Test
    void givenPiecesListWithFriendlyPieceNotInNewPositionThenAcceptMove() {

        Team friendlyTeam = Team.WHITE;
        Piece movingPiece = new Piece(PieceType.KING, friendlyTeam, Position.d7);
        Piece friendlyPiece = new Piece(PieceType.PAWN, friendlyTeam, Position.a4);
        List<Piece> pieces = List.of(movingPiece, friendlyPiece);

        Assertions.assertTrue(piecesMoveValidator.isValidMove(pieces, movingPiece, Position.d8));
    }

    @Test
    void givenPiecesListWithEnemyPieceInNewPositionThenAcceptMove() {

        Position newPosition = Position.d8;
        Piece movingPiece = new Piece(PieceType.KING, Team.WHITE, Position.d7);
        Piece enemyPiece = new Piece(PieceType.PAWN, Team.BLACK, Position.d8);
        List<Piece> pieces = List.of(movingPiece, enemyPiece);

        Assertions.assertTrue(piecesMoveValidator.isValidMove(pieces, movingPiece, newPosition));
    }

    @Test
    void givenPiecesListWithEnemyPieceNotInNewPositionThenAcceptMove() {

        Piece movingPiece = new Piece(PieceType.KING, Team.WHITE, Position.d7);
        Piece enemyPiece = new Piece(PieceType.PAWN, Team.BLACK, Position.a4);
        List<Piece> pieces = List.of(movingPiece, enemyPiece);

        Assertions.assertTrue(piecesMoveValidator.isValidMove(pieces, movingPiece, Position.d8));
    }

}

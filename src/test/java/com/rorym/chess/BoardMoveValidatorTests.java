package com.rorym.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class BoardMoveValidatorTests {

    BoardMoveValidator boardMoveValidator;

    @BeforeEach
    void setup() {
        boardMoveValidator = new BoardMoveValidator();
    }

    @Test
    void givenUnblockedPawnThenCanMoveForwardOneRank() {

        Piece pawn = new Piece(PieceType.PAWN, Team.WHITE, Position.e6);
        List<Piece> pieces = Collections.singletonList(pawn);

        List<Position> validPositions = List.of(Position.e7);

        testMoveValidityForPieceTypeAndPosition(validPositions, pieces, pawn);
    }

    @Test
    void pawnCannotMoveToBlockingPieceIfFriendly() {

        Piece pawn = new Piece(PieceType.PAWN, Team.WHITE, Position.e6);
        Piece blockingPiece = new Piece(PieceType.KNIGHT, Team.WHITE, Position.e7);
        List<Piece> pieces = List.of(pawn, blockingPiece);

        List<Position> validPositions = Collections.emptyList();

        testMoveValidityForPieceTypeAndPosition(validPositions, pieces, pawn);
    }

    @Test
    void pawnCanMoveToBlockingPieceIfEnemy() {

        Piece piece = new Piece(PieceType.PAWN, Team.WHITE, Position.e6);
        Piece blockingPiece = new Piece(PieceType.KNIGHT, Team.BLACK, Position.e7);
        List<Piece> pieces = List.of(piece, blockingPiece);

        List<Position> validPositions = Collections.singletonList(Position.e7);

        testMoveValidityForPieceTypeAndPosition(validPositions, pieces, piece);
    }

    @Test
    void givenUnblockedKnightThenCanMoveInLShape() {

        Piece knight = new Piece(PieceType.KNIGHT, Team.WHITE, Position.f3);
        List<Piece> pieces = Collections.singletonList(knight);

        List<Position> validPositions = List.of(
                Position.g5, Position.h4, Position.h2, Position.g1, Position.e1, Position.d2, Position.d4, Position.e5
        );

        testMoveValidityForPieceTypeAndPosition(validPositions, pieces, knight);
    }

    @Test
    void knightCannotMoveToBlockingPieceIfFriendly() {

        Piece knight = new Piece(PieceType.KNIGHT, Team.WHITE, Position.f3);
        Piece blockingPiece = new Piece(PieceType.BISHOP, Team.WHITE, Position.e1);
        List<Piece> pieces = List.of(knight, blockingPiece);

        List<Position> validPositions = List.of(
                Position.g5, Position.h4, Position.h2, Position.g1, Position.d2, Position.d4, Position.e5
        );

        testMoveValidityForPieceTypeAndPosition(validPositions, pieces, knight);
    }

    @Test
    void knightCanMoveToBlockingPieceIfEnemy() {

        Piece knight = new Piece(PieceType.KNIGHT, Team.WHITE, Position.f3);
        Piece blockingPiece = new Piece(PieceType.BISHOP, Team.BLACK, Position.e1);
        List<Piece> pieces = List.of(knight, blockingPiece);

        List<Position> validPositions = List.of(
                Position.g5, Position.h4, Position.h2, Position.g1, Position.e1, Position.d2, Position.d4, Position.e5
        );

        testMoveValidityForPieceTypeAndPosition(validPositions, pieces, knight);
    }

    @Test
    void givenUnblockedBishopThenCanMoveDiagonally() {

        Piece piece = new Piece(PieceType.BISHOP, Team.WHITE, Position.d4);
        List<Piece> pieces = Collections.singletonList(piece);

        List<Position> validPositions = List.of(
                Position.a7, Position.b6, Position.c5, Position.e3, Position.f2, Position.g1,
                Position.a1, Position.b2, Position.c3, Position.e5, Position.f6, Position.g7, Position.h8
        );

        testMoveValidityForPieceTypeAndPosition(validPositions, pieces, piece);
    }

    @Test
    void bishopCannotMoveToBlockingPieceIfFriendly() {

        Piece bishop = new Piece(PieceType.BISHOP, Team.WHITE, Position.f6);
        Piece blockingPiece = new Piece(PieceType.ROOK, Team.WHITE, Position.d4);
        List<Piece> pieces = List.of(bishop, blockingPiece);

        List<Position> validPositions = List.of(
                Position.d8, Position.e7,
                Position.g7, Position.h8,
                Position.g5, Position.h4,
                Position.e5
        );

        testMoveValidityForPieceTypeAndPosition(validPositions, pieces, bishop);
    }

    @Test
    void bishopCanMoveToBlockingPieceIfEnemy() {

        Piece bishop = new Piece(PieceType.BISHOP, Team.WHITE, Position.f6);
        Piece blockingPiece = new Piece(PieceType.ROOK, Team.BLACK, Position.d4);
        List<Piece> pieces = List.of(bishop, blockingPiece);

        List<Position> validPositions = List.of(
                Position.d8, Position.e7,
                Position.g7, Position.h8,
                Position.g5, Position.h4,
                Position.e5, Position.d4
        );

        testMoveValidityForPieceTypeAndPosition(validPositions, pieces, bishop);
    }

    @Test
    void givenUnblockedRookThenCanMoveInStraightLines() {

        Piece piece = new Piece(PieceType.ROOK, Team.WHITE, Position.f5);
        List<Piece> pieces = Collections.singletonList(piece);

        List<Position> validPositions = List.of(
                Position.f6, Position.f7, Position.f8,
                Position.g5, Position.h5,
                Position.f4, Position.f3, Position.f2, Position.f1,
                Position.e5, Position.d5, Position.c5, Position.b5, Position.a5
        );

        testMoveValidityForPieceTypeAndPosition(validPositions, pieces, piece);
    }

    @Test
    void rookCannotMoveToBlockingPieceIfFriendly() {

        Piece rook = new Piece(PieceType.ROOK, Team.WHITE, Position.f5);
        Piece blockingPiece = new Piece(PieceType.ROOK, Team.WHITE, Position.b5);
        List<Piece> pieces = List.of(rook, blockingPiece);

        List<Position> validPositions = List.of(
                Position.f6, Position.f7, Position.f8,
                Position.g5, Position.h5,
                Position.f4, Position.f3, Position.f2, Position.f1,
                Position.e5, Position.d5, Position.c5
        );

        testMoveValidityForPieceTypeAndPosition(validPositions, pieces, rook);
    }

    @Test
    void rookCanMoveToBlockingPieceIfEnemy() {

        Piece rook = new Piece(PieceType.ROOK, Team.WHITE, Position.f5);
        Piece blockingPiece = new Piece(PieceType.ROOK, Team.BLACK, Position.b5);
        List<Piece> pieces = List.of(rook, blockingPiece);

        List<Position> validPositions = List.of(
                Position.f6, Position.f7, Position.f8,
                Position.g5, Position.h5,
                Position.f4, Position.f3, Position.f2, Position.f1,
                Position.e5, Position.d5, Position.c5, Position.b5
        );

        testMoveValidityForPieceTypeAndPosition(validPositions, pieces, rook);
    }

    @Test
    void givenUnblockedQueenThenCanMoveDiagonallyOrInStraightLine() {

        Piece piece = new Piece(PieceType.QUEEN, Team.WHITE, Position.d4);
        List<Piece> pieces = Collections.singletonList(piece);

        List<Position> validPositions = List.of(
                Position.d8, Position.h8,
                Position.a7, Position.d7, Position.g7,
                Position.b6, Position.d6, Position.f6,
                Position.c5, Position.d5, Position.e5,
                Position.a4, Position.b4, Position.c4, Position.e4, Position.f4, Position.g4, Position.h4,
                Position.c3, Position.d3, Position.e3,
                Position.b2, Position.d2, Position.f2,
                Position.a1, Position.d1, Position.g1
        );

        testMoveValidityForPieceTypeAndPosition(validPositions, pieces, piece);
    }

    @Test
    void queenCannotMoveToBlockingPieceIfFriendly() {

        Piece queen = new Piece(PieceType.QUEEN, Team.WHITE, Position.d4);
        Piece blockingPiece = new Piece(PieceType.BISHOP, Team.WHITE, Position.e4);
        List<Piece> pieces = List.of(queen, blockingPiece);

        List<Position> validPositions = List.of(
                Position.d8, Position.h8,
                Position.a7, Position.d7, Position.g7,
                Position.b6, Position.d6, Position.f6,
                Position.c5, Position.d5, Position.e5,
                Position.a4, Position.b4, Position.c4,
                Position.c3, Position.d3, Position.e3,
                Position.b2, Position.d2, Position.f2,
                Position.a1, Position.d1, Position.g1
        );

        testMoveValidityForPieceTypeAndPosition(validPositions, pieces, queen);
    }

    @Test
    void queenCanMoveToBlockingPieceIfEnemy() {

        Piece queen = new Piece(PieceType.QUEEN, Team.WHITE, Position.d4);
        Piece blockingPiece = new Piece(PieceType.BISHOP, Team.BLACK, Position.e4);
        List<Piece> pieces = List.of(queen, blockingPiece);

        List<Position> validPositions = List.of(
                Position.d8, Position.h8,
                Position.a7, Position.d7, Position.g7,
                Position.b6, Position.d6, Position.f6,
                Position.c5, Position.d5, Position.e5,
                Position.e4,
                Position.a4, Position.b4, Position.c4,
                Position.c3, Position.d3, Position.e3,
                Position.b2, Position.d2, Position.f2,
                Position.a1, Position.d1, Position.g1
        );

        testMoveValidityForPieceTypeAndPosition(validPositions, pieces, queen);
    }

    @Test
    void givenUnblockedKingThenCanMoveToAdjacent() {

        Piece piece = new Piece(PieceType.KING, Team.WHITE, Position.e4);
        List<Piece> pieces = Collections.singletonList(piece);

        List<Position> validPositions = List.of(
                Position.d5, Position.e5, Position.f5,
                Position.d4, Position.f4,
                Position.d3, Position.e3, Position.f3
        );

        testMoveValidityForPieceTypeAndPosition(validPositions, pieces, piece);
    }

    @Test
    void kingCannotMoveToBlockingPieceIfFriendly() {

        Piece king = new Piece(PieceType.KING, Team.BLACK, Position.e4);
        Piece blockingPiece = new Piece(PieceType.PAWN, Team.BLACK, Position.f4);
        List<Piece> pieces = List.of(king, blockingPiece);

        List<Position> validPositions = List.of(
                Position.d5, Position.e5, Position.f5,
                Position.d4,
                Position.d3, Position.e3, Position.f3
        );

        testMoveValidityForPieceTypeAndPosition(validPositions, pieces, king);
    }

    @Test
    void kingCanMoveToBlockingPieceIfEnemy() {

        Piece king = new Piece(PieceType.KING, Team.BLACK, Position.e4);
        Piece blockingPiece = new Piece(PieceType.PAWN, Team.WHITE, Position.f4);
        List<Piece> pieces = List.of(king, blockingPiece);

        List<Position> validPositions = List.of(
                Position.d5, Position.e5, Position.f5,
                Position.d4, Position.f4,
                Position.d3, Position.e3, Position.f3
        );

        testMoveValidityForPieceTypeAndPosition(validPositions, pieces, king);
    }

    private void testMoveValidityForPieceTypeAndPosition(List<Position> validPositions, List<Piece> pieces, Piece piece) {

        EnumSet<Position> invalidPositions = EnumSet.allOf(Position.class);
        validPositions.forEach(invalidPositions::remove);

        for (Position validPosition : validPositions) {
            Assertions.assertTrue(boardMoveValidator.isValidMove(pieces, piece, validPosition));
        }
        for (Position invalidPosition : invalidPositions) {
            Assertions.assertFalse(boardMoveValidator.isValidMove(pieces, piece, invalidPosition));
        }
    }

}

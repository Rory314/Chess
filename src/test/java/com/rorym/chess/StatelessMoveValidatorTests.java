package com.rorym.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.List;

public class StatelessMoveValidatorTests {

    StatelessMoveValidator statelessMoveValidator;

    @BeforeEach
    void setup() {
        statelessMoveValidator = new StatelessMoveValidator();
    }

    @Test
    void pawnCanMoveForwardOneRank() {

        List<Position> validPositions = List.of(Position.e7);

        testMoveValidityForPieceTypeAndPosition(validPositions, PieceType.PAWN, Position.e6);
    }

    @Test
    void knightCanMoveInLShape() {

        List<Position> validPositions = List.of(
                Position.g5, Position.h4, Position.h2, Position.g1, Position.e1, Position.d2, Position.d4, Position.e5
        );

        testMoveValidityForPieceTypeAndPosition(validPositions, PieceType.KNIGHT, Position.f3);
    }

    @Test
    void bishopCanMoveDiagonally() {

        List<Position> validPositions = List.of(
                Position.a7, Position.b6, Position.c5, Position.e3, Position.f2, Position.g1,
                Position.a1, Position.b2, Position.c3, Position.e5, Position.f6, Position.g7, Position.h8
        );

        testMoveValidityForPieceTypeAndPosition(validPositions, PieceType.BISHOP, Position.d4);
    }

    @Test
    void rookCanMoveInStraightLines() {

        List<Position> validPositions = List.of(
                Position.f6, Position.f7, Position.f8,
                Position.g5, Position.h5,
                Position.f4, Position.f3, Position.f2, Position.f1,
                Position.e5, Position.d5, Position.c5, Position.b5, Position.a5
        );

        testMoveValidityForPieceTypeAndPosition(validPositions, PieceType.ROOK, Position.f5);
    }

    @Test
    void queenCanMoveDiagonallyOrInStraightLine() {

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

        testMoveValidityForPieceTypeAndPosition(validPositions, PieceType.QUEEN, Position.d4);
    }

    @Test
    void kingCanMoveToAdjacent() {

        List<Position> validPositions = List.of(
                Position.d5, Position.e5, Position.f5,
                Position.d4, Position.f4,
                Position.d3, Position.e3, Position.f3
        );

        testMoveValidityForPieceTypeAndPosition(validPositions, PieceType.KING, Position.e4);
    }

    private void testMoveValidityForPieceTypeAndPosition(List<Position> validPositions, PieceType pieceType, Position position) {

        Piece piece = new Piece(pieceType, Team.WHITE, position);

        EnumSet<Position> invalidPositions = EnumSet.allOf(Position.class);
        validPositions.forEach(invalidPositions::remove);

        for (Position validPosition : validPositions) {
            Assertions.assertTrue(statelessMoveValidator.isValidMove(piece, validPosition));
        }
        for (Position invalidPosition : invalidPositions) {
            Assertions.assertFalse(statelessMoveValidator.isValidMove(piece, invalidPosition));
        }
    }

}

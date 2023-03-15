package com.rorym.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class PieceTests {

    @Test
    void canCreatePieceWithPieceTypeTeamAndPosition() {
        PieceType pieceType = PieceType.KNIGHT;
        Team team = Team.WHITE;
        Position position = Position.b2;

        Piece piece = new Piece(pieceType, team, position);

        Assertions.assertEquals(pieceType, ReflectionTestUtils.getField(piece, "pieceType"));
        Assertions.assertEquals(team, ReflectionTestUtils.getField(piece, "team"));
        Assertions.assertEquals(position, ReflectionTestUtils.getField(piece, "position"));
    }

    @Test
    void givenPieceHasPositionThenHasPositionReturnsTrueAndFalseOtherwise() {
        Position position = Position.a5;
        Piece piece = new Piece(PieceType.KING, Team.BLACK, position);

        Assertions.assertTrue(piece.hasPosition(position));
        Assertions.assertFalse(piece.hasPosition(Position.a6));
    }

    @Test
    void givenPieceHasTeamThenHasTeamReturnsTrueAndFalseOtherwise() {
        Team team = Team.BLACK;
        Piece piece = new Piece(PieceType.KING, team, Position.a5);

        Assertions.assertTrue(piece.hasTeam(team));
        Assertions.assertFalse(piece.hasTeam(Team.WHITE));
    }

    @Test
    void givenPieceHasPieceTypeThenHasPieceTypeReturnsTrueAndFalseOtherwise() {
        PieceType pieceType = PieceType.KING;
        Piece piece = new Piece(pieceType, Team.BLACK, Position.a5);

        Assertions.assertTrue(piece.hasPieceType(pieceType));
        Assertions.assertFalse(piece.hasPieceType(PieceType.QUEEN));
    }

    @Test
    void pieceEqualIfHasEqualPieceTypeTeamAndPositionAndNotEqualOtherwise() {

        Assertions.assertEquals(
                new Piece(PieceType.KNIGHT, Team.BLACK, Position.a8),
                new Piece(PieceType.KNIGHT, Team.BLACK, Position.a8)
        );

        Assertions.assertNotEquals(
                new Piece(PieceType.KNIGHT, Team.BLACK, Position.a8),
                new Piece(PieceType.BISHOP, Team.BLACK, Position.a8)
        );

        Assertions.assertNotEquals(
                new Piece(PieceType.KNIGHT, Team.BLACK, Position.a8),
                new Piece(PieceType.KNIGHT, Team.WHITE, Position.a8)
        );

        Assertions.assertNotEquals(
                new Piece(PieceType.KNIGHT, Team.BLACK, Position.a8),
                new Piece(PieceType.KNIGHT, Team.BLACK, Position.b2)
        );
    }

    @Test
    void canMovePieceToNewPosition() {

        Piece piece = new Piece(PieceType.KNIGHT, Team.BLACK, Position.c3);

        Position newPosition = Position.a5;
        piece.moveTo(newPosition);

        Assertions.assertTrue(piece.hasPosition(newPosition));
    }

    @Test
    void canGetPieceType() {

        PieceType pieceType = PieceType.QUEEN;
        Piece piece = new Piece(pieceType, Team.WHITE, Position.d8);

        Assertions.assertEquals(pieceType, piece.getPieceType());
    }

    @Test
    void canGetPosition() {

        Position position = Position.d8;
        Piece piece = new Piece(PieceType.QUEEN, Team.WHITE, position);

        Assertions.assertEquals(position, piece.getPosition());
    }

}

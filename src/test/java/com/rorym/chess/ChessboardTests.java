package com.rorym.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ChessboardTests {

    @Mock
    PieceMover pieceMover;
    @InjectMocks
    Chessboard chessboard;

    @Test
    void givenEmptyChessboardCanAddPiece() {

        Piece newPiece = new Piece(PieceType.PAWN, Team.WHITE, Position.a1);

        chessboard.withPiece(newPiece);

        Assertions.assertEquals(Collections.singletonList(newPiece), ReflectionTestUtils.getField(chessboard, "pieces"));
    }

    @Test
    void givenChessboardWithPieceCanGetPieceByPosition() {

        Position position = Position.a8;
        Piece piece = new Piece(PieceType.ROOK, Team.BLACK, position);

        chessboard.withPiece(piece);

        Assertions.assertEquals(piece, chessboard.getPieceBy(position).get());
    }

    @Test
    void givenChessboardWithPieceCanGetPieceListByTeam() {

        Team team = Team.BLACK;
        Piece piece = new Piece(PieceType.ROOK, team, Position.a8);

        chessboard.withPiece(piece);

        Assertions.assertEquals(Collections.singletonList(piece), chessboard.getPieceBy(team));
    }

    @Test
    void givenChessboardWithPieceCanGetPieceListByPieceType() {

        PieceType pieceType = PieceType.ROOK;
        Piece piece = new Piece(pieceType, Team.BLACK, Position.a8);

        chessboard.withPiece(piece);

        Assertions.assertEquals(Collections.singletonList(piece), chessboard.getPieceBy(pieceType));
    }

    @Test
    void givenChessboardWithPieceCanGetPieceListByPieceTypeAndTeam() {

        PieceType pieceType = PieceType.ROOK;
        Team team = Team.BLACK;
        Piece piece = new Piece(pieceType, team, Position.a8);

        chessboard.withPiece(piece);

        Assertions.assertEquals(Collections.singletonList(piece), chessboard.getPieceBy(pieceType, team));
    }

    @Test
    void givenChessboardWithExistingPieceWhenMoveThenPieceMoverCalled() {

        Piece piece = new Piece(PieceType.PAWN, Team.WHITE, Position.a1);
        List<Piece> pieces = Collections.singletonList(piece);
        Position newPosition = Position.a2;

        chessboard.withPiece(piece);
        chessboard.doMove(piece, newPosition);

        Mockito.verify(pieceMover, Mockito.times(1)).movePiece(pieces, piece, newPosition);
    }

    // TODO: Something like givenPieceMoverThrowsIllegalStateExceptionThenDoSomething

}

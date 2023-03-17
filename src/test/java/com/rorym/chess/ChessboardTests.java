package com.rorym.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ChessboardTests {

    @Mock
    PieceMover pieceMover;
    @Mock
    CaptureHandler captureHandler;
    @InjectMocks
    Chessboard chessboard;

    @Test
    void givenEmptyChessboardCanAddPiece() {

        Piece newPiece = new Piece(PieceType.PAWN, Team.WHITE, Position.a1);

        chessboard.withPiece(newPiece);

        Assertions.assertEquals(Collections.singletonList(newPiece), ReflectionTestUtils.getField(chessboard, "pieces"));
    }

    @Test
    void givenChessboardWithExistingPieceWhenMoveThenPieceMoverCalledFollowedByCaptureHandler() {

        Piece piece = new Piece(PieceType.PAWN, Team.WHITE, Position.a1);
        List<Piece> pieces = Collections.singletonList(piece);
        Position newPosition = Position.a2;

        chessboard.withPiece(piece);
        chessboard.doMove(piece, newPosition);

        InOrder inOrder = Mockito.inOrder(pieceMover, captureHandler);
        inOrder.verify(pieceMover, Mockito.times(1)).movePiece(pieces, piece, newPosition);
        inOrder.verify(captureHandler, Mockito.times(1)).handleCapture(pieces, piece);
    }

    @Test
    void givenCaptureHandlerReturnsCapturedPieceThenRemoveCapturedPieceFromPieces() {

        Piece piece = new Piece(PieceType.PAWN, Team.WHITE, Position.d7);
        Position newPosition = Position.d8;
        Piece capturedPiece = new Piece(PieceType.BISHOP, Team.BLACK, newPosition);
        List<Piece> pieces = List.of(piece, capturedPiece);

        chessboard.withPiece(piece);
        chessboard.withPiece(capturedPiece);

        Mockito.doAnswer(invocation -> {
            ReflectionTestUtils.setField(piece, "position", newPosition);
            return null;
        }).when(pieceMover).movePiece(pieces, piece, newPosition);
        Mockito.when(captureHandler.handleCapture(pieces, piece)).thenReturn(Optional.of(capturedPiece));

        chessboard.doMove(piece, newPosition);

        Assertions.assertEquals(Collections.singletonList(piece), ReflectionTestUtils.getField(chessboard, "pieces"));
    }

    // TODO: Something like givenPieceMoverThrowsIllegalStateExceptionThenDoSomething

}

package com.rorym.chess;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
class Chessboard {

    private final List<Piece> pieces = new ArrayList<>();
    private final PieceMover pieceMover;
    private final CaptureHandler captureHandler;

    Chessboard(PieceMover pieceMover, CaptureHandler captureHandler) {
        this.pieceMover = pieceMover;
        this.captureHandler = captureHandler;
    }

    void withPiece(Piece piece) {

        pieces.add(piece);
    }

    void doMove(Piece piece, Position newPosition) {
        pieceMover.movePiece(pieces, piece, newPosition);

        removeCapturedPiece(piece);
    }

    private void removeCapturedPiece(Piece piece) {
        Optional<Piece> capturedPiece = captureHandler.handleCapture(pieces, piece);
        capturedPiece.ifPresent(pieces::remove);
    }

}

package com.rorym.chess;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class CaptureHandler {

    Optional<Piece> handleCapture(List<Piece> pieces, Piece movingPiece) {

        return getCapturedPiece(pieces, movingPiece);
    }

    private Optional<Piece> getCapturedPiece(List<Piece> pieces, Piece movingPiece) {

        return pieces.stream()
                .filter(p1 -> !p1.equals(movingPiece))
                .filter(p1 -> p1.getPosition().equals(movingPiece.getPosition()))
                .filter(p -> !p.hasTeam(movingPiece.getTeam()))
                .findFirst();
    }
}

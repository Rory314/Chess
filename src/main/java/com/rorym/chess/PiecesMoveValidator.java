package com.rorym.chess;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Checks that move is valid with respect to position of other pieces on board. Ignores game state.
 * E.g. cannot (typically) move to position of friendly piece.
 */
@Component
class PiecesMoveValidator {
    boolean isValidMove(List<Piece> pieces, Piece piece, Position newPosition) {

        return !friendlyPieceInNewPosition(
                pieces.stream().filter(p -> !p.equals(piece)).collect(Collectors.toList()),
                piece.getTeam(),
                newPosition
        );
    }

    private boolean friendlyPieceInNewPosition(List<Piece> pieces, Team friendlyTeam, Position newPosition) {

        return pieces.stream().filter(piece -> piece.hasTeam(friendlyTeam))
                .map(Piece::getPosition).anyMatch(pos -> pos.equals(newPosition));
    }
}

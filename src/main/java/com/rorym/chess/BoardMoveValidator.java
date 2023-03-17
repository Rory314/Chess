package com.rorym.chess;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * This component validates moves of a piece while accounting for the blocking of other pieces.
 * Ignores special game state like first move, etc.
 * A move to current position is not valid.
 */
@Component
class BoardMoveValidator {

    boolean isValidMove(List<Piece> pieces, Piece piece, Position newPosition) {
        // Cover simplest (stateless) cases. E.g. ignore pawn moving twice on first move, en passant, etc.

        return switch (piece.getPieceType()) {
            case KING -> isValidKingMove(pieces, piece, newPosition);
            case QUEEN -> isValidQueenMove(pieces, piece, newPosition);
            case ROOK -> isValidRookMove(pieces, piece, newPosition);
            case BISHOP -> isValidBishopMove(pieces, piece, newPosition);
            case KNIGHT -> isValidKnightMove(pieces, piece, newPosition);
            case PAWN -> isValidPawnMove(pieces, piece, newPosition);
        };
    }

    private boolean isValidKingMove(List<Piece> pieces, Piece piece, Position newPosition) {

        List<Position> occupiedPositions = getOccupiedPositions(pieces, piece);

        List<Position> validPositions = Stream.of(
                piece.getPosition().topLeft(1), piece.getPosition().nRanksForward(1), piece.getPosition().topRight(1),
                piece.getPosition().nFilesLeft(1), piece.getPosition().nFilesRight(1),
                piece.getPosition().bottomLeft(1), piece.getPosition().nRanksBackward(1), piece.getPosition().bottomRight(1)
        ).filter(Optional::isPresent).map(Optional::get).filter(pos -> !occupiedPositions.contains(pos)).toList();

        return validPositions.contains(newPosition);

    }

    private boolean isValidQueenMove(List<Piece> pieces, Piece piece, Position newPosition) {

        return isValidRookMove(pieces, piece, newPosition) || isValidBishopMove(pieces, piece, newPosition);
    }

    private boolean isValidRookMove(List<Piece> pieces, Piece piece, Position newPosition) {

        List<Position> validPositions = new ArrayList<>();

        validPositions.addAll(getValidPositionsInDirection(pieces, piece, newPosition, step -> piece.getPosition().nRanksForward(step)));
        validPositions.addAll(getValidPositionsInDirection(pieces, piece, newPosition, step -> piece.getPosition().nRanksBackward(step)));
        validPositions.addAll(getValidPositionsInDirection(pieces, piece, newPosition, step -> piece.getPosition().nFilesRight(step)));
        validPositions.addAll(getValidPositionsInDirection(pieces, piece, newPosition, step -> piece.getPosition().nFilesLeft(step)));

        return validPositions.contains(newPosition);
    }

    private boolean isValidBishopMove(List<Piece> pieces, Piece piece, Position newPosition) {

        List<Position> validPositions = new ArrayList<>();

        validPositions.addAll(getValidPositionsInDirection(pieces, piece, newPosition, step -> piece.getPosition().topLeft(step)));
        validPositions.addAll(getValidPositionsInDirection(pieces, piece, newPosition, step -> piece.getPosition().topRight(step)));
        validPositions.addAll(getValidPositionsInDirection(pieces, piece, newPosition, step -> piece.getPosition().bottomLeft(step)));
        validPositions.addAll(getValidPositionsInDirection(pieces, piece, newPosition, step -> piece.getPosition().bottomRight(step)));

        return validPositions.contains(newPosition);
    }

    private boolean isValidKnightMove(List<Piece> pieces, Piece piece, Position newPosition) {

        if (newPosition.equals(piece.getPosition())) {
            return false;
        }

        List<Integer> validMoveIndices = List.of(-17, -15, -10, -6, 6, 10, 15, 17);
        boolean couldMove = validMoveIndices.contains(newPosition.ordinal() - piece.getPosition().ordinal());

        if (!couldMove) {
            return false;
        }

        return pieces.stream()
                .filter(p1 -> !p1.equals(piece)) // Remove current
                .filter(p1 -> p1.hasTeam(piece.getTeam())) // Get friendly pieces
                .map(Piece::getPosition)
                .noneMatch(p -> p.equals(newPosition)); // and ensure none in new position
    }

    private boolean isValidPawnMove(List<Piece> pieces, Piece piece, Position newPosition) {

        if (getOccupiedPositions(pieces, piece).contains(newPosition)) {
            return false;
        }

        return piece.getPosition().nRanksForward(1).map(newPosition::equals).orElse(false);
    }

    private List<Position> getValidPositionsInDirection(
            List<Piece> pieces,
            Piece piece,
            Position newPosition,
            Function<Integer, Optional<Position>> function
    ) {

        List<Position> validPositions = new ArrayList<>();

        for (int i = 1; i <= 8; i++) {
            // Off board
            Optional<Position> positionInDirection = function.apply(i);
            if (positionInDirection.isEmpty()) {
                break;
            }

            // Unoccupied
            Optional<Piece> pieceAtPosition = pieces.stream().filter(p -> p.hasPosition(positionInDirection.get())).findFirst();
            if (pieceAtPosition.isEmpty()) {
                validPositions.add(positionInDirection.get());
                continue;
            }

            // Moving to enemy piece
            if (positionInDirection.get().equals(newPosition) && !pieceAtPosition.get().hasTeam(piece.getTeam())) {
                validPositions.add(positionInDirection.get());
            }

            break;
        }

        return validPositions;
    }

    private List<Position> getOccupiedPositions(List<Piece> pieces, Piece piece) {
        return pieces.stream()
                .filter(p -> !p.equals(piece))
                .filter(p -> p.hasTeam(piece.getTeam()))
                .map(Piece::getPosition).toList();
    }

}

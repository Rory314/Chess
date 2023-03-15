package com.rorym.chess;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This component validates moves of a piece as though it were the only piece on the board.
 * Ignore other pieces, check, first move, etc.
 * A move to current position is not valid.
 */
@Component
class BoardMoveValidator {

    boolean isValidMove(Piece piece, Position newPosition) {
        // Cover simplest (stateless) cases. E.g. ignore pawn moving twice on first move, en passant, etc.

        return switch (piece.getPieceType()) {
            case KING -> isValidKingMove(piece, newPosition);
            case QUEEN -> isValidQueenMove(piece, newPosition);
            case ROOK -> isValidRookMove(piece, newPosition);
            case BISHOP -> isValidBishopMove(piece, newPosition);
            case KNIGHT -> isValidKnightMove(piece, newPosition);
            case PAWN -> isValidPawnMove(piece, newPosition);
        };
    }

    private boolean isValidKingMove(Piece piece, Position newPosition) {

        List<Position> validPositions = Stream.of(
                piece.getPosition().topLeft(1), piece.getPosition().nRanksForward(1), piece.getPosition().topRight(1),
                piece.getPosition().nFilesRight(-1), piece.getPosition().nFilesRight(1),
                piece.getPosition().bottomLeft(1), piece.getPosition().nRanksForward(-1), piece.getPosition().bottomRight(1)
        ).filter(Optional::isPresent).map(Optional::get).toList();

        return validPositions.contains(newPosition);
    }

    private boolean isValidQueenMove(Piece piece, Position newPosition) {

        return isValidRookMove(piece, newPosition) || isValidBishopMove(piece, newPosition);
    }

    private boolean isValidRookMove(Piece piece, Position newPosition) {

        Stream<Position> above = IntStream.range(1, 8).mapToObj(piece.getPosition()::nRanksForward).filter(Optional::isPresent).map(Optional::get);
        Stream<Position> right = IntStream.range(1, 8).mapToObj(piece.getPosition()::nFilesRight).filter(Optional::isPresent).map(Optional::get);
        Stream<Position> below = IntStream.range(-7, -0).mapToObj(piece.getPosition()::nRanksForward).filter(Optional::isPresent).map(Optional::get);
        Stream<Position> left = IntStream.range(-7, 0).mapToObj(piece.getPosition()::nFilesRight).filter(Optional::isPresent).map(Optional::get);

        List<Position> validPositions = Stream.of(above, right, below, left).flatMap(i -> i).toList();

        return validPositions.contains(newPosition);
    }

    private boolean isValidBishopMove(Piece piece, Position newPosition) {

        Stream<Position> topLeft = IntStream.range(1, 9).mapToObj(piece.getPosition()::topLeft).filter(Optional::isPresent).map(Optional::get);
        Stream<Position> topRight = IntStream.range(1, 9).mapToObj(piece.getPosition()::topRight).filter(Optional::isPresent).map(Optional::get);
        Stream<Position> bottomLeft = IntStream.range(1, 9).mapToObj(piece.getPosition()::bottomLeft).filter(Optional::isPresent).map(Optional::get);
        Stream<Position> bottomRight = IntStream.range(1, 9).mapToObj(piece.getPosition()::bottomRight).filter(Optional::isPresent).map(Optional::get);

        List<Position> validPositions = Stream.of(topLeft, topRight, bottomLeft, bottomRight).flatMap(i -> i).toList();

        return validPositions.contains(newPosition);
    }

    private boolean isValidKnightMove(Piece piece, Position newPosition) {

        List<Integer> validMoveIndices = List.of(6, 10, 15, 17);
        return validMoveIndices.contains(Math.abs(newPosition.ordinal() - piece.getPosition().ordinal()));
    }

    private boolean isValidPawnMove(Piece piece, Position newPosition) {

        return piece.getPosition().nRanksForward(1).map(newPosition::equals).orElse(false);
    }

}

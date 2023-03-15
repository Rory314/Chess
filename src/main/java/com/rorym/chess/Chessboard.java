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

    Chessboard(PieceMover pieceMover) {
        this.pieceMover = pieceMover;
    }

    void withPiece(Piece piece) {

        pieces.add(piece);
    }

    Optional<Piece> getPieceBy(Position position) {

        return pieces.stream().filter(piece -> piece.hasPosition(position)).findFirst();
    }

    List<Piece> getPieceBy(Team team) {

        return pieces.stream().filter(piece -> piece.hasTeam(team)).collect(Collectors.toList());
    }

    List<Piece> getPieceBy(PieceType pieceType) {

        return pieces.stream().filter(piece -> piece.hasPieceType(pieceType)).collect(Collectors.toList());
    }

    List<Piece> getPieceBy(PieceType pieceType, Team team) {

        return pieces.stream().filter(piece -> piece.hasPieceType(pieceType) && piece.hasTeam(team)).collect(Collectors.toList());
    }

    void doMove(Piece piece, Position newPosition) {
        pieceMover.movePiece(pieces, piece, newPosition);
        // TODO: CaptureChecker/CheckmateChecker/NewQueenChecker/Whatever here after pieceMover
    }

}

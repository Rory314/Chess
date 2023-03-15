package com.rorym.chess;

class Piece {

    private final PieceType pieceType;
    private final Team team;
    private Position position;

    Piece(PieceType pieceType, Team team, Position position) {

        this.pieceType = pieceType;
        this.team = team;
        this.position = position;
    }

    PieceType getPieceType() {
        return pieceType;
    }

    public Position getPosition() {
        return position;
    }

    boolean hasPosition(Position position) {

        return this.position == position;
    }

    boolean hasTeam(Team team) {

        return this.team == team;
    }

    boolean hasPieceType(PieceType pieceType) {

        return this.pieceType == pieceType;
    }

    void moveTo(Position newPosition) {

        this.position = newPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Piece piece = (Piece) o;

        if (pieceType != piece.pieceType) return false;
        if (team != piece.team) return false;
        return position == piece.position;
    }

    @Override
    public int hashCode() {
        int result = pieceType.hashCode();
        result = 31 * result + team.hashCode();
        result = 31 * result + position.hashCode();
        return result;
    }
}

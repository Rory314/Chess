package com.rorym.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest
public class MatchIT {

    @Autowired
    private Match match;

    @TestConfiguration
    static class MatchITContextConfiguration {
        @Bean
        public int turn() {
            return 0;
        }
    }

    @Test
    void whiteCanMakeValidMoveFollowedByBlackMakingValidMove() {

        Actor whiteActor = new Actor(Team.WHITE, "white");
        Actor blackActor = new Actor(Team.BLACK, "black");

        Chessboard chessboard = (Chessboard) ReflectionTestUtils.getField(match, "chessboard");

        Piece whiteQueen = new Piece(PieceType.QUEEN, Team.WHITE, Position.d4);
        chessboard.withPiece(whiteQueen);

        Piece blackBishop = new Piece(PieceType.BISHOP, Team.BLACK, Position.h1);
        chessboard.withPiece(blackBishop);

        match.doMove(whiteActor, whiteQueen, Position.d5);
        Assertions.assertEquals(Position.d5, whiteQueen.getPosition());
        Assertions.assertEquals(1, ReflectionTestUtils.getField(match, "turn"));

        match.doMove(blackActor, blackBishop, Position.d5);
        Assertions.assertEquals(Position.d5, blackBishop.getPosition());
        Assertions.assertEquals(2, ReflectionTestUtils.getField(match, "turn"));
    }

}

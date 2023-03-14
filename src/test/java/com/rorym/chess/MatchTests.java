package com.rorym.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class MatchTests {

    @Mock
    Chessboard chessboard;
    @Mock
    ActorActionChecker actorActionChecker;
    Match match;

    @BeforeEach
    void setup() {
        match = new Match(chessboard, actorActionChecker, 0);
    }

    @Test
    void whenActorActionCheckerDeniesMoveThenThrowIllegalStateExceptionAndDoNotChangeTurn() {

        Actor actor = new Actor(Team.WHITE, "white");
        int turn = 0;

        Mockito.when(actorActionChecker.canMove(actor, turn)).thenReturn(false);
        Assertions.assertThrows(
                IllegalStateException.class,
                () -> match.doMove(actor, new Piece(PieceType.KNIGHT, Team.BLACK, Position.a8), Position.a5)
        );
        Assertions.assertEquals(turn, ReflectionTestUtils.getField(match, "turn"));
    }

    @Test
    void whenActorActionCheckerAllowsMoveAndChessboardCanMovePieceSuccessfullyThenIncrementTurnByOne() {

        Actor actor = new Actor(Team.WHITE, "white");
        int turn = 0;

        Mockito.when(actorActionChecker.canMove(actor, turn)).thenReturn(true);
        match.doMove(actor, new Piece(PieceType.PAWN, Team.WHITE, Position.e6), Position.c5);
        Assertions.assertEquals(turn + 1, ReflectionTestUtils.getField(match, "turn"));
    }

    // TODO: Behaviour when actor checker allows but chessboard does not

}

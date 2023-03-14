package com.rorym.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ActorActionCheckerTests {

    ActorActionChecker actorActionChecker;

    @BeforeEach
    void setup() {
        actorActionChecker = new ActorActionChecker();
    }

    @Test
    void givenActorWithTeamWhiteThenCanMoveOnEvenTurn() {

        Actor actor = new Actor(Team.WHITE, "white");

        boolean canMove = actorActionChecker.canMove(actor, 4);

        Assertions.assertTrue(canMove);
    }

    @Test
    void givenActorWithTeamWhiteThenCannotMoveOnOddTurn() {

        Actor actor = new Actor(Team.WHITE, "white");

        boolean canMove = actorActionChecker.canMove(actor, 17);

        Assertions.assertFalse(canMove);
    }

    @Test
    void givenActorWithTeamBlackThenCannotMoveOnEvenTurn() {

        Actor actor = new Actor(Team.BLACK, "black");

        boolean canMove = actorActionChecker.canMove(actor, 0);

        Assertions.assertFalse(canMove);
    }

    @Test
    void givenActorWithTeamBlackThenCanMoveOnOddTurn() {

        Actor actor = new Actor(Team.BLACK, "black");

        boolean canMove = actorActionChecker.canMove(actor, 9);

        Assertions.assertTrue(canMove);
    }

}

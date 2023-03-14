package com.rorym.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class ActorTests {

    @Test
    void canCreateActorWithTeamAndName() {

        Team team = Team.BLACK;
        String name = "black";

        Actor actor = new Actor(team, name);

        Assertions.assertEquals(team, ReflectionTestUtils.getField(actor, "team"));
        Assertions.assertEquals(name, ReflectionTestUtils.getField(actor, "name"));
    }

    @Test
    void givenActorWithTeamThenCanGetTeam() {

        Team team = Team.WHITE;

        Actor actor = new Actor(team, "white");

        Assertions.assertEquals(team, actor.getTeam());
    }

}

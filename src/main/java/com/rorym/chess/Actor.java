package com.rorym.chess;

class Actor {

    private final Team team;
    private final String name;


    Actor(Team team, String name) {
        this.team = team;
        this.name = name;
    }

    Team getTeam() {
        return team;
    }
}

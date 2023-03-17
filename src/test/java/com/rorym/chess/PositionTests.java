package com.rorym.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class PositionTests {

    @Test
    void canMoveOneRankForwardOfMiddleSquare() {

        Position middleSquare = Position.d4;

        Position newSquare = middleSquare.nRanksForward(1).get();

        Assertions.assertEquals(Position.d5, newSquare);
    }

    @Test
    void canMoveTwoRankForwardOfMiddleSquare() {

        Position middleSquare = Position.d4;

        Position newSquare = middleSquare.nRanksForward(2).get();

        Assertions.assertEquals(Position.d6, newSquare);
    }

    @Test
    void cannotMoveOneRankForwardIfWouldBeOffBoard() {

        Optional<Position> empty = Position.b8.nRanksForward(1);

        Assertions.assertTrue(empty.isEmpty());
    }

    @Test
    void cannotMoveTwoRanksForwardIfWouldBeOffBoard() {

        Optional<Position> empty = Position.c7.nRanksForward(2);

        Assertions.assertTrue(empty.isEmpty());
    }

    @Test
    void canMoveOneRankBackwardOfMiddleSquare() {

        Position middleSquare = Position.d4;

        Position newSquare = middleSquare.nRanksBackward(1).get();

        Assertions.assertEquals(Position.d3, newSquare);
    }

    @Test
    void canMoveTwoRankBackwardOfMiddleSquare() {

        Position middleSquare = Position.e4;

        Position newSquare = middleSquare.nRanksBackward(2).get();

        Assertions.assertEquals(Position.e2, newSquare);
    }

    @Test
    void cannotMoveOneRankBackwardIfWouldBeOffBoard() {

        Optional<Position> empty = Position.c1.nRanksBackward(1);

        Assertions.assertTrue(empty.isEmpty());
    }

    @Test
    void cannotMoveTwoRanksBackwardIfWouldBeOffBoard() {

        Optional<Position> empty = Position.b2.nRanksBackward(2);

        Assertions.assertTrue(empty.isEmpty());
    }

    @Test
    void canMoveOneFileRightOfMiddleSquare() {

        Position middleSquare = Position.d4;

        Position newSquare = middleSquare.nFilesRight(1).get();

        Assertions.assertEquals(Position.e4, newSquare);
    }

    @Test
    void canMoveTwoFileRightOfMiddleSquare() {

        Position middleSquare = Position.d4;

        Position newSquare = middleSquare.nFilesRight(2).get();

        Assertions.assertEquals(Position.f4, newSquare);
    }

    @Test
    void cannotMoveOneFileRightIfWouldBeOffBoard() {

        Optional<Position> empty = Position.h3.nFilesRight(1);

        Assertions.assertTrue(empty.isEmpty());
    }

    @Test
    void cannotMoveTwoFilesRightIfWouldBeOffBoard() {

        Optional<Position> empty = Position.g5.nFilesRight(2);

        Assertions.assertTrue(empty.isEmpty());
    }

    @Test
    void canMoveOneFileLeftOfMiddleSquare() {

        Position middleSquare = Position.d4;

        Position newSquare = middleSquare.nFilesLeft(1).get();

        Assertions.assertEquals(Position.c4, newSquare);
    }

    @Test
    void canMoveTwoFileLeftOfMiddleSquare() {

        Position middleSquare = Position.d4;

        Position newSquare = middleSquare.nFilesLeft(2).get();

        Assertions.assertEquals(Position.b4, newSquare);
    }

    @Test
    void cannotMoveOneFileLeftIfWouldBeOffBoard() {

        Optional<Position> empty = Position.a3.nFilesLeft(1);

        Assertions.assertTrue(empty.isEmpty());
    }

    @Test
    void cannotMoveTwoFilesLeftIfWouldBeOffBoard() {

        Optional<Position> empty = Position.b5.nFilesLeft(2);

        Assertions.assertTrue(empty.isEmpty());
    }

    @Test
    void canMoveOneToBottomRightOfMiddleSquare() {

        Position middleSquare = Position.d4;

        Position newSquare = middleSquare.bottomRight(1).get();

        Assertions.assertEquals(Position.e3, newSquare);
    }

    @Test
    void canMoveTwoToBottomRightOfMiddleSquare() {

        Position middleSquare = Position.d4;

        Position newSquare = middleSquare.bottomRight(2).get();

        Assertions.assertEquals(Position.f2, newSquare);
    }

    @Test
    void cannotMoveOneToBottomRightIfWouldBeOffBoard() {

        Optional<Position> empty = Position.d1.bottomRight(1);

        Assertions.assertTrue(empty.isEmpty());
    }

    @Test
    void cannotMoveTwoToBottomRightIfWouldBeOffBoard() {

        Optional<Position> empty = Position.g6.bottomRight(2);

        Assertions.assertTrue(empty.isEmpty());
    }

    @Test
    void canMoveOneToBottomLeftOfMiddleSquare() {

        Position middleSquare = Position.d4;

        Position newSquare = middleSquare.bottomLeft(1).get();

        Assertions.assertEquals(Position.c3, newSquare);
    }

    @Test
    void canMoveTwoToBottomLeftOfMiddleSquare() {

        Position middleSquare = Position.d4;

        Position newSquare = middleSquare.bottomLeft(2).get();

        Assertions.assertEquals(Position.b2, newSquare);
    }

    @Test
    void cannotMoveOneToBottomLeftIfWouldBeOffBoard() {

        Optional<Position> empty = Position.a7.bottomLeft(1);

        Assertions.assertTrue(empty.isEmpty());
    }

    @Test
    void cannotMoveTwoToBottomLeftIfWouldBeOffBoard() {

        Optional<Position> empty = Position.b4.bottomLeft(2);

        Assertions.assertTrue(empty.isEmpty());
    }

    @Test
    void canMoveOneToTopRightOfMiddleSquare() {

        Position middleSquare = Position.d4;

        Position newSquare = middleSquare.topRight(1).get();

        Assertions.assertEquals(Position.e5, newSquare);
    }

    @Test
    void canMoveTwoToTopRightOfMiddleSquare() {

        Position middleSquare = Position.d4;

        Position newSquare = middleSquare.topRight(2).get();

        Assertions.assertEquals(Position.f6, newSquare);
    }

    @Test
    void cannotMoveOneToTopRightIfWouldBeOffBoard() {

        Optional<Position> empty = Position.b8.topRight(1);

        Assertions.assertTrue(empty.isEmpty());
    }

    @Test
    void cannotMoveTwoToTopRightIfWouldBeOffBoard() {

        Optional<Position> empty = Position.e7.topRight(2);

        Assertions.assertTrue(empty.isEmpty());
    }

    @Test
    void canMoveOneToTopLeftOfMiddleSquare() {

        Position middleSquare = Position.d4;

        Position newSquare = middleSquare.topLeft(1).get();

        Assertions.assertEquals(Position.c5, newSquare);
    }

    @Test
    void canMoveTwoToTopLeftOfMiddleSquare() {

        Position middleSquare = Position.d4;

        Position newSquare = middleSquare.topLeft(2).get();

        Assertions.assertEquals(Position.b6, newSquare);
    }

    @Test
    void cannotMoveOneToTopLeftIfWouldBeOffBoard() {

        Optional<Position> empty = Position.a5.topLeft(1);

        Assertions.assertTrue(empty.isEmpty());
    }

    @Test
    void cannotMoveTwoToTopLeftIfWouldBeOffBoard() {

        Optional<Position> empty = Position.b5.topLeft(2);

        Assertions.assertTrue(empty.isEmpty());
    }

}

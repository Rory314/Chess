package com.rorym.chess;

import java.util.Arrays;
import java.util.Optional;

enum Position {
    a8, b8, c8, d8, e8, f8, g8, h8,
    a7, b7, c7, d7, e7, f7, g7, h7,
    a6, b6, c6, d6, e6, f6, g6, h6,
    a5, b5, c5, d5, e5, f5, g5, h5,
    a4, b4, c4, d4, e4, f4, g4, h4,
    a3, b3, c3, d3, e3, f3, g3, h3,
    a2, b2, c2, d2, e2, f2, g2, h2,
    a1, b1, c1, d1, e1, f1, g1, h1;

    private static final Position[] values = Position.values().clone();

    Optional<Position> nRanksForward(int n) {

        if (n <= 0) {
            throw new IllegalStateException("n must be positive.");
        }

        Optional<Position> nRanksForward = nRanksForward();
        for (int i = 0; i < n - 1; i++) {
            if (nRanksForward.isPresent()) {
                nRanksForward = nRanksForward.get().nRanksForward();
            } else {
                break;
            }
        }

        return nRanksForward;
    }

    private Optional<Position> nRanksForward() {
        int currentIndex = Arrays.binarySearch(values, this);

        if (isOnTopOfBoard(currentIndex)) {
            return Optional.empty();
        }

        return Optional.of(values[currentIndex - 8]);
    }

    Optional<Position> nRanksBackward(int n) {

        if (n <= 0) {
            throw new IllegalStateException("n must be positive.");
        }

        Optional<Position> nRanksBackward = nRanksBackward();
        for (int i = 0; i < n - 1; i++) {
            if (nRanksBackward.isPresent()) {
                nRanksBackward = nRanksBackward.get().nRanksBackward();
            } else {
                break;
            }
        }

        return nRanksBackward;
    }

    private Optional<Position> nRanksBackward() {
        int currentIndex = Arrays.binarySearch(values, this);

        if (isOnBottomOfBoard(currentIndex)) {
            return Optional.empty();
        }

        return Optional.of(values[currentIndex + 8]);
    }

    Optional<Position> nFilesRight(int n) {

        if (n <= 0) {
            throw new IllegalStateException("n must be positive.");
        }

        Optional<Position> nFilesRight = nFilesRight();
        for (int i = 0; i < n - 1; i++) {
            if (nFilesRight.isPresent()) {
                nFilesRight = nFilesRight.get().nFilesRight();
            } else {
                break;
            }
        }

        return nFilesRight;
    }

    private Optional<Position> nFilesRight() {
        int currentIndex = Arrays.binarySearch(values, this);

        if (isOnRightOfBoard(currentIndex)) {
            return Optional.empty();
        }

        return Optional.of(values[currentIndex + 1]);
    }

    Optional<Position> nFilesLeft(int n) {

        if (n <= 0) {
            throw new IllegalStateException("n must be positive.");
        }

        Optional<Position> nFilesLeft = nFilesLeft();
        for (int i = 0; i < n - 1; i++) {
            if (nFilesLeft.isPresent()) {
                nFilesLeft = nFilesLeft.get().nFilesLeft();
            } else {
                break;
            }
        }

        return nFilesLeft;
    }

    private Optional<Position> nFilesLeft() {
        int currentIndex = Arrays.binarySearch(values, this);

        if (isOnLeftOfBoard(currentIndex)) {
            return Optional.empty();
        }

        return Optional.of(values[currentIndex - 1]);
    }

    Optional<Position> topLeft(int n) {

        if (n <= 0) {
            throw new IllegalStateException("n must be positive.");
        }

        Optional<Position> topLeft = topLeft();
        for (int i = 0; i < n - 1; i++) {
            if (topLeft.isPresent()) {
                topLeft = topLeft.get().topLeft();
            } else {
                break;
            }
        }

        return topLeft;
    }

    private Optional<Position> topLeft() {
        int currentIndex = Arrays.binarySearch(values, this);

        if (isOnLeftOfBoard(currentIndex) || isOnTopOfBoard(currentIndex)) {
            return Optional.empty();
        }

        return Optional.of(values[currentIndex - 9]);
    }

    Optional<Position> topRight(int n) {

        if (n <= 0) {
            throw new IllegalStateException("n must be positive.");
        }

        Optional<Position> topRight = topRight();
        for (int i = 0; i < n - 1; i++) {
            if (topRight.isPresent()) {
                topRight = topRight.get().topRight();
            } else {
                break;
            }
        }

        return topRight;
    }

    private Optional<Position> topRight() {
        int currentIndex = Arrays.binarySearch(values, this);

        if (isOnRightOfBoard(currentIndex) || isOnTopOfBoard(currentIndex)) {
            return Optional.empty();
        }

        return Optional.of(values[currentIndex - 7]);
    }

    Optional<Position> bottomLeft(int n) {

        if (n <= 0) {
            throw new IllegalStateException("n must be positive.");
        }

        Optional<Position> bottomLeft = bottomLeft();
        for (int i = 0; i < n - 1; i++) {
            if (bottomLeft.isPresent()) {
                bottomLeft = bottomLeft.get().bottomLeft();
            } else {
                break;
            }
        }

        return bottomLeft;
    }

    private Optional<Position> bottomLeft() {
        int currentIndex = Arrays.binarySearch(values, this);

        if (isOnLeftOfBoard(currentIndex) || isOnBottomOfBoard(currentIndex)) {
            return Optional.empty();
        }

        return Optional.of(values[currentIndex + 7]);
    }

    Optional<Position> bottomRight(int n) {

        if (n <= 0) {
            throw new IllegalStateException("n must be positive.");
        }

        Optional<Position> bottomRight = bottomRight();
        for (int i = 0; i < n - 1; i++) {
            if (bottomRight.isPresent()) {
                bottomRight = bottomRight.get().bottomRight();
            } else {
                break;
            }
        }

        return bottomRight;
    }

    private Optional<Position> bottomRight() {
        int currentIndex = Arrays.binarySearch(values, this);

        if (isOnRightOfBoard(currentIndex) || isOnBottomOfBoard(currentIndex)) {
            return Optional.empty();
        }

        return Optional.of(values[currentIndex + 9]);
    }

    private boolean isOnRightOfBoard(int index) {

        return (index + 1) % 8 == 0;
    }

    private boolean isOnBottomOfBoard(int index) {

        return index >= 56;
    }

    private boolean isOnLeftOfBoard(int index) {

        return index % 8 == 0;
    }

    private boolean isOnTopOfBoard(int index) {

        return index <= 7;
    }

}

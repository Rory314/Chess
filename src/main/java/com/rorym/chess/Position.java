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

        int currentIndex = Arrays.binarySearch(values, this);

        int newIndex = currentIndex - n * 8;

        if (newIndex < 0 || newIndex > 63) {
            return Optional.empty();
        }

        return Optional.of(values[newIndex]);
    }

    Optional<Position> nFilesRight(int n) {

        int currentIndex = Arrays.binarySearch(values, this);

        int newIndex = currentIndex + n;

        // On different rank
        if (newIndex / 8 != currentIndex / 8) {
            return Optional.empty();
        }

        return Optional.of(values[newIndex]);
    }

    Optional<Position> topLeft(int n) {

        int currentIndex = Arrays.binarySearch(values, this);

        int newIndex = currentIndex - n * 9;

        if (newIndex < 0) {
            return Optional.empty();
        }

        return Optional.of(values[newIndex]);
    }

    Optional<Position> topRight(int n) {

        int currentIndex = Arrays.binarySearch(values, this);

        int newIndex = currentIndex - n * 7;

        // Zero is edge-case, cannot be top right. Others are to prevent ArrayIndexOutOfBoundsException
        if (newIndex <= 0) {
            return Optional.empty();
        }

        return Optional.of(values[newIndex]);
    }

    Optional<Position> bottomLeft(int n) {

        int currentIndex = Arrays.binarySearch(values, this);

        int newIndex = currentIndex + n * 7;

        // 63 is edge-case, cannot be bottomLeft. Others are to prevent ArrayIndexOutOfBoundsException
        if (newIndex >= 63) {
            return Optional.empty();
        }

        return Optional.of(values[newIndex]);
    }

    Optional<Position> bottomRight(int n) {

        int currentIndex = Arrays.binarySearch(values, this);

        int newIndex = currentIndex + n * 9;

        if (newIndex > 63) {
            return Optional.empty();
        }

        return Optional.of(values[newIndex]);
    }


}

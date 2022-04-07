package com.crebma.kata;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FrameTest {

    @Test
    public void storesAndSumsRoll() {
        Frame frame = new Frame();
        frame.addRoll(3);

        assertEquals(3, frame.score(List.of()));
    }

    @Test
    public void storesAndSumsMultipleRolls() {
        Frame frame = new Frame();
        frame.addRoll(3);
        frame.addRoll(4);
        frame.addRoll(1);

        assertEquals(8, frame.score(List.of()));
    }

    @Test
    public void zeroIfNoRolls() {
        Frame frame = new Frame();
        assertEquals(0, frame.score(List.of()));
        assertEquals(0, frame.score(List.of()));
    }

    @Test
    public void nonSpecialFrameCanScoreItself() {
        Frame frame = new Frame();
        frame.addRoll(5);
        frame.addRoll(4);

        int score = frame.score(List.of());

        assertEquals(9, score);
    }

    @Test
    public void spareFrameCanScoreItself() {
        Frame frame = new Frame();
        frame.addRoll(3);
        frame.addRoll(7);

        Frame nextFrame = new Frame();
        nextFrame.addRoll(3);

        int score = frame.score(List.of(nextFrame));

        assertEquals(13, score);
    }

    @Test
    public void spareFrameWithNoFollowingFramesCanScoreItself() {
        Frame frame = new Frame();
        frame.addRoll(3);
        frame.addRoll(7);

        int score = frame.score(List.of());

        assertEquals(10, score);
    }

    @Test
    public void strikeFrameCanScoreItself() {
        Frame frame = new Frame();
        frame.addRoll(10);

        Frame nextFrame = new Frame();
        nextFrame.addRoll(3);
        nextFrame.addRoll(5);

        int score = frame.score(List.of(nextFrame));

        assertEquals(18, score);
    }

    @Test
    public void strikeFrameWithNoFollowingFramesCanScoreItself() {
        Frame frame = new Frame();
        frame.addRoll(10);

        int score = frame.score(List.of());

        assertEquals(10, score);
    }

    @Test
    public void strikeFrameWithAnotherStrikeFollowingCanScoreItself() {
        Frame frame = new Frame();
        frame.addRoll(10);

        Frame nextFrame = new Frame();
        nextFrame.addRoll(10);
        nextFrame.addRoll(5);
        nextFrame.addRoll(3);

        int score = frame.score(List.of(nextFrame));

        assertEquals(25, score);
    }

    @Test
    public void strikeFrameWithTwoStrikesFollowingCanScoreItself() {
        Frame frame = new Frame();
        frame.addRoll(10);

        Frame nextFrame = new Frame();
        nextFrame.addRoll(10);

        Frame nextNextFrame = new Frame();
        nextFrame.addRoll(10);

        int score = frame.score(List.of(nextFrame, nextNextFrame));

        assertEquals(30, score);
    }

    @Test
    public void scoresTwoStrikesInARowBeforeLastFrame() {
        Frame frame = new Frame();
        frame.addRoll(10);

        Frame nextFrame = new Frame();
        nextFrame.addRoll(10);

        Frame nextNextFrame = new Frame();
        nextNextFrame.addRoll(3);
        nextNextFrame.addRoll(3);

        assertEquals(23, frame.score(List.of(nextFrame, nextNextFrame)));
        assertEquals(16, nextFrame.score(List.of(nextNextFrame)));
        assertEquals(6, nextNextFrame.score(List.of()));
    }

    @Test
    public void weirdSpareCaseWhereNextFrameHasNoRolls() {
        Frame frame = new Frame();
        frame.addRoll(10);

        Frame nextFrame = new Frame();

        assertEquals(10, frame.score(List.of(nextFrame)));
    }
}

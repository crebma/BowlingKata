package com.crebma.kata;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    @Test
    public void scoresNoFrames() {
        Game game = new Game();
        assertEquals(0, game.score());
    }

    @Test
    public void scoresOneFrame() {
        Game game = new Game();
        game.addFrame(makeFrame(1, 1));

        assertEquals(2, game.score());
    }

    @Test
    public void scoresAnotherFrame() {
        Game game = new Game();
        game.addFrame(makeFrame(1, 2));
        assertEquals(3, game.score());
    }

    @Test
    public void scoresTwoFrames() {
        Game game = new Game();
        game.addFrame(makeFrame(1, 2), makeFrame(2, 3));
        assertEquals(8, game.score());
    }

    @Test
    public void scoresASpareBeforeLastFrame() {
        Game game = new Game();
        game.addFrame(makeFrame(5, 5), makeFrame(2, 3));
        assertEquals(17, game.score());
    }

    @Test
    public void scoresAStrikeBeforeLastFrame() {
        Game game = new Game();
        game.addFrame(makeFrame(10), makeFrame(2, 3));
        assertEquals(20, game.score());
    }

    @Test
    public void scoresAStrikeInLastFrame() {
        Game game = new Game();
        game.addFrame(makeFrame(10, 2, 2));
        assertEquals(14, game.score());
    }

    @Test
    public void scoresASpareInLastFrame() {
        Game game = new Game();
        game.addFrame(makeFrame(5, 5, 2));
        assertEquals(12, game.score());
    }

    @Test
    public void scoresTwoStrikesInARow() {
        Game game = new Game();
        game.addFrame(makeFrame(10), makeFrame(10));
        assertEquals(30, game.score());
    }

    @Test
    public void scoresTwoStrikesInARowBeforeLastFrame() {
        Game game = new Game();
        game.addFrame(makeFrame(10), makeFrame(10), makeFrame(3, 3));
        assertEquals(45, game.score());
    }

    @Test
    public void scoresTwoStrikesInLastFrame() {
        Game game = new Game();
        game.addFrame(makeFrame(10), makeFrame(10, 5, 3));
        assertEquals(43, game.score());
    }

    @Test
    public void scoresFullGame() {
        Game game = new Game();
        game.addFrame(
                makeFrame(10),
                makeFrame(9, 1),
                makeFrame(5, 5),
                makeFrame(7, 2),
                makeFrame(10),
                makeFrame(10),
                makeFrame(10),
                makeFrame(9, 0),
                makeFrame(8, 2),
                makeFrame(9, 1, 10)
                     );
        assertEquals(187, game.score());
    }

    public Frame makeFrame(int... rolls) {
        Frame frame = new Frame();
        Arrays.stream(rolls).boxed().toList().forEach(frame::addRoll);
        return frame;
    }
}

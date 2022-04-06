package com.crebma.kata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private final List<List<Integer>> frames = new ArrayList<>();

    @SafeVarargs
    public final void addFrame(List<Integer>... frames) {
        this.frames.addAll(Arrays.asList(frames));
    }

    public int score() {
        int totalScore = 0;
        int numberOfFrames = this.frames.size();
        for (int index = 0; index < numberOfFrames; index++) {
            List<Integer> frame = this.frames.get(index);
            int pinsHit = frame.stream().reduce(Integer::sum).orElse(0);

            if (isSpareOrStrike(pinsHit)) {
                totalScore += getBonus(numberOfFrames, index, frame);
            }
            totalScore += pinsHit;
        }

        return totalScore;
    }

    private int getBonus(int numberOfFrames, int index, List<Integer> frame) {
        int bonus = 0;
        if (hasAtLeastOneMoreFrame(numberOfFrames, index)) {
            List<Integer> nextFrame = this.frames.get(index + 1);

            Integer firstRollOfNextFrame = nextFrame.get(0);
            bonus += firstRollOfNextFrame;

            if (isStrike(frame)) {
                bonus += getStrikeBonus(numberOfFrames, index, nextFrame);
            }
        }
        return bonus;
    }

    private int getStrikeBonus(int numberOfFrames, int index, List<Integer> nextFrame) {
        int strikeBonus = 0;
        if (!isStrike(nextFrame)) {
            Integer secondRollOfNextFrame = nextFrame.get(1);
            strikeBonus += secondRollOfNextFrame;
        } else if (hasAtLeastTwoMoreFrames(numberOfFrames, index)) {
            List<Integer> nextNextFrame = this.frames.get(index + 2);
            Integer firstRollOfNextNextFrame = nextNextFrame.get(0);
            strikeBonus += firstRollOfNextNextFrame;
        }
        return strikeBonus;
    }

    private boolean isStrike(List<Integer> frame) {
        return frame.size() == 1;
    }

    private boolean isSpareOrStrike(int frameScore) {
        return frameScore == 10;
    }

    private boolean hasAtLeastOneMoreFrame(int numberOfFrames, int index) {
        return index < numberOfFrames - 1;
    }

    private boolean hasAtLeastTwoMoreFrames(int numberOfFrames, int index) {
        return index < numberOfFrames - 2;
    }
}

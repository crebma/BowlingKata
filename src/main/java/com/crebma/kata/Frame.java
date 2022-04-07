package com.crebma.kata;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    private final List<Integer> rolls = new ArrayList<>();

    public void addRoll(int roll) {
        rolls.add(roll);
    }

    public int score(List<Frame> followingFrames) {
        int score = pinsHit();
        if (tenPinsHit() && !followingFrames.isEmpty()) {
            Frame nextFrame = followingFrames.get(0);
            score += nextFrame.getFirstRoll();

            if (isStrike()) {
                if(nextFrame.isStrike()) {
                    score += followingFrames.stream().skip(1).findFirst().map(Frame::getFirstRoll).orElse(0);
                } else {
                    score += nextFrame.getSecondRoll();
                }
            }
        }
        return score;
    }

    private int pinsHit() {
        return rolls.stream().reduce(Integer::sum).orElse(0);
    }

    private boolean isStrike() {
        return tenPinsHit() && rolls.size() == 1;
    }

    private boolean tenPinsHit() {
        return pinsHit() > 9;
    }

    private int getFirstRoll() {
        return getRoll(0);
    }

    private int getSecondRoll() {
        return getRoll(1);
    }

    private int getRoll(int rollNumber) {
        if (rollNumber < rolls.size()) {
            return rolls.get(rollNumber);
        }
        return 0;
    }
}

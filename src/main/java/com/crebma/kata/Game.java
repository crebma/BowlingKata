package com.crebma.kata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class Game {
    private final List<Frame> frames = new ArrayList<>();

    public final void addFrame(Frame... frames) {
        this.frames.addAll(Arrays.asList(frames));
    }

    public int score() {
        return scoreFrames(this.frames);
    }

    private int scoreFrames(List<Frame> frames) {
        Optional<Frame> firstFrame = frames.stream().findFirst();
        if (firstFrame.isPresent()) {
            List<Frame> tail = frames.stream().skip(1).collect(toList());
            return firstFrame.get().score(tail) + scoreFrames(tail);
        }
        return 0;
    }

}

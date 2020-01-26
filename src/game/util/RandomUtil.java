package game.util;

import java.util.Random;

public abstract class RandomUtil {
    public static int getRandom(int min, int max) {
        if (min >= max) { throw new IllegalArgumentException("max must be greater than min"); }
        return new Random().nextInt((max - min) + 1) + min;
    }
}

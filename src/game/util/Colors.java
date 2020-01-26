package game.util;

import java.awt.*;
import java.util.Random;
import java.util.stream.IntStream;

public abstract class Colors {

    public static Color fixedRandomColor() {
        Random rand = new Random();
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        int max = IntStream.of(r, g, b).max().getAsInt();
        if(r == max) r = 255;
        if(g == max) g = 255;
        if(b == max) b = 255;

        return new Color(r, g, b);
    }

    public static Color pureRandomColor() {
        Random rand = new Random();
        return new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }
}

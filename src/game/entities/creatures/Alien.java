package game.entities.creatures;

import game.entities.creatures.stats.Stats;
import game.util.Colors;
import game.util.Vector2D;
import game.util.input.KeyHandler;
import game.util.input.MouseHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Alien extends Enemy {

    private boolean colorSet;

    public Alien(Vector2D pos, Vector2D size, Stats stats) {
        super(pos, size, stats);
        colorSet = false;
    }

    @Override
    public void render(Graphics2D g) {
        if (sprite != null && !colorSet) {
            randomizeSpriteColor();
            colorSet = true;
        }
        super.render(g);
    }

    protected void randomizeSpriteColor() {
        Color color = Colors.fixedRandomColor();
        BufferedImage img = sprite.getImg();
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color tempColor = new Color(img.getRGB(j, i), true);
                if (!new Color(img.getRGB(j, i)).equals(Color.BLACK) && tempColor.getAlpha() >= 1) {
                    img.setRGB(j, i, color.getRGB());
                }
            }
        }
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) { }

    @Override
    public void update() {
        Random rand = new Random();
        int x = rand.nextInt(2);
        pos.x += x == 1 ? 2 : -2;
        int y = rand.nextInt(2);
        pos.y += stats.get("MOVE_SPEED").current; // y == 1 ? stats.get("MOVE_SPEED").current : -stats.get("MOVE_SPEED").current;
    }
}

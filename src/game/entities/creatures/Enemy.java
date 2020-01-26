package game.entities.creatures;

import game.entities.creatures.stats.Stats;
import game.util.Colors;
import game.util.input.KeyHandler;
import game.util.input.MouseHandler;
import game.util.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends Creature {
    public Enemy(Vector2D pos, Vector2D size, Stats stats) {
        super(pos, size, stats);
    }

    @Override
    public void render(Graphics2D g) {
//        g.setColor(Colors.pureRandomColor());
//        g.fillRect(pos.x, pos.y, size.x, size.y);
        if (sprite != null) randomizeSpriteColor();
        super.render(g);
        getHitBox().render(g);
    }

    private void randomizeSpriteColor() {
        Color color = Colors.pureRandomColor();
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
    public void input(MouseHandler mouse, KeyHandler key) {

    }

    @Override
    public void update() {
        Random rand = new Random();
        int x = rand.nextInt(2);
        pos.x += x == 1 ? stats.get("MOVE_SPEED").current : -stats.get("MOVE_SPEED").current;
        int y = rand.nextInt(2);
        pos.y += stats.get("MOVE_SPEED").current; // y == 1 ? stats.get("MOVE_SPEED").current : -stats.get("MOVE_SPEED").current;
    }
}

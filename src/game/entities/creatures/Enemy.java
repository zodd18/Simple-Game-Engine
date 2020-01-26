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
        super.render(g);
        getHitBox().render(g);
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) { }

    @Override
    public void update() { }
}

package game.entities.creatures;

import game.entities.creatures.stats.Stats;
import game.util.Vector2D;

import java.awt.*;

public class EpilepticAlien extends Alien {
    public EpilepticAlien(Vector2D pos, Vector2D size, Stats stats) {
        super(pos, size, stats);
    }

    @Override
    public void render(Graphics2D g) {
        if (sprite != null) {
            randomizeSpriteColor();
            sprite.render(g, pos.x, pos.y);
        }
        getHitBox().render(g);
    }
}

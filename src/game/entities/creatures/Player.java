package game.entities.creatures;

import game.entities.Entity;
import game.entities.Projectile;
import game.entities.creatures.stats.Stats;
import game.scenes.SceneManager;
import game.util.input.KeyHandler;
import game.util.input.MouseHandler;
import game.util.Vector2D;

import java.awt.*;

public class Player extends Creature {

    private int timer;

    public Player(Vector2D pos, Vector2D size, Stats stats) {
        super(pos, size, stats);
        timer = 0;
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        getHitBox().render(g);
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {

        if(key.left.down || key.arrowLeft.down) {
            pos.x -= (int) stats.get("MOVE_SPEED").current;
        } if(key.right.down || key.arrowRight.down) {
            pos.x += (int) stats.get("MOVE_SPEED").current;
        }

        // SHOOT
        if(timer <= 0) {
            if(key.arrowUp.down || key.space.down) {
                SceneManager.currentScene.entities.add(
                        new Projectile(
                                new Vector2D(pos.x + (size.x / 2) - 2
                                , pos.y - (size.y / 2))
                                , new Vector2D(5, 20)
                                , 0
                                , (int) stats.get("ATTACK_POWER").current));
                timer = (int) stats.get("ATTACK_SPEED").current;
            }
        }
    }

    @Override
    public void update() {
        if(timer >= 0) timer--;
        if(vulnerableTimer > 0) vulnerableTimer--;

        for (Entity e : SceneManager.currentScene.entities) {
            if(e instanceof Enemy && overlaps(e) && vulnerableTimer == 0) {
                damage(((Enemy) e).getStats().get("ATTACK_POWER").current);
                vulnerableTimer = (int) stats.get("INVINCIBLE_TIME").current;
            }
        }
    }
}

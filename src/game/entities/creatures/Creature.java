package game.entities.creatures;

import game.entities.Entity;
import game.entities.creatures.stats.StatBar;
import game.entities.creatures.stats.Stats;
import game.scenes.Scene;
import game.scenes.SceneManager;
import game.util.LogSystem;
import game.util.input.KeyHandler;
import game.util.input.MouseHandler;
import game.util.Vector2D;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public abstract class Creature extends Entity {

    protected Stats stats;
    private LinkedHashSet<StatBar> statBars;

    protected int vulnerableTimer;

    public Creature(Vector2D pos, Vector2D size, Stats stats) {
        super(pos, size);
        this.stats = stats;
        this.statBars = new LinkedHashSet<>();
        if (stats.get("INVINCIBLE_TIME") == null)
            this.vulnerableTimer = 0;
    }

    public void damage(float dmg) {
        stats.get("HEALTH").current = dmg >= stats.get("HEALTH").current ? 0 : stats.get("HEALTH").current - dmg;
        if(stats.get("HEALTH").current <= 0) destroy = true;
    }

    public void addStatBar(StatBar sb) { statBars.add(sb); }

    public void addStatBar(String statName, int w, int h) {
        StatBar sb = new StatBar(this, statName, w, h);
        stats.toggleDisplay(statName, true);
        statBars.add(sb);
    }

    public StatBar getStatBar(String statName) {
        Iterator<StatBar> it = statBars.iterator();
        StatBar res = null;
        boolean found = false;

        while (it.hasNext() && !found) {
            res = it.next();
            if (res.statName.equals(statName)) found = true;
        }

        if (res == null || !res.statName.equals(statName)) res = null;
        return res;
    }

    @Override
    public void render(Graphics2D g) {
        if (sprite != null) sprite.render(g, pos.x, pos.y);
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) { }

    @Override
    public void update() {
        if(vulnerableTimer > 0) vulnerableTimer--;
    }

    public Stats getStats() { return stats; }

    public LinkedHashSet<StatBar> getStatBars() { return statBars; }
}

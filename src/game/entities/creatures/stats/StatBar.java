package game.entities.creatures.stats;

import game.entities.creatures.Creature;
import game.graphics.UI;
import game.util.LogSystem;
import game.util.Vector2D;
import sun.rmi.runtime.Log;

import java.awt.*;

public class StatBar extends UI {

    public static final int HIDDEN = -1;
    public static final int FIXED_POSITION = 0;
    public static final int FOLLOW_MODE = 1;

    private Creature c;
    public String statName;
    private Vector2D pos;

    private int currentMode;

    private int width, height;

    private Color outline, back, front;

    public StatBar(Creature c, String statName, int w, int h) {
        Stat stat = c.getStats().get(statName);
        if (stat != null) {
            this.c = c;
            this.statName = statName;
            this.currentMode = FOLLOW_MODE;
            this.width = w;
            this.height = h;

            outline = Color.WHITE;
            back = Color.GRAY;
            front = Color.GREEN;
        } else {
            LogSystem.errorLog("Stat: " + statName + " could not be found");
            throw new StatException("Stat: " + statName + " could not be found");
        }
    }

    public void setMode(int mode, Vector2D pos) {
        if (mode == FOLLOW_MODE) {
            currentMode = FOLLOW_MODE;
        } else if (mode == FIXED_POSITION) {
            this.currentMode = mode;
            this.pos = pos;
        } else if (mode == HIDDEN) {
            this.currentMode = HIDDEN;
        } else{
            LogSystem.errorLog("Selected mode: " + mode + " does not exist");
            throw new StatException("Selected mode: " + mode + " does not exist");
        }
    }

    public void render(Graphics2D g) {

        if (currentMode == FOLLOW_MODE) {

        } else if (currentMode == FIXED_POSITION) {
            g.setColor(outline);
            g.fillRect(pos.x, pos.y, width, height);

            g.setColor(back);
            g.fillRect(pos.x + 2, pos.y + 2, width - 4, height - 4);

            g.setColor(front);
            double statRatio =  ((double) c.getStats().get(statName).current / c.getStats().get(statName).initial);
            int fixedWidth = (int) (statRatio * (width - 4));
            g.fillRect(pos.x + 2, pos.y + 2, fixedWidth, height - 4);
        } else if (currentMode == HIDDEN) {

        } else {
            LogSystem.errorLog("Selected mode: " + currentMode + " does not exist");
            throw new StatException("Selected mode: " + currentMode + " does not exist");
        }
    }

    // Setters

    public void setBack(Color back) { this.back = back; }

    public void setFront(Color front) { this.front = front; }

    public void setOutline(Color outline) { this.outline = outline; }

    public void setWidth(int width) { this.width = width; }

    public void setHeight(int height) { this.height = height; }

    // Getters

    public Creature getCreature() {
        return c;
    }
}

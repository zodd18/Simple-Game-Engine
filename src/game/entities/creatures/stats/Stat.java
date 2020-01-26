package game.entities.creatures.stats;

public class Stat {
    public String name;
    public float current;
    public float initial;

    public boolean showBar;

    public Stat(String name, float value) {
        this.initial = value;
        this.current = value;
        this.name = name;
        this.showBar = false;
    }

    public void reset() {
        current = initial;
    }

    @Override
    public String toString() {
        return "(" + current + "/" + initial + ")";
    }
}

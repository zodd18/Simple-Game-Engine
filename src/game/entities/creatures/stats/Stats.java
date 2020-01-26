package game.entities.creatures.stats;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Stats {

    public HashMap<String, Stat> values;

    public Stats(float health, float moveSpeed, float attackSpeed) {
        values = new HashMap<>();
    }

    // HEALTH, MOVE_SPEED, ATTACK_SPEED
    public Stats(Stat... stats) {
        values = new HashMap<>();
        for (Stat s : stats) {
            values.put(s.name, s);
        }
    }

    public void add(String statName, float statValue) {
        values.put(statName, new Stat(statName, statValue));
    }

    public Stat get(String statName) {
        return values.get(statName);
    }

    public void toggleDisplay(String statName, boolean display) {
        get(statName).showBar = display;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        for (Map.Entry<String, Stat> entry : values.entrySet())
            sj.add(entry.getKey() + ": " + entry.getValue());

        return sj.toString();
    }
}

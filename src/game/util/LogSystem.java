package game.util;

public abstract class LogSystem {
    public static final int NONE = 0;
    public static final int INFO = 1;
    public static final int WARN = 2;
    public static final int MAX = 3;
    public static final int ALL = 4;

                 private static int infoLevel = MAX;
                                                            /*
        +------------------------------------------------+
        |           |   InfoLevel hierarchy   |          |
        +------------------------------------------------+
        |    0: []                                       |
        |    1: [ERROR]                                  |
        |    2: [ERROR], [WARNING]                       |
        |    3: [ERROR], [WARNING], [INFO]               |
        |    4: [ERROR], [WARNING], [INFO], {HITBOXES}   |
        +------------------------------------------------+
    */

    public static void log(String msg) {
        if(infoLevel >= MAX) System.out.println("[INFO] " + msg);
    }

    public static void warningLog(String msg) {
        if (infoLevel >= WARN) System.out.println("[WARNING] " + msg);
    }

    public static void errorLog(String msg) {
        if(infoLevel >= INFO) System.out.println("[ERROR] " + msg);
    }

    public static void setInfoLevel(int level) {
        infoLevel = level;
        if (infoLevel > ALL) infoLevel = NONE;
        else if (infoLevel < NONE) infoLevel = NONE;
    }

    public static int getInfoLevel() { return infoLevel; }
}

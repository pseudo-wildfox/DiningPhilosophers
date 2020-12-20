package pseudo.wildfox;

public class Properties {
    public static final String TITLE = "Dining philosophers";
    public static final int SCENE_WIDTH = 500;
    public static final int SCENE_HEIGHT = 500;

    public static final String [] PHILOSOPHERS_NAMES = {"Aristotle", "Plato", "Epicurus", "Socrates", "Descartes"};
    public static final int[][] COORDINATES = {
            {200, 10},
            {390, 160},
            {330, 350},
            {70, 350},
            {0, 160}
    };
    public static final double BRIGHT = 1.0;
    public static final double PALE = 0.3;
    public static final int TIME_TO_EAT = 2000;
    public static final int TIME_TO_THINK = 2000;


    public static final int TABLE_SIZE = 285;
    public static final int TABLE_X = 110;
    public static final int TABLE_Y = 120;
}

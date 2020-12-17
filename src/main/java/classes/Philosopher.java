package classes;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Log4j
@Data
@EqualsAndHashCode(callSuper = true)
public class Philosopher extends Thread {
    //private String name;
    private Fork firstFork;
    private Fork secondFork;

    Random random = new Random();

    public static final String [] NAMES = {"Aristotle", "Plato", "Epicurus", "Confucius", "Descartes"};
    public static final int[][] COORDINATES = {
            {200, 10},
            {390, 160},
            {300, 380},
            {100, 380},
            {0, 160}
    };
    private Philosopher(String name) {
        super(name);
    }


    public static List<Philosopher> philosophersFactory() {
        return Arrays.stream(NAMES).map(Philosopher::new).collect(Collectors.toList());
    }

    @Override
    public void run() {
        log.info("Running " + getName());
        try {
            while (this.isAlive()) {
                Thread.sleep(random.nextInt(2000));
                synchronized (firstFork) {
                    log.info(getName() + " has taken left fork");
                    synchronized (secondFork) {
                        JavaFXManager.getInstance().setOpacity(this.getName(), JavaFXManager.BRIGHT);
                        log.info(getName() + " has taken right fork");
                        log.info(getName() + " is EATING");
                        Thread.sleep(random.nextInt(5000));
                        JavaFXManager.getInstance().setOpacity(this.getName(), JavaFXManager.PALE);
                    }
                    log.info(getName() + " has put left fork");
                }
                Thread.sleep(random.nextInt(4000));
            }
        } catch (InterruptedException e) {
            log.error("Error during " + getName(), e);
        }
    }
}

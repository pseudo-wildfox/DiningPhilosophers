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

    private Fork firstFork;  //left fork
    private Fork secondFork; //right fork

    Random random = new Random();

    private Philosopher(String name) {
        super(name);
    }

    public static List<Philosopher> philosophersFactory() {
        return Arrays.stream(Properties.PHILOSOPHERS_NAMES).map(Philosopher::new).collect(Collectors.toList());
    }

    @Override
    public void run() {
        try {
            while (this.isAlive()) {
                if (firstFork.getId() < secondFork.getId()) {
                    Fork temp = firstFork;
                    firstFork = secondFork;
                    secondFork = temp;
                }
                Thread.sleep(random.nextInt(1000));
                synchronized (firstFork) {
                    log.info(getName() + " has taken left fork");
                    synchronized (secondFork) {
                        JavaFXManager.getInstance().setOpacity(this.getName(), Properties.BRIGHT);
                        log.info(getName() + " has taken right fork");
                        log.info(getName() + " is EATING");
                        Thread.sleep(random.nextInt(Properties.TIME_TO_EAT));
                        JavaFXManager.getInstance().setOpacity(this.getName(), Properties.PALE);
                    }
                    log.info(getName() + " has put left fork");
                }
                Thread.sleep(random.nextInt(Properties.TIME_TO_THINK));
            }
        } catch (InterruptedException e) {
            log.error("Error during " + getName(), e);
        }
    }
}

package classes;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static classes.Properties.TIME_TO_EAT;
import static classes.Properties.TIME_TO_THINK;

@Log4j
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SimplePhilosopher extends Thread {

    protected Fork firstFork;  //left fork
    protected Fork secondFork; //right fork



    public SimplePhilosopher(String name) {
        super(name);
    }

    public static List<SimplePhilosopher> philosophersFactory() {
        List<SimplePhilosopher> guests = Arrays.stream(Properties.PHILOSOPHERS_NAMES).map(SimplePhilosopher::new).collect(Collectors.toList());
        for (int i = 0; i < guests.size(); i++) {
            Fork fork = new Fork(i);
            guests.get(i).setFirstFork(fork);
            guests.get((i+1) % guests.size()).setSecondFork(fork);
        }
        return guests;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
            synchronized (firstFork) {
                log.info(getName() + " has taken left fork");
                synchronized (secondFork) {
                    JavaFXManager.getInstance().setOpacity(this.getName(), Properties.BRIGHT);
                    log.info(getName() + " has taken right fork");
                    log.info(getName() + " is EATING");
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000, TIME_TO_EAT));
                    JavaFXManager.getInstance().setOpacity(this.getName(), Properties.PALE);
                }
                log.info(getName() + " has put left fork");
            }
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, TIME_TO_THINK));
        } catch (InterruptedException e) {
            log.error("Error during " + getName(), e);
        }
    }
}

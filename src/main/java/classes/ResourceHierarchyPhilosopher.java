package classes;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static classes.Properties.*;


@Data
@Log4j
@EqualsAndHashCode(callSuper = true)
public class ResourceHierarchyPhilosopher extends Thread {

    protected Fork firstFork;  //left fork
    protected Fork secondFork; //right fork


    public ResourceHierarchyPhilosopher(String name) {
        super(name);
    }

    public static List<ResourceHierarchyPhilosopher> philosophersFactory() {
        List<ResourceHierarchyPhilosopher> guests = Arrays.stream(PHILOSOPHERS_NAMES).map(ResourceHierarchyPhilosopher::new).collect(Collectors.toList());
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
            while (this.isAlive()) {
                if (firstFork.getId() < secondFork.getId()) {
                    Fork temp = firstFork;
                    firstFork = secondFork;
                    secondFork = temp;
                }
                synchronized (firstFork) {
                    log.info(getName() + " has taken left fork");
                    synchronized (secondFork) {
                        JavaFXManager.getInstance().setOpacity(this.getName(), Properties.BRIGHT);
                        log.info(getName() + " has taken right fork");
                        log.info(getName() + " is EATING");
                        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, TIME_TO_EAT));
                        log.info(getName() + " has put right fork");
                        JavaFXManager.getInstance().setOpacity(this.getName(), Properties.PALE);
                    }
                    log.info(getName() + " has put left fork");
                }
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, TIME_TO_THINK));
            }
        } catch (InterruptedException e) {
            JavaFXManager.getInstance().setOpacity(this.getName(), Properties.BRIGHT);
            log.error("Error during " + getName(), e);
            synchronized (JavaFXManager.getInstance()) {
                JavaFXManager.getInstance().notifyAll();
            }
        }
    }
}

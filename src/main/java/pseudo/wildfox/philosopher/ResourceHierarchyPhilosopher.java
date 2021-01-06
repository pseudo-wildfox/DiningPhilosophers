package pseudo.wildfox.philosopher;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j;
import pseudo.wildfox.Fork;
import pseudo.wildfox.services.JavaFXManager;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static pseudo.wildfox.Properties.*;


@Data
@Log4j
@EqualsAndHashCode(callSuper = true)
public class ResourceHierarchyPhilosopher extends Thread {

    private Fork firstFork;  //left fork
    private Fork secondFork; //right fork


    private ResourceHierarchyPhilosopher(String name) {
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
            if (firstFork.getId() < secondFork.getId()) {
                Fork temp = firstFork;
                firstFork = secondFork;
                secondFork = temp;
            }
            while (this.isAlive()) {
                synchronized (firstFork) {
                    log.info(getName() + " has taken left fork");
                    synchronized (secondFork) {
                        JavaFXManager.getInstance().setOpacity(this.getName(), BRIGHT);
                        log.info(getName() + " has taken right fork");
                        log.info(getName() + " is EATING");
                        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, TIME_TO_EAT));
                        log.info(getName() + " has put right fork");
                        JavaFXManager.getInstance().setOpacity(this.getName(), PALE);
                    }
                    log.info(getName() + " has put left fork");
                }
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, TIME_TO_THINK));
            }
        } catch (InterruptedException e) {
            log.info("Error during " + getName(), e);
        } finally {
            JavaFXManager.getInstance().setOpacity(this.getName(), PALE);
        }
    }
}

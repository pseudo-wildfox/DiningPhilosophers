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
public class LitePhilosopher extends Thread {



    public LitePhilosopher(String name) {
        super(name);
    }

    public static List<LitePhilosopher> philosophersFactory() {
        List<LitePhilosopher> guests = Arrays.stream(Properties.PHILOSOPHERS_NAMES).map(LitePhilosopher::new).collect(Collectors.toList());
        return guests;
    }

    @Override
    public void run() {
        try {
            JavaFXManager.getInstance().setOpacity(this.getName(), Properties.BRIGHT);
            log.info(getName() + " is EATING");
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, TIME_TO_EAT));
            JavaFXManager.getInstance().setOpacity(this.getName(), Properties.PALE);
        } catch (InterruptedException e) {
            log.error("Error during " + getName(), e);
        }
    }
}

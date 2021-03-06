package pseudo.wildfox.philosopher;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import pseudo.wildfox.services.JavaFXManager;
import pseudo.wildfox.Properties;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Log4j
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LitePhilosopher extends Thread {



    private LitePhilosopher(String name) {
        super(name);
    }

    public static List<LitePhilosopher> philosophersFactory() {
        return Arrays.stream(Properties.PHILOSOPHERS_NAMES)
                .map(LitePhilosopher::new).collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    public void run() {
        try {
            JavaFXManager.getInstance().setOpacity(this.getName(), Properties.BRIGHT);
            log.info(getName() + " is EATING");
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, Properties.TIME_TO_EAT));
            JavaFXManager.getInstance().setOpacity(this.getName(), Properties.PALE);
        } catch (InterruptedException e) {
            log.error("Error during " + getName(), e);
            throw e;
        }
    }
}

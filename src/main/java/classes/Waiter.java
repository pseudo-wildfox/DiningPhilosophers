package classes;


import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;

import java.util.List;
import java.util.Random;

@Log4j
@NoArgsConstructor
public class Waiter extends Thread {

    Random rand = new Random();

    @Override
    @SneakyThrows
    public void run() {
        this.setName("Waiter");


        List<? extends Thread> guests;
        guests = ResourceHierarchyPhilosopher.philosophersFactory();
        guests.forEach(Thread::start);
        JavaFXManager.getInstance().makeInterrupt("Next", guests);

        guests.get(0).join();

        Thread.sleep(1000);
        log.info(this.getName() + " returned from join.");


        guests = LitePhilosopher.philosophersFactory();
        List<? extends Thread> trackers = Tracker.createTrackers(guests);
        JavaFXManager.getInstance().makeInterrupt("Next", trackers);
        trackers.forEach(Thread::start);
    }


}

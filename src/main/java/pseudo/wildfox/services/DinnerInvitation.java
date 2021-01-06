package pseudo.wildfox.services;


import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import pseudo.wildfox.philosopher.LitePhilosopher;
import pseudo.wildfox.philosopher.ResourceHierarchyPhilosopher;

import java.util.List;

import static pseudo.wildfox.Properties.BUTTON_NAME;

@Log4j
public class DinnerInvitation extends Thread {
    public DinnerInvitation() {
        this.setName("DinnerInvitation");
    }

    @Override
    @SneakyThrows
    public void run() {
        List<? extends Thread> guests;
        guests = ResourceHierarchyPhilosopher.philosophersFactory();
        JavaFXManager.getInstance().makeInterrupt(BUTTON_NAME, guests);
        guests.forEach(Thread::start);

        guests.get(0).join();
        Thread.sleep(3000);
        log.info(this.getName() + " returned from join.");

        guests = LitePhilosopher.philosophersFactory();
        List<? extends Thread> waiters = Waiter.createWaiters(guests);
        JavaFXManager.getInstance().makeInterrupt(BUTTON_NAME, waiters);
        waiters.forEach(Thread::start);
    }

}

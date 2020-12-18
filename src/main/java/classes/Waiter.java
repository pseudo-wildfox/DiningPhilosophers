package classes;


import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;


@NoArgsConstructor
public class Waiter extends Thread {
    private List<? extends Thread> guests;


    @Override
    @SneakyThrows
    public void run() {
        this.setName("Waiter");
        this.guests = ResourceHierarchyPhilosopher.philosophersFactory();
        guests.forEach(Thread::start);

        JavaFXManager.getInstance().makeInterrupt("Next", guests);

        Object o = new Object();

        synchronized (JavaFXManager.getInstance()) {
            JavaFXManager.getInstance().wait();
        }


        //join threads


        this.guests = SimplePhilosopher.philosophersFactory();
        guests.forEach(Thread::start);
        System.out.println("!!!!!!!!!!!!!!");


    }
}

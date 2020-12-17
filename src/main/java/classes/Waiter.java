package classes;

import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
public class Waiter extends Thread {
    private List<Philosopher> guests;


    @Override
    public void run() {
        this.setName("Waiter");
        this.handOutCutlery();

        guests.forEach(Philosopher::start);
    }

    private void handOutCutlery() {
        for (int i = 0; i < guests.size(); i++) {
            Fork fork = new Fork(i);
            guests.get(i).setFirstFork(fork);
            guests.get((i+1) % guests.size()).setSecondFork(fork);
        }
    }

    private boolean isAnyEating() {
        return guests.stream().anyMatch(Thread::isAlive);
    }

}

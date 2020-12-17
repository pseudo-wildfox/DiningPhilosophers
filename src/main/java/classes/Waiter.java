package classes;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Waiter extends Thread {
    private List<Philosopher> guests;


    @Override
    public void run() {
        this.setName("Waiter");
        this.handOutCutlery();
        //JavaFXManager.getInstance().getRoot().getChildren().filtered(e -> e.getId() == "Aristotle").get(0).setOpacity(1.0);
        //JavaFXManager.getInstance().setOpacity("Descartes", 1.0);

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

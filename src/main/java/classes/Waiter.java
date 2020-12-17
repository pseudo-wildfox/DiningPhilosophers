package classes;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Waiter {
    private List<Philosopher> guests = new ArrayList<>();


    public void startDinner() {
        this.handOutCutlery();



        for(Philosopher x : guests) {
           x.start();
        }
        guests.get(0).isAlive();


    }

    private void handOutCutlery() {
        for (int i = 0; i < guests.size(); i++) {
            Fork fork = new Fork();
            guests.get(i).setLeftFork(fork);
            guests.get((i+1) % guests.size()).setRightFork(fork);
        }
    }

}

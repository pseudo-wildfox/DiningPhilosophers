package classes;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class Waiter extends Thread  {
    private  List<Philosopher> table = new ArrayList<>();


    public void startDinner() {
        handOutCutlery();

        for(Philosopher x : table) {
            x.start();
        }

        //System.out.println(table.get(0).isAlive());
    }

    private void handOutCutlery() {
        for (int i = 0; i < table.size() - 1; i++) {
            Fork fork = new Fork();
            table.get(i).setLeftFork(fork);
            table.get(i+1).setRightFork(fork);
        }
        Fork fork = new Fork();
        table.get(table.size() - 1).setLeftFork(fork);
        table.get(0).setRightFork(fork);
    }

}

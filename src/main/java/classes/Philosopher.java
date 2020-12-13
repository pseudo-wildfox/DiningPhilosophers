package classes;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Philosopher extends Thread {
    //private String name;
    private Fork rightFork;
    private Fork leftFork;

    private PhilosopherState CurrentState;
    public static final String [] names = {"Aristotle", "Plato", "Epicurus", "Confucius", "Descartes"};
    public static int[][] coordinates = {
            {200, 10},
            {400, 200},
            {300, 400},
            {100, 400},
            {0, 200}
    };
    public Philosopher(String name) {
        super(name);
        this.CurrentState = PhilosopherState.THINKING;
    }


    public static List<Philosopher> philosophersFactory() {
        return Arrays.stream(names).map(name -> new Philosopher(name)).collect(Collectors.toList());
    }

    @Override
    public void run() {
        System.out.println("Running " + getName());
        try {
            synchronized (leftFork) {
                long start = System.nanoTime();
                System.out.println(getName() + " has taken left fork");

                synchronized (rightFork) {
                    System.out.println(getName() + " has taken right fork");

                    System.out.println(getName() + " is EATING");
                    CurrentState = PhilosopherState.EATING;

                    Thread.sleep(10_000);
                    System.out.println(getName() + " has put left fork");
                }
                System.out.println(getName() + " has put left fork");
            }
        }catch (InterruptedException e) {
            System.out.println("Thread " +  getName() + " interrupted.");
        }
        System.out.println("Stopping " + getName());
    }

}

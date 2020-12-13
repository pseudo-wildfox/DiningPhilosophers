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

    public Philosopher(String name) {
        super(name);
    }

    private static final String [] names = {"Aristotle", "Plato", "Epicurus", "Confucius", "Rene Descartes"};

    public static List<Philosopher> philosophersFactory() {
        return Arrays.stream(names).map(name -> new Philosopher(name)).collect(Collectors.toList());
    }

    @Override
    public void run() {
        System.out.println("Running " + getName());
        try {
            synchronized (leftFork) {
                System.out.println(getName() + " has taken left fork");
                synchronized (rightFork) {
                    System.out.println(getName() + " has taken right fork");

                    System.out.println(getName() + " is EATING");
                    Thread.sleep(5000);

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

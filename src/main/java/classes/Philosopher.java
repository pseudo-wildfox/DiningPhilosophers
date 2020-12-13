package classes;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Philosopher extends Thread {
    private String name;
    private Fork rightFork;
    private Fork leftFork;
    //private boolean eating;
    private static final String [] names = {"Aristotle", "Plato", "Epicurus", "Confucius", "Rene Descartes"};

    public static List<Philosopher> philosophersFactory() {
        return Arrays.stream(names).map(name -> new Philosopher(name)).collect(Collectors.toList());
    }
    @Override
    public void run() {
        System.out.println("Running " + name);
        try {
            synchronized (leftFork) {
                System.out.println(name + " has taken left fork");
                synchronized (rightFork) {
                    System.out.println(name + " has taken right fork");

                    System.out.println(name + " is EATING");
                    Thread.sleep(5000);

                    System.out.println(name + " has put left fork");
                }
                System.out.println(name + " has put left fork");
            }
        }catch (InterruptedException e) {
            System.out.println("Thread " +  name + " interrupted.");
        }
        System.out.println("Stopping " + name);
    }

    public Philosopher(String name) {
        this.name = name;
        //this.eating = false;
    }

    public Fork getRightFork() {
        return rightFork;
    }

    public void setRightFork(Fork rightFork) {
        this.rightFork = rightFork;
    }

    public Fork getLeftFork() {
        return leftFork;
    }

    public void setLeftFork(Fork leftFork) {
        this.leftFork = leftFork;
    }
}

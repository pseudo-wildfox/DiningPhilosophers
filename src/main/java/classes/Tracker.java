package classes;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static classes.Properties.PHILOSOPHERS_NAMES;

@Data
@NoArgsConstructor
class Tracker extends Thread {
    @Setter
    List<? extends Thread> guests;
    static int first = -1;
    static int second = -1;
    Random rand = new Random();
    final static Object action = new Object();

    static List <? extends Thread> createTrackers (List<? extends Thread> guests) {
        List<Tracker> result = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Tracker temp = new Tracker();
            temp.setGuests(guests);
            result.add(temp);
        }
        return result;
    }

    @Override
    public void run() {
        if (first == -1) {
            first = rand.nextInt(PHILOSOPHERS_NAMES.length);
            firstTracker();
        } else {
            second = pickRandOpposite(first);
            secondTracker();
        }
    }

    private void firstTracker() {
        while (Thread.currentThread().isAlive()) {
            guests.get(first).run();
            synchronized (action) {
                first = pickOppositeNeighbor(second, first);
            }
        }
    }

    private void secondTracker() {
        while (Thread.currentThread().isAlive()) {
            guests.get(second).run();
            synchronized (action) {
                second = pickOppositeNeighbor(first, second);
            }
        }
    }

    private int pickOppositeNeighbor(int leader, int follower) {
        int temp = (follower + 3) % PHILOSOPHERS_NAMES.length;
        return (temp == leader) ? (leader + 3) % PHILOSOPHERS_NAMES.length : (leader + 2) % PHILOSOPHERS_NAMES.length;
    }

    private int pickRandOpposite(int leader) {
        return  (leader + 2 + rand.nextInt(2)) % PHILOSOPHERS_NAMES.length;
    }

}

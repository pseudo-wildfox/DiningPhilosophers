package pseudo.wildfox.services;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static pseudo.wildfox.Properties.PHILOSOPHERS_NAMES;

@Log4j
@NoArgsConstructor
class Waiter extends Thread {
    @Setter
    private List<? extends Thread> guests;
    private static int first = -1;
    private static int second = -1;
    private Random rand = new Random();


    static List <? extends Thread> createWaiters (List<? extends Thread> guests) {
        List<Waiter> toReturn = Arrays.asList(new Waiter(), new Waiter());
        toReturn.forEach(e -> e.setGuests(guests));
        return toReturn;
    }

    @Override
    public void run() {
        if (first == -1) {
            first = rand.nextInt(PHILOSOPHERS_NAMES.length);
            try {
                firstTracker();
            } catch (Exception e) {
                log.info("First waiter is stopped ", e);
                JavaFXManager.getInstance().makeStopProgram("Next");
            }
        } else {
            second = pickRandOpposite(first);
            try {
                secondTracker();
            } catch (Exception e) {
                log.info("First waiter is stopped ", e);
            }
        }
    }

    private void firstTracker() {
        while (Thread.currentThread().isAlive()) {
            guests.get(first).run();
            synchronized (guests) {
                first = pickOppositeNeighbor(second, first);
            }
        }
    }

    private void secondTracker() {
        while (Thread.currentThread().isAlive()) {
            guests.get(second).run();
            synchronized (guests) {
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

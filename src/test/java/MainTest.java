import classes.SimplePhilosopher;
import org.junit.Assert;
import org.junit.Test;


public class MainTest {

    @Test
    public void test() {
        Assert.assertNotNull(SimplePhilosopher.philosophersFactory());

    }

}

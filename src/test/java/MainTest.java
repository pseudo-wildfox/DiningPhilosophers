import classes.Philosopher;
import org.junit.Assert;
import org.junit.Test;


public class MainTest {

    @Test
    public void test() {
        Assert.assertNotNull(Philosopher.philosophersFactory());

    }

}

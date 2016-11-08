import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SumTest {

    @Test
    public void nullMinusSumGivesSum() {
        Sum ti = new Sum(10, 80);
        assertThat(Sum.empty.minus(ti), is(new Sum(-10, 80)));
    }
}
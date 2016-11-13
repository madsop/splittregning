import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SumTest {

    @Test
    public void nullMinusSumGivesSum() {
        Euro ti = new Euro(10, 80);
        assertThat(Euro.empty.minus(ti), is(new Euro(-10, 80)));
    }

    @Test
    public void minusPlussPlussOereproblematikk() {
        Euro start = new Euro(-10, 40);
        Euro tillegg = new Euro(5, 30);
        assertThat(start.pluss(tillegg), is(new Euro(-5, 10)));
    }

    @Test
    public void minusPlussPlussOereproblematikkRundt0() {
        Euro start = new Euro(0, -40);
        Euro tillegg = new Euro(5, 30);
        assertThat(start.pluss(tillegg), is(new Euro(4, 90)));
    }

    @Test
    public void plussPlussPlussOereproblematikk() {
        Euro start = new Euro(10, 40);
        Euro tillegg = new Euro(5, 30);
        assertThat(start.pluss(tillegg), is(new Euro(15, 70)));
    }

    @Test
    public void fractionsWithDifferentLength() {
        Euro toSiffer = new Euro(0, 10);
        Euro treSiffer = new Euro(0, 100);
        assertThat(treSiffer.minus(toSiffer), is(Euro.empty));
        assertThat(treSiffer, is(toSiffer));
    }

    @Test
    public void parseDouble() {
        Double d = Double.parseDouble("1.90");
    }

}
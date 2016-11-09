import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SumTest {

    @Test
    public void nullMinusSumGivesSum() {
        Sum ti = new Sum(10, 80);
        assertThat(Sum.empty.minus(ti), is(new Sum(-10, 80)));
    }

    @Test
    public void minusPlussPlussOereproblematikk() {
        Sum start = new Sum(-10, 40);
        Sum tillegg = new Sum(5, 30);
        assertThat(start.pluss(tillegg), is(new Sum(-5, 10)));
    }

    @Test
    public void minusPlussPlussOereproblematikkRundt0() {
        Sum start = new Sum(0, -40);
        Sum tillegg = new Sum(5, 30);
        assertThat(start.pluss(tillegg), is(new Sum(4, 90)));
    }

    @Test
    public void plussPlussPlussOereproblematikk() {
        Sum start = new Sum(10, 40);
        Sum tillegg = new Sum(5, 30);
        assertThat(start.pluss(tillegg), is(new Sum(15, 70)));
    }

    @Test
    public void fractionsWithDifferentLength() {
        Sum toSiffer = new Sum(0, 10);
        Sum treSiffer = new Sum(0, 100);
        assertThat(treSiffer.minus(toSiffer), is(Sum.empty));
        assertThat(treSiffer, is(toSiffer));
    }

}
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MaaltidTest {

    @Test
    public void getUtestaaendeForUbetaltRekningErHeileSummen() {
        Deltakar mads = new Deltakar("Mads");
        Rett rett = new Rett(100, 0, mads);

        Maaltid maaltid = new Maaltid(rett);
        maaltid.setBetaler(null);
        assertThat(maaltid.getUtestaaende(mads), is(new Sum(100, 0)));
    }

    @Test
    public void getUtestaaendeForBetaltRekningEr0() {
        Deltakar mads = new Deltakar("Mads");
        Rett rett = new Rett(100, 0, mads);

        Maaltid maaltid = new Maaltid(rett);
        maaltid.setBetaler(mads);
        assertThat(maaltid.getUtestaaende(mads), is(Sum.empty));
    }

    @Test
    public void getUtestaaendeErNegativForDenSomHarBetaltOgHalvpartenForDenAndre() {
        Deltakar mads = new Deltakar("Mads");
        Deltakar marie = new Deltakar("Marie");
        Rett rett1 = new Rett(100, 0, mads);
        Rett rett2 = new Rett(100, 0, marie);

        Maaltid maaltid = new Maaltid(rett1, rett2);
        maaltid.setBetaler(mads);
        assertThat(maaltid.getUtestaaende(mads), is(new Sum(-100, 0)));
        assertThat(maaltid.getUtestaaende(marie), is(new Sum(100, 0)));
    }

    @Test
    public void testAtFellesdelenBlirFordeltRett() {
        Deltakar mads = new Deltakar("Mads");
        Deltakar marie = new Deltakar("Marie");
        Rett rett1 = new Rett(0, 0, mads);
        Rett rett2 = new Rett(0, 0, marie);
        Rett felles = new Rett(100, 0, null);

        Maaltid maaltid = new Maaltid(rett1, rett2, felles);
        maaltid.setBetaler(mads);
        assertThat(maaltid.getUtestaaende(mads), is(new Sum(-50, 0)));
        assertThat(maaltid.getUtestaaende(marie), is(new Sum(50, 0)));
    }
}
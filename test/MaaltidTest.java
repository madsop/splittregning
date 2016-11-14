import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MaaltidTest {

    @Test
    public void getUtestaaendeForUbetaltRekningErHeileSummen() {
        Deltakar mads = new Deltakar("Mads");
        Rett rett = new Rett<>("", new Euro(100, 0), mads);

        Maaltid maaltid = new Maaltid("", rett);
        maaltid.setBetaler(null);
        assertThat(maaltid.getUtestaaende(mads), is(new Euro(100, 0)));
    }

    @Test
    public void getUtestaaendeForBetaltRekningEr0() {
        Deltakar mads = new Deltakar("Mads");
        Rett rett = new Rett<>("", new Euro(100, 0), mads);

        Maaltid maaltid = new Maaltid("", rett);
        maaltid.setBetaler(mads);
        assertThat(maaltid.getUtestaaende(mads), is(Euro.empty));
    }

    @Test
    public void getUtestaaendeErNegativForDenSomHarBetaltOgHalvpartenForDenAndre() {
        Deltakar mads = new Deltakar("Mads");
        Deltakar marie = new Deltakar("Marie");
        Rett rett1 = new Rett<>("", new Euro(100, 0), mads);
        Rett rett2 = new Rett<>("", new Euro(100, 0), marie);

        Maaltid maaltid = new Maaltid("", rett1, rett2);
        maaltid.setBetaler(mads);
        assertThat(maaltid.getUtestaaende(mads), is(new Euro(-100, 0)));
        assertThat(maaltid.getUtestaaende(marie), is(new Euro(100, 0)));
    }

    @Test
    public void testAtFellesdelenBlirFordeltRett() {
        Deltakar mads = new Deltakar("Mads");
        Deltakar marie = new Deltakar("Marie");
        Rett rett1 = new Rett<>("", Euro.empty, mads);
        Rett rett2 = new Rett<>("", Euro.empty, marie);
        Rett felles = new Rett<>("", new Euro(100, 0));

        Maaltid maaltid = new Maaltid("", rett1, rett2, felles);
        maaltid.setBetaler(mads);
        assertThat(maaltid.getUtestaaende(mads), is(new Euro(-50, 0)));
        assertThat(maaltid.getUtestaaende(marie), is(new Euro(50, 0)));
        assertThat(maaltid.getRetterFor(mads), is(Collections.singletonList(rett1)));
    }

    @Test
    public void getRetterForTarMedAlleDenneDeltokI() {
        Deltakar mads = new Deltakar("Mads");
        Deltakar marie = new Deltakar("Marie");
        Rett rettMads1 = new Rett<>("", Euro.empty, mads);
        Rett rettMarie = new Rett<>("", Euro.empty, marie);
        Rett rettMads2 = new Rett<>("", Euro.empty, mads);
        Rett felles = new Rett<>("", new Euro(100, 0));

        Maaltid maaltid = new Maaltid("", rettMads1, rettMarie, rettMads2, felles);
        assertThat(maaltid.getRetterFor(mads), is(Arrays.asList(rettMads1, rettMads2)));
    }
}
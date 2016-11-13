import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TurTest {

    @Test
    public void valuta() {
        Tur tur = new Tur();
        Deltakar mads = new Deltakar("Mads");
        Deltakar marie = new Deltakar("Marie");
        tur.addDeltakarar(mads, marie);

        Rett rettEuro = new Rett("Eurorett", new Euro(10, 0), mads, marie);
        Maaltid maaltidEuro = new Maaltid(rettEuro);
        tur.addMaaltid(maaltidEuro);
        maaltidEuro.addBetaling(mads, new Euro(10, 0));

        Rett rettNOK = new Rett("NOKrett", new NOK(10, 0), mads, marie);
        Maaltid maaltidNOK = new Maaltid(rettNOK);
        tur.addMaaltid(maaltidNOK);
        maaltidNOK.addBetaling(marie, new NOK(10, 0));

//        assertThat(tur.getSum(), is())
    }

    @Test
    public void getUtestaaendeReturnerer0VissIngentingErBetalt() {
        Tur tur = new Tur();
        assertThat(tur.getUtestaaende(new Deltakar("Mads")), is(Euro.empty));
    }

}
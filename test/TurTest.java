import javaslang.collection.HashMap;
import maaltid.Maaltid;
import maaltid.Rett;
import org.junit.Test;
import tur.Deltakar;
import sum.Euro;
import sum.NOK;
import sum.Sum;
import tur.Tur;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TurTest {

    @Test
    public void valuta() {
        Deltakar mads = new Deltakar("Mads");
        Deltakar marie = new Deltakar("Marie");
        Tur tur = new Tur(mads, marie);

        Rett rettEuro = new Rett("Eurorett", new Euro(10, 0), mads, marie);
        Maaltid maaltidEuro = new Maaltid("", rettEuro);
        tur.addMaaltid(maaltidEuro);
        maaltidEuro.addBetaling(mads, new Euro(10, 0));

        Rett rettNOK = new Rett("NOKrett", new NOK(10, 0), mads, marie);
        Maaltid maaltidNOK = new Maaltid("", rettNOK);
        tur.addMaaltid(maaltidNOK);
        maaltidNOK.addBetaling(marie, new NOK(10, 0));

        assertThat(tur.getSumINOK(), is(new NOK(103, 300)));
    }

    @Test
    public void getUtestaaendeReturnerer0VissIngentingErBetalt() {
        Tur tur = new Tur();
        assertThat(tur.getUtestaaende(new Deltakar("Mads")), is(Euro.empty));
    }

    @Test
    public void testOppgjer() {
        Deltakar mads = new Deltakar("Mads");
        Deltakar marie = new Deltakar("Marie");
        Tur tur = new Tur(mads, marie);

        Rett rett1 = new Rett("maaltid.Rett 1", new Euro(10, 0), mads, marie);
        Maaltid maaltidEuro = new Maaltid("", rett1);
        tur.addMaaltid(maaltidEuro);
        maaltidEuro.addBetaling(mads, new Euro(10, 0));

        Rett rett2 = new Rett("maaltid.Rett 2", new Euro(10, 0), mads, marie);
        Maaltid maaltidNOK = new Maaltid("", rett2);
        tur.addMaaltid(maaltidNOK);
        maaltidNOK.addBetaling(mads, new Euro(10, 0));

        HashMap<Deltakar, Sum> oppgjer = HashMap.of(
                mads, new Euro(-10, 0), marie, new Euro(10, 0));
        assertThat(tur.getOppgjer(), is(oppgjer));
    }

}
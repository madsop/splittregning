import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BratislavaTest {

    private Deltakar mads;
    private Deltakar marie;
    private Deltakar paal;
    private Deltakar sofie;
    private Deltakar lorents;
    private Deltakar edvard;
    private Deltakar freddy;

    private BratislavaMaaltidFactory bratislavaMaaltidFactory;

    @Before
    public void setUp() throws Exception {
        mads = new Deltakar("Mads");
        marie = new Deltakar("Marie");
        paal = new Deltakar("Pål");
        sofie = new Deltakar("Sofie");
        lorents = new Deltakar("Lorents");
        edvard = new Deltakar("Edvard");
        freddy = new Deltakar("Freddy");
        bratislavaMaaltidFactory = new BratislavaMaaltidFactory(mads, marie, paal, sofie, lorents, edvard, freddy);
    }

    public void getUtestaaendeReturnerer0VissIngentingErBetalt() {
        Tur tur = new Tur();
        assertThat(tur.getUtestaaende(new Deltakar("Mads")), is(Sum.empty));
    }

    @Test
    public void getUtestaaendeFor3120Maaltid() {
        Tur tur = new Tur();
        tur.addDeltakarar(mads, marie, paal, sofie, freddy, lorents, edvard);

        Maaltid lePapillonMaaltid = bratislavaMaaltidFactory.getLePapillonMaaltid(mads, marie, paal, sofie);
        tur.addMaaltid(lePapillonMaaltid);

        assertThat(tur.getUtestaaende(mads), is(createSum(-20, 275)));
        assertThat(tur.getUtestaaende(marie), is(createSum(8, 525)));
        assertThat(tur.getUtestaaende(paal), is(createSum(8, 525)));
        assertThat(tur.getUtestaaende(sofie), is(createSum(3, 225)));
        assertThat(tur.getSum(), is(createSum(31, 20)));
        assertThat(tur.sumOffset(), is(Sum.empty));
    }

    @Test
    public void getUtestaaendeForRedLion3060() {
        Tur tur = new Tur();
        tur.addDeltakarar(mads, marie, paal, sofie, freddy, lorents, edvard);

        Maaltid redLionMaaltid = bratislavaMaaltidFactory.getTheRedLionMaaltid(mads, marie, paal, sofie, lorents, edvard, freddy);
        tur.addMaaltid(redLionMaaltid);

        assertThat(tur.getSum(), is(createSum(30, 60)));
        assertThat(tur.sumOffset(), is(Sum.empty));
    }

    @Test
    public void getUtestaaendeForRedLion3060OgPapillon3120() {
        Tur tur = new Tur();
        tur.addDeltakarar(mads, marie, paal, sofie, freddy, lorents, edvard);

        Maaltid redLionMaaltid = bratislavaMaaltidFactory.getTheRedLionMaaltid(mads, marie, paal, sofie, lorents, edvard, freddy);
        tur.addMaaltid(redLionMaaltid);
        Maaltid lePapillonMaaltid = bratislavaMaaltidFactory.getLePapillonMaaltid(mads, marie, paal, sofie);
        tur.addMaaltid(lePapillonMaaltid);

        assertThat(tur.getSum(), is(createSum(61, 80)));
        assertThat(tur.sumOffset(), is(Sum.empty));
        assertThat(tur.getTotaltBruktUtenFelles(mads), is(new Sum(13, 70)));
        assertThat(tur.getTotaltBruktMedFelles(mads), is(new Sum(14, 425)));
        assertThat(tur.getTotaltBruktFelles(mads), is(new Sum(0, 725)));
        assertThat(tur.getTotaltBruktFelles(lorents), is(Sum.empty));

        tur.printRapportMedRettarFor(mads);
        tur.printRapportMedRettarFor(lorents);
    }

    private Sum createSum(int heltall, int fraction) {
        return new Sum(heltall, fraction);
    }

    private Sum createSum(double verdi) {
        return new Sum(verdi);
    }

    @Test
    public void getUtestaaendeForHeleTuren() {
        Tur tur = new Tur();
        tur.addDeltakarar(mads, marie, paal, sofie, freddy, lorents, edvard);

        Maaltid lePapillonMaaltid = bratislavaMaaltidFactory.getLePapillonMaaltid(mads, marie, paal, sofie);
        tur.addMaaltid(lePapillonMaaltid);

        assertThat(tur.getUtestaaende(mads), is(createSum(-20, 275)));
        assertThat(tur.getUtestaaende(marie), is(createSum(8, 525)));
        assertThat(tur.getUtestaaende(paal), is(createSum(8, 525)));
        assertThat(tur.getUtestaaende(sofie), is(createSum(3, 225)));
        assertThat(tur.getSum(), is(createSum(31, 20)));


        assertThat(tur.sumOffset(), is(Sum.empty));
//        lePapillonMaaltid.print();

        Maaltid redLion = bratislavaMaaltidFactory.getTheRedLionMaaltid(mads, marie, paal, sofie, lorents, edvard, freddy);
        tur.addMaaltid(redLion);

        assertThat(tur.getUtestaaende(mads), is(createSum(-47, 375)));
        assertThat(tur.getUtestaaende(paal), is(createSum(11, 825)));
        assertThat(tur.getUtestaaende(lorents), is(createSum(3, 20)));
        assertThat(tur.getUtestaaende(marie), is(createSum(14, 425)));
        assertThat(tur.getUtestaaende(sofie), is(createSum(6, 125)));
        assertThat(tur.getUtestaaende(edvard), is(createSum(5, 90)));
        assertThat(tur.getUtestaaende(freddy), is(createSum(5, 90)));
//        redLion.print();

        assertThat(tur.getSum(), is(createSum(61, 80)));
        assertThat(tur.sumOffset(), is(Sum.empty));

        Maaltid kontaktMaaltid = bratislavaMaaltidFactory.getKontaktMaaltid();
        tur.addMaaltid(kontaktMaaltid);

        assertThat(tur.getUtestaaende(mads), is(createSum(-39, 675)));
        assertThat(tur.getUtestaaende(paal), is(createSum(17, 125)));
        assertThat(tur.getUtestaaende(lorents), is(createSum(9, 40)));
        assertThat(tur.getUtestaaende(marie), is(createSum(18, 925)));
        assertThat(tur.getUtestaaende(sofie), is(createSum(9, 125)));
        assertThat(tur.getUtestaaende(edvard), is(createSum(-26, 40)));
        assertThat(tur.getUtestaaende(freddy), is(createSum(11, 50)));
        assertThat(tur.getSum(), is(createSum(99, 70)));
        assertThat(tur.sumOffset(), is(Sum.empty));

        Maaltid ufoMaaltid = bratislavaMaaltidFactory.getUFOMaaltid();
   //     ufoMaaltid.print();
        tur.addMaaltid(ufoMaaltid);
        assertThat(tur.getUtestaaende(mads), is(createSum(-33, 775)));
        assertThat(tur.getUtestaaende(paal), is(createSum(24, 625)));
        assertThat(tur.getUtestaaende(lorents), is(createSum(-32, 45)));
        assertThat(tur.getUtestaaende(marie), is(createSum(26, 425)));
        assertThat(tur.getUtestaaende(sofie), is(createSum(12, 775)));
        assertThat(tur.getUtestaaende(edvard), is(createSum(-17, 75)));
        assertThat(tur.getUtestaaende(freddy), is(createSum(20, 15)));
        assertThat(tur.getSum(), is(createSum(147, 45)));
        assertThat(tur.sumOffset(), is(Sum.empty));

        Maaltid urbanSpaceMaaltid = bratislavaMaaltidFactory.getUrbanSpaceMaaltid();
       // urbanSpaceMaaltid.print();
        tur.addMaaltid(urbanSpaceMaaltid);
        assertThat(tur.getUtestaaende(mads), is(createSum(-20, 615)));
        assertThat(tur.getUtestaaende(paal), is(createSum(37, 785)));
        assertThat(tur.getUtestaaende(lorents), is(createSum(-32, 45)));
        assertThat(tur.getUtestaaende(marie), is(createSum(-30, 715)));
        assertThat(tur.getUtestaaende(sofie), is(createSum(12, 775)));
        assertThat(tur.getUtestaaende(edvard), is(createSum(0, -59)));
        assertThat(tur.getUtestaaende(freddy), is(createSum(33, 81)));
        assertThat(tur.getSum(), is(createSum(217, 83)));
        assertThat(tur.sumOffset(), is(Sum.empty));

        Maaltid kanin = bratislavaMaaltidFactory.getKaninMaaltid();
//        kanin.print();
        tur.addMaaltid(kanin);
        assertThat(tur.getUtestaaende(mads), is(createSum(-35, 529)));
        assertThat(tur.getUtestaaende(marie), is(createSum(-5, 859)));
        assertThat(tur.getUtestaaende(paal), is(createSum(-15, 629)));
        assertThat(tur.getUtestaaende(lorents), is(createSum(0, 636)));
        assertThat(tur.getUtestaaende(edvard), is(createSum(11, 756)));
        assertThat(tur.getUtestaaende(freddy), is(new Sum(32.166)));
        assertThat(tur.getUtestaaende(sofie), is(createSum(12, 461)));
        assertThat(tur.getSum(), is(new Sum(464, 83)));
        assertThat(tur.sumOffset(), is(Sum.empty));

        assertThat(tur.getTotaltBetalt(mads), is(new Sum(111, 80)));
        assertThat(tur.getTotaltBetalt(marie), is(new Sum(80, 38)));

        Maaltid ucetMaaltid = bratislavaMaaltidFactory.getUdetMaaltid();
        tur.addMaaltid(ucetMaaltid);
        assertThat(tur.sumOffset(), is(Sum.empty));
        assertThat(tur.getUtestaaende(mads), is(createSum(-23, 988)));
        assertThat(tur.getUtestaaende(marie), is(createSum(5, 672)));
        assertThat(tur.getUtestaaende(paal), is(createSum(-101.088)));
        assertThat(tur.getUtestaaende(sofie), is(createSum(24, 132)));
        assertThat(tur.getUtestaaende(edvard), is(createSum(31, 777)));
        assertThat(tur.getUtestaaende(freddy), is(createSum(46, 787)));
        assertThat(tur.getUtestaaende(lorents), is(createSum(16, 707)));
        // ucetMaaltid.print();
        assertThat(tur.getSum(), is(new Sum(561, 83)));

        Maaltid urbanBistroMaaltid = bratislavaMaaltidFactory.getUrbanBistroMaaltid();
        printSluttrapport(tur);
    }

    private void printSluttrapport(Tur tur) {
        tur.printRapportMedRettarFor(mads);
        printSkiljeline();
        tur.printRapportMedRettarFor(marie);
        printSkiljeline();
        tur.printRapportMedRettarFor(paal);
        printSkiljeline();
        tur.printRapportMedRettarFor(sofie);
        printSkiljeline();
        tur.printRapportMedRettarFor(lorents);
        printSkiljeline();
        tur.printRapportMedRettarFor(freddy);
        printSkiljeline();
        tur.printRapportMedRettarFor(edvard);
    }

    private void printSkiljeline() {
        System.out.println("--------------------------------------");
    }
}
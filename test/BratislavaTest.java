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

    @Test
    public void getUtestaaendeFor3120Maaltid() {
        Tur tur = new Tur();
        tur.addDeltakarar(mads, marie, paal, sofie, freddy, lorents, edvard);

        Maaltid lePapillonMaaltid = bratislavaMaaltidFactory.getLePapillonMaaltid(mads, marie, paal, sofie);
        tur.addMaaltid(lePapillonMaaltid);

        assertThat(tur.getUtestaaende(mads), is(createEuro(-20, 275)));
        assertThat(tur.getUtestaaende(marie), is(createEuro(8, 525)));
        assertThat(tur.getUtestaaende(paal), is(createEuro(8, 525)));
        assertThat(tur.getUtestaaende(sofie), is(createEuro(3, 225)));
        assertThat(tur.getSum(), is(createEuro(31, 20)));
        assertThat(tur.sumOffset(), is(Euro.empty));
    }

    @Test
    public void getUtestaaendeForRedLion3060() {
        Tur tur = new Tur();
        tur.addDeltakarar(mads, marie, paal, sofie, freddy, lorents, edvard);

        Maaltid redLionMaaltid = bratislavaMaaltidFactory.getTheRedLionMaaltid(mads, marie, paal, sofie, lorents, edvard, freddy);
        tur.addMaaltid(redLionMaaltid);

        assertThat(tur.getSum(), is(createEuro(30, 60)));
        assertThat(tur.sumOffset(), is(Euro.empty));
    }

    @Test
    public void getUtestaaendeForRedLion3060OgPapillon3120() {
        Tur tur = new Tur();
        tur.addDeltakarar(mads, marie, paal, sofie, freddy, lorents, edvard);

        Maaltid redLionMaaltid = bratislavaMaaltidFactory.getTheRedLionMaaltid(mads, marie, paal, sofie, lorents, edvard, freddy);
        tur.addMaaltid(redLionMaaltid);
        Maaltid lePapillonMaaltid = bratislavaMaaltidFactory.getLePapillonMaaltid(mads, marie, paal, sofie);
        tur.addMaaltid(lePapillonMaaltid);

        assertThat(tur.getSum(), is(createEuro(61, 80)));
        assertThat(tur.sumOffset(), is(Euro.empty));
        assertThat(tur.getTotaltBruktUtenFelles(mads), is(new Euro(13, 70)));
        assertThat(tur.getTotaltBruktMedFelles(mads), is(new Euro(14, 425)));
        assertThat(tur.getTotaltBruktFelles(mads), is(new Euro(0, 725)));
        assertThat(tur.getTotaltBruktFelles(lorents), is(Euro.empty));

        tur.printRapportMedRettarFor(mads);
        tur.printRapportMedRettarFor(lorents);
    }

    private Euro createEuro(int heltall, int fraction) {
        return new Euro(heltall, fraction);
    }

    private Euro createEuro(double verdi) {
        return new Euro(verdi);
    }

    @Test
    public void getUtestaaendeForHeleTuren() {
        Tur tur = new Tur();
        tur.addDeltakarar(mads, marie, paal, sofie, freddy, lorents, edvard);

        Maaltid lePapillonMaaltid = bratislavaMaaltidFactory.getLePapillonMaaltid(mads, marie, paal, sofie);
        tur.addMaaltid(lePapillonMaaltid);

        assertThat(tur.getUtestaaende(mads), is(createEuro(-20, 275)));
        assertThat(tur.getUtestaaende(marie), is(createEuro(8, 525)));
        assertThat(tur.getUtestaaende(paal), is(createEuro(8, 525)));
        assertThat(tur.getUtestaaende(sofie), is(createEuro(3, 225)));
        assertThat(tur.getSum(), is(createEuro(31, 20)));


        assertThat(tur.sumOffset(), is(Euro.empty));
//        lePapillonMaaltid.print();

        Maaltid redLion = bratislavaMaaltidFactory.getTheRedLionMaaltid(mads, marie, paal, sofie, lorents, edvard, freddy);
        tur.addMaaltid(redLion);

        assertThat(tur.getUtestaaende(mads), is(createEuro(-47, 375)));
        assertThat(tur.getUtestaaende(paal), is(createEuro(11, 825)));
        assertThat(tur.getUtestaaende(lorents), is(createEuro(3, 20)));
        assertThat(tur.getUtestaaende(marie), is(createEuro(14, 425)));
        assertThat(tur.getUtestaaende(sofie), is(createEuro(6, 125)));
        assertThat(tur.getUtestaaende(edvard), is(createEuro(5, 90)));
        assertThat(tur.getUtestaaende(freddy), is(createEuro(5, 90)));
//        redLion.print();

        assertThat(tur.getSum(), is(createEuro(61, 80)));
        assertThat(tur.sumOffset(), is(Euro.empty));

        Maaltid kontaktMaaltid = bratislavaMaaltidFactory.getKontaktMaaltid();
        tur.addMaaltid(kontaktMaaltid);

        assertThat(tur.getUtestaaende(mads), is(createEuro(-39, 675)));
        assertThat(tur.getUtestaaende(paal), is(createEuro(17, 125)));
        assertThat(tur.getUtestaaende(lorents), is(createEuro(9, 40)));
        assertThat(tur.getUtestaaende(marie), is(createEuro(18, 925)));
        assertThat(tur.getUtestaaende(sofie), is(createEuro(9, 125)));
        assertThat(tur.getUtestaaende(edvard), is(createEuro(-26, 40)));
        assertThat(tur.getUtestaaende(freddy), is(createEuro(11, 50)));
        assertThat(tur.getSum(), is(createEuro(99, 70)));
        assertThat(tur.sumOffset(), is(Euro.empty));

        Maaltid ufoMaaltid = bratislavaMaaltidFactory.getUFOMaaltid();
   //     ufoMaaltid.print();
        tur.addMaaltid(ufoMaaltid);
        assertThat(tur.getUtestaaende(mads), is(createEuro(-33, 775)));
        assertThat(tur.getUtestaaende(paal), is(createEuro(24, 625)));
        assertThat(tur.getUtestaaende(lorents), is(createEuro(-32, 45)));
        assertThat(tur.getUtestaaende(marie), is(createEuro(26, 425)));
        assertThat(tur.getUtestaaende(sofie), is(createEuro(12, 775)));
        assertThat(tur.getUtestaaende(edvard), is(createEuro(-17, 75)));
        assertThat(tur.getUtestaaende(freddy), is(createEuro(20, 15)));
        assertThat(tur.getSum(), is(createEuro(147, 45)));
        assertThat(tur.sumOffset(), is(Euro.empty));

        Maaltid urbanSpaceMaaltid = bratislavaMaaltidFactory.getUrbanSpaceMaaltid();
       // urbanSpaceMaaltid.print();
        tur.addMaaltid(urbanSpaceMaaltid);
        assertThat(tur.getUtestaaende(mads), is(createEuro(-20, 615)));
        assertThat(tur.getUtestaaende(paal), is(createEuro(37, 785)));
        assertThat(tur.getUtestaaende(lorents), is(createEuro(-32, 45)));
        assertThat(tur.getUtestaaende(marie), is(createEuro(-30, 715)));
        assertThat(tur.getUtestaaende(sofie), is(createEuro(12, 775)));
        assertThat(tur.getUtestaaende(edvard), is(createEuro(0, -59)));
        assertThat(tur.getUtestaaende(freddy), is(createEuro(33, 81)));
        assertThat(tur.getSum(), is(createEuro(217, 83)));
        assertThat(tur.sumOffset(), is(Euro.empty));

        Maaltid kanin = bratislavaMaaltidFactory.getKaninMaaltid();
//        kanin.print();
        tur.addMaaltid(kanin);
        assertThat(tur.getUtestaaende(mads), is(createEuro(-35, 529)));
        assertThat(tur.getUtestaaende(marie), is(createEuro(-5, 859)));
        assertThat(tur.getUtestaaende(paal), is(createEuro(-15, 629)));
        assertThat(tur.getUtestaaende(lorents), is(createEuro(0, 636)));
        assertThat(tur.getUtestaaende(edvard), is(createEuro(11, 756)));
        assertThat(tur.getUtestaaende(freddy), is(new Euro(32.166)));
        assertThat(tur.getUtestaaende(sofie), is(createEuro(12, 461)));
        assertThat(tur.getSum(), is(new Euro(464, 83)));
        assertThat(tur.sumOffset(), is(Euro.empty));

        assertThat(tur.getTotaltBetalt(mads), is(new Euro(111, 80)));
        assertThat(tur.getTotaltBetalt(marie), is(new Euro(80, 38)));

        Maaltid ucetMaaltid = bratislavaMaaltidFactory.getUdetMaaltid();
        tur.addMaaltid(ucetMaaltid);
        assertThat(tur.sumOffset(), is(Euro.empty));
        assertThat(tur.getUtestaaende(mads), is(createEuro(-23, 988)));
        assertThat(tur.getUtestaaende(marie), is(createEuro(5, 672)));
        assertThat(tur.getUtestaaende(paal), is(createEuro(-101.088)));
        assertThat(tur.getUtestaaende(sofie), is(createEuro(24, 132)));
        assertThat(tur.getUtestaaende(edvard), is(createEuro(31, 777)));
        assertThat(tur.getUtestaaende(freddy), is(createEuro(46, 787)));
        assertThat(tur.getUtestaaende(lorents), is(createEuro(16, 707)));
        // ucetMaaltid.print();
        assertThat(tur.getSum(), is(new Euro(561, 83)));

        Maaltid urbanBistroMaaltid = bratislavaMaaltidFactory.getUrbanBistroMaaltid();
        tur.addMaaltid(urbanBistroMaaltid);
        assertThat(tur.sumOffset(), is(Euro.empty));
        assertThat(tur.getSum(), is(new Euro(611, 83)));
        assertThat(tur.getUtestaaende(mads), is(createEuro(-11, 488)));
        assertThat(tur.getUtestaaende(marie), is(createEuro(18, 172)));
        assertThat(tur.getUtestaaende(paal), is(createEuro(-98.588)));
        assertThat(tur.getUtestaaende(sofie), is(createEuro(-3, 368)));
        assertThat(tur.getUtestaaende(edvard), is(createEuro(31, 777)));
        assertThat(tur.getUtestaaende(freddy), is(createEuro(46, 787)));
        assertThat(tur.getUtestaaende(lorents), is(createEuro(16, 707)));

        Maaltid iLeiligheta = bratislavaMaaltidFactory.getILeiligheta();
        tur.addMaaltid(iLeiligheta);
        assertThat(tur.sumOffset(), is(Euro.empty));
        assertThat(tur.getUtestaaende(mads), is(createEuro(-12, 488)));
        assertThat(tur.getUtestaaende(marie), is(createEuro(1, 172)));
        assertThat(tur.getUtestaaende(paal), is(createEuro(-98, 588)));
        assertThat(tur.getUtestaaende(sofie), is(createEuro(2, 632)));
        assertThat(tur.getUtestaaende(lorents), is(createEuro(28, 707)));
        assertThat(tur.getUtestaaende(edvard), is(createEuro(37, 777)));
        assertThat(tur.getUtestaaende(freddy), is(createEuro(40, 787)));

        Maaltid overnattingBratislava = bratislavaMaaltidFactory.getOvernattingBratislava();
        tur.addMaaltid(overnattingBratislava);
        assertThat(tur.sumOffset(), is(Euro.empty));


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

        StringBuilder stringBuilder = new StringBuilder()
                .append("\nUteståande Mads: ").append(tur.getUtestaaende(mads).getOriginalAndNOK())
                .append("\nUteståande Marie: ").append(tur.getUtestaaende(marie).getOriginalAndNOK())
                .append("\nUteståande Pål: ").append(tur.getUtestaaende(paal).getOriginalAndNOK())
                .append("\nUteståande Sofie: ").append(tur.getUtestaaende(sofie).getOriginalAndNOK())
                .append("\nUteståande Lorents: ").append(tur.getUtestaaende(lorents).getOriginalAndNOK())
                .append("\nUteståande Freddy: ").append(tur.getUtestaaende(freddy).getOriginalAndNOK())
                .append("\nUteståande Edvard: ").append(tur.getUtestaaende(edvard).getOriginalAndNOK());
        System.out.println(stringBuilder);
    }

    private void printSkiljeline() {
        System.out.println("--------------------------------------");
    }
}
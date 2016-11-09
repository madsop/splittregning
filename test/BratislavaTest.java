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

    @Before
    public void setUp() throws Exception {
        mads = new Deltakar("Mads");
        marie = new Deltakar("Marie");
        paal = new Deltakar("Pål");
        sofie = new Deltakar("Sofie");
        lorents = new Deltakar("Lorents");
        edvard = new Deltakar("Edvard");
        freddy = new Deltakar("Freddy");
    }

    public void getUtestaaendeReturnerer0VissIngentingErBetalt() {
        Tur tur = new Tur();
        assertThat(tur.getUtestaaende(new Deltakar("Mads")), is(Sum.empty));
    }

    @Test
    public void getUtestaaendeFor3120Maaltid() {
        Tur tur = new Tur();
        tur.addDeltakarar(mads, marie, paal, sofie, freddy, lorents, edvard);

        Maaltid lePapillonMaaltid = getLePapillonMaaltid(mads, marie, paal, sofie);
        tur.addMaaltid(lePapillonMaaltid);

        assertThat(tur.getUtestaaende(mads), is(createSum(-20, 28)));
        assertThat(tur.getUtestaaende(marie), is(createSum(8, 52)));
        assertThat(tur.getUtestaaende(paal), is(createSum(8, 52)));
        assertThat(tur.getUtestaaende(sofie), is(createSum(3, 22)));
        assertThat(tur.getSum(), is(createSum(31, 20)));
        assertThat(tur.sumOffset(), is(0));
    }

    private Sum createSum(int heltall, int fraction) {
        return new Sum(heltall, fraction);
    }

    @Test
    public void getUtestaaendeForHeleTuren() {
        Tur tur = new Tur();
        tur.addDeltakarar(mads, marie, paal, sofie, freddy, lorents, edvard);

        Maaltid lePapillonMaaltid = getLePapillonMaaltid(mads, marie, paal, sofie);
        tur.addMaaltid(lePapillonMaaltid);

        assertThat(tur.getUtestaaende(mads), is(createSum(-20, 28)));
        assertThat(tur.getUtestaaende(marie), is(createSum(8, 52)));
        assertThat(tur.getUtestaaende(paal), is(createSum(8, 52)));
        assertThat(tur.getUtestaaende(sofie), is(createSum(3, 22)));
        assertThat(tur.getSum(), is(createSum(31, 20)));

        assertThat(tur.sumOffset(), is(0));
//        lePapillonMaaltid.print();

        Maaltid redLion = getTheRedLionMaaltid(mads, marie, paal, sofie, lorents, edvard, freddy);
        tur.addMaaltid(redLion);

        assertThat(tur.getUtestaaende(mads), is(createSum(-47, 38)));
        assertThat(tur.getUtestaaende(paal), is(createSum(11, 82)));
        assertThat(tur.getUtestaaende(lorents), is(createSum(3, 20)));
        assertThat(tur.getUtestaaende(marie), is(createSum(14, 42)));
        assertThat(tur.getUtestaaende(sofie), is(createSum(6, 12)));
        assertThat(tur.getUtestaaende(edvard), is(createSum(5, 90)));
        assertThat(tur.getUtestaaende(freddy), is(createSum(5, 90)));
//        redLion.print();

        assertThat(tur.getSum(), is(createSum(61, 80)));
        assertThat(tur.sumOffset(), is(0));

        Maaltid kontaktMaaltid = getKontaktMaaltid();
        tur.addMaaltid(kontaktMaaltid);

        assertThat(tur.getUtestaaende(mads), is(createSum(-39, 8)));
        assertThat(tur.getUtestaaende(paal), is(createSum(17, 12)));
        assertThat(tur.getUtestaaende(lorents), is(createSum(9, 40)));
        assertThat(tur.getUtestaaende(marie), is(createSum(18, 92)));
        assertThat(tur.getUtestaaende(sofie), is(createSum(9, 12)));
        assertThat(tur.getUtestaaende(edvard), is(createSum(-26, 20)));
        assertThat(tur.getUtestaaende(freddy), is(createSum(11, 50)));
        assertThat(tur.getSum(), is(createSum(99, 70)));
        assertThat(tur.sumOffset(), is(0));

        Maaltid ufoMaaltid = getUFOMaaltid();
   //     ufoMaaltid.print();
        tur.addMaaltid(ufoMaaltid);
        assertThat(tur.getUtestaaende(mads), is(createSum(-34, 98)));
        assertThat(tur.getUtestaaende(paal), is(createSum(24, 62)));
        assertThat(tur.getUtestaaende(lorents), is(createSum(-31, 25)));
        assertThat(tur.getUtestaaende(marie), is(createSum(26, 42)));
        assertThat(tur.getUtestaaende(sofie), is(createSum(12, 77)));
        assertThat(tur.getUtestaaende(edvard), is(createSum(-18, 85)));
        assertThat(tur.getUtestaaende(freddy), is(createSum(20, 15)));
        assertThat(tur.getSum(), is(createSum(147, 45)));
        assertThat(tur.sumOffset(), is(0));

        Maaltid urbanSpaceMaaltid = getUrbanSpaceMaaltid();
       // urbanSpaceMaaltid.print();
        tur.addMaaltid(urbanSpaceMaaltid);
        assertThat(tur.getUtestaaende(mads), is(createSum(-20, 14)));
        assertThat(tur.getUtestaaende(paal), is(createSum(37, 78)));
        assertThat(tur.getUtestaaende(lorents), is(createSum(-31, 25)));
        assertThat(tur.getUtestaaende(marie), is(createSum(-31, 56)));
        assertThat(tur.getUtestaaende(sofie), is(createSum(12, 77)));
        assertThat(tur.getUtestaaende(edvard), is(createSum(0, 1)));
        assertThat(tur.getUtestaaende(freddy), is(createSum(33, 81)));
        assertThat(tur.getSum(), is(createSum(217, 83)));
        assertThat(tur.sumOffset(), is(0));

        Maaltid kanin = getKaninMaaltid();
//        kanin.print();
        tur.addMaaltid(kanin);
        assertThat(tur.getUtestaaende(mads), is(createSum(-35, 19)));
        assertThat(tur.getUtestaaende(marie), is(createSum(-6, 28)));
        assertThat(tur.getUtestaaende(paal), is(createSum(-15, 33)));
        assertThat(tur.getUtestaaende(lorents), is(createSum(2, 20)));
        assertThat(tur.getUtestaaende(edvard), is(createSum(12, 22)));
        assertThat(tur.getUtestaaende(freddy), is(createSum(33, 59)));
        assertThat(tur.getUtestaaende(sofie), is(createSum(13, 22)));
        assertThat(tur.sumOffset(), is(0));

       // Maaltid ucetMaaltid = getUcetMaaltid(); TODO
       // ucetMaaltid.print();
    }

    private Maaltid getKaninMaaltid() {
        Rett bonaqua = new Rett(createSum(6, 0), null);
        Rett slivovica = new Rett(createSum(20, 30), null);
        Rett mangalica = new Rett(createSum(18, 90), mads);
        Rett diviakPomarancVillsvinFreddy = new Rett(createSum(16, 90), freddy);
        Rett diviakPomarancVillsvinPaal = new Rett(createSum(16, 90), paal);
        Rett diviakPomarancVillsvinLorents = new Rett(createSum(16, 90), lorents);
        Rett jahnacieLytkoKanin = new Rett(createSum(22, 90), edvard);
        Rett kuraciSteakKylling = new Rett(createSum(10, 90), sofie);
        Rett kralikNaVine = new Rett(createSum(13, 90), marie);
        Rett zlatyBazantPaal = new Rett(createSum(2, 90), paal);
        Rett zlatyBazantMads = new Rett(createSum(2, 90), mads);
        Rett zlatyBazantLorents = new Rett(createSum(2, 90), lorents);
        Rett vinFreddy = new Rett(createSum(7, 67), freddy);
        Rett vinMarie = new Rett(createSum(7, 67), marie);
        Rett vinEdvard = new Rett(createSum(7, 66), edvard);

        Rett zmzrlinovyPoharDessertSofie = new Rett(createSum(4, 50), sofie);
        Rett zmzrlinovyPoharDessertFreddy = new Rett(createSum(4, 50), freddy);
        Rett zmzrlinovyPoharDessertEdvard = new Rett(createSum(4, 50), edvard);
        Rett makovesulanceDessertMads = new Rett(createSum(4, 0), mads);
        Rett makovesulanceDessertPaal = new Rett(createSum(4, 0), paal);
        Rett makovesulanceDessertLorents = new Rett(createSum(4, 0), lorents);
        Rett cokotortaDessert = new Rett(createSum(4, 0), marie);
        Rett kaffe = new Rett(createSum(15, 40), null);
        Rett baileys = new Rett(createSum(3, 50), paal);
        Rett service = new Rett(createSum(22, 40), null);


        Maaltid kaninMaaltid = new Maaltid(bonaqua, slivovica, mangalica, diviakPomarancVillsvinFreddy,
                diviakPomarancVillsvinPaal, diviakPomarancVillsvinLorents, jahnacieLytkoKanin, kuraciSteakKylling,
                kralikNaVine, zlatyBazantPaal, zlatyBazantMads, zlatyBazantLorents, vinFreddy, vinMarie, vinEdvard,
                zmzrlinovyPoharDessertSofie, zmzrlinovyPoharDessertFreddy, zmzrlinovyPoharDessertEdvard,
                makovesulanceDessertMads, makovesulanceDessertPaal, makovesulanceDessertLorents, cokotortaDessert,
                kaffe, baileys, service);
        assertThat(kaninMaaltid.getSum(), is(createSum(246, 10)));
        assertThat(kaninMaaltid.getUtestaaende(mads), is(createSum(34, 95)));
        assertThat(kaninMaaltid.getUtestaaende(marie), is(createSum(34, 72)));
        assertThat(kaninMaaltid.getUtestaaende(paal), is(createSum(36, 45)));
        assertThat(kaninMaaltid.getUtestaaende(lorents), is(createSum(32, 95)));
        assertThat(kaninMaaltid.getUtestaaende(edvard), is(createSum(44, 21)));
        assertThat(kaninMaaltid.getUtestaaende(freddy), is(createSum(38, 22)));
        assertThat(kaninMaaltid.getUtestaaende(sofie), is(createSum(24, 55)));
        kaninMaaltid.addBetaling(freddy, createSum(40, 0));
        kaninMaaltid.addBetaling(sofie, createSum(25, 0));
        kaninMaaltid.addBetaling(edvard, createSum(32, 0));
        kaninMaaltid.addBetaling(mads, createSum(50, 0));
        kaninMaaltid.addBetaling(paal, createSum(90, 0));
        kaninMaaltid.addBetaling(marie, createSum(10, 0));
        assertThat(kaninMaaltid.getUtestaaende(mads), is(createSum(-15, 5)));
        assertThat(kaninMaaltid.getUtestaaende(lorents), is(createSum(32, 95)));
        assertThat(kaninMaaltid.getUtestaaende(sofie), is(createSum(0, 45)));
        assertThat(kaninMaaltid.getUtestaaende(marie), is(createSum(24, 72)));
        assertThat(kaninMaaltid.getUtestaaende(edvard), is(createSum(12, 21)));
        assertThat(kaninMaaltid.getUtestaaende(freddy), is(createSum(-1, 78)));
        assertThat(kaninMaaltid.getUtestaaende(paal), is(createSum(-53, 55)));
        //TODO endre uteståande-logikken til å ta høgde for delvise betalingar
        return kaninMaaltid;
    }

    private Maaltid getUcetMaaltid() {
        Rett loparMads = new Rett(createSum(4, 97), mads);
        Rett loparMarie = new Rett(createSum(4, 96), marie);
        Rett loparPaal = new Rett(createSum(4, 97), paal);
        Rett kuracieEdvard = new Rett(createSum(9, 90), edvard);
        Rett kuracieLorents = new Rett(createSum(9, 90), lorents);
        Rett urpiner1 = new Rett(createSum(2, 20), sofie);
        Rett urpiner2 = new Rett(createSum(2, 20), sofie);
        Rett ukjentPolotmave1 = new Rett(createSum(4, 50), freddy); // Antar Freddy drakk minst ein øl her
        Rett ukjentPolotmave2 = new Rett(createSum(4, 50), null); // Polotmave = red ale
        Rett pivoKlasicMads = new Rett(createSum(3, 90), mads);
        Rett pivoKlasicMarie = new Rett(createSum(3, 90), marie);
        Rett pivoKlasicPaal = new Rett(createSum(3, 90), paal);
        Rett ukjentMedovinaSlovenska0Komma1Likoer = new Rett(createSum(3, 50), lorents); // Trur denne var Lorents'?
        Rett zemiakoveKnedlikyDumplings = new Rett(createSum(2, 30), sofie);
        Rett loparFreddy = new Rett(createSum(7, 45), freddy);
        Rett loparEdvard = new Rett(createSum(7, 45), edvard);
        Rett espresso = new Rett(createSum(11, 90), null);
        Rett peceneZemikayPommesFrites = new Rett(createSum(2, 30), sofie);
        Rett peceneZemikayPommesFritesNummerTo = new Rett(createSum(2, 30), null); //Usikker på kven denne var til?

        Maaltid ucetMaaltid = new Maaltid(loparMads, loparMarie, loparPaal, kuracieEdvard, kuracieLorents,
                urpiner1, urpiner2, ukjentPolotmave1, ukjentPolotmave2, pivoKlasicMads, pivoKlasicMarie, pivoKlasicPaal,
                ukjentMedovinaSlovenska0Komma1Likoer, zemiakoveKnedlikyDumplings, loparFreddy, loparEdvard, espresso,
                peceneZemikayPommesFrites, peceneZemikayPommesFritesNummerTo);
        ucetMaaltid.setBetaler(paal);
        assertThat(ucetMaaltid.getSum(), is(createSum(97, 0)));
        assertThat(ucetMaaltid.getUtestaaende(mads), is(createSum(11, 54)));
        assertThat(ucetMaaltid.getUtestaaende(marie), is(createSum(11, 53)));
        assertThat(ucetMaaltid.getUtestaaende(paal), is(createSum(-84, 82)));
        assertThat(ucetMaaltid.getUtestaaende(edvard), is(createSum(20, 66)));
        assertThat(ucetMaaltid.getUtestaaende(lorents), is(createSum(16, 71)));
        assertThat(ucetMaaltid.getUtestaaende(freddy), is(createSum(10, 76)));
        assertThat(ucetMaaltid.getUtestaaende(sofie), is(createSum(12, 81)));
        return ucetMaaltid;
    }

    private Maaltid getUrbanSpaceMaaltid() {
        Rett tapas1 = new Rett(createSum(12, 90), null);
        Rett tapas2 = new Rett(createSum(12, 90), null);
        Rett karmaKola = new Rett(createSum(3, 50), edvard);
        Rett brewdogDeadPonyClub = new Rett(createSum(3, 90), mads);
        Rett mangoLassi = new Rett(createSum(3, 90), paal);
        Rett golguz = new Rett(createSum(3, 98), marie);
        Rett brewdog5amsaintEdvard = new Rett(createSum(4, 40), edvard);
        Rett brewdog5amsaintFreddy = new Rett(createSum(4, 40), freddy);
        Rett broed = new Rett(createSum(1, 0), null);
        Rett irishCoffee = new Rett(createSum(19, 50), null);

        Maaltid urbanSpaceMaaltid = new Maaltid(tapas1, tapas2, karmaKola, brewdogDeadPonyClub,
                mangoLassi, golguz, brewdog5amsaintEdvard, brewdog5amsaintFreddy, broed, irishCoffee);
        urbanSpaceMaaltid.setBetaler(marie);

        assertThat(urbanSpaceMaaltid.getSum(), is(createSum(70, 38)));
        assertThat(urbanSpaceMaaltid.getUtestaaende(mads), is(createSum(13, 16)));
        assertThat(urbanSpaceMaaltid.getUtestaaende(paal), is(createSum(13, 16)));
        assertThat(urbanSpaceMaaltid.getUtestaaende(freddy), is(createSum(13, 66)));
        assertThat(urbanSpaceMaaltid.getUtestaaende(edvard), is(createSum(17, 16)));
        assertThat(urbanSpaceMaaltid.getUtestaaende(marie), is(createSum(-57, 14)));
        return urbanSpaceMaaltid;
    }

    private Maaltid getUFOMaaltid() {
        Rett nikkaFreddy = new Rett(createSum(8, 65), freddy);
        Rett nikkaEdvard = new Rett(createSum(8, 65), edvard);
        Rett oelMads = new Rett(createSum(5, 90), mads);
        Rett oelLorents = new Rett(createSum(5, 90), lorents);
        Rett bazant = new Rett(createSum(3, 65), sofie);
        Rett irishCoffee = new Rett(createSum(7, 50), paal);
        Rett kakao = new Rett(createSum(7, 50), marie);
        Maaltid ufoMaaltid = new Maaltid(nikkaFreddy, nikkaEdvard, oelMads, oelLorents, bazant, irishCoffee, kakao);
        ufoMaaltid.setBetaler(lorents);

        assertThat(ufoMaaltid.getUtestaaende(mads), is(createSum(5, 90)));
        // Merk: I kvitteringa er desse to under slått saman til 1 "event drink" à 15 euro.
        assertThat(ufoMaaltid.getUtestaaende(marie), is(createSum(7, 50)));
        assertThat(ufoMaaltid.getUtestaaende(paal), is(createSum(7, 50)));
        assertThat(ufoMaaltid.getUtestaaende(sofie), is(createSum(3, 65)));
        assertThat(ufoMaaltid.getUtestaaende(edvard), is(createSum(8, 65)));
        assertThat(ufoMaaltid.getUtestaaende(freddy), is(createSum(8, 65)));
        assertThat(ufoMaaltid.getUtestaaende(lorents), is(createSum(-41, 85)));
        assertThat(ufoMaaltid.getSum(), is(createSum(47, 75)));
        return ufoMaaltid;
    }

    private Maaltid getKontaktMaaltid() {
        Rett fernet = new Rett(createSum(2, 20), mads);
        Rett urpiner1 = new Rett(createSum(1, 50), sofie);
        Rett urpiner2 = new Rett(createSum(1, 50), sofie);
        Rett absinth = new Rett(createSum(3, 60), lorents);
        Rett radler = new Rett(createSum(1, 70), marie);
        Rett kalteneckerChopperMads = new Rett(createSum(2, 80), mads);
        Rett kalteneckerChopperEdvard1 = new Rett(createSum(2, 80), edvard);
        Rett kalteneckerChopperEdvard2 = new Rett(createSum(2, 80), edvard);
        Rett kalteneckerChopperFreddy1 = new Rett(createSum(2, 80), freddy);
        Rett kalteneckerChopperFreddy2 = new Rett(createSum(2, 80), freddy);
        Rett kalteneckerFiestaPaal = new Rett(createSum(2, 60), paal);
        Rett kalteneckerFiestaLorents = new Rett(createSum(2, 60), lorents);
        Rett espresso = new Rett(createSum(2, 80), marie);
        Rett pallMallMads = new Rett(createSum(2, 70), mads);
        Rett pallMallPaal = new Rett(createSum(2, 70), paal);
        Maaltid kontaktMaaltid = new Maaltid(fernet, urpiner1, urpiner2, absinth, radler, kalteneckerChopperMads,
                kalteneckerChopperEdvard1, kalteneckerChopperEdvard2, kalteneckerChopperFreddy1, kalteneckerChopperFreddy2,
                kalteneckerFiestaPaal, kalteneckerFiestaLorents, espresso, pallMallMads, pallMallPaal);
        kontaktMaaltid.setBetaler(edvard);
        assertThat(kontaktMaaltid.getSum(), is(createSum(37, 90)));
        assertThat(kontaktMaaltid.getUtestaaende(mads), is(createSum(7, 70)));
        assertThat(kontaktMaaltid.getUtestaaende(marie), is(createSum(4, 50)));
        assertThat(kontaktMaaltid.getUtestaaende(paal), is(createSum(5, 30)));
        assertThat(kontaktMaaltid.getUtestaaende(lorents), is(createSum(6, 20)));
        assertThat(kontaktMaaltid.getUtestaaende(sofie), is(createSum(3, 0)));
        assertThat(kontaktMaaltid.getUtestaaende(edvard), is(createSum(-32, 30)));
        return kontaktMaaltid;
    }

    private Maaltid getTheRedLionMaaltid(Deltakar mads, Deltakar marie, Deltakar paal, Deltakar sofie, Deltakar lorents, Deltakar edvard, Deltakar freddy) {
        Rett londonPride = new Rett(createSum(3, 30), paal);
        Rett luckyBastard = new Rett(createSum(3, 50), mads);
        Rett zlatyBazant = new Rett(createSum(3, 20), lorents);
        Rett mojitoMarie = new Rett(createSum(5, 90), marie);
        Rett mojitoEdvard = new Rett(createSum(5, 90), edvard);
        Rett pinaColada = new Rett(createSum(5, 90), freddy);
        Rett kaltenecker = new Rett(createSum(2, 90), sofie);

        Maaltid redLion = new Maaltid(londonPride, luckyBastard, zlatyBazant, mojitoMarie, mojitoEdvard, pinaColada, kaltenecker);
        redLion.setBetaler(mads);
        assertThat(redLion.getSum(), is(createSum(30, 60)));
        return redLion;
    }

    private Maaltid getLePapillonMaaltid(Deltakar mads, Deltakar marie, Deltakar paal, Deltakar sofie) {
        Rett kasteelBarista = new Rett(createSum(5, 30), mads);
        Rett zlatyBazant = new Rett(createSum(2, 50), sofie);
        Rett cappucino = new Rett(createSum(2, 90), marie);
        Rett caffeLatte = new Rett(createSum(2, 90), paal);
        Rett cubaCake1 = new Rett(createSum(4, 90), paal);
        Rett cubaCake2 = new Rett(createSum(4, 90), mads);
        Rett brownieCake = new Rett(createSum(4, 90), marie);
        Rett serviceCharge = new Rett(createSum(2, 90), null);

        Maaltid maaltid =
                new Maaltid(kasteelBarista, zlatyBazant, cappucino, caffeLatte, cubaCake1, cubaCake2, brownieCake, serviceCharge);
        maaltid.setBetaler(mads);
        assertThat(maaltid.getSum(), is(createSum(31, 20)));
        return maaltid;
    }
}
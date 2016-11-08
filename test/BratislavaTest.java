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

        Maaltid lePapillonMaaltid = getLePapillonMaaltid(mads, marie, paal, sofie);
        tur.addMaaltid(lePapillonMaaltid);

        assertThat(tur.getUtestaaende(mads), is(new Sum(-20, 28)));
        assertThat(tur.getUtestaaende(marie), is(new Sum(8, 52)));
        assertThat(tur.getUtestaaende(paal), is(new Sum(8, 52)));
        assertThat(tur.getUtestaaende(sofie), is(new Sum(3, 22)));
        assertThat(tur.getSum(), is(new Sum(31, 20)));
//        lePapillonMaaltid.print();

        Maaltid redLion = getTheRedLionMaaltid(mads, marie, paal, sofie, lorents, edvard, freddy);
        tur.addMaaltid(redLion);

        assertThat(tur.getUtestaaende(mads), is(new Sum(-47, 38)));
        assertThat(tur.getUtestaaende(paal), is(new Sum(11, 82)));
        assertThat(tur.getUtestaaende(lorents), is(new Sum(3, 20)));
        assertThat(tur.getUtestaaende(marie), is(new Sum(14, 42)));
        assertThat(tur.getUtestaaende(sofie), is(new Sum(6, 12)));
        assertThat(tur.getUtestaaende(edvard), is(new Sum(5, 90)));
        assertThat(tur.getUtestaaende(freddy), is(new Sum(5, 90)));
//        redLion.print();

        assertThat(tur.getSum(), is(new Sum(61, 80)));

        Maaltid kontaktMaaltid = getKontaktMaaltid();
        tur.addMaaltid(kontaktMaaltid);

        assertThat(tur.getUtestaaende(mads), is(new Sum(-39, 8)));
        assertThat(tur.getUtestaaende(paal), is(new Sum(17, 12)));
        assertThat(tur.getUtestaaende(lorents), is(new Sum(9, 40)));
        assertThat(tur.getUtestaaende(marie), is(new Sum(18, 92)));
        assertThat(tur.getUtestaaende(sofie), is(new Sum(9, 12)));
        assertThat(tur.getUtestaaende(edvard), is(new Sum(-26, 20)));
        assertThat(tur.getUtestaaende(freddy), is(new Sum(11, 50)));
        assertThat(tur.getSum(), is(new Sum(99, 70)));

        Maaltid ufoMaaltid = getUFOMaaltid();
   //     ufoMaaltid.print();
        tur.addMaaltid(ufoMaaltid);
        assertThat(tur.getUtestaaende(mads), is(new Sum(-34, 98)));
        assertThat(tur.getUtestaaende(paal), is(new Sum(24, 62)));
        assertThat(tur.getUtestaaende(lorents), is(new Sum(-31, 25)));
        assertThat(tur.getUtestaaende(marie), is(new Sum(26, 42)));
        assertThat(tur.getUtestaaende(sofie), is(new Sum(12, 77)));
        assertThat(tur.getUtestaaende(edvard), is(new Sum(-18, 85)));
        assertThat(tur.getUtestaaende(freddy), is(new Sum(20, 15)));
        assertThat(tur.getSum(), is(new Sum(147, 45)));

        Maaltid urbanSpaceMaaltid = getUrbanSpaceMaaltid();
       // urbanSpaceMaaltid.print();
        tur.addMaaltid(urbanSpaceMaaltid);
        assertThat(tur.getUtestaaende(mads), is(new Sum(-20, 14)));
        assertThat(tur.getUtestaaende(paal), is(new Sum(37, 78)));
        assertThat(tur.getUtestaaende(lorents), is(new Sum(-31, 25)));
        assertThat(tur.getUtestaaende(marie), is(new Sum(-31, 56)));
        assertThat(tur.getUtestaaende(sofie), is(new Sum(12, 77)));
        assertThat(tur.getUtestaaende(edvard), is(new Sum(0, 1)));
        assertThat(tur.getUtestaaende(freddy), is(new Sum(33, 81)));
        assertThat(tur.getSum(), is(new Sum(217, 83)));

        Maaltid kanin = getKaninMaaltid();

       // Maaltid ucetMaaltid = getUcetMaaltid(); TODO
       // ucetMaaltid.print();
    }

    private Maaltid getKaninMaaltid() {
        Rett bonaqua = new Rett(new Sum(6, 0), null);
        Rett slivovica = new Rett(new Sum(20, 30), null);
        Rett mangalica = new Rett(new Sum(18, 90), mads);
        Rett diviakPomarancVillsvinFreddy = new Rett(new Sum(16, 90), freddy);
        Rett diviakPomarancVillsvinPaal = new Rett(new Sum(16, 90), paal);
        Rett diviakPomarancVillsvinLorents = new Rett(new Sum(16, 90), lorents);
        Rett jahnacieLytkoKanin = new Rett(new Sum(22, 90), edvard);
        Rett kuraciSteakKylling = new Rett(new Sum(10, 90), sofie);
        Rett kralikNaVine = new Rett(new Sum(13, 90), marie);
        Rett zlatyBazantPaal = new Rett(new Sum(2, 90), paal);
        Rett zlatyBazantMads = new Rett(new Sum(2, 90), mads);
        Rett zlatyBazantLorents = new Rett(new Sum(2, 90), lorents);
        Rett vinFreddy = new Rett(new Sum(7, 67), freddy);
        Rett vinMarie = new Rett(new Sum(7, 67), marie);
        Rett vinEdvard = new Rett(new Sum(7, 66), edvard);

        Rett zmzrlinovyPoharDessertSofie = new Rett(new Sum(4, 50), sofie);
        Rett zmzrlinovyPoharDessertFreddy = new Rett(new Sum(4, 50), freddy);
        Rett zmzrlinovyPoharDessertEdvard = new Rett(new Sum(4, 50), edvard);
        Rett makovesulanceDessertMads = new Rett(new Sum(4, 0), mads);
        Rett makovesulanceDessertPaal = new Rett(new Sum(4, 0), paal);
        Rett makovesulanceDessertLorents = new Rett(new Sum(4, 0), lorents);
        Rett cokotortaDessert = new Rett(new Sum(4, 0), marie);
        Rett kaffe = new Rett(new Sum(15, 40), null);
        Rett baileys = new Rett(new Sum(3, 50), paal);
        Rett service = new Rett(new Sum(22, 40), null);


        Maaltid kaninMaaltid = new Maaltid(bonaqua, slivovica, mangalica, diviakPomarancVillsvinFreddy,
                diviakPomarancVillsvinPaal, diviakPomarancVillsvinLorents, jahnacieLytkoKanin, kuraciSteakKylling,
                kralikNaVine, zlatyBazantPaal, zlatyBazantMads, zlatyBazantLorents, vinFreddy, vinMarie, vinEdvard,
                zmzrlinovyPoharDessertSofie, zmzrlinovyPoharDessertFreddy, zmzrlinovyPoharDessertEdvard,
                makovesulanceDessertMads, makovesulanceDessertPaal, makovesulanceDessertLorents, cokotortaDessert,
                kaffe, baileys, service);
        assertThat(kaninMaaltid.getSum(), is(new Sum(246, 10)));
        assertThat(kaninMaaltid.getUtestaaende(mads), is(new Sum(34, 95)));
        assertThat(kaninMaaltid.getUtestaaende(marie), is(new Sum(34, 72)));
        assertThat(kaninMaaltid.getUtestaaende(paal), is(new Sum(36, 45)));
        assertThat(kaninMaaltid.getUtestaaende(lorents), is(new Sum(32, 95)));
        assertThat(kaninMaaltid.getUtestaaende(edvard), is(new Sum(44, 21)));
        assertThat(kaninMaaltid.getUtestaaende(freddy), is(new Sum(38, 22)));
        assertThat(kaninMaaltid.getUtestaaende(sofie), is(new Sum(24, 55)));
        kaninMaaltid.addBetaling(freddy, new Sum(40, 0));
        kaninMaaltid.addBetaling(sofie, new Sum(25, 0));
        kaninMaaltid.addBetaling(edvard, new Sum(32, 0));
        kaninMaaltid.addBetaling(mads, new Sum(50, 0));
        kaninMaaltid.addBetaling(paal, new Sum(90, 0));
        kaninMaaltid.addBetaling(marie, new Sum(10, 0));
        //TODO endre uteståande-logikken til å ta høgde for delvise betalingar
        return kaninMaaltid;
    }

    private Maaltid getUcetMaaltid() {
        Rett loparMads = new Rett(new Sum(4, 97), mads);
        Rett loparMarie = new Rett(new Sum(4, 96), marie);
        Rett loparPaal = new Rett(new Sum(4, 97), paal);
        Rett kuracieEdvard = new Rett(new Sum(9, 90), edvard);
        Rett kuracieLorents = new Rett(new Sum(9, 90), lorents);
        Rett urpiner1 = new Rett(new Sum(2, 20), sofie);
        Rett urpiner2 = new Rett(new Sum(2, 20), sofie);
        Rett ukjentPolotmave1 = new Rett(new Sum(4, 50), freddy); // Antar Freddy drakk minst ein øl her
        Rett ukjentPolotmave2 = new Rett(new Sum(4, 50), null); // Polotmave = red ale
        Rett pivoKlasicMads = new Rett(new Sum(3, 90), mads);
        Rett pivoKlasicMarie = new Rett(new Sum(3, 90), marie);
        Rett pivoKlasicPaal = new Rett(new Sum(3, 90), paal);
        Rett ukjentMedovinaSlovenska0Komma1Likoer = new Rett(new Sum(3, 50), lorents); // Trur denne var Lorents'?
        Rett zemiakoveKnedlikyDumplings = new Rett(new Sum(2, 30), sofie);
        Rett loparFreddy = new Rett(new Sum(7, 45), freddy);
        Rett loparEdvard = new Rett(new Sum(7, 45), edvard);
        Rett espresso = new Rett(new Sum(11, 90), null);
        Rett peceneZemikayPommesFrites = new Rett(new Sum(2, 30), sofie);
        Rett peceneZemikayPommesFritesNummerTo = new Rett(new Sum(2, 30), null); //Usikker på kven denne var til?

        Maaltid ucetMaaltid = new Maaltid(loparMads, loparMarie, loparPaal, kuracieEdvard, kuracieLorents,
                urpiner1, urpiner2, ukjentPolotmave1, ukjentPolotmave2, pivoKlasicMads, pivoKlasicMarie, pivoKlasicPaal,
                ukjentMedovinaSlovenska0Komma1Likoer, zemiakoveKnedlikyDumplings, loparFreddy, loparEdvard, espresso,
                peceneZemikayPommesFrites, peceneZemikayPommesFritesNummerTo);
        ucetMaaltid.setBetaler(paal);
        assertThat(ucetMaaltid.getSum(), is(new Sum(97, 0)));
        assertThat(ucetMaaltid.getUtestaaende(mads), is(new Sum(11, 54)));
        assertThat(ucetMaaltid.getUtestaaende(marie), is(new Sum(11, 53)));
        assertThat(ucetMaaltid.getUtestaaende(paal), is(new Sum(-84, 82)));
        assertThat(ucetMaaltid.getUtestaaende(edvard), is(new Sum(20, 66)));
        assertThat(ucetMaaltid.getUtestaaende(lorents), is(new Sum(16, 71)));
        assertThat(ucetMaaltid.getUtestaaende(freddy), is(new Sum(10, 76)));
        assertThat(ucetMaaltid.getUtestaaende(sofie), is(new Sum(12, 81)));
        return ucetMaaltid;
    }

    private Maaltid getUrbanSpaceMaaltid() {
        Rett tapas1 = new Rett(new Sum(12, 90), null);
        Rett tapas2 = new Rett(new Sum(12, 90), null);
        Rett karmaKola = new Rett(new Sum(3, 50), edvard);
        Rett brewdogDeadPonyClub = new Rett(new Sum(3, 90), mads);
        Rett mangoLassi = new Rett(new Sum(3, 90), paal);
        Rett golguz = new Rett(new Sum(3, 98), marie);
        Rett brewdog5amsaintEdvard = new Rett(new Sum(4, 40), edvard);
        Rett brewdog5amsaintFreddy = new Rett(new Sum(4, 40), freddy);
        Rett broed = new Rett(new Sum(1, 0), null);
        Rett irishCoffee = new Rett(new Sum(19, 50), null);

        Maaltid urbanSpaceMaaltid = new Maaltid(tapas1, tapas2, karmaKola, brewdogDeadPonyClub,
                mangoLassi, golguz, brewdog5amsaintEdvard, brewdog5amsaintFreddy, broed, irishCoffee);
        urbanSpaceMaaltid.setBetaler(marie);

        assertThat(urbanSpaceMaaltid.getSum(), is(new Sum(70, 38)));
        assertThat(urbanSpaceMaaltid.getUtestaaende(mads), is(new Sum(13, 16)));
        assertThat(urbanSpaceMaaltid.getUtestaaende(paal), is(new Sum(13, 16)));
        assertThat(urbanSpaceMaaltid.getUtestaaende(freddy), is(new Sum(13, 66)));
        assertThat(urbanSpaceMaaltid.getUtestaaende(edvard), is(new Sum(17, 16)));
        assertThat(urbanSpaceMaaltid.getUtestaaende(marie), is(new Sum(-57, 14)));
        return urbanSpaceMaaltid;
    }

    private Maaltid getUFOMaaltid() {
        Rett nikkaFreddy = new Rett(new Sum(8, 65), freddy);
        Rett nikkaEdvard = new Rett(new Sum(8, 65), edvard);
        Rett oelMads = new Rett(new Sum(5, 90), mads);
        Rett oelLorents = new Rett(new Sum(5, 90), lorents);
        Rett bazant = new Rett(new Sum(3, 65), sofie);
        Rett irishCoffee = new Rett(new Sum(7, 50), paal);
        Rett kakao = new Rett(new Sum(7, 50), marie);
        Maaltid ufoMaaltid = new Maaltid(nikkaFreddy, nikkaEdvard, oelMads, oelLorents, bazant, irishCoffee, kakao);
        ufoMaaltid.setBetaler(lorents);

        assertThat(ufoMaaltid.getUtestaaende(mads), is(new Sum(5, 90)));
        // Merk: I kvitteringa er desse to under slått saman til 1 "event drink" à 15 euro.
        assertThat(ufoMaaltid.getUtestaaende(marie), is(new Sum(7, 50)));
        assertThat(ufoMaaltid.getUtestaaende(paal), is(new Sum(7, 50)));
        assertThat(ufoMaaltid.getUtestaaende(sofie), is(new Sum(3, 65)));
        assertThat(ufoMaaltid.getUtestaaende(edvard), is(new Sum(8, 65)));
        assertThat(ufoMaaltid.getUtestaaende(freddy), is(new Sum(8, 65)));
        assertThat(ufoMaaltid.getUtestaaende(lorents), is(new Sum(-41, 85)));
        assertThat(ufoMaaltid.getSum(), is(new Sum(47, 75)));
        return ufoMaaltid;
    }

    private Maaltid getKontaktMaaltid() {
        Rett fernet = new Rett(new Sum(2, 20), mads);
        Rett urpiner1 = new Rett(new Sum(1, 50), sofie);
        Rett urpiner2 = new Rett(new Sum(1, 50), sofie);
        Rett absinth = new Rett(new Sum(3, 60), lorents);
        Rett radler = new Rett(new Sum(1, 70), marie);
        Rett kalteneckerChopperMads = new Rett(new Sum(2, 80), mads);
        Rett kalteneckerChopperEdvard1 = new Rett(new Sum(2, 80), edvard);
        Rett kalteneckerChopperEdvard2 = new Rett(new Sum(2, 80), edvard);
        Rett kalteneckerChopperFreddy1 = new Rett(new Sum(2, 80), freddy);
        Rett kalteneckerChopperFreddy2 = new Rett(new Sum(2, 80), freddy);
        Rett kalteneckerFiestaPaal = new Rett(new Sum(2, 60), paal);
        Rett kalteneckerFiestaLorents = new Rett(new Sum(2, 60), lorents);
        Rett espresso = new Rett(new Sum(2, 80), marie);
        Rett pallMallMads = new Rett(new Sum(2, 70), mads);
        Rett pallMallPaal = new Rett(new Sum(2, 70), paal);
        Maaltid kontaktMaaltid = new Maaltid(fernet, urpiner1, urpiner2, absinth, radler, kalteneckerChopperMads,
                kalteneckerChopperEdvard1, kalteneckerChopperEdvard2, kalteneckerChopperFreddy1, kalteneckerChopperFreddy2,
                kalteneckerFiestaPaal, kalteneckerFiestaLorents, espresso, pallMallMads, pallMallPaal);
        kontaktMaaltid.setBetaler(edvard);
        assertThat(kontaktMaaltid.getSum(), is(new Sum(37, 90)));
        assertThat(kontaktMaaltid.getUtestaaende(mads), is(new Sum(7, 70)));
        assertThat(kontaktMaaltid.getUtestaaende(marie), is(new Sum(4, 50)));
        assertThat(kontaktMaaltid.getUtestaaende(paal), is(new Sum(5, 30)));
        assertThat(kontaktMaaltid.getUtestaaende(lorents), is(new Sum(6, 20)));
        assertThat(kontaktMaaltid.getUtestaaende(sofie), is(new Sum(3, 0)));
        assertThat(kontaktMaaltid.getUtestaaende(edvard), is(new Sum(-32, 30)));
        return kontaktMaaltid;
    }

    private Maaltid getTheRedLionMaaltid(Deltakar mads, Deltakar marie, Deltakar paal, Deltakar sofie, Deltakar lorents, Deltakar edvard, Deltakar freddy) {
        Rett londonPride = new Rett(new Sum(3, 30), paal);
        Rett luckyBastard = new Rett(new Sum(3, 50), mads);
        Rett zlatyBazant = new Rett(new Sum(3, 20), lorents);
        Rett mojitoMarie = new Rett(new Sum(5, 90), marie);
        Rett mojitoEdvard = new Rett(new Sum(5, 90), edvard);
        Rett pinaColada = new Rett(new Sum(5, 90), freddy);
        Rett kaltenecker = new Rett(new Sum(2, 90), sofie);

        Maaltid redLion = new Maaltid(londonPride, luckyBastard, zlatyBazant, mojitoMarie, mojitoEdvard, pinaColada, kaltenecker);
        redLion.setBetaler(mads);
        assertThat(redLion.getSum(), is(new Sum(30, 60)));
        return redLion;
    }

    private Maaltid getLePapillonMaaltid(Deltakar mads, Deltakar marie, Deltakar paal, Deltakar sofie) {
        Rett kasteelBarista = new Rett(new Sum(5, 30), mads);
        Rett zlatyBazant = new Rett(new Sum(2, 50), sofie);
        Rett cappucino = new Rett(new Sum(2, 90), marie);
        Rett caffeLatte = new Rett(new Sum(2, 90), paal);
        Rett cubaCake1 = new Rett(new Sum(4, 90), paal);
        Rett cubaCake2 = new Rett(new Sum(4, 90), mads);
        Rett brownieCake = new Rett(new Sum(4, 90), marie);
        Rett serviceCharge = new Rett(new Sum(2, 90), null);

        Maaltid maaltid =
                new Maaltid(kasteelBarista, zlatyBazant, cappucino, caffeLatte, cubaCake1, cubaCake2, brownieCake, serviceCharge);
        maaltid.setBetaler(mads);
        assertThat(maaltid.getSum(), is(new Sum(31, 20)));
        return maaltid;
    }
}
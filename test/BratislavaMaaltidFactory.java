import maaltid.Maaltid;
import maaltid.Rett;
import org.hamcrest.Matcher;
import tur.Deltakar;
import sum.Euro;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class BratislavaMaaltidFactory {
    private final Deltakar mads;
    private final Deltakar marie;
    private final Deltakar paal;
    private final Deltakar sofie;
    private final Deltakar lorents;
    private final Deltakar edvard;
    private final Deltakar freddy;

    BratislavaMaaltidFactory(Deltakar mads, Deltakar marie, Deltakar paal, Deltakar sofie, Deltakar lorents, Deltakar edvard, Deltakar freddy) {
        this.mads = mads;
        this.marie = marie;
        this.paal = paal;
        this.sofie = sofie;
        this.lorents = lorents;
        this.edvard = edvard;
        this.freddy = freddy;
    }

    Maaltid getLePapillonMaaltid(Deltakar mads, Deltakar marie, Deltakar paal, Deltakar sofie) {
        Rett kasteelBarista = new Rett("Kasteel Barista", createSum(5, 30), mads);
        Rett zlatyBazant = new Rett("Zlaty bazant", createSum(2, 50), sofie);
        Rett cappucino = new Rett("Cappucino", createSum(2, 90), marie);
        Rett caffeLatte = new Rett("Caffe latte", createSum(2, 90), paal);
        Rett cubaCake1 = new Rett("Cuba cake", createSum(4, 90), paal);
        Rett cubaCake2 = new Rett("Cuba cake", createSum(4, 90), mads);
        Rett brownieCake = new Rett("Brownie cake", createSum(4, 90), marie);
        Rett serviceCharge = new Rett("Service charge", createSum(2, 90));

        Maaltid maaltid =
                new Maaltid("Le Papillon", kasteelBarista, zlatyBazant, cappucino, caffeLatte, cubaCake1, cubaCake2, brownieCake, serviceCharge);
        maaltid.setBetaler(mads);
        assertThat(maaltid.getSum(), is(createSum(31, 20)));
        assertThat(maaltid.getUtestaaende(mads), is(createSum(-20, 275)));
        assertThat(maaltid.getUtestaaende(marie), is(createSum(8, 525)));
        assertThat(maaltid.getUtestaaende(paal), is(createSum(8, 525)));
        assertThat(maaltid.getUtestaaende(sofie), is(createSum(3, 225)));
        return maaltid;
    }

    Maaltid getTheRedLionMaaltid(Deltakar mads, Deltakar marie, Deltakar paal, Deltakar sofie, Deltakar lorents, Deltakar edvard, Deltakar freddy) {
        Rett londonPride = new Rett("London Pride", createSum(3, 30), paal);
        Rett luckyBastard = new Rett("Lucky bastard", createSum(3, 50), mads);
        Rett zlatyBazant = new Rett("Zlaty bazant", createSum(3, 20), lorents);
        Rett mojitoMarie = new Rett("Mojito", createSum(5, 90), marie);
        Rett mojitoEdvard = new Rett("Mojito", createSum(5, 90), edvard);
        Rett pinaColada = new Rett("Pina colada", createSum(5, 90), freddy);
        Rett kaltenecker = new Rett("Kaltenecker", createSum(2, 90), sofie);

        Maaltid redLion = new Maaltid("The Red Lion", londonPride, luckyBastard, zlatyBazant, mojitoMarie, mojitoEdvard, pinaColada, kaltenecker);
        redLion.setBetaler(mads);
        assertThat(redLion.getSum(), is(createSum(30, 60)));
        assertThat(redLion.getUtestaaende(mads), is(createSum(-27, 10)));
        assertThat(redLion.getUtestaaende(marie), is(createSum(5, 90)));
        assertThat(redLion.getUtestaaende(paal), is(createSum(3, 30)));
        assertThat(redLion.getUtestaaende(sofie), is(createSum(2, 90)));
        assertThat(redLion.getUtestaaende(lorents), is(createSum(3, 20)));
        assertThat(redLion.getUtestaaende(edvard), is(createSum(5, 90)));
        assertThat(redLion.getUtestaaende(freddy), is(createSum(5, 90)));
        return redLion;
    }


    Maaltid getKaninMaaltid() {
        Rett bonaqua = new Rett("Bonaqua", createSum(6, 0));
        Rett slivovica = new Rett("Slivovica", createSum(20, 30));
        Rett mangalica = new Rett("Mangalica", createSum(18, 90), mads);
        Rett diviakPomarancVillsvinFreddy = new Rett("Diviak pomaranc villsvin", createSum(16, 90), freddy);
        Rett diviakPomarancVillsvinPaal = new Rett("Diviak pomaranc villsvin", createSum(16, 90), paal);
        Rett diviakPomarancVillsvinLorents = new Rett("Diviak pomaranc villsvin", createSum(16, 90), lorents);
        Rett jahnacieLytkoKanin = new Rett("Jahnacie lytko kanin", createSum(22, 90), edvard);
        Rett kuraciSteakKylling = new Rett("Kuraci steak kylling", createSum(10, 90), sofie);
        Rett kralikNaVine = new Rett("Kralik na vine", createSum(13, 90), marie);
        Rett zlatyBazantPaal = new Rett("Zlaty bazant", createSum(2, 90), paal);
        Rett zlatyBazantMads = new Rett("Zlaty bazant", createSum(2, 90), mads);
        Rett zlatyBazantLorents = new Rett("Zlaty bazant", createSum(2, 90), lorents);
        Rett vinFreddy = new Rett("Vin", createSum(7, 67), freddy);
        Rett vinMarie = new Rett("Vin", createSum(7, 67), marie);
        Rett vinEdvard = new Rett("Vin", createSum(7, 66), edvard);
        //        maaltid.Rett vin = new maaltid.Rett("Vin", createSum(23, 0), marie, edvard, freddy);

        Rett zmzrlinovyPoharDessertSofie = new Rett("Zmzrlinovy Pohar dessert", createSum(4, 50), sofie);
        Rett zmzrlinovyPoharDessertFreddy = new Rett("Zmzrlinovy Pohar dessert", createSum(4, 50), freddy);
        Rett zmzrlinovyPoharDessertEdvard = new Rett("Zmzrlinovy Pohar dessert", createSum(4, 50), edvard);
        Rett makovesulanceDessertMads = new Rett("Makovesulance dessert", createSum(4, 0), mads);
        Rett makovesulanceDessertPaal = new Rett("Makovesulance dessert", createSum(4, 0), paal);
        Rett makovesulanceDessertLorents = new Rett("Makovesulance dessert", createSum(4, 0), lorents);
        Rett cokotortaDessert = new Rett("Cokotorta dessert", createSum(4, 0), marie);
        Rett kaffe = new Rett("Kaffe", createSum(15, 40));
        Rett baileys = new Rett("Baileys", createSum(3, 50), paal);
        Rett service = new Rett("Service", createSum(22, 40));
        Rett driks = new Rett("Driks", createSum(0, 90));


        Maaltid kaninMaaltid = new Maaltid("Kanin-måltid", bonaqua, slivovica, mangalica, diviakPomarancVillsvinFreddy,
                diviakPomarancVillsvinPaal, diviakPomarancVillsvinLorents, jahnacieLytkoKanin, kuraciSteakKylling,
                kralikNaVine, zlatyBazantPaal, zlatyBazantMads, zlatyBazantLorents, vinFreddy, vinMarie, vinEdvard,
                zmzrlinovyPoharDessertSofie, zmzrlinovyPoharDessertFreddy, zmzrlinovyPoharDessertEdvard,
                makovesulanceDessertMads, makovesulanceDessertPaal, makovesulanceDessertLorents, cokotortaDessert,
                kaffe, baileys, service, driks);
        assertThat(kaninMaaltid.getSum(), is(createSum(247, 0)));
        assertThat(kaninMaaltid.getUtestaaende(mads), is(new Euro(35.086)));
        assertThat(kaninMaaltid.getUtestaaende(marie), is(createSum(34, 856)));
        assertThat(kaninMaaltid.getUtestaaende(paal), is(createSum(36, 586)));
        assertThat(kaninMaaltid.getUtestaaende(lorents), is(new Euro(33.086)));
        assertThat(kaninMaaltid.getUtestaaende(edvard), is(createSum(44, 346)));
        assertThat(kaninMaaltid.getUtestaaende(freddy), is(createSum(38, 356)));
        assertThat(kaninMaaltid.getUtestaaende(sofie), is(createSum(24, 686)));
        kaninMaaltid.addBetaling(freddy, createSum(40, 0));
        kaninMaaltid.addBetaling(sofie, createSum(25, 0));
        kaninMaaltid.addBetaling(edvard, createSum(32, 0));
        kaninMaaltid.addBetaling(mads, createSum(50, 0));
        kaninMaaltid.addBetaling(paal, createSum(90, 0));
        kaninMaaltid.addBetaling(marie, createSum(10, 0));
        assertThat(kaninMaaltid.getUtestaaende(mads), is(new Euro(-14.914)));
        assertThat(kaninMaaltid.getUtestaaende(lorents), is(new Euro(33.086)));
        assertThat(kaninMaaltid.getUtestaaende(sofie), is(createSum(0, -314)));
        assertThat(kaninMaaltid.getUtestaaende(marie), is(createSum(24, 856)));
        assertThat(kaninMaaltid.getUtestaaende(edvard), is(createSum(12, 346)));
        assertThat(kaninMaaltid.getUtestaaende(freddy), is(createSum(-1, 644)));
        assertThat(kaninMaaltid.getUtestaaende(paal), is(createSum(-53, 414)));
        return kaninMaaltid;
    }

    Maaltid getUdetMaaltid() {
        Rett loparMads = new Rett("Lopar (tapas-isj)", createSum(4, 97), mads);
        Rett loparMarie = new Rett("Lopar (tapas-isj)", createSum(4, 96), marie);
        Rett loparPaal = new Rett("Lopar (tapas-isj)", createSum(4, 97), paal);
        Rett kuracieEdvard = new Rett("Kuracie", createSum(9, 90), edvard);
        Rett kuracieLorents = new Rett("Kuracie", createSum(9, 90), lorents);
        Rett urpiner1 = new Rett("Urpiner", createSum(2, 20), sofie);
        Rett urpiner2 = new Rett("Urpiner", createSum(2, 20), sofie);
        Rett ukjentPolotmave1 = new Rett("Ukjent polotmave", createSum(4, 50), freddy); // Antar Freddy drakk minst ein øl her
        Rett ukjentPolotmave2 = new Rett("Ukjent polotmave", createSum(4, 50)); // Polotmave = red ale
        Rett pivoKlasicMads = new Rett("Pivo klasic", createSum(3, 90), mads);
        Rett pivoKlasicMarie = new Rett("Pivo klasic", createSum(3, 90), marie);
        Rett pivoKlasicPaal = new Rett("Pivo klasic", createSum(3, 90), paal);
        Rett ukjentMedovinaSlovenska0Komma1Likoer = new Rett("Medovina slovenska 0,1", createSum(3, 50), lorents); // Trur denne var Lorents'?
        Rett zemiakoveKnedlikyDumplings = new Rett("Zemiakove knedliky dumplings", createSum(2, 30), sofie);
        Rett loparFreddy = new Rett("Lopar (tapas-isj)", createSum(7, 45), freddy);
        Rett loparEdvard = new Rett("Lopar (tapas-isj)", createSum(7, 45), edvard);
        Rett espresso = new Rett("Espresso", createSum(11, 90));
        Rett peceneZemikayPommesFrites = new Rett("Pecene zemikay pommes frites", createSum(2, 30), sofie);
        Rett peceneZemikayPommesFritesNummerTo = new Rett("Pecene zemikay pommes frites", createSum(2, 30)); //Usikker på kven denne var til?

        Maaltid udetMaaltid = new Maaltid("Predbezny udet", loparMads, loparMarie, loparPaal, kuracieEdvard, kuracieLorents,
                urpiner1, urpiner2, ukjentPolotmave1, ukjentPolotmave2, pivoKlasicMads, pivoKlasicMarie, pivoKlasicPaal,
                ukjentMedovinaSlovenska0Komma1Likoer, zemiakoveKnedlikyDumplings, loparFreddy, loparEdvard, espresso,
                peceneZemikayPommesFrites, peceneZemikayPommesFritesNummerTo);
        udetMaaltid.setBetaler(paal);
        assertThat(udetMaaltid.getSum(), is(createSum(97, 0)));
        assertThat(udetMaaltid.getUtestaaende(mads), is(createSum(11, 541)));
        assertThat(udetMaaltid.getUtestaaende(marie), is(createSum(11, 531)));
        assertThat(udetMaaltid.getUtestaaende(paal), is(createSum(-85, 459)));
        assertThat(udetMaaltid.getUtestaaende(edvard), is(createSum(20.021)));
        assertThat(udetMaaltid.getUtestaaende(lorents), is(createSum(16.071)));
        assertThat(udetMaaltid.getUtestaaende(freddy), is(createSum(14, 621)));
        assertThat(udetMaaltid.getUtestaaende(sofie), is(createSum(11, 671)));
        return udetMaaltid;
    }

    private Matcher<Euro> getEuroMatcher(Euro sum) {
        return is(sum);
    }

    private Euro createSum(double verdi) {
        return new Euro(verdi);
    }

    Maaltid getUrbanSpaceMaaltid() {
        Rett tapas1 = new Rett("Tapas", createSum(12, 90));
        Rett tapas2 = new Rett("Tapas", createSum(12, 90));
        Rett karmaKola = new Rett("Karma cola", createSum(3, 50), edvard);
        Rett brewdogDeadPonyClub = new Rett("Brewdog Dead pony club", createSum(3, 90), mads);
        Rett mangoLassi = new Rett("Mango lassi", createSum(3, 90), paal);
        Rett golguz = new Rett("Golguz", createSum(3, 98), marie);
        Rett brewdog5amsaintEdvard = new Rett("Brewdog 5.am saint", createSum(4, 40), edvard);
        Rett brewdog5amsaintFreddy = new Rett("Brewdog 5.am saint", createSum(4, 40), freddy);
        Rett broed = new Rett("Brød", createSum(1, 0));
        Rett irishCoffee = new Rett("Irish coffee", createSum(19, 50));

        Maaltid urbanSpaceMaaltid = new Maaltid("Urban space", tapas1, tapas2, karmaKola, brewdogDeadPonyClub,
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

    Maaltid getUFOMaaltid() {
        Rett nikkaFreddy = new Rett("Nikka", createSum(8, 65), freddy);
        Rett nikkaEdvard = new Rett("Nikka", createSum(8, 65), edvard);
        Rett oelMads = new Rett("Øl", createSum(5, 90), mads);
        Rett oelLorents = new Rett("Øl", createSum(5, 90), lorents);
        Rett bazant = new Rett("Bazant", createSum(3, 65), sofie);
        Rett irishCoffee = new Rett("Irish coffee", createSum(7, 50), paal);
        Rett kakao = new Rett("Kakao", createSum(7, 50), marie);
        Maaltid ufoMaaltid = new Maaltid("UFO", nikkaFreddy, nikkaEdvard, oelMads, oelLorents, bazant, irishCoffee, kakao);
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

    Maaltid getKontaktMaaltid() {
        Rett fernet = new Rett("Fernet", createSum(2, 20), mads);
        Rett urpiner1 = new Rett("Urpiner", createSum(1, 50), sofie);
        Rett urpiner2 = new Rett("Urpiner", createSum(1, 50), sofie);
        Rett absinth = new Rett("Absinth", createSum(3, 60), lorents);
        Rett radler = new Rett("Radler", createSum(1, 70), marie);
        Rett kalteneckerChopperMads = new Rett("Kaltenecker chopper", createSum(2, 80), mads);
        Rett kalteneckerChopperEdvard1 = new Rett("Kaltenecker chopper", createSum(2, 80), edvard);
        Rett kalteneckerChopperEdvard2 = new Rett("Kaltenecker chopper", createSum(2, 80), edvard);
        Rett kalteneckerChopperFreddy1 = new Rett("Kaltenecker chopper", createSum(2, 80), freddy);
        Rett kalteneckerChopperFreddy2 = new Rett("Kaltenecker chopper", createSum(2, 80), freddy);
        Rett kalteneckerFiestaPaal = new Rett("Kaltenecker fiesta", createSum(2, 60), paal);
        Rett kalteneckerFiestaLorents = new Rett("Kaltenecker fiesta", createSum(2, 60), lorents);
        Rett espresso = new Rett("Espresso", createSum(2, 80), marie);
        Rett pallMallMads = new Rett("Pall mall", createSum(2, 70), mads);
        Rett pallMallPaal = new Rett("Pall mall", createSum(2, 70), paal);
        Maaltid kontaktMaaltid = new Maaltid("Kontakt", fernet, urpiner1, urpiner2, absinth, radler, kalteneckerChopperMads,
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

    private Euro createSum(int heltall, int fraction) {
        return new Euro(heltall, fraction);
    }

    Maaltid getUrbanBistroMaaltid() {
        Rett alt = new Rett("Alt saman", createSum(50, 0), mads, marie, paal, sofie);
        Maaltid urbanBistroMaaltid = new Maaltid("Urban bistro", alt);
        urbanBistroMaaltid.addBetaling(paal, createSum(10, 0));
        urbanBistroMaaltid.addBetaling(sofie, createSum(40, 0));

        assertThat(urbanBistroMaaltid.getSum(), is(new Euro(50, 0)));
        assertThat(urbanBistroMaaltid.getUtestaaende(mads), is(createSum(12, 50)));
        assertThat(urbanBistroMaaltid.getUtestaaende(marie), is(createSum(12, 50)));
        assertThat(urbanBistroMaaltid.getUtestaaende(paal), is(createSum(2, 50)));
        assertThat(urbanBistroMaaltid.getUtestaaende(sofie), is(createSum(-27, 50)));
        return urbanBistroMaaltid;
    }

    Maaltid getILeiligheta() {
        Rett f = new Rett("Freddy", createSum(6, 0), freddy);
        Rett e = new Rett("Freddy", createSum(6, 0), edvard);
        Rett l = new Rett("Lorents", createSum(12, 0), lorents);
        Rett ms = new Rett("Marie", createSum(1), marie);
        Rett mo = new Rett("Mads", createSum(1, 0), mads);
        Rett s = new Rett("Sofie", createSum(6), sofie);
        Maaltid iLeiligheta = new Maaltid("I leiligheta", f, e, l, ms, mo, s);
        iLeiligheta.addBetaling(freddy, createSum(12, 0));
        iLeiligheta.addBetaling(marie, createSum(18, 0));
        iLeiligheta.addBetaling(mads, createSum(2, 0));

        assertThat(iLeiligheta.getSum(), is(createSum(32, 0)));
        assertThat(iLeiligheta.getUtestaaende(mads), is(createSum(-1, 0)));
        assertThat(iLeiligheta.getUtestaaende(marie), is(createSum(-17, 0)));
        assertThat(iLeiligheta.getUtestaaende(freddy), is(createSum(-6, 0)));
        assertThat(iLeiligheta.getUtestaaende(lorents), is(createSum(12, 0)));
        assertThat(iLeiligheta.getUtestaaende(sofie), is(createSum(6, 0)));
        assertThat(iLeiligheta.getUtestaaende(edvard), is(createSum(6, 0)));
        return iLeiligheta;
    }

    Maaltid getOvernattingBratislava() {
        // TODO: Endre til sum.NOK
        double verdi = 298.285;
        Euro beloep = new Euro(verdi);
        Rett leilighetBratislava = new Rett("Leilighet Bratislava", beloep, mads, marie, paal, sofie, lorents, freddy, edvard);
        Maaltid overnattingBratislava = new Maaltid("Leilighet Bratislava", leilighetBratislava);
        overnattingBratislava.addBetaling(mads, beloep);
        assertThat(overnattingBratislava.getUtestaaende(mads), is(createSum(-255, 673)));
        assertThat(overnattingBratislava.getUtestaaende(marie), is(createSum(42, 612)));
        assertThat(overnattingBratislava.getUtestaaende(paal), is(createSum(42, 612)));
        assertThat(overnattingBratislava.getUtestaaende(sofie), is(createSum(42, 612)));
        assertThat(overnattingBratislava.getUtestaaende(freddy), is(createSum(42, 612)));
        assertThat(overnattingBratislava.getUtestaaende(lorents), is(createSum(42, 612)));
        assertThat(overnattingBratislava.getUtestaaende(edvard), is(createSum(42, 612)));
        assertThat(overnattingBratislava.getSum(), is(createSum(298, 285)));
        return overnattingBratislava;
    }
}
